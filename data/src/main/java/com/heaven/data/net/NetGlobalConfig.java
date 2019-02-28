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


    public static int MAX_STALE = 60 * 60 * 24;
    public static int TIMEOUT = 20;

    public static boolean CACHE_ABLE = true;
    public static int CACHEMAXAGE = Integer.MAX_VALUE / 2;

    public static int MULTPARTSIZE = 50 * 1024 * 1024;

    public static String CONTENTTYPE = "Content-Type";
    public static String CONTENTTYPEJSON = "application/json";
    public static String CONTENTTYPEXML = "application/xml";

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



}
