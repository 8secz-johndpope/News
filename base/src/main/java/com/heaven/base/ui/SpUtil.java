package com.heaven.base.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.heaven.base.ui.activity.BaseActivity;
import com.heaven.base.ui.activity.BasePtActivity;


public class SpUtil {
    static SharedPreferences prefs;

    public static String getDataByKey(String key) {
        return prefs.getString(key, "");
    }

    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isNight() {
        return prefs.getBoolean("isNight", false);
    }

    public static void setNight(Context context, boolean isNight) {
        prefs.edit().putBoolean("isNight", isNight).apply();
        if (context instanceof BaseActivity)
            ((BaseActivity) context).reload();
        else if(context instanceof BasePtActivity) {
            ((BasePtActivity) context).reload();
        }
    }

}
