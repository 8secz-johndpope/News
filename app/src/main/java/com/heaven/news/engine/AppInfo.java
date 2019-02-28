package com.heaven.news.engine;

/**
 * FileName: com.heaven.core.baseconfig.AppInfo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2016-10-01 21:30
 *
 * @version V1.0 Terminal information
 */
public class AppInfo {
    public static final String APP_ID = "5";
    public String APP_IP = "127.0.0.1";
    public final String DEVICE_TYPE = "android";
    // 移动设备相关属性
    public String MOBILE_MODEL = android.os.Build.MODEL;// 手机型号
    public int MOBILE_SDK = android.os.Build.VERSION.SDK_INT;// SDK版本
    public String MOBILE_RELEASE = android.os.Build.VERSION.RELEASE;// 系统版本

    // 应用相关的属性
    public String packageName;
    public int verCode;
    public String verName ;
    public String name;
    public String sourceDir;
}
