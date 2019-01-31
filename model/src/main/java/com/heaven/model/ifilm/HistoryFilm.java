package com.heaven.model.ifilm;


/**
 * Created by LeoLu on 2017/1/4.
 */
public class HistoryFilm {


    /**
     * sampleId : 29
     * orderNo : 20161010173910101603331118
     * editorId : 2
     * storagePlatform : 2
     * coverPageUrl : http://ifilmo-thumb.oss-cn-hongkong.aliyuncs.com/XpK2wpBAmA_1478402806732_1.jpg
     * sampleStoragePlatform : null
     * sampleUrl : http://hk-editorsample.oss-cn-hongkong.aliyuncs.com/XpK2wpBAmA.mp4
     * title : Demo
     * sampleFormat : video/mp4
     * smapleDuration : 30
     * sampleSize : 1249656
     * sampleVersion : 0
     * sampleStatus : 0
     * sampleFeedback : null
     * playTimes : null
     * createTime : 1478402809000
     * updateTime : null
     * isVisible : null
     * isFinal : null
     * isShare : 1
     * isDelete : null
     * latestSampleId : 29
     * editorName : 小幸运
     * editorAvatar : http://hk-editoravatar.oss-cn-hongkong.aliyuncs.com/itnPGySMZE.jpg
     * userName : Mark
     * userId : 100048
     * userAvatar : http://wx.qlogo.cn/mmopen/ajNVdqHZLLBXica9UiaXdFuExx1LIwmWsBLWFlNku2pPAKzpp5KXpMXhibKzNY0aj7XFvxtmvCge7B7YlI7OV2PcA/0
     * statusShow : 验收中
     * orderTitle : 22323
     * fileSize4M : 1.19MB
     * orderSubType : null
     * createTimeStr : null
     */

    int sampleId;
    String orderNo;
    int editorId;
    int storagePlatform;
    String coverPageUrl;
    String sampleStoragePlatform;
    String sampleUrl;
    String title;
    String sampleFormat;
    int smapleDuration;
    int sampleSize;
    String sampleVersion;
    int sampleStatus;
    String sampleFeedback;
    int playTimes;
    long createTime;
    long updateTime;
    String isVisible;
    String isFinal;
    int isShare;
    String isDelete;
    int latestSampleId;
    String editorName;
    String editorAvatar;
    String userName;
    int userId;
    String userAvatar;
    String statusShow;
    String orderTitle;
    String fileSize4M;
    String orderSubType;
    String createTimeStr;

    public HistoryFilm() {

    }

    public HistoryFilm(int sampleId, String orderNo, int editorId, int storagePlatform, String coverPageUrl, String sampleStoragePlatform, String sampleUrl, String title, String sampleFormat, int smapleDuration, int sampleSize, String sampleVersion, int sampleStatus, String sampleFeedback, int playTimes, long createTime, long updateTime, String isVisible, String isFinal, int isShare, String isDelete, int latestSampleId, String editorName, String editorAvatar, String userName, int userId, String userAvatar, String statusShow, String orderTitle, String fileSize4M, String orderSubType, String createTimeStr) {
        this.sampleId = sampleId;
        this.orderNo = orderNo;
        this.editorId = editorId;
        this.storagePlatform = storagePlatform;
        this.coverPageUrl = coverPageUrl;
        this.sampleStoragePlatform = sampleStoragePlatform;
        this.sampleUrl = sampleUrl;
        this.title = title;
        this.sampleFormat = sampleFormat;
        this.smapleDuration = smapleDuration;
        this.sampleSize = sampleSize;
        this.sampleVersion = sampleVersion;
        this.sampleStatus = sampleStatus;
        this.sampleFeedback = sampleFeedback;
        this.playTimes = playTimes;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isVisible = isVisible;
        this.isFinal = isFinal;
        this.isShare = isShare;
        this.isDelete = isDelete;
        this.latestSampleId = latestSampleId;
        this.editorName = editorName;
        this.editorAvatar = editorAvatar;
        this.userName = userName;
        this.userId = userId;
        this.userAvatar = userAvatar;
        this.statusShow = statusShow;
        this.orderTitle = orderTitle;
        this.fileSize4M = fileSize4M;
        this.orderSubType = orderSubType;
        this.createTimeStr = createTimeStr;
    }

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getEditorId() {
        return editorId;
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }

    public int getStoragePlatform() {
        return storagePlatform;
    }

    public void setStoragePlatform(int storagePlatform) {
        this.storagePlatform = storagePlatform;
    }

    public String getCoverPageUrl() {
        return coverPageUrl;
    }

    public void setCoverPageUrl(String coverPageUrl) {
        this.coverPageUrl = coverPageUrl;
    }

    public String getSampleStoragePlatform() {
        return sampleStoragePlatform;
    }

    public void setSampleStoragePlatform(String sampleStoragePlatform) {
        this.sampleStoragePlatform = sampleStoragePlatform;
    }

    public String getSampleUrl() {
        return sampleUrl;
    }

    public void setSampleUrl(String sampleUrl) {
        this.sampleUrl = sampleUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSampleFormat() {
        return sampleFormat;
    }

    public void setSampleFormat(String sampleFormat) {
        this.sampleFormat = sampleFormat;
    }

    public int getSmapleDuration() {
        return smapleDuration;
    }

    public void setSmapleDuration(int smapleDuration) {
        this.smapleDuration = smapleDuration;
    }

    public int getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(int sampleSize) {
        this.sampleSize = sampleSize;
    }

    public String getSampleVersion() {
        return sampleVersion;
    }

    public void setSampleVersion(String sampleVersion) {
        this.sampleVersion = sampleVersion;
    }

    public int getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(int sampleStatus) {
        this.sampleStatus = sampleStatus;
    }

    public String getSampleFeedback() {
        return sampleFeedback;
    }

    public void setSampleFeedback(String sampleFeedback) {
        this.sampleFeedback = sampleFeedback;
    }

    public int getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(int playTimes) {
        this.playTimes = playTimes;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(String isVisible) {
        this.isVisible = isVisible;
    }

    public String getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(String isFinal) {
        this.isFinal = isFinal;
    }

    public int getIsShare() {
        return isShare;
    }

    public void setIsShare(int isShare) {
        this.isShare = isShare;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public int getLatestSampleId() {
        return latestSampleId;
    }

    public void setLatestSampleId(int latestSampleId) {
        this.latestSampleId = latestSampleId;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public String getEditorAvatar() {
        return editorAvatar;
    }

    public void setEditorAvatar(String editorAvatar) {
        this.editorAvatar = editorAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getStatusShow() {
        return statusShow;
    }

    public void setStatusShow(String statusShow) {
        this.statusShow = statusShow;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getFileSize4M() {
        return fileSize4M;
    }

    public void setFileSize4M(String fileSize4M) {
        this.fileSize4M = fileSize4M;
    }

    public String getOrderSubType() {
        return orderSubType;
    }

    public void setOrderSubType(String orderSubType) {
        this.orderSubType = orderSubType;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}
