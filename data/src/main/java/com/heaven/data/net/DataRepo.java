package com.heaven.data.net;

import android.content.Context;
import android.text.TextUtils;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.heaven.data.convert.normal.ProtoStuffConvertFactory;
import com.heaven.data.convert.szair.SzAirConvertFactory;
import com.heaven.data.net.cookie.CookiesManager;
import com.heaven.data.util.MD5Utils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
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
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

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
    final Context context;
    Headers headers;
    final String baseUrl;
    NetGlobalConfig.PROTOTYPE prototype = JSON;
    HeaderInterceptor headerInterceptor;
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
        this.repoIdentify = MD5Utils.getMd5Value(builder.baseUrl);
        this.context = builder.context;
        this.headerInterceptor = builder.headerInterceptor;
        this.headers = builder.headers;
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

    /**
     * 添加请求头
     *
     * @param key
     *         key
     * @param value
     *         value
     */
    public void addHeader(String key, String value) {
        if (TextUtils.isEmpty(headers.get(key))) {
            headers = headers.newBuilder().add(key, value).build();
        } else {
            headers = headers.newBuilder().set(key, value).build();
        }
        updateHeaders(headers);
    }

    /**
     * 添加请求头
     *
     * @param extraHeaders
     *         请求头
     */
    public void addExtraHeader(HashMap<String, String> extraHeaders) {
        Headers.Builder builder = headers.newBuilder();
        Disposable subscribe = Flowable.fromIterable(extraHeaders.entrySet()).subscribe(stringStringEntry -> {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            if (!TextUtils.isEmpty(headers.get(key))) {
                builder.set(key, value);
            } else {
                builder.add(key, value);
            }

        });
        headers = builder.build();
        updateHeaders(headers);
    }

    /**
     * 删除请求头
     *
     * @param key
     *         key
     * @param value
     *         value
     */
    public void removeExtraHeader(String key, String value) {
        Headers.Builder builder = headers.newBuilder();
        builder.removeAll(key);
        headers = builder.build();
        updateHeaders(headers);
    }

    /**
     * 删除请求头
     *
     * @param extraHeaders
     *         请求头
     */
    public void removeExtraHeader(HashMap<String, String> extraHeaders) {
        Headers.Builder builder = headers.newBuilder();
        Disposable subscribe = Flowable.fromIterable(extraHeaders.entrySet()).subscribe(stringStringEntry -> {
            String key = stringStringEntry.getKey();
            builder.removeAll(key);
        });
        headers = builder.build();
        updateHeaders(headers);
    }

    /**
     * 更新请求头信息
     *
     * @param headers
     *         请求头信息
     */
    private void updateHeaders(Headers headers) {
        headerInterceptor.setHeaders(headers);
    }

    public static final class Builder {
        Context context;
        OkHttpClient.Builder okHttpBuilder;
        Retrofit.Builder retrofitBuilder;
        Headers headers;
        String baseUrl;
        NetGlobalConfig.PROTOTYPE prototype = JSON;
        Converter.Factory converterFactory;
        CallAdapter.Factory adapterFactory = RxJava2CallAdapterFactory.create();
        HeaderInterceptor headerInterceptor;
        int[] certificates;
        int timeOut = 30;
        boolean isCacheable = false;
        boolean isCookie = false;
        boolean isRedirect = true;
        boolean isSslRedirect = false;
        boolean isRetryFailure = true;

        public Builder(Context context) {
            this.context = context;
            this.initDefaultHeader();
            this.okHttpBuilder = new OkHttpClient.Builder();
            this.retrofitBuilder = new Retrofit.Builder();
            this.headerInterceptor = new HeaderInterceptor(headers);

            ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2)
                    .cipherSuites(
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                    .build();

            okHttpBuilder
//                    .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS,ConnectionSpec.CLEARTEXT))
                    .cookieJar(new CookiesManager(context))
                    .addInterceptor(headerInterceptor)
                    .addInterceptor(new NetInterceptor())
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(new StethoInterceptor())
                    .connectionPool(new ConnectionPool(5, 5, TimeUnit.MINUTES))
                    .followRedirects(isRedirect)
                    .retryOnConnectionFailure(isRetryFailure)//连接失败后是否重新连接
                    .connectTimeout(timeOut, TimeUnit.SECONDS)//超时时间15S
                    .readTimeout(timeOut, TimeUnit.SECONDS)
                    .writeTimeout(timeOut, TimeUnit.SECONDS);
            retrofitBuilder.addCallAdapterFactory(adapterFactory);
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
            this.baseUrl = baseUrl;
            this.converterFactory = converterFactory;
            retrofitBuilder
                    .baseUrl(baseUrl)
                    .addConverterFactory(converterFactory);
            return this;
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
            if (TextUtils.isEmpty(headers.get(key))) {
                headers = headers.newBuilder().add(key, value).build();
            } else {
                headers = headers.newBuilder().set(key, value).build();
            }
        }

        private void initDefaultHeader() {
            Headers.Builder headerBuilder = new Headers.Builder();
            //请求头部
            headerBuilder
                    .add("User-Agent", "Android")
                    .add("APP-Key", "")//应用的key值
                    .add("APP-Secret", "")//应用的密钥
                    .add("Charset", "UTF-8")//字符编码格式
                    .add("Accept", "*/*")//能够接受的数据格式
                    .add("Accept-Language", "zh-cn")//接受的语言
                    .add("Content-Type", "application/json")//内容数据格式application/json text/xml
                    .add("Connection", "close");
            headers = headerBuilder.build();
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
                Logger.i("checkClientTrusted--X509Certificate:" + Arrays.toString(chain) + "--authType:" + authType);
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                Logger.i("checkClientTrusted--X509Certificate:" + Arrays.toString(chain) + "--authType:" + authType);
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
