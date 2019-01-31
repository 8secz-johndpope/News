package com.heaven.model.ifilm;


import java.io.Serializable;

/**
 * Created by LeoLu on 2016/12/11.
 */
public class ChatMessage implements Serializable {

    private String messageId;
    private String content; //文字内容
    private String contentType; //text image audio video
    private String platform;  //pc android ios
    private String receiveUid;  //接收者
    private String sendUid;  //发送者
    private String orderNo; //订单编号
    private String url; //视频，图片，音频 地址
    private int isRead;//是否已经读  0 未读  1 已读
    private int isSpeak;// 接收时： 是否已经播放  0 未播放  1 已经播放。
    private int isSendSuccess; // 0  正在发送， 1 已发送
    private int duration; //时长
    private String localUrl; //本地地址
    private String videoThumbnail;
    private String fname; //文字内容
    private long contentSize; //内容大小
    private int type;
    private long timestamp;
    private int isJiGuang; //0 不是极光，1是极光

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getReceiveUid() {
        return receiveUid;
    }

    public void setReceiveUid(String receiveUid) {
        this.receiveUid = receiveUid;
    }

    public String getSendUid() {
        return sendUid;
    }

    public void setSendUid(String sendUid) {
        this.sendUid = sendUid;
    }

    public String getOrderNo() {
            return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getIsSpeak() {
        return isSpeak;
    }

    public void setIsSpeak(int isSpeak) {
        this.isSpeak = isSpeak;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public long getContentSize() {
        return contentSize;
    }

    public void setContentSize(long contentSize) {
        this.contentSize = contentSize;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getIsSendSuccess() {
        return isSendSuccess;
    }

    public void setIsSendSuccess(int isSendSuccess) {
        this.isSendSuccess = isSendSuccess;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    public int getIsJiGuang() {
        return isJiGuang;
    }

    public void setIsJiGuang(int isJiGuang) {
        this.isJiGuang = isJiGuang;
    }
}
