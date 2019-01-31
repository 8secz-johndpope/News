package com.heaven.data.net.cookie;


import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * 作者：Heaven
 * 时间: on 2016/10/18 17:49
 * 邮箱：heavenisme@aliyun.com
 */

public class CookiesManager implements CookieJar {
    public static String APP_PLATFORM = "app-platform";


    private static PersistentCookieStore cookieStore;

    public CookiesManager(Context context) {
        if (cookieStore == null ) {
            cookieStore = new PersistentCookieStore(context);
        }

    }





    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies =cookieStore.get(url);
        return cookies;
    }

    static class Customer {

        private String userID;
        private String token;

        public Customer(String userID, String token) {
            this.userID = userID;
            this.token = token;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
