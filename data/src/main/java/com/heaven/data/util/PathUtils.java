package com.heaven.data.util;

import android.content.Context;
import android.os.Environment;

/**
 * 作者:Heaven
 * 时间: on 2017/8/29 12:30
 * 邮箱:heavenisme@aliyun.com
 */

public class PathUtils {


    public static String getVideoPath() {
        return Environment.getExternalStorageDirectory() + "/heaven/video/";
    }
}
