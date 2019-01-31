package com.heaven.model.entity.login;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class User implements Parcelable,Serializable{
    public String userId;
    public String userName;
    public String avatar;
    public String email;
    public String phone;
    public String password;
    public String oldPassword;
    public String thirdUserId;
    public String openId;
    public String token;
    public String unionId;
    public String sourceFrom; //sourceFrom(1account,2wechat,3webo,4qq)
    public String enterpriseName;
    public String code;
    public String weChatOpenId;
    public String weiboOpenId;
    public String qqOpenId;
    public String platform;
    public String userAvator;
    public boolean owner;
    public boolean showDelete;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.avatar);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.password);
        dest.writeString(this.oldPassword);
        dest.writeString(this.thirdUserId);
        dest.writeString(this.openId);
        dest.writeString(this.token);
        dest.writeString(this.unionId);
        dest.writeString(this.sourceFrom);
        dest.writeString(this.enterpriseName);
        dest.writeString(this.code);
        dest.writeString(this.weChatOpenId);
        dest.writeString(this.weiboOpenId);
        dest.writeString(this.qqOpenId);
        dest.writeString(this.platform);
        dest.writeString(this.userAvator);
        dest.writeByte(this.owner ? (byte) 1 : (byte) 0);
        dest.writeByte(this.showDelete ? (byte) 1 : (byte) 0);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.avatar = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.password = in.readString();
        this.oldPassword = in.readString();
        this.thirdUserId = in.readString();
        this.openId = in.readString();
        this.token = in.readString();
        this.unionId = in.readString();
        this.sourceFrom = in.readString();
        this.enterpriseName = in.readString();
        this.code = in.readString();
        this.weChatOpenId = in.readString();
        this.weiboOpenId = in.readString();
        this.qqOpenId = in.readString();
        this.platform = in.readString();
        this.userAvator = in.readString();
        this.owner = in.readByte() != 0;
        this.showDelete = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
