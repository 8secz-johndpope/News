package com.heaven.data.net;

import android.content.Context;
import android.text.TextUtils;

import com.heaven.data.net.cookie.CookiesManager;
import com.heaven.data.util.MD5Utils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import okhttp3.Cache;
import okhttp3.CipherSuite;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.TlsVersion;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.heaven.data.net.NetGlobalConfig.PROTOTYPE.JSON;

/**
 * FileName: com.heaven.data.net.DataRepo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-27 10:11
 *
 * @version V1.0 网络请求封装
 */
public class DataRepo {
    public String repoIdentify;
    public Retrofit retrofit;
    private final Context context;
    private final String baseUrl;
    NetGlobalConfig.PROTOTYPE prototype = JSON;
    final int[] certificates;
    int timeOut = 20;
    boolean isCacheable = false;
    boolean isCookie = false;
    boolean isRedirect = false;
    boolean isSslRedirect = false;
    boolean isRetryFailure = true;

    /**
     * 构造方法
     *
     * @param builder
     *         builder
     */
    private DataRepo(Builder builder) {
        this.repoIdentify = builder.repoIdentify;
        this.context = builder.context;
        this.baseUrl = builder.baseUrl;
        this.prototype = builder.prototype;
        this.certificates = builder.certificates;
        this.timeOut = builder.timeOut;
        this.isCacheable = builder.isCacheable;

        this.isCookie = builder.isCookie;
        this.isRedirect = builder.isRedirect;
        this.isSslRedirect = builder.isSslRedirect;
        this.isRetryFailure = builder.isRetryFailure;


        OkHttpClient client = builder.okHttpBuilder.build();
        retrofit = builder.retrofitBuilder.callFactory(client).build();
    }


    public static final class Builder {
        public String repoIdentify;
        Context context;
        OkHttpClient.Builder okHttpBuilder;
        Retrofit.Builder retrofitBuilder;
        String baseUrl;
        NetGlobalConfig.PROTOTYPE prototype = JSON;
        Converter.Factory converterFactory;
        CallAdapter.Factory adapterFactory = RxJava2CallAdapterFactory.create();
        int[] certificates;
        int timeOut = 120;
        boolean isCacheable = false;
        boolean isCookie = false;
        boolean isRedirect = true;
        boolean isSslRedirect = false;
        boolean isRetryFailure = true;

        public Builder(Context context) {
            this.context = context;
            this.okHttpBuilder = new OkHttpClient.Builder();
            this.retrofitBuilder = new Retrofit.Builder();

            ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_1)
                    .tlsVersions(TlsVersion.TLS_1_2)
                    .cipherSuites(
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                    .build();


            okHttpBuilder
                    .connectionSpecs(Arrays.asList(spec, ConnectionSpec.COMPATIBLE_TLS,ConnectionSpec.CLEARTEXT))
                    .cookieJar(new CookiesManager(context))
                    .addInterceptor(new NetInterceptor())
//                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectionPool(new ConnectionPool(5, 5, TimeUnit.MINUTES))
                    .followRedirects(isRedirect)
                    .retryOnConnectionFailure(isRetryFailure)//连接失败后是否重新连接
                    .connectTimeout(timeOut, TimeUnit.SECONDS)//超时时间15S
                    .readTimeout(timeOut, TimeUnit.SECONDS)
                    .writeTimeout(timeOut, TimeUnit.SECONDS);
            retrofitBuilder.addCallAdapterFactory(adapterFactory);
        }

        Builder(DataRepo dataRepo) {

        }

        /**
         * 添加自定义动态拦截器
         * @param dynamicInterceptor 拦截器
         */
        public void addDynamicInterceptor(Interceptor dynamicInterceptor) {
            if(okHttpBuilder != null) {
                okHttpBuilder.addInterceptor(dynamicInterceptor);
            }
        }



        /**
         * 基础url
         *
         * @param baseUrl
         *         url
         *
         * @return builder
         */
        public Builder baseUrl(String baseUrl, Converter.Factory converterFactory) {
            this.repoIdentify = MD5Utils.getMd5Value(baseUrl);
            this.baseUrl = baseUrl;
            this.converterFactory = converterFactory;
            retrofitBuilder
                    .baseUrl(baseUrl)
                    .addConverterFactory(converterFactory);
            return this;
        }

        public Builder setTime(int timeConn,int timeRead,int timeWrite) {
            okHttpBuilder.connectTimeout(timeConn, TimeUnit.SECONDS)//超时时间15S
                    .readTimeout(timeRead, TimeUnit.SECONDS)
                    .writeTimeout(timeWrite, TimeUnit.SECONDS);
            return this;
        }

        /**
         * 网络基础属性设置
         *
         * @param isCookie
         *         是否使用cookie
         *
         * @return builder
         */
        public Builder baseNetParam(int timeOut, boolean isCacheable, boolean isCookie) {
            this.timeOut = timeOut;
            this.isCookie = isCookie;
            this.isCacheable = isCacheable;

            if (isCookie) {
                okHttpBuilder.cookieJar(new CookiesManager(context));
            }

            if (isCacheable) {
                //设置缓存文件夹
                File cacheFile = new File(context.getCacheDir(), "okhttpcache");
                Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
                okHttpBuilder.cache(cache);

                CacheInterceptor cacheInterceptor = new CacheInterceptor(context);
                okHttpBuilder.addNetworkInterceptor(cacheInterceptor);
                okHttpBuilder.addInterceptor(cacheInterceptor);
            }

            okHttpBuilder.connectTimeout(timeOut, TimeUnit.SECONDS);//超时时间15S

            return this;
        }


        /**
         * https设置
         *
         * @param certificates
         *         安全证书
         *
         * @return Builder
         */
        public Builder netHttps(int[] certificates) {
            if (certificates != null && certificates.length > 0) {
                httpsBycert(certificates);
            } else {
                httpsTrustAll();
            }

            return this;
        }

        /**
         * 初始化https安全组件
         *
         * @param certificates
         *         安全证书
         */
        private void httpsBycert(int[] certificates) {
            if (certificates != null && certificates.length > 0 && context != null) {
                TrustManager[] trustManagers = SSLManager.getTrustManager(context, certificates);
                SSLSocketFactory sslSocketFactory = SSLManager.getSSLSocketFactory(context, certificates);
                X509TrustManager x509TrustManager = (trustManagers != null && trustManagers.length > 0) ? (X509TrustManager) trustManagers[0] : null;
                if (x509TrustManager != null) {
                    okHttpBuilder.sslSocketFactory(sslSocketFactory, x509TrustManager);
                }
            }
        }

        private void httpsTrustAll() {
            if (okHttpBuilder != null) {
                TrustAllManager trustAllManager = new TrustAllManager();
                okHttpBuilder.sslSocketFactory(createTrustAllSSLFactory(trustAllManager), trustAllManager)
                        .hostnameVerifier(createTrustAllHostnameVerifier());

            }
        }

        private SSLSocketFactory createTrustAllSSLFactory(TrustAllManager trustAllManager) {
            SSLSocketFactory ssfFactory = null;
            try {
                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, new TrustManager[]{trustAllManager}, new SecureRandom());
                ssfFactory = sc.getSocketFactory();
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }

            return ssfFactory;
        }

        private class TrustAllManager implements X509TrustManager {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                Logger.i("checkClientTrusted--X509Certificate:" + Arrays.toString(chain) + "--authType:" + authType);
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                Logger.i("checkClientTrusted--X509Certificate:" + Arrays.toString(chain) + "--authType:" + authType);
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }

        //获取HostnameVerifier
        private HostnameVerifier createTrustAllHostnameVerifier() {
            return (hostname, session) -> true;
        }


        public DataRepo build() {
            return new DataRepo(this);
        }
    }

}
