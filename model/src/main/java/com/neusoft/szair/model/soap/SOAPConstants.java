package com.neusoft.szair.model.soap;


import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

public class SOAPConstants {
//     public static String WEB_HOST = "https://mobile.shenzhenair.com";
     public static String WEB_HOST = "https://mobile.shenzhenair.com/develop";
//     public static String WEB_HOST = "https://mobile.shenzhenair.com/53yanshou";
//       public static String WEB_HOST = "https://mobile.shenzhenair.com/apptest";

//    public static String WEB_HOST =  "http://mobile.shenzhenair.com/app111";
    public static final int CONNECTION_TIMEOUT = 5 * 1000;
    public static final int SOCKET_TIMEOUT = 2 * 60 * 1000;

    //电粉相关请求时用
    public static final String APP_ID = "5";
    public static final String APP_IP = "127.0.0.1";
    public static final String DEVICE_TYPE = "ANDROID";
    public static final String EC_LOGIN_FAILURE_6018 = "6018";

    public static String DIC_PATH = "dic/";

    public static String getHostUrl() {
        return WEB_HOST + "/szairMobileWS/services/";
    }

}
