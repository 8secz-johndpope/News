package com.heaven.news.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.util.Log;


import com.heaven.news.engine.AppEngine;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by baixiaokang on 16/12/10.
 */

public class BaseUtils {

    @SuppressLint("TrulyRandom")
    public static byte[] encrypt(byte[] origData, byte[] keyData) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyData, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cryptedData = new byte[cipher.getOutputSize(origData.length)];
        int ctLength = cipher.update(origData, 0, origData.length, cryptedData, 0);
        ctLength += cipher.doFinal(cryptedData, ctLength);
        return cryptedData;
    }

    public static List<String> getOldWeekDays() {
        final Calendar c = Calendar.getInstance();
        String[] months = new String[8];
        for (int i = 0; i < 8; i++) {
            months[i] = new SimpleDateFormat("MM.dd").format(new Date(c
                    .getTimeInMillis()));
            c.add(Calendar.DAY_OF_MONTH, -1);
        }
        return Arrays.asList(months);
    }

    public static Paint getPaint(Paint.Style style, int color) {
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(style);
        mPaint.setColor(color);
        mPaint.setTextSize(30);
        return mPaint;
    }

    /**
     * @param fraction
     * @param startValue
     * @param endValue
     * @return
     */
    public static Integer evaluate(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;
        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;
        return (int) ((startA + (int) (fraction * (endA - startA))) << 24)
                | (int) ((startR + (int) (fraction * (endR - startR))) << 16)
                | (int) ((startG + (int) (fraction * (endG - startG))) << 8)
                | (int) ((startB + (int) (fraction * (endB - startB))));
    }

    /**
     * 压缩图片
     *
     * @param srcPath
     * @return
     */
    public static String getUpLoadimage(String srcPath) {
        try {
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true;
            Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
            newOpts.inJustDecodeBounds = false;
            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
            float hh = 800f;// 这里设置高度为800f
            float ww = 480f;// 这里设置宽度为480f
            // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            int be = 1;// be=1表示不缩放
            if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
                be = (int) (newOpts.outWidth / ww);
            } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
                be = (int) (newOpts.outHeight / hh);
            }
            if (be <= 0)
                be = 1;
            newOpts.inSampleSize = be;// 设置缩放比例
            // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
            return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("Exception", "大小压缩失败");
            return srcPath;
        }

    }

    private static String compressImage(Bitmap image) throws Exception {
        String url = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 99, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            options -= 10;// 每次都减少10
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        image.recycle();
        Long dd = new Date().getTime();
        try {
            url = saveFile(baos, dd + ".png");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e("Exception", "质量压缩失败");
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 保存文件
     *
     * @param baos
     * @param fileName
     * @throws Exception
     */
    public static String saveFile(ByteArrayOutputStream baos, String fileName)
            throws Exception {
        String Path = AppEngine.instance().getFilesDir();
        File dirFile = new File(Path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(Path + "/" + fileName);
        FileOutputStream fi = new FileOutputStream(myCaptureFile);
        baos.writeTo(fi);
        baos.flush();
        baos.close();
        return myCaptureFile.getAbsolutePath();
    }
    public static String getHourAndMinuteAndSecond(long mils) {
        mils /= 1000;
        int[] time = new int[3];
        time[0] = (int) mils / 3600;
        time[1] = (int) mils / 60 % 60;
        time[2] = (int) mils % 60;
        return String.format(Locale.CHINESE, "%02d:%02d:%02d", time[0], time[1], time[2]);
    }

    public static String getMinuteAndSecond(long mils) {
        mils /= 1000;
        int[] time = new int[3];
        time[0] = (int) mils / 3600;
        time[1] = (int) mils / 60 % 60;
        time[2] = (int) mils % 60;
        if (time[0]>0){
            return String.format(Locale.CHINESE, "%02d:%02d:%02d", time[0], time[1], time[2]);
        }else {
            return String.format(Locale.CHINESE, "%02d'%02d\"",time[1], time[2]);
        }
    }

    /**
     * 调此方法输入所要转换的时间输入例如（"2014-06-14-16-09-00"）返回时间戳
     *
     * @param time
     * @return
     */
    public static String dataOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14  16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timeDate(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        return sdr.format(new Date(time));
    }

    /**
     * 得到amr的时长
     *
     * @param file
     * @return amr文件时间长度
     * @throws IOException
     */
    public static int getAmrDuration(File file) {
        long duration = -1;
        int[] packedSize = {12, 13, 15, 17, 19, 20, 26, 31, 5, 0, 0, 0, 0, 0,
                0, 0};
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            long length = file.length();// 文件的长度
            int pos = 6;// 设置初始位置
            int frameCount = 0;// 初始帧数
            int packedPos = -1;

            byte[] datas = new byte[1];// 初始数据值
            while (pos <= length) {
                randomAccessFile.seek(pos);
                if (randomAccessFile.read(datas, 0, 1) != 1) {
                    duration = length > 0 ? ((length - 6) / 650) : 0;
                    break;
                }
                packedPos = (datas[0] >> 3) & 0x0F;
                pos += packedSize[packedPos] + 1;
                frameCount++;
            }

            duration += frameCount * 20;// 帧数*20
        } catch (Exception e) {
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return (int) ((duration / 1000) + 1);
    }
}
