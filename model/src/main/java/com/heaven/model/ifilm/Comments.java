package com.heaven.model.ifilm;


/**
 * Created by LeoLu on 2016/12/10.
 */

public class Comments {


    /**
     * commentId : 3
     * orderNo : 1111111
     * userId : 100001
     * userName : MarkUpdate
     * editorId : 1
     * star : 5
     * checkbox : -1
     * comment : very very good!!
     * status : 1
     * createTime : 1475572420000
     * deleteTime : null
     * userAvatar : http://wx.qlogo.cn/mmopen/vaOGI2Rw1gju1KIxkvp7PvJ6rM0Hicnms6EcEK6CHed0IgKpFt6xzHZ1cblGicSbnibW5AOn2nvW83IFibTl885frjpicUdIBO65R/0
     */

    private String commentId;
    private String orderNo;
    private String userId;
    private String userName;
    private String editorId;
    private String star;
    private String checkbox;
    private String comment;
    private String status;
    private Long createTime;
    private Long deleteTime;
    private String userAvatar;
    private String isShare;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEditorId() {
        return editorId;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getIsShare() {
        return isShare;
    }

    public void setIsShare(String isShare) {
        this.isShare = isShare;
    }
}
