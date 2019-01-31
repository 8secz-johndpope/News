package com.heaven.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.heaven.flybetter.aidl.MediaFolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-04-08 09:32
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class MediaFolder implements Parcelable {
    //封面路径
    public String indexPath;
    //文件夹名
    public String folderName;
    //封面是否video
    public boolean isIndexVideo;
    //相册路径
    public String folderPath;
    //文件夹下集合
    public List<MediaBean> mediaList = new ArrayList<>();


    private boolean isShowSelectedCount = true;
    @Override
    public int describeContents() {
        return 0;
    }

    public void addBean(MediaBean mediaBean) {
        this.mediaList.add(mediaBean);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.indexPath);
        dest.writeString(this.folderName);
        dest.writeByte(this.isIndexVideo ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.mediaList);
        dest.writeString(this.folderPath);
    }

    public MediaFolder(){
    }

    public MediaFolder(String indexPath, String folderName, String folderPath, boolean isShowSelectedCount) {
        this.indexPath = indexPath;
        this.folderName = folderName;
        this.folderPath = folderPath;
        this.isShowSelectedCount = isShowSelectedCount;
    }

    protected MediaFolder(Parcel in) {
        this.indexPath = in.readString();
        this.folderName = in.readString();
        this.isIndexVideo = in.readByte() != 0;
        this.mediaList = in.createTypedArrayList(MediaBean.CREATOR);
        this.folderPath = in.readString();
    }

    public static final Creator<MediaFolder> CREATOR = new Creator<MediaFolder>() {
        @Override
        public MediaFolder createFromParcel(Parcel source) {
            return new MediaFolder(source);
        }

        @Override
        public MediaFolder[] newArray(int size) {
            return new MediaFolder[size];
        }
    };

    public void readFromParcel(Parcel in) {
        this.indexPath = in.readString();
        this.folderName = in.readString();
        this.isIndexVideo = in.readByte() != 0;
        this.mediaList = in.createTypedArrayList(MediaBean.CREATOR);
        this.folderPath = in.readString();
    }
}
