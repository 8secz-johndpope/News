package com.heaven.model.ifilm;

/**
 * Created by LeoLu on 2016/12/28.
 */

public class FilmComment {

    /**
     * commentId : 78
     * caseId : 54
     * userId : 100075
     * comment : 是这个评论不
     * isDeleted : 1
     * createTime : 2016-12-23 10:33:16
     * deleteTime : null
     * userName : 吴老东
     * userAvatar : http://hk-userportrait.oss-cn-hongkong.aliyuncs.com/643530a78ab12ae0ace15c53127d687d
     * platform : null
     * sampleType : 2
     */

    private String commentId;
    private int caseId;
    private int userId;
    private String comment;
    private int isDeleted;
    private String createTime;
    private String deleteTime;
    private String userName;
    private String userAvatar;
    private String platform;
    private int sampleType;
    private int liveTime;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getSampleType() {
        return sampleType;
    }

    public void setSampleType(int sampleType) {
        this.sampleType = sampleType;
    }

    public int getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(int liveTime) {
        this.liveTime = liveTime;
    }
}
