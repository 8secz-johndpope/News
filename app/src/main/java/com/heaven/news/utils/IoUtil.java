package com.heaven.news.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * FileName: com.heaven.news.utils.IoUtil.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-29 19:01
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class IoUtil {

    public static void save(String fileName, String content) {
        FileOutputStream fos = null;
        try {
            /* 判断sd的外部设置状态是否可以读写 */
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File file = new File(Environment.getExternalStorageDirectory(), fileName + ".txt");
                if(!file.exists()) {
                    file.createNewFile();
                }
                // 先清空内容再写入
                fos = new FileOutputStream(file);
                byte[] buffer = content.getBytes();
                fos.write(buffer);
                fos.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
