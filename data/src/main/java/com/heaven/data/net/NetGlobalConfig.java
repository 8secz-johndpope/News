package com.heaven.data.net;

import okhttp3.Headers;

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

    public static boolean CACHE_ABLE = true;
    public static int CACHEMAXAGE = Integer.MAX_VALUE / 2;

    public static int MULTPARTSIZE = 50 * 1024 * 1024;

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

}
