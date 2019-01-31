package com.heaven.model.ifilm;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TK on 2016/10/18.
 * 多媒体文件夹预览bean
 */
public class MediaFolderBean implements Parcelable,Serializable {
    public static final int CLOUND = 1;

    public static final int INVITE_FRIEND = 2;

    public int foldertype = -1;

    //封面路径
    private String indexPath;
    //文件夹名
    private String folderName;
    //封面是否video
    private boolean isIndexVideo;
    //文件夹下集合
    private List<MediaBean> mediaList = new ArrayList<>();

    private String folderPath;

    private boolean isShowSelectedCount = true;

    private Integer cloudLinkCount;

    private int currentSelectedCount;

    public MediaFolderBean(){

    }

    public MediaFolderBean(String indexPath, String folderName, String folderPath, boolean isShowSelectedCount) {
        this.indexPath = indexPath;
        this.folderName = folderName;
        this.folderPath = folderPath;
        this.isShowSelectedCount = isShowSelectedCount;
    }

    public void addBean(MediaBean mediaBean) {
        this.mediaList.add(mediaBean);
    }

    public String getIndexPath() {
        return indexPath;
    }

    public void setIndexPath(String indexPath) {
        this.indexPath = indexPath;
    }


    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public boolean isIndexVideo() {
        return isIndexVideo;
    }

    public void setIndexVideo(boolean indexVideo) {
        isIndexVideo = indexVideo;
    }

    public List<MediaBean> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<MediaBean> mediaList) {
        this.mediaList = mediaList;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public boolean isShowSelectedCount() {
        return isShowSelectedCount;
    }

    public void setShowSelectedCount(boolean showSelectedCount) {
        isShowSelectedCount = showSelectedCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.indexPath);
        dest.writeString(this.folderName);
        dest.writeByte(this.isIndexVideo ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.mediaList);
        dest.writeString(this.folderPath);
        dest.writeByte(this.isShowSelectedCount ? (byte) 1 : (byte) 0);
    }

    protected MediaFolderBean(Parcel in) {
        this.indexPath = in.readString();
        this.folderName = in.readString();
        this.isIndexVideo = in.readByte() != 0;
        this.mediaList = in.createTypedArrayList(MediaBean.CREATOR);
        this.folderPath = in.readString();
        this.isShowSelectedCount = in.readByte() != 0;
    }

    public static final Creator<MediaFolderBean> CREATOR = new Creator<MediaFolderBean>() {
        @Override
        public MediaFolderBean createFromParcel(Parcel source) {
            return new MediaFolderBean(source);
        }

        @Override
        public MediaFolderBean[] newArray(int size) {
            return new MediaFolderBean[size];
        }
    };


    public MediaFolderBean copy() throws IOException, ClassNotFoundException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        return (MediaFolderBean)ois.readObject();
    }

    public Integer getCloudLinkCount() {
        return cloudLinkCount;
    }

    public void setCloudLinkCount(Integer cloudLinkCount) {
        this.cloudLinkCount = cloudLinkCount;
    }

    public int getCurrentSelectedCount() {
        return currentSelectedCount;
    }

    public void setCurrentSelectedCount(int currentSelectedCount) {
        this.currentSelectedCount = currentSelectedCount;
    }
}
