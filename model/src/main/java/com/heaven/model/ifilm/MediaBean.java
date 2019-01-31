package com.heaven.model.ifilm;

import android.os.Parcel;
import android.os.Parcelable;


import java.io.Serializable;

/**
 * FileName: com.ifilmo.photography.activities.MetarialManager.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2016-12-12 21:10
 *
 * @version V1.0 媒体类型
 */
public class MediaBean implements Parcelable, Comparable<MediaBean>, Cloneable, Serializable {
    //路径
    private String path;
    //文件名
    private String name;
    //日期
    private long date;
    //是否视频
    private boolean isVideo;
    //文件大小
    private long size;

    private int duration;

    private boolean isUploading;

    private boolean isEditItem;

    private int position;

    private boolean isSelected;

    private int progress;

    private boolean isCompleted;

    private String orderId;

    private String ossMateriaLocation;

    private String password;

    private boolean isCloundDisk;

    private boolean isEditble;

    private String filmName;

    private String orderMaderialName;

    private boolean isUploaded;

    private String folderPath;

    private String ext;

    private Integer materialId;

    private Integer cloudLinkCount;



    public MediaBean() {
    }

    public MediaBean(String path, String name, long date, boolean isVideo, long size, int duration, String folderPath, String ext) {
        this.path = path;
        this.name = name;
        this.date = date;
        this.isVideo = isVideo;
        this.size = size;
        this.duration = duration;
        this.folderPath = folderPath;
        this.ext = ext;

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isUploading() {
        return isUploading;
    }

    public void setUploading(boolean uploading) {
        isUploading = uploading;
    }

    public boolean isEditItem() {
        return isEditItem;
    }

    public void setEditItem(boolean editItem) {
        isEditItem = editItem;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOssMateriaLocation() {
        return ossMateriaLocation;
    }

    public void setOssMateriaLocation(String ossMateriaLocation) {
        this.ossMateriaLocation = ossMateriaLocation;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCloundDisk() {
        return isCloundDisk;
    }

    public void setCloundDisk(boolean cloundDisk) {
        isCloundDisk = cloundDisk;
    }

    public boolean isEditble() {
        return isEditble;
    }

    public void setEditble(boolean editble) {
        isEditble = editble;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getOrderMaderialName() {
        return orderMaderialName;
    }

    public void setOrderMaderialName(String orderMaderialName) {
        this.orderMaderialName = orderMaderialName;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }


    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getCloudLinkCount() {
        return cloudLinkCount;
    }

    public void setCloudLinkCount(Integer cloudLinkCount) {
        this.cloudLinkCount = cloudLinkCount;
    }



    public void startUpload() {
//        materialId = ossDataManager.getMaterialId(ossMateriaLocation);
//        ossDataManager.removeUploadMaterial(this);
        isUploading = true;
        isCompleted = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MediaBean mediaBean = (MediaBean) o;

        if (date != mediaBean.date) return false;
        if (isVideo != mediaBean.isVideo) return false;
        if (size != mediaBean.size) return false;
        if (path != null ? !path.equals(mediaBean.path) : mediaBean.path != null) return false;
        if (duration != mediaBean.duration) return false;
        if (isUploading != mediaBean.isUploading) return false;
        if (isEditItem != mediaBean.isEditItem) return false;
        if (position != mediaBean.position) return false;
        if (isSelected != mediaBean.isSelected) return false;

        return name != null ? name.equals(mediaBean.name) : mediaBean.name == null;

    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (date ^ (date >>> 32));
        result = 31 * result + (isVideo ? 1 : 0);
        result = 31 * result + (int) (size ^ (size >>> 32));
        result = 31 * result + (int) (duration ^ (size >>> 32));
        return result;
    }


    @Override
    public int compareTo(MediaBean another) {
        //倒序
        if (this.date > another.getDate()) {
            return -1;
        }
        return 1;
    }


    @Override
    public MediaBean clone() {
        MediaBean mediaBean = null;
        try {
            mediaBean = (MediaBean) super.clone();
            mediaBean.isUploading = false;
            mediaBean.isCompleted = false;
            mediaBean.ossMateriaLocation = new String();
            mediaBean.orderId = new String();
            mediaBean.orderMaderialName = new String();
            mediaBean.filmName = new String();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return mediaBean;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.name);
        dest.writeLong(this.date);
        dest.writeByte(this.isVideo ? (byte) 1 : (byte) 0);
        dest.writeLong(this.size);
        dest.writeInt(this.duration);
        dest.writeByte(this.isUploading ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isEditItem ? (byte) 1 : (byte) 0);
        dest.writeInt(this.position);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        dest.writeInt(this.progress);
        dest.writeByte(this.isCompleted ? (byte) 1 : (byte) 0);
        dest.writeString(this.orderId);
        dest.writeString(this.ossMateriaLocation);
        dest.writeString(this.password);
        dest.writeByte(this.isCloundDisk ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isEditble ? (byte) 1 : (byte) 0);
        dest.writeString(this.filmName);
        dest.writeString(this.orderMaderialName);
        dest.writeByte(this.isUploaded ? (byte) 1 : (byte) 0);
        dest.writeString(this.folderPath);
        dest.writeString(this.ext);
        dest.writeValue(this.materialId);
        dest.writeValue(this.cloudLinkCount);
    }

    protected MediaBean(Parcel in) {
        this.path = in.readString();
        this.name = in.readString();
        this.date = in.readLong();
        this.isVideo = in.readByte() != 0;
        this.size = in.readLong();
        this.duration = in.readInt();
        this.isUploading = in.readByte() != 0;
        this.isEditItem = in.readByte() != 0;
        this.position = in.readInt();
        this.isSelected = in.readByte() != 0;
        this.progress = in.readInt();
        this.isCompleted = in.readByte() != 0;
        this.orderId = in.readString();
        this.ossMateriaLocation = in.readString();
        this.password = in.readString();
        this.isCloundDisk = in.readByte() != 0;
        this.isEditble = in.readByte() != 0;
        this.filmName = in.readString();
        this.orderMaderialName = in.readString();
        this.isUploaded = in.readByte() != 0;
        this.folderPath = in.readString();
        this.ext = in.readString();
        this.materialId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.cloudLinkCount = (Integer) in.readValue(Integer.class.getClassLoader());
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

    @Override
    public String toString() {
        return "MediaBean{" +
                "cloudLinkCount=" + cloudLinkCount +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", isVideo=" + isVideo +
                ", size=" + size +
                ", duration=" + duration +
                ", isUploading=" + isUploading +
                ", isEditItem=" + isEditItem +
                ", position=" + position +
                ", isSelected=" + isSelected +
                ", progress=" + progress +
                ", isCompleted=" + isCompleted +
                ", orderId='" + orderId + '\'' +
                ", ossMateriaLocation='" + ossMateriaLocation + '\'' +
                ", password='" + password + '\'' +
                ", isCloundDisk=" + isCloundDisk +
                ", isEditble=" + isEditble +
                ", filmName='" + filmName + '\'' +
                ", orderMaderialName='" + orderMaderialName + '\'' +
                ", isUploaded=" + isUploaded +
                ", folderPath='" + folderPath + '\'' +
                ", ext='" + ext + '\'' +
                ", materialId=" + materialId +
                '}';
    }
}
