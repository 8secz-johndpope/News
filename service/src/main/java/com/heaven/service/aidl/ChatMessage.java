package com.heaven.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ChatMessage implements Parcelable,Serializable {

    public String messageId;
    public String content; //文字内容
    public String contentType; //text image audio video
    public String platform;  //pc android ios
    public String receiveUid;  //接收者
    public String sendUid;  //发送者
    public String orderNo; //订单编号
    public String url; //视频，图片，音频 地址
    public int isRead;//是否已经读  0 未读  1 已读
    public int isSpeak;// 接收时： 是否已经播放  0 未播放  1 已经播放。
    public int isSendSuccess; // 0  正在发送， 1 已发送
    public int duration; //时长
    public String localUrl; //本地地址
    public String videoThumbnail;
    public String fname; //文字内容
    public long contentSize; //内容大小
    public int type;
    public long timestamp;
    public int isJiGuang; //0 不是极光，1是极光


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.messageId);
        dest.writeString(this.content);
        dest.writeString(this.contentType);
        dest.writeString(this.platform);
        dest.writeString(this.receiveUid);
        dest.writeString(this.sendUid);
        dest.writeString(this.orderNo);
        dest.writeString(this.url);
        dest.writeInt(this.isRead);
        dest.writeInt(this.isSpeak);
        dest.writeInt(this.isSendSuccess);
        dest.writeInt(this.duration);
        dest.writeString(this.localUrl);
        dest.writeString(this.videoThumbnail);
        dest.writeString(this.fname);
        dest.writeLong(this.contentSize);
        dest.writeInt(this.type);
        dest.writeLong(this.timestamp);
        dest.writeInt(this.isJiGuang);
    }

    public ChatMessage() {
    }

    protected ChatMessage(Parcel in) {
        this.messageId = in.readString();
        this.content = in.readString();
        this.contentType = in.readString();
        this.platform = in.readString();
        this.receiveUid = in.readString();
        this.sendUid = in.readString();
        this.orderNo = in.readString();
        this.url = in.readString();
        this.isRead = in.readInt();
        this.isSpeak = in.readInt();
        this.isSendSuccess = in.readInt();
        this.duration = in.readInt();
        this.localUrl = in.readString();
        this.videoThumbnail = in.readString();
        this.fname = in.readString();
        this.contentSize = in.readLong();
        this.type = in.readInt();
        this.timestamp = in.readLong();
        this.isJiGuang = in.readInt();
    }

    public static final Creator<ChatMessage> CREATOR = new Creator<ChatMessage>() {
        @Override
        public ChatMessage createFromParcel(Parcel source) {
            return new ChatMessage(source);
        }

        @Override
        public ChatMessage[] newArray(int size) {
            return new ChatMessage[size];
        }
    };

    public void readFromParcel(Parcel in) {
        this.messageId = in.readString();
        this.content = in.readString();
        this.contentType = in.readString();
        this.platform = in.readString();
        this.receiveUid = in.readString();
        this.sendUid = in.readString();
        this.orderNo = in.readString();
        this.url = in.readString();
        this.isRead = in.readInt();
        this.isSpeak = in.readInt();
        this.isSendSuccess = in.readInt();
        this.duration = in.readInt();
        this.localUrl = in.readString();
        this.videoThumbnail = in.readString();
        this.fname = in.readString();
        this.contentSize = in.readLong();
        this.type = in.readInt();
        this.timestamp = in.readLong();
        this.isJiGuang = in.readInt();
    }
}
