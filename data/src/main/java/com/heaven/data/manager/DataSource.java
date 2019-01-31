package com.heaven.data.manager;

import android.content.Context;
import android.text.TextUtils;

import com.heaven.data.dbentity.DownEntity;
import com.heaven.data.fileworker.HttpUpDownService;
import com.heaven.data.net.NetGlobalConfig;
import com.heaven.data.pool.PriorityExecutor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

/**
 * 作者：Heaven
 * 时间: on 2016/10/19 12:49
 * 邮箱：heavenisme@aliyun.com
 */

public class DataSource {
    private CacheManager cacheManager;
    private HttpManager httpManager;
    private ExecutorService executorService;
    private FileUpDownManager fileUpDownManager;
    private static DataSource instance;
    public static final int ALL = 0;
    public static final int MEMORY = 1;
    public static final int DB = 2;
    public static final int DISK = 3;

    public static DataSource getInstance(Context context,String baseNetUrl) {
        if(instance == null) {
            instance = new DataSource(context,baseNetUrl);
        }
        return instance;
    }

    private DataSource(Context context,String baseNetUrl) {
        init(context,baseNetUrl);
    }

    public static void initDataSource(Context context,String baseNetUrl) {
        if (instance == null) {
            instance = new DataSource(context,baseNetUrl);
        }
    }

    private void init(Context context,String baseNetUrl){
        executorService = new PriorityExecutor(5, false);
        initNetConfig(baseNetUrl);
        httpManager = new HttpManager(context);
        cacheManager = new CacheManager(context);
        fileUpDownManager = new FileUpDownManager(context,getNetApi(HttpUpDownService.class));
    }

    private void initNetConfig(String baseNetUrl) {
        NetGlobalConfig.BASETURL = baseNetUrl;
        NetGlobalConfig.PROTOCOL = NetGlobalConfig.PROTOCOLTYPE.JSON;
        NetGlobalConfig.BASETURL = baseNetUrl;
    }

    /**
     *
     * @param apiClass api接口
     * @param <T> api类型
     * @return api 实例
     */
    public <T> T getNetApi(Class<T> apiClass) {
        return httpManager.getApi(apiClass);
    }

    /**
     * 添加请求头
     * @param key 键
     * @param value 值
     */
    public void addRequestHeader(String key,String value) {
        NetGlobalConfig.addExtraHeader(key,value);
    }

    /**
     * 批量添加请求头
     * @param hashMap 请求头集合
     */
    public void addRequestHeader(HashMap<String,String> hashMap) {
        NetGlobalConfig.addExtraHeader(hashMap);
    }

    /**
     * 移出请求头
     * @param hashMap 头集合
     */
    public void removeRequestHeader(HashMap<String,String> hashMap) {
        NetGlobalConfig.removeExtraHeader(hashMap);
    }

    /**
     * 按类型缓存数据
     * @param type 缓存类型
     * @param key 键值
     * @param entity 缓存的数据
     */
    public void cacheData(int type,String key,Object entity) {
        if(!TextUtils.isEmpty(key) && entity != null) {
            String hashKey = hashKeyForDisk(key);
            if(type == ALL) {
                persistAll(hashKey,entity);
            } else if(type == MEMORY) {
                persistMemory(hashKey,entity);
            } else if(type == DB) {
                persistDB(hashKey,entity);
            } else if(type == DISK) {
                persistDisk(hashKey,entity);
            } else {
                persistAll(hashKey,entity);
            }
        }
    }

    /**
     * 缓存所有类型的数据(内存 数据库 硬盘)
     * @param hashKey 键值
     * @param entity 缓存的数据
     */
    private void persistAll(String hashKey, Object entity) {
            executorService.execute(() -> cacheManager.persistentMemory(hashKey, entity));
            cacheManager.persistentDB(hashKey, entity);
            executorService.execute(() -> cacheManager.persistentDisk(hashKey, entity));
    }

    /**
     * 移出所有类型的缓存数据(内存 数据库 硬盘)
     * @param key 键值
     */
    public void removeAllTypeCacheData(String key) {
        if(!TextUtils.isEmpty(key)) {
            cacheManager.removeAllTypeCacheData(hashKeyForDisk(key));
        }
    }

    /**
     * 缓存到内存中
     * @param hashKey 键值
     * @param entity 缓存的数据
     */
    private void persistMemory(String hashKey, Object entity) {
            executorService.execute(() -> cacheManager.persistentMemory(hashKey, entity));
    }

    /**
     * 从内存中移出缓存数据
     * @param key 键值
     */
    public void removeMemory(String key) {
        executorService.execute(() -> cacheManager.removeMemory(hashKeyForDisk(key)));
    }

    /**
     * 缓存到数据库中
     * @param hashKey 键值
     * @param entity 缓存的数据
     */
    private void persistDB(String hashKey, Object entity) {
            cacheManager.persistentDB(hashKey, entity);
    }

    /**
     * 从数据库中移出缓存数据
     * @param key 键值
     */
    public void removeFromDB(String key) {
        cacheManager.removeDataFromDB(hashKeyForDisk(key));
    }

    /**
     * 缓存到硬盘中
     * @param hashKey 键值
     * @param entity 缓存的数据
     */
    private void persistDisk(String hashKey, Object entity) {
            executorService.execute(() -> cacheManager.persistentDisk(hashKey, entity));
    }

    /**
     * 从硬盘上移出缓存数据
     * @param key 键值
     */
    public void removeFromDisk(String key) {
        executorService.execute(() -> cacheManager.removeFromDisk(hashKeyForDisk(key)));
    }

    /**
     *
     * @param key 键值
     * @param <E> 缓存数据泛型
     * @return 缓存的数据
*/
    public <E> E getCacheEntity(String key) {
        if (key != null) {
            return cacheManager.getMemoryEntity(hashKeyForDisk(key));
        }
        return null;
    }

    /**
     *
     * @param type 存储类型
     * @param key  key
     * @param <E>  返回类型
     * @return 结果
     */
    public <E> E getCacheEntity(int type, String key) {
        E cacheData = null;
        if(!TextUtils.isEmpty(key)) {
            String hashKey = hashKeyForDisk(key);
             if(type == DB) {
                 cacheData = cacheManager.getDbEntity(hashKey);
            } else if(type == DISK) {
                 cacheData = cacheManager.getDiskEntity(hashKey);
            } else {
                 cacheData = cacheManager.getMemoryEntity(hashKey);
            }
        }

        return cacheData;
    }

    public void downLoadFile(DownEntity downEntity) {
        if(fileUpDownManager != null) {
            fileUpDownManager.downLoadFile(downEntity);
        }
    }

    /**
     * 用md5生成key
     * @param key 键值
     * @return md5key
     */
    private String hashKeyForDisk(String key) {
        String cacheKey = null;
        if(!TextUtils.isEmpty(key)) {
            try {
                final MessageDigest mDigest = MessageDigest.getInstance("MD5");
                mDigest.update(key.getBytes());
                cacheKey = bytesToHexString(mDigest.digest());
            } catch (NoSuchAlgorithmException e) {
                cacheKey = String.valueOf(key.hashCode());
            }
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    public void runWorkThread(Runnable runnable){
        executorService.execute(runnable);
    }
}
