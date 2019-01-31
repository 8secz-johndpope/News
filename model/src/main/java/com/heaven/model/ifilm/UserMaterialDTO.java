package com.heaven.model.ifilm;

/**
 * Created by heaven on 2016/12/24.
 */

public class UserMaterialDTO {
    private Integer urlId;  //云盘ID
    private Integer userId; //用户ID
    private String orderNo; //订单编号
    private String url;//上传云盘的url
    private String password;  //云盘链接密码
    private Integer materialId; //素材ID
    private String materialUrl; //素材链接URL
    private String comment;
    private Integer materialDuration;
    private String coverUrl;
    private String materialCreateTime;
    private String materialUpdateTime;
    private String urlCreateTime;
    private long size;
    private String materialType;
    private String preferContent;


    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialUrl() {
        return materialUrl;
    }

    public void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getMaterialDuration() {
        return materialDuration;
    }

    public void setMaterialDuration(Integer materialDuration) {
        this.materialDuration = materialDuration;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getMaterialCreateTime() {
        return materialCreateTime;
    }

    public void setMaterialCreateTime(String materialCreateTime) {
        this.materialCreateTime = materialCreateTime;
    }

    public String getMaterialUpdateTime() {
        return materialUpdateTime;
    }

    public void setMaterialUpdateTime(String materialUpdateTime) {
        this.materialUpdateTime = materialUpdateTime;
    }

    public String getUrlCreateTime() {
        return urlCreateTime;
    }

    public void setUrlCreateTime(String urlCreateTime) {
        this.urlCreateTime = urlCreateTime;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getPreferContent() {
        return preferContent;
    }

    public void setPreferContent(String preferContent) {
        this.preferContent = preferContent;
    }
}
