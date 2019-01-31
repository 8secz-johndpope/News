package com.heaven.model.ifilm;

import java.util.Date;

/**
 * Created by LeoLu on 2016/12/20.
 */

public class Material {


    /**
     * urlId : 70
     * url : www.baidu.com
     * password : null
     * materialId : null
     * userId : 100186
     * orderNo : 20161219193541579877039799
     * materialUrl : null
     * comment : null
     * materialDuration : null
     * coverUrl : null
     * materialCreateTime : null
     * materialUpdateTime : null
     * urlCreateTime : null
     * urlUpdateTime : null
     * size : null
     * fileName : null
     * bucket : null
     */

    private String urlId;
    private String url;
    private String password;
    private String materialId;
    private String userId;
    private String orderNo;
    private String materialUrl;
    private String comment;
    private Long materialDuration;
    private String coverUrl;
    private Date materialCreateTime;
    private Date materialUpdateTime;
    private Date urlCreateTime;
    private Date urlUpdateTime;
    private Long size;
    private String fileName;
    private String bucket;

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
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

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public Long getMaterialDuration() {
        return materialDuration;
    }

    public void setMaterialDuration(Long materialDuration) {
        this.materialDuration = materialDuration;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Date getMaterialCreateTime() {
        return materialCreateTime;
    }

    public void setMaterialCreateTime(Date materialCreateTime) {
        this.materialCreateTime = materialCreateTime;
    }

    public Date getMaterialUpdateTime() {
        return materialUpdateTime;
    }

    public void setMaterialUpdateTime(Date materialUpdateTime) {
        this.materialUpdateTime = materialUpdateTime;
    }

    public Date getUrlCreateTime() {
        return urlCreateTime;
    }

    public void setUrlCreateTime(Date urlCreateTime) {
        this.urlCreateTime = urlCreateTime;
    }

    public Date getUrlUpdateTime() {
        return urlUpdateTime;
    }

    public void setUrlUpdateTime(Date urlUpdateTime) {
        this.urlUpdateTime = urlUpdateTime;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
