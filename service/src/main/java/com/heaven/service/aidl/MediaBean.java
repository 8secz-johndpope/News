package com.heaven.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * FileName: com.heaven.flybetter.aidl.MediaBean.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-04-08 09:32
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class MediaBean implements Parcelable{
    //路径
    public String path;
    //文件名
    public String mediaName;
    //日期
    public long date;
    //是否视频
    public boolean isVideo;
    //文件大小
    public long size;
    //媒体时间
    public int duration;
    //媒体类型
    public String ext;
    public String folderPath;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.mediaName);
        dest.writeLong(this.date);
        dest.writeByte(this.isVideo ? (byte) 1 : (byte) 0);
        dest.writeLong(this.size);
        dest.writeInt(this.duration);
        dest.writeString(this.ext);
    }

    public MediaBean() {
    }

    public MediaBean(String path, String name, long date, boolean isVideo, long size, int duration, String folderPath, String ext) {
        this.path = path;
        this.mediaName = name;
        this.date = date;
        this.isVideo = isVideo;
        this.size = size;
        this.duration = duration;
        this.folderPath = folderPath;
        this.ext = ext;

    }

    protected MediaBean(Parcel in) {
        this.path = in.readString();
        this.mediaName = in.readString();
        this.date = in.readLong();
        this.isVideo = in.readByte() != 0;
        this.size = in.readLong();
        this.duration = in.readInt();
        this.ext = in.readString();
    }

    public static final Creator<MediaBean> CREATOR = new Creator<MediaBean>() {
        @Override
        public MediaBean createFromParcel(Parcel source) {
            return new MediaBean(source);
        }

        @Override
        public MediaBean[] newArray(int size) {
            return new MediaBean[size];
        }
    };

    public void readFromParcel(Parcel in) {
        this.path = in.readString();
        this.mediaName = in.readString();
        this.date = in.readLong();
        this.isVideo = in.readByte() != 0;
        this.size = in.readLong();
        this.duration = in.readInt();
        this.ext = in.readString();
    }
}
