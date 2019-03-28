package com.heaven.base.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

/**
 * FileName: com.heaven.base.utils.ScreenUtil.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-27 13:10
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class ScreenUtil {
    @TargetApi(13)
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService("window");
        Point p = new Point();
        wm.getDefaultDisplay().getSize(p);
        return p.x;
    }

    @TargetApi(13)
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService("window");
        Point p = new Point();
        wm.getDefaultDisplay().getSize(p);
        return p.y;
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    public static int getImageResId(Context context,String imageResId) {
        //如果没有在"mipmap"下找到imageResId,将会返回0
        return context.getResources().getIdentifier(imageResId, "mipmap", context.getPackageName());
    }

    public static int getStringResId(Context context,String stringResId) {
        //如果没有在"stirng"下找到stringResId,将会返回0
        return context.getResources().getIdentifier(stringResId, "stirng", context.getPackageName());
    }
}
