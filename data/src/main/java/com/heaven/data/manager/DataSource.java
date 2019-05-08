package com.heaven.data.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;

import com.heaven.data.dbentity.DownEntity;
import com.heaven.data.fileworker.HttpUpDownService;
import com.heaven.data.net.DataRepo;
import com.heaven.data.net.NetGlobalConfig;
import com.heaven.data.pool.PriorityExecutor;
import com.heaven.data.util.MD5Utils;
import com.orhanobut.logger.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import okhttp3.Headers;
import retrofit2.Converter;
import retrofit2.Retrofit;

import static com.heaven.data.net.NetGlobalConfig.PROTOTYPE.JSON;

/**
 * 作者：Heaven
 * 时间: on 2016/10/19 12:49
 * 邮箱：heavenisme@aliyun.com
 */

public class DataSource {
    private DataRepo mainRepo;
    private Map<String, DataRepo> repos;
    private HashMap<String, Object> apiMap = new HashMap<>();
    private CacheManager cacheManager;
    private ExecutorService executorService;
    private FileUpDownManager fileUpDownManager;
    public static final int ALL = 0;
    public static final int MEMORY = 1;
    public static final int DB = 2;
    public static final int DISK = 3;
    public static final int SHARE = 3;

    private DataSource(Builder builder) {
        this.mainRepo = builder.mainRepo;
        this.repos = builder.repos;
        executorService = new PriorityExecutor(10, false);
        cacheManager = new CacheManager(builder.context);
        fileUpDownManager = new FileUpDownManager(builder.context, getNetApi(HttpUpDownService.class));
    }

    public static final class Builder {
        Context context;
        DataRepo mainRepo;
        NetGlobalConfig.PROTOTYPE prototype = JSON;
        Map<String, DataRepo> repos = new HashMap<>();
        Map<String, DataRepo.Builder> reposBuilder = new HashMap<>();

        public Builder(Context context) {
            this.context = context;
        }


        /**
         * 根据baseurl添加网络请求对象
         *
         * @param baseUrl
         *         url
         * @param converterFactory
         *         协议类型
         *
         * @return Builder
         */
        public Builder addNetRepo(String baseUrl, Converter.Factory converterFactory) {
            return addNetRepo(baseUrl, converterFactory, null);
        }

        /**
         * 根据baseurl添加网络请求对象
         *
         * @param baseUrl
         *         url
         * @param converterFactory
         *         协议类型
         *
         * @return DataRepo.Builder
         */
        public DataRepo.Builder addNetRepoBuilder(String baseUrl, Converter.Factory converterFactory) {
            return addNetRepoBuilder(baseUrl, converterFactory, null);
        }


        /**
         * 根据baseurl添加网络请求对象
         *
         * @param baseUrl
         *         url
         * @param converterFactory
         *         协议类型
         * @param certificates
         *         证书
         *
         * @return Builder
         */
        public Builder addNetRepo(String baseUrl, Converter.Factory converterFactory, int[] certificates) {
            URI uri = null;
            try {
                uri = new URI(baseUrl);
                String scheme = uri.getScheme();
                DataRepo.Builder repoBuilder = new DataRepo.Builder(context);
                repoBuilder.baseUrl(baseUrl, converterFactory);
                if (NetGlobalConfig.HTTPS.equals(scheme)) {
                    repoBuilder.netHttps(certificates);
                }
                if (repos.size() == 0) {
                    mainRepo = repoBuilder.build();
                    repos.put(mainRepo.repoIdentify, mainRepo);
                } else {
                    DataRepo repo = repoBuilder.build();
                    repos.put(repo.repoIdentify, repo);
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return this;
        }

        /**
         * 根据baseurl添加网络请求对象
         *
         * @param baseUrl
         *         url
         * @param converterFactory
         *         协议类型
         * @param certificates
         *         证书
         *
         * @return DataRepo.Builder
         */
        public DataRepo.Builder addNetRepoBuilder(String baseUrl, Converter.Factory converterFactory, int[] certificates) {
            DataRepo.Builder repoBuilder = null;
            try {
                URI uri = new URI(baseUrl);
                String scheme = uri.getScheme();
                repoBuilder = new DataRepo.Builder(context);
                repoBuilder.baseUrl(baseUrl, converterFactory);
                if (NetGlobalConfig.HTTPS.equals(scheme)) {
                    repoBuilder.netHttps(certificates);
                }
                if (repos.size() == 0) {
                    reposBuilder.put(repoBuilder.repoIdentify, repoBuilder);
                } else {
                    reposBuilder.put(repoBuilder.repoIdentify, repoBuilder);
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return repoBuilder;
        }

        public DataSource build() {
            buildRepro();
            return new DataSource(this);
        }

        private void buildRepro() {
            if(reposBuilder.size() > 0) {
                for(String key : reposBuilder.keySet()) {
                    if(!repos.containsKey(key)) {
                        DataRepo.Builder repoBuilder = reposBuilder.get(key);
                        if(repoBuilder != null) {
                            repos.put(repoBuilder.repoIdentify,repoBuilder.build());
                        }
                    }
                }
            }
        }

    }

    /**
     * 获取apiclass
     *
     * @param apiClass
     *         api接口
     * @param <T>
     *         范型
     *
     * @return api接口
     */
    @SuppressWarnings("unchecked")
    public <T> T getNetApi(Class<T> apiClass) {
        T api = null;
        try {
            if (apiMap.containsKey(apiClass.getName())) {
                api = (T) apiMap.get(apiClass.getName());
            } else {
                if (mainRepo != null && mainRepo.retrofit != null) {
                    api = mainRepo.retrofit.create(apiClass);
                    apiMap.put(apiClass.getName(), api);
                } else {
                    Logger.i("apiClassException:null");
                }
            }
        } catch (ClassCastException e) {
            Logger.i("ClassCastException:" + e.getMessage());
        }
        return api;
    }

    /**
     * 根据baseurl来获取不同协议的apiclass
     *
     * @param baseUrl
     *         url
     * @param apiClass
     *         api接口
     * @param <T>
     *         api类型
     *
     * @return api接口
     *
     * @throws Exception
     *         异常
     */
    @SuppressWarnings("unchecked")
    public <T> T getNetApi(@Nonnull String baseUrl, Class<T> apiClass){
        T api = null;
        if (!TextUtils.isEmpty(baseUrl) && apiClass != null) {
            try {
                if (apiMap.containsKey(apiClass.getName())) {
                    api = (T) apiMap.get(apiClass.getName());
                } else {
                    if (repos != null && repos.size() > 0) {
                        String identify = MD5Utils.getMd5Value(baseUrl);
                        if (repos.containsKey(identify)) {
                            DataRepo repo = repos.get(identify);
                            if (repo != null && repo.retrofit != null) {
                                api = repo.retrofit.create(apiClass);
                                apiMap.put(apiClass.getName(), api);
                            }
                        }
                    }
                }
            } catch (ClassCastException e) {
                Logger.i("ClassCastException:" + e.getMessage());
            }
        }
        return api;
    }

    /**
     * 添加请求头
     *
     * @param key
     *         key
     * @param value
     *         value
     */
    public void addHeader(String key, String value) {
        if (mainRepo != null) {
            mainRepo.addHeader(key, value);
        }
    }

    /**
     * 添加请求头
     *
     * @param extraHeaders
     *         请求头
     */
    public void addExtraHeader(HashMap<String, String> extraHeaders) {
        if (mainRepo != null) {
            mainRepo.addExtraHeader(extraHeaders);
        }
    }


    /**
     * 添加请求头
     *
     * @param baseUrl
     *         url
     * @param key
     *         key
     * @param value
     *         value
     */
    public void addHeader(String baseUrl, String key, String value) {
        if (TextUtils.isEmpty(baseUrl)) {
            if (mainRepo != null) {
                mainRepo.addHeader(key, value);
            }
        } else {
            DataRepo repo = getRepo(baseUrl);
            if (repo != null) {
                repo.addHeader(key, value);
            }
        }
    }

    /**
     * 添加请求头
     *
     * @param baseUrl
     *         url
     * @param extraHeaders
     *         请求头
     */
    public void addExtraHeader(String baseUrl, HashMap<String, String> extraHeaders) {
        if (TextUtils.isEmpty(baseUrl)) {
            if (mainRepo != null) {
                mainRepo.addExtraHeader(extraHeaders);
            }
        } else {
            DataRepo repo = getRepo(baseUrl);
            if (repo != null) {
                repo.addExtraHeader(extraHeaders);
            }
        }
    }

    /**
     * 添加请求头
     *
     * @param key
     *         key
     * @param value
     *         value
     */
    public void removeExtraHeader(String key, String value) {
        if (mainRepo != null) {
            mainRepo.removeExtraHeader(key, value);
        }
    }

    /**
     * 删除请求头
     *
     * @param extraHeaders
     *         请求头
     */
    public void removeExtraHeader(HashMap<String, String> extraHeaders) {
        if (mainRepo != null) {
            mainRepo.removeExtraHeader(extraHeaders);
        }
    }

    /**
     * 添加请求头
     *
     * @param baseUrl
     *         url
     * @param key
     *         key
     * @param value
     *         value
     */
    public void removeExtraHeader(String baseUrl, String key, String value) {
        if (TextUtils.isEmpty(baseUrl)) {
            if (mainRepo != null) {
                mainRepo.removeExtraHeader(key, value);
            }
        } else {
            DataRepo repo = getRepo(baseUrl);
            if (repo != null) {
                repo.removeExtraHeader(key, value);
            }
        }
    }

    /**
     * 删除请求头
     *
     * @param baseUrl
     *         url
     * @param extraHeaders
     *         请求头
     */
    public void removeExtraHeader(String baseUrl, HashMap<String, String> extraHeaders) {
        if (TextUtils.isEmpty(baseUrl)) {
            if (mainRepo != null) {
                mainRepo.removeExtraHeader(extraHeaders);
            }
        } else {
            DataRepo repo = getRepo(baseUrl);
            if (repo != null) {
                repo.removeExtraHeader(extraHeaders);
            }
        }
    }

    /**
     * 取得网络数据源
     *
     * @param baseUrl
     *         url
     *
     * @return 数据源
     */
    private DataRepo getRepo(String baseUrl) {
        DataRepo repo = mainRepo;
        if (!TextUtils.isEmpty(baseUrl)) {
            String identify = MD5Utils.getMd5Value(baseUrl);
            if (repos.containsKey(identify)) {
                repo = repos.get(identify);
            }
        }
        return repo;
    }

    /**
     * 按类型缓存数据
     *
     * @param type
     *         缓存类型
     * @param key
     *         键值
     * @param entity
     *         缓存的数据
     */
    public void cacheData(int type, String key, Object entity) {
        if (!TextUtils.isEmpty(key) && entity != null) {
            String hashKey = hashKeyForDisk(key);
            if (type == ALL) {
                persistAll(hashKey, entity);
            } else if (type == MEMORY) {
                persistMemory(hashKey, entity);
            } else if (type == DB) {
                persistDB(hashKey, entity);
            } else if (type == DISK) {
                persistDisk(hashKey, entity);
            } else {
                persistAll(hashKey, entity);
            }
        }
    }

    /**
     * 缓存所有类型的数据(内存 数据库 硬盘)
     *
     * @param hashKey
     *         键值
     * @param entity
     *         缓存的数据
     */
    private void persistAll(String hashKey, Object entity) {
        executorService.execute(() -> cacheManager.persistentMemory(hashKey, entity));
        cacheManager.persistentDB(hashKey, entity);
        executorService.execute(() -> cacheManager.persistentDisk(hashKey, entity));
    }

    /**
     * 移出所有类型的缓存数据(内存 数据库 硬盘)
     *
     * @param key
     *         键值
     */
    public void removeAllTypeCacheData(String key) {
        if (!TextUtils.isEmpty(key)) {
            cacheManager.removeAllTypeCacheData(hashKeyForDisk(key));
        }
    }

    /**
     * 缓存到内存中
     *
     * @param hashKey
     *         键值
     * @param entity
     *         缓存的数据
     */
    private void persistMemory(String hashKey, Object entity) {
        executorService.execute(() -> cacheManager.persistentMemory(hashKey, entity));
    }

    /**
     * 从内存中移出缓存数据
     *
     * @param key
     *         键值
     */
    public void removeMemory(String key) {
        executorService.execute(() -> cacheManager.removeMemory(hashKeyForDisk(key)));
    }

    /**
     * 缓存到数据库中
     *
     * @param hashKey
     *         键值
     * @param entity
     *         缓存的数据
     */
    private void persistDB(String hashKey, Object entity) {
        cacheManager.persistentDB(hashKey, entity);
    }

    /**
     * 从数据库中移出缓存数据
     *
     * @param key
     *         键值
     */
    public void removeFromDB(String key) {
        cacheManager.removeDataFromDB(hashKeyForDisk(key));
    }

    /**
     * 缓存到硬盘中
     *
     * @param hashKey
     *         键值
     * @param entity
     *         缓存的数据
     */
    private void persistDisk(String hashKey, Object entity) {
        executorService.execute(() -> cacheManager.persistentDisk(hashKey, entity));
    }

    /**
     * 从硬盘上移出缓存数据
     *
     * @param key
     *         键值
     */
    public void removeFromDisk(String key) {
        executorService.execute(() -> cacheManager.removeFromDisk(hashKeyForDisk(key)));
    }

    /**
     * @param key
     *         键值
     * @param <E>
     *         缓存数据泛型
     *
     * @return 缓存的数据
     */
    public <E> E getCacheEntity(String key) {
        if (key != null) {
            return cacheManager.getMemoryEntity(hashKeyForDisk(key));
        }
        return null;
    }

    /**
     * @param type
     *         存储类型
     * @param key
     *         key
     * @param <E>
     *         返回类型
     *
     * @return 结果
     */
    public <E> E getCacheEntity(int type, String key) {
        E cacheData = null;
        if (!TextUtils.isEmpty(key)) {
            String hashKey = hashKeyForDisk(key);
            if (type == DB) {
                cacheData = cacheManager.getDbEntity(hashKey);
            } else if (type == DISK) {
                cacheData = cacheManager.getDiskEntity(hashKey);
            } else {
                cacheData = cacheManager.getMemoryEntity(hashKey);
            }
        }

        return cacheData;
    }

    /**
     * sharepre boolean
     * @param key key
     * @param value value
     */
    public void setSharePreBoolean(String key,boolean value) {
        cacheManager.setSharePreBoolean(key,value);
    }

    /**
     * sharepre string
     * @param key key
     * @param value value
     */
    public void setSharePreString(String key,String value) {
        cacheManager.setSharePreString(key,value);
    }

    /**
     * sharepre set
     * @param key key
     * @param value value
     */
    public void setSharePreSet(String key, Set<String> value) {
        cacheManager.setSharePreSet(key,value);
    }

    /**
     * get boolean
     * @param key key
     * @return value
     */
    public boolean getSharePreBoolean(String key) {
        return cacheManager.getSharePreBoolean(key);
    }

    /**
     * get string
     * @param key key
     * @return value
     */
    public String getSharePreString(String key) {
        return cacheManager.getSharePreString(key);
    }

    /**
     * get string set
     * @param key key
     * @return set
     */
    public Set<String> getSharePreSet(String key) {
        return cacheManager.getSharePreSet(key);
    }

    public void downLoadFile(DownEntity downEntity) {
        if (fileUpDownManager != null) {
            fileUpDownManager.downLoadFile(downEntity);
        }
    }

    /**
     * 用md5生成key
     *
     * @param key
     *         键值
     *
     * @return md5key
     */
    private String hashKeyForDisk(String key) {
        String cacheKey = null;
        if (!TextUtils.isEmpty(key)) {
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


    public void runWorkThread(Runnable runnable) {
        executorService.execute(runnable);
    }
}
