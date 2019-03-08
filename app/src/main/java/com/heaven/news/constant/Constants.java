package com.heaven.news.constant;

import com.heaven.news.utils.CryptUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * FileName: com.heaven.news.constant.Constants.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-09 00:37
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class Constants {
    public static String ADINFO = "adinfo";//广告信息
    public static String LOGININFO = "logininfo";//登录信息
    public static String ISAUTOLOGIN = "isautologin";//是否自动登录
    public static String USERINFO = "userinfo";//登录用户信息

    public static String getPassword(String passwords) {
        String password = passwords;
        String AES_KEY = "szair-";
        String key = AES_KEY + new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        byte[] passwordByte = CryptUtility.encrypt(passwords.getBytes(), key.getBytes());
        password = CryptUtility.base64Encode(passwordByte);
        password = password.replaceAll("\n", "");
        return password;
    }
}
