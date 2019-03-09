package com.heaven.data.net;

import android.content.Context;
import android.text.TextUtils;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.heaven.data.convert.szair.SzAirConvertFactory;
import com.heaven.data.net.cookie.CookiesManager;
import com.heaven.data.util.MD5Utils;

import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
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
import okhttp3.Headers;
import okhttp3.OkHttpClient;
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
    boolean isTrustAll = false;
    boolean isCookie = false;
    boolean isRedirect = false;
    boolean isSslRedirect = false;
    boolean isRetryFailure = true;
    boolean isLogInterceptor = false;

    /**
     * 构造方法
     * @param builder builder
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
        this.isTrustAll = builder.isTrustAll;

        this.isCookie = builder.isCookie;
        this.isRedirect = builder.isRedirect;
        this.isSslRedirect = builder.isSslRedirect;
        this.isRetryFailure = builder.isRetryFailure;
        this.isLogInterceptor = builder.isLogInterceptor;

        OkHttpClient client =  builder.okHttpBuilder.build();
        retrofit = builder.retrofitBuilder.callFactory(client).build();
    }

    /**
     * 添加请求头
     * @param key key
     * @param value value
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
     * @param extraHeaders 请求头
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
     * @param key key
     * @param value value
     */
    public void removeExtraHeader(String key, String value) {
        Headers.Builder builder = headers.newBuilder();
        builder.removeAll(key);
        headers = builder.build();
        updateHeaders(headers);
    }

    /**
     * 删除请求头
     * @param extraHeaders 请求头
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
     * @param headers 请求头信息
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
        int timeOut = 10;
        boolean isCacheable = false;
        boolean isTrustAll = false;
        boolean isCookie = false;
        boolean isRedirect = true;
        boolean isSslRedirect = false;
        boolean isRetryFailure = true;
        boolean isLogInterceptor = false;

        public Builder(Context context) {
            this.context = context;
            this.initDefaultHeader();
            this.okHttpBuilder = new OkHttpClient.Builder();
            this.retrofitBuilder = new Retrofit.Builder();
            this.headerInterceptor = new HeaderInterceptor(headers);
            okHttpBuilder
                    .cookieJar(new CookiesManager(context))
                    .addInterceptor(headerInterceptor)
                    .addInterceptor(new NetInterceptor())
//                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                    .addInterceptor(new StethoInterceptor())
                    .followRedirects(isRedirect)
                    .retryOnConnectionFailure(isRetryFailure)//连接失败后是否重新连接
                    .connectTimeout(timeOut, TimeUnit.SECONDS);//超时时间15S
            retrofitBuilder.addCallAdapterFactory(adapterFactory);
        }

        public Builder baseUrl(String baseUrl) {
            return this.baseUrl(baseUrl, prototype);
        }

        /**
         * 基础url 和协议类型
         *
         * @param baseUrl
         *         url
         * @param protocoltype
         *         协议类型
         *
         * @return builder
         */
        public Builder baseUrl(String baseUrl, NetGlobalConfig.PROTOTYPE protocoltype) {
            this.baseUrl = baseUrl;
            this.prototype = protocoltype;
            this.initProtoCovert(protocoltype);
            retrofitBuilder
                    .baseUrl(baseUrl);
            return this;
        }

        /**
         * 添加请求头
         * @param key key
         * @param value value
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
            headerBuilder.add("User-Agent", "Android");
            headerBuilder.add("APP-Key", "");//应用的key值
            headerBuilder.add("APP-Secret", "");//应用的密钥
            headerBuilder.add("Charset", "UTF-8");//字符编码格式
            headerBuilder.add("Accept", "*/*");//能够接受的数据格式
            headerBuilder.add("Accept-Language", "zh-cn");//接受的语言
            headerBuilder.add("Content-Type", "application/json");//内容数据格式application/json text/xml
            headers = headerBuilder.build();
        }

        /**
         * 请求基础参数设置
         *
         * @param timeOut
         *         超时时间
         * @param isLogInterceptor
         *         是否显示请求日志
         *
         * @return Builder
         */
        public Builder baseNetParam(int timeOut, boolean isLogInterceptor) {
            return this.baseNetParam(timeOut, isCacheable, isCookie, isRedirect, isRetryFailure, isLogInterceptor);
        }

        /**
         * 请求基础参数设置
         *
         * @param timeOut
         *         超时时间
         * @param isCacheable
         *         无网络时是否使用是否缓存
         * @param isLogInterceptor
         *         是否显示请求日志
         *
         * @return Builder
         */
        public Builder baseNetParam(int timeOut, boolean isCacheable, boolean isLogInterceptor) {
            return this.baseNetParam(timeOut, isCacheable, isCookie, isRedirect, isRetryFailure, isLogInterceptor);
        }

        /**
         * 网络基础属性设置
         *
         * @param isCookie
         *         是否使用cookie
         * @param isRedirect
         *         是否可以重定向
         * @param isRetryFailure
         *         失败后是否可以重试
         * @param isLogInterceptor
         *         是否显示报文日志
         *
         * @return builder
         */
        public Builder baseNetParam(int timeOut, boolean isCacheable, boolean isCookie, boolean isRedirect, boolean isRetryFailure, boolean isLogInterceptor) {
            this.timeOut = timeOut;
            this.isCookie = isCookie;
            this.isRedirect = isRedirect;
            this.isRetryFailure = isRetryFailure;
            this.isLogInterceptor = isLogInterceptor;
            this.isCacheable = isCacheable;

            if (isCookie) {
                okHttpBuilder.cookieJar(new CookiesManager(context));
            }

            if (isLogInterceptor) {
                okHttpBuilder.addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
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

            okHttpBuilder
                    .followRedirects(isRedirect)
                    .retryOnConnectionFailure(isRetryFailure)//连接失败后是否重新连接
                    .connectTimeout(timeOut, TimeUnit.SECONDS);//超时时间15S

            return this;
        }

        /**
         * https设置
         * @param isTrustAll 是否全部信任
         * @return Builder
         */
        public Builder netHttps(boolean isTrustAll) {
            return this.netHttps(isTrustAll, isSslRedirect, null);
        }

        /**
         * https设置
         * @param certificates 安全证书
         * @return Builder
         */
        public Builder netHttps(int[] certificates) {
            return this.netHttps(isTrustAll, isSslRedirect, certificates);
        }

        /**
         * https设置
         * @param isTrustAll 是否全部信任
         * @param isSslRedirect 是否可以重定向
         * @return Builder
         */
        public Builder netHttps(boolean isTrustAll, boolean isSslRedirect) {
            return this.netHttps(isTrustAll, isSslRedirect, null);
        }

        /**
         * https设置
         * @param certificates 安全证书
         * @param isSslRedirect 是否可以重定向
         * @return Builder
         */
        public Builder netHttps(int[] certificates,boolean isSslRedirect) {
            return this.netHttps(isTrustAll, isSslRedirect, certificates);
        }


        /**
         * https设置
         * @param isTrustAll 是否全部信任
         * @param isSslRedirect 是否可以重定向
         * @param certificates 安全证书
         * @return Builder
         */
        public Builder netHttps(boolean isTrustAll, boolean isSslRedirect, int[] certificates) {
            this.isSslRedirect = isSslRedirect;
            this.certificates = certificates;
            this.okHttpBuilder.followSslRedirects(isSslRedirect);
            if(isTrustAll) {
                setAllCerPass(okHttpBuilder);
            } else {
                initSSL(certificates);
            }
            return this;
        }


        /**
         * 初始化https安全组件
         * @param certificates 安全证书
         */
        private void initSSL(int[] certificates) {
            if (certificates != null && certificates.length > 0 && context != null) {
                TrustManager[] trustManagers = SSLManager.getTrustManager(context, certificates);
                SSLSocketFactory sslSocketFactory = SSLManager.getSSLSocketFactory(context, certificates);
                X509TrustManager x509TrustManager = (trustManagers != null && trustManagers.length > 0) ? (X509TrustManager) trustManagers[0] : null;
                if (x509TrustManager != null) {
                    okHttpBuilder.sslSocketFactory(sslSocketFactory, x509TrustManager);
                }
            }
        }

        /**
         * 让客户端通过所有证书的验证.
         * 注意:容易导致中间人攻击,轻易不要使用
         *
         * @param httpBuilder httpBuilder
         */
        private void setAllCerPass(OkHttpClient.Builder httpBuilder) {
            X509TrustManager xtm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
//                    Logger.i("checkClientTrusted--X509Certificate:" + Arrays.toString(chain) + "--authType:" + authType );
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
//                    Logger.i("checkServerTrusted--X509Certificate:" + Arrays.toString(chain) + "--authType:" + authType );
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            };

            try {
                SSLContext sslContext = SSLContext.getInstance("SSL");

                sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

                HostnameVerifier doNotVerify = (hostname, session) -> true;

                httpBuilder.sslSocketFactory(sslContext.getSocketFactory(), xtm)
                        .hostnameVerifier(doNotVerify);

            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                e.printStackTrace();
            }
        }

        /**
         * 初始化convert
         *
         * @param protocol
         *         协议类型
         */
        private void initProtoCovert(NetGlobalConfig.PROTOTYPE protocol) {
            switch (protocol) {
                case JSON:
                    converterFactory = GsonConverterFactory.create();
                    addHeader(NetGlobalConfig.CONTENTTYPE,NetGlobalConfig.CONTENTTYPEJSON);
                    break;
                case XML:
//                    converterFactory = JaxbConverterFactory.create();
//                    converterFactory = SimpleXmlConverterFactory.create();
                    converterFactory = SzAirConvertFactory.create();
                    addHeader(NetGlobalConfig.CONTENTTYPE,NetGlobalConfig.CONTENTTYPEXML);
                    break;
                case PROTOBUF:
                    break;
                case JACKSON:
                    break;
                case MOSHI:
                    break;
                case WIRE:
                    break;
                case SCALARS:
                    break;
                default:
                    converterFactory = GsonConverterFactory.create();
            }
        }


        public DataRepo build() {
            if(converterFactory == null) {
                converterFactory = GsonConverterFactory.create();
            }
            retrofitBuilder
                    .addConverterFactory(converterFactory);
            return new DataRepo(this);
        }
    }

}
