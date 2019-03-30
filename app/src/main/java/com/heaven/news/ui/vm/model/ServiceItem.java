package com.heaven.news.ui.vm.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by heaven on 2016/5/8.
 * 首页和服务页服务选项数据模型
 */
public class ServiceItem implements Parcelable, Serializable {
    //类型
    int type = -1;
    //服务id
    public int id = -1;
    //服务icon id
    public String iconID = null;
    //服务名字
    public String name = null;
    //服务url简短
    public String urlShort = null;
    //服务url详细
    public String urlDetail = null;
    //排列顺序
    public int rankNo = 0;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeInt(this.id);
        dest.writeString(this.iconID);
        dest.writeString(this.name);
        dest.writeString(this.urlShort);
        dest.writeString(this.urlDetail);
        dest.writeInt(this.rankNo);
    }

    public ServiceItem() {
    }

    protected ServiceItem(Parcel in) {
        this.type = in.readInt();
        this.id = in.readInt();
        this.iconID = in.readString();
        this.name = in.readString();
        this.urlShort = in.readString();
        this.urlDetail = in.readString();
        this.rankNo = in.readInt();
    }

    public static final Creator<ServiceItem> CREATOR = new Creator<ServiceItem>() {
        @Override
        public ServiceItem createFromParcel(Parcel source) {
            return new ServiceItem(source);
        }

        @Override
        public ServiceItem[] newArray(int size) {
            return new ServiceItem[size];
        }
    };

    @Override
    public String toString() {
        return "ServiceItem{" +
                "type=" + type +
                ", id=" + id +
                ", iconID='" + iconID + '\'' +
                ", name='" + name + '\'' +
                ", urlShort='" + urlShort + '\'' +
                ", urlDetail='" + urlDetail + '\'' +
                ", rankNo=" + rankNo +
                '}';
    }
}
