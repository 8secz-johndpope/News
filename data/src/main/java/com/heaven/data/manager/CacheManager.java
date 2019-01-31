package com.heaven.data.manager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;

import com.heaven.data.cache.ACache;
import com.heaven.data.cache.DBMigration;
import com.heaven.data.cache.DiskLruCache;
import com.heaven.data.cache.MemoryCache;
import com.heaven.data.dbentity.CacheEntity;
import com.heaven.data.dbentity.DownEntity;
import com.heaven.data.pool.PriorityExecutor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * 作者：Heaven
 * 时间: on 2016/10/20 10:56
 * 邮箱：heavenisme@aliyun.com
 */

class CacheManager {
    private ExecutorService executorService;
    private MemoryCache<String, Object> memoryCache;
    private Realm secondCache;
    private ACache thirdAcache;
    private DiskLruCache thirdDiskCache;
    private MemoryCache.EntryRemovedListener<String, Object> stringSecondListener = new MemoryCache.EntryRemovedListener<String, Object>() {
        @Override
        public void entryRemoved(String key, Object oldValue, Object newValue) {
            persistentDB(key, newValue);
            executorService.execute(() -> persistentDisk(key, oldValue));
        }

        @Override
        public Object getSecondCache(String key) {
            Object result = getDbEntity(key);
            if (result == null) {
                result = getDiskEntity(key);
            }
            return result;
        }
    };

    CacheManager(Context context) {
        init(context);
    }

    private void init(Context context) {
        long maxMemory = Runtime.getRuntime().maxMemory();
        executorService = new PriorityExecutor(10, false);
        memoryCache = new MemoryCache<>((int) maxMemory / 6, stringSecondListener);
        int versionCode = 1;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = info.versionCode;
            thirdDiskCache = DiskLruCache.open(getDiskCacheDir(context, "DiskLruCache"), versionCode, 1, (int) maxMemory / 6);
        } catch (PackageManager.NameNotFoundException | IOException e) {
            e.printStackTrace();
        }
        thirdAcache = ACache.get(getDiskCacheDir(context, "ACache"), 100 * 1024 * 1024, 1);
        executorService.execute(() -> thirdAcache.clearTimeLimitCache());
        initRealmDb(context, versionCode);
    }

    private void initRealmDb(Context context, int versionCode) {
        byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("heaven.realm")
//                .encryptionKey(key)
                .schemaVersion(versionCode)//版本号
                .deleteRealmIfMigrationNeeded()
                .migration(new DBMigration())//表变更
                .build();
        Realm.setDefaultConfiguration(config);
        secondCache = Realm.getInstance(config);
    }

    /**
     * 取得缓存路径
     *
     * @param context
     *         上下文
     * @param uniqueName
     *         缓存
     *
     * @return 缓存路径
     */
    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        File cacheDir = new File(cachePath + File.separator + uniqueName);

        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }

    /**
     * 一二三级缓存都缓存
     *
     * @param key
     *         键值
     * @param entity
     *         缓存对象
     */
    void persistentAllEntity(String key, Object entity) {
        if (key != null && entity != null) {
            executorService.execute(() -> memoryCache.put(key, entity));
            persistentDB(key, entity);
            executorService.execute(() -> persistentDisk(key, entity));
        }
    }

    void removeAllTypeCacheData(String key) {
        executorService.execute(() -> removeMemory(key));
        removeDataFromDB(key);
        executorService.execute(() -> removeFromDisk(key));
    }

    /**
     * 一级内存缓存
     *
     * @param key
     *         键值
     * @param entity
     *         缓存对象
     */
    void persistentMemory(String key, Object entity) {
        if (key != null && entity != null) {
            memoryCache.put(key, entity);
        }
    }

    /**
     * 删除内存缓存
     *
     * @param key
     *         键值
     */
    void removeMemory(String key) {
        memoryCache.remove(key);
    }

    /**
     * 二级缓存 realm数据库缓存
     *
     * @param key
     *         键值
     * @param entity
     *         缓存对象必须是序列化对象
     */
    void persistentDB(String key, Object entity) {
        if (key != null) {
            if (entity != null && entity instanceof Serializable) {
                secondCache.executeTransactionAsync(realm -> {
                    CacheEntity cache = realm.where(CacheEntity.class).equalTo("key", key).findFirst();
                    if (cache != null) {
                        cache.cacheObject = (getObjectByte((Serializable) entity));
                    } else {
                        long stamp = Long.parseLong(new SimpleDateFormat("yyyyMM", Locale.US).format(new Date()));
                        CacheEntity cacheEntity = new CacheEntity();
                        cacheEntity.createTime = stamp;
                        cacheEntity.key = key;
                        cacheEntity.cacheObject = getObjectByte((Serializable) entity);
                        realm.copyToRealmOrUpdate(cacheEntity);
                    }
                });
            }
        } else {
            if (entity != null) {
                if (entity instanceof RealmObject) {
                    secondCache.executeTransactionAsync(realm -> realm.copyToRealmOrUpdate((RealmObject) entity));
                } else if (entity instanceof List) {
                    secondCache.executeTransactionAsync(realm -> realm.copyToRealmOrUpdate((List) entity));
                }
            }
        }
    }

    /**
     * 删除数据库缓存
     *
     * @param key
     *         键值
     */
    void removeDataFromDB(String key) {
        if (key != null) {
            secondCache.executeTransactionAsync(realm -> {
                CacheEntity cache = realm.where(CacheEntity.class).equalTo("key", key).findFirst();
                if (cache != null) {
                    cache.deleteFromRealm();
                }
            });
        }
    }

    /**
     * 三级缓存 硬盘文件缓存
     *
     * @param key
     *         键值
     * @param entity
     *         缓存对象
     */
    void persistentDisk(String key, Object entity) {
        if (key != null && entity != null && entity instanceof Serializable) {
            try {
                DiskLruCache.Editor editor = thirdDiskCache.edit(key);
                if (editor != null) {
                    OutputStream out = editor.newOutputStream(0);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
                    objectOutputStream.writeObject(entity);
                    objectOutputStream.close();
                    editor.commit();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除硬盘缓存
     *
     * @param key
     *         键值
     */
    void removeFromDisk(String key) {
        try {
            thirdDiskCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param key
     *         key键
     * @param value
     *         缓存的序列化对象
     * @param saveTime
     *         保存时间
     */
    public void timeLimitObjCache(String key, Serializable value, int saveTime) {
        thirdAcache.put(key, value, saveTime);
    }

    /**
     * 保存 byte数据 到 缓存中
     *
     * @param key
     *         保存的key
     * @param value
     *         保存的数据
     * @param saveTime
     *         保存的时间，单位：秒
     */
    public void timeLimitByteCache(String key, byte[] value, int saveTime) {
        thirdAcache.put(key, value, saveTime);
    }

    public Object getTimeLimitObject(String key) {
        return thirdAcache.getAsObject(key);
    }

    /**
     * 取得内存缓存
     *
     * @param key
     *         键值
     * @param <E>
     *         泛型
     *
     * @return 缓存的对象
     */
    @SuppressWarnings(value = {"unchecked"})
    <E> E getMemoryEntity(String key) {
        E result = null;
        if (key != null) {
            result = (E) memoryCache.get(key);
        }
        return result;
    }

    /**
     * 取得数据库缓存
     *
     * @param key
     *         键值
     * @param <E>
     *         泛型
     *
     * @return 缓存的对象
     */
    @SuppressWarnings(value = {"unchecked"})
    <E> E getDbEntity(String key) {
        E result = null;
        if (key != null) {
            CacheEntity entity = secondCache.where(CacheEntity.class).equalTo("key", key).findFirst();
            if (entity != null) {
                result = (E) composeObject(entity.cacheObject);
            }
        }
        return result;
    }


    /**
     * 取得数据库下载文件的历史记录
     *
     * @param url
     *         键值
     *
     * @return 缓存的对象
     */
    DownEntity getDownLoadInfoFromDb(String url) {
        DownEntity result = null;
        if (!TextUtils.isEmpty(url)) {
            result = secondCache.where(DownEntity.class).equalTo("url", url).findFirst();
        }
        return result;
    }

    /**
     * 取得数据库中的存储数据
     *
     * @param clazz
     *         对应的数据结构==表结构必须继承RealmObject
     *
     * @return 缓存的对象
     */
    <T extends RealmObject> List<T> getCacheAllFromDb(Class<T> clazz) {
        ArrayList<T> cacheInfoList = new ArrayList<>();
        final RealmResults<T> cacheInfo = secondCache.where(clazz).findAll();
        if (cacheInfo != null && cacheInfo.size() > 0) {
            cacheInfoList.addAll(cacheInfo);
        }
        return cacheInfoList;
    }

    /**
     * 取得硬盘缓存
     *
     * @param key
     *         键值
     * @param <E>
     *         泛型
     *
     * @return 缓存的对象
     */
    @SuppressWarnings(value = {"unchecked"})
    <E> E getDiskEntity(String key) {
        E result = null;
        try {
            if (key != null) {
                DiskLruCache.Snapshot snapShot = thirdDiskCache.get(key);
                if (snapShot != null) {
                    InputStream inputStream = snapShot.getInputStream(0);
                    ObjectInput objectInput = new ObjectInputStream(inputStream);
                    result = (E) objectInput.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据字节数组生成对象
     *
     * @param objdata
     *         字节数组
     *
     * @return 生成的对象
     */
    private Object composeObject(byte[] objdata) {
        Object result = null;
        if (objdata != null && objdata.length > 0) {
            try {
                ObjectInput objectInput = new ObjectInputStream(new ByteArrayInputStream(objdata));
                result = objectInput.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据序列化对象取得字节数组
     *
     * @param obj
     *         对象
     *
     * @return 字节数组
     */
    private byte[] getObjectByte(Serializable obj) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bo.toByteArray();
    }
}
