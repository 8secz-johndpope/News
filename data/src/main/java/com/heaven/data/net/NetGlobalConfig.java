package com.heaven.data.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;

import com.heaven.data.net.cookie.CookiesManager;

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

import static com.heaven.data.net.NetGlobalConfig.PROTOTYPE.*;

/**
 * 作者:Heaven
 * 时间: on 2017/8/15 12:02
 * 邮箱:heavenisme@aliyun.com
 */

public class NetGlobalConfig {
    public static String HTTPS = "https";
    public static String HTTP = "http";


    public static Headers HEADERS;

    public static String BASETURL;

    public static int[] CERTIFICATES;
    public static boolean IS_HTTPS = false;
    public static boolean IS_COOKIES = true;
    public static boolean IS_REDIRECT = true;
    public static boolean IS_SSL_REDIRECTS = true;
    public static boolean IS_RETRY_FAILURE = true;
    public static boolean LOGGING_INTERCEPTOR = false;

    public static int MAX_STALE = 60 * 60 * 24;
    public static int TIMEOUT = 20;

    public static PROTOTYPE PROTOCOL = JSON;

    public static boolean CACHE_ABLE = true;
    public static int CACHEMAXAGE = Integer.MAX_VALUE / 2;

    public static int MULTPARTSIZE = 50 * 1024 * 1024;

    public static Converter.Factory CONVERTER_FACTORY = GsonConverterFactory.create();//SimpleXmlConverterFactory.create();//GsonConverterFactory.create()
    public static CallAdapter.Factory ADAPTER_FACTORY = RxJava2CallAdapterFactory.create();
//    private static Context mContext;
    /**
     * 标识登录状态的关键字
     */
    private String tokenKey = "token";

    public enum PROTOTYPE {
        /**
         * json协议
         * xml协议
         * proto协议
         * jackson协议
         */
        JSON,
        XML,
        PROTOBUF,
        JACKSON,
        MOSHI,
        WIRE,
        SCALARS
    }


    static {
        Headers.Builder headerBuilder = new Headers.Builder();
        //请求头部
        headerBuilder.add("User-Agent", "Android");
        headerBuilder.add("APP-Key", "");//应用的key值
        headerBuilder.add("APP-Secret", "");//应用的密钥
        headerBuilder.add("Charset", "UTF-8");//字符编码格式
        headerBuilder.add("Accept", "*/*");//能够接受的数据格式
        headerBuilder.add("Accept-Language", "zh-cn");//接受的语言
        headerBuilder.add("Content-Type", "application/json");//内容数据格式application/json text/xml
        HEADERS = headerBuilder.build();
    }

    public static void initHttp(Context context, OkHttpClient.Builder okHttpBuilder, Retrofit.Builder retrofitBuilder) {
        initCovert();

        if (IS_COOKIES) {
            okHttpBuilder.cookieJar(new CookiesManager(context));
        }

        if (LOGGING_INTERCEPTOR) {
            okHttpBuilder.addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        okHttpBuilder
                .addInterceptor(new NetInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .followRedirects(IS_REDIRECT)
                .followSslRedirects(IS_SSL_REDIRECTS)
                .retryOnConnectionFailure(IS_RETRY_FAILURE)//连接失败后是否重新连接
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS);//超时时间15S

        retrofitBuilder
                .baseUrl(BASETURL)
                .addConverterFactory(CONVERTER_FACTORY)
                .addCallAdapterFactory(ADAPTER_FACTORY);

        if (IS_HTTPS) {
            if (CERTIFICATES != null && CERTIFICATES.length > 0) {
                setHttps(context, okHttpBuilder, true);
            } else {
                setHttps(context, okHttpBuilder, false);
            }
        }

        if (CACHE_ABLE) {
            //设置缓存文件夹
            File cacheFile = new File(context.getCacheDir(), "okhttpcache");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
            okHttpBuilder.cache(cache);

            CacheInterceptor cacheInterceptor = new CacheInterceptor(context);
            okHttpBuilder.addNetworkInterceptor(cacheInterceptor);
            okHttpBuilder.addInterceptor(cacheInterceptor);

        }
    }

    private static void initCovert() {
        switch (PROTOCOL) {
            case JSON:
                CONVERTER_FACTORY = GsonConverterFactory.create();
                break;
            case XML:
                CONVERTER_FACTORY = SimpleXmlConverterFactory.create();
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
                CONVERTER_FACTORY = GsonConverterFactory.create();
        }
    }

    public static void addExtraHeader(String key, String value) {
        if (TextUtils.isEmpty(HEADERS.get(key))) {
            HEADERS = HEADERS.newBuilder().add(key, value).build();
        } else {
            HEADERS = HEADERS.newBuilder().set(key, value).build();
        }
    }

    @SuppressLint("CheckResult")
    public static void addExtraHeader(HashMap<String, String> extraHeaders) {
        Headers.Builder builder = HEADERS.newBuilder();
        Flowable.fromIterable(extraHeaders.entrySet()).subscribe(stringStringEntry -> {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            if (!TextUtils.isEmpty(HEADERS.get(key))) {
                builder.set(key, value);
            } else {
                builder.add(key, value);
            }

        });
        HEADERS = builder.build();
    }

    @SuppressLint("CheckResult")
    public static void removeExtraHeader(HashMap<String, String> extraHeaders) {
        Headers.Builder builder = HEADERS.newBuilder();
        Flowable.fromIterable(extraHeaders.entrySet()).subscribe(stringStringEntry -> {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            builder.removeAll(key);
        });
        HEADERS = builder.build();
    }

    public static void setHttps(Context context, OkHttpClient.Builder builder, boolean trustAll) {
        if (trustAll) {
            setAllCerPass(builder);
        } else {
            initSSL(context, builder);
        }
    }

    private static void initSSL(Context context, OkHttpClient.Builder builder) {
        if (CERTIFICATES != null && CERTIFICATES.length > 0 && context != null) {
            TrustManager[] trustManagers = SSLManager.getTrustManager(context, CERTIFICATES);
            SSLSocketFactory sslSocketFactory = SSLManager.getSSLSocketFactory(context, CERTIFICATES);
            X509TrustManager x509TrustManager = (trustManagers != null && trustManagers.length > 0) ? (X509TrustManager) trustManagers[0] : null;
            if (x509TrustManager != null) {
                builder.sslSocketFactory(sslSocketFactory, x509TrustManager);
            }
        }
    }

    /**
     * 让客户端通过所有证书的验证.
     * 注意:容易导致中间人攻击,轻易不要使用
     *
     * @param httpBuilder
     */
    private static void setAllCerPass(OkHttpClient.Builder httpBuilder) {
        X509TrustManager xtm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");

            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

            HostnameVerifier DO_NOT_VERIFY = (hostname, session) -> true;

            httpBuilder.sslSocketFactory(sslContext.getSocketFactory(), xtm)
                    .hostnameVerifier(DO_NOT_VERIFY);

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
