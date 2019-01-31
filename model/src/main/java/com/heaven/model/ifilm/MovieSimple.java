package com.heaven.model.ifilm;


import android.view.View;
import android.widget.Toast;


/**
 * Created by LeoLu on 2016/12/9.
 */
public class MovieSimple {


    /**
     * caseId : 479
     * sourceId : 0
     * source : 0
     * likes : 0
     * dislikes : 0
     * reportCount : 0
     * reportMessage : null
     * coverUrl : http://ifilmo-thumb.oss-cn-hongkong.aliyuncs.com/5YYcrkiGY8_1481355234210_1.jpg
     * createTime : 1481355260000
     * updateTime : null
     * reportTime : null
     * isDelete : 1
     * editorName : 格格
     * editorId : 14
     * editorAvatar : http://hk-editoravatar.oss-cn-hongkong.aliyuncs.com/QwBsiEicZS.jpg
     * isCollect : 0
     * category : 6
     * movieTitle : 作品2
     * movieDuration : 15
     * videoId : http://hk-editorsample.oss-cn-hongkong.aliyuncs.com/5YYcrkiGY8.MP4
     * userId : 0
     * thumpUp : 0
     * thumpDown : 0
     * filmTheme : 0
     * coverPageUrl : http://ifilmo-thumb.oss-cn-hongkong.aliyuncs.com/5YYcrkiGY8_1481355234210_1.jpg
     * sampleUrl : http://hk-editorsample.oss-cn-hongkong.aliyuncs.com/5YYcrkiGY8.MP4
     * editorRate : null
     * referencePrice : 1000
     * sampleCategoryCode : 6
     */

    int caseId;
    String sourceId;
    String source;
    String likes;
    int dislikes;
    String reportCount;
    String reportMessage;
    String coverUrl;
    String createTime;
    String updateTime;
    String reportTime;
    String isDelete;
    String editorName;
    int editorId;
    String editorAvatar;
    String isCollect; //0 代表未收藏  1 代表已收藏
    String category;
    String categoryName;
    String movieTitle;
    String movieDuration;
    String videoId;
    String userId;
    int thumpUp;
    int thumpDown;
    String filmTheme;
    String coverPageUrl;
    String sampleUrl;
    String editorRate;
    String referencePrice;
    int sampleCategoryCode;
    float star;

    public boolean isVideoOn;
    public MovieSimple() {

    }

    public MovieSimple(int caseId, String sourceId, String source, String likes, int dislikes, String reportCount, String reportMessage, String coverUrl, String createTime, String updateTime, String reportTime, String isDelete, String editorName, int editorId, String editorAvatar, String isCollect, String category, String categoryName, String movieTitle, String movieDuration, String videoId, String userId, int thumpUp, int thumpDown, String filmTheme, String coverPageUrl, String sampleUrl, String editorRate, String referencePrice, int sampleCategoryCode, float star) {
        this.caseId = caseId;
        this.sourceId = sourceId;
        this.source = source;
        this.likes = likes;
        this.dislikes = dislikes;
        this.reportCount = reportCount;
        this.reportMessage = reportMessage;
        this.coverUrl = coverUrl;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.reportTime = reportTime;
        this.isDelete = isDelete;
        this.editorName = editorName;
        this.editorId = editorId;
        this.editorAvatar = editorAvatar;
        this.isCollect = isCollect;
        this.category = category;
        this.categoryName = categoryName;
        this.movieTitle = movieTitle;
        this.movieDuration = movieDuration;
        this.videoId = videoId;
        this.userId = userId;
        this.thumpUp = thumpUp;
        this.thumpDown = thumpDown;
        this.filmTheme = filmTheme;
        this.coverPageUrl = coverPageUrl;
        this.sampleUrl = sampleUrl;
        this.editorRate = editorRate;
        this.referencePrice = referencePrice;
        this.sampleCategoryCode = sampleCategoryCode;
        this.star = star;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getReportCount() {
        return reportCount;
    }

    public void setReportCount(String reportCount) {
        this.reportCount = reportCount;
    }

    public String getReportMessage() {
        return reportMessage;
    }

    public void setReportMessage(String reportMessage) {
        this.reportMessage = reportMessage;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public int getEditorId() {
        return editorId;
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }

    public String getEditorAvatar() {
        return editorAvatar;
    }

    public void setEditorAvatar(String editorAvatar) {
        this.editorAvatar = editorAvatar;
    }

    public String getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getThumpUp() {
        return thumpUp;
    }

    public void setThumpUp(int thumpUp) {
        this.thumpUp = thumpUp;
    }

    public int getThumpDown() {
        return thumpDown;
    }

    public void setThumpDown(int thumpDown) {
        this.thumpDown = thumpDown;
    }

    public String getFilmTheme() {
        return filmTheme;
    }

    public void setFilmTheme(String filmTheme) {
        this.filmTheme = filmTheme;
    }

    public String getCoverPageUrl() {
        return coverPageUrl;
    }

    public void setCoverPageUrl(String coverPageUrl) {
        this.coverPageUrl = coverPageUrl;
    }

    public String getSampleUrl() {
        return sampleUrl;
    }

    public void setSampleUrl(String sampleUrl) {
        this.sampleUrl = sampleUrl;
    }

    public String getEditorRate() {
        return editorRate;
    }

    public void setEditorRate(String editorRate) {
        this.editorRate = editorRate;
    }

    public String getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(String referencePrice) {
        this.referencePrice = referencePrice;
    }

    public int getSampleCategoryCode() {
        return sampleCategoryCode;
    }

    public void setSampleCategoryCode(int sampleCategoryCode) {
        this.sampleCategoryCode = sampleCategoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public float getStar() {

        return Math.round(star);
    }

    public void setStar(float star) {
        this.star = star;
    }

    //在这个实体中添加一个点击时间的方法
    public void onClick(View view){
        Toast.makeText(view.getContext(),"已点击",Toast.LENGTH_SHORT).show();
    }

    @Override
    public String toString() {
        return "MovieSimple{" +
                "caseId=" + caseId +
                ", sourceId='" + sourceId + '\'' +
                ", source='" + source + '\'' +
                ", likes='" + likes + '\'' +
                ", dislikes=" + dislikes +
                ", reportCount='" + reportCount + '\'' +
                ", reportMessage='" + reportMessage + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", reportTime='" + reportTime + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", editorName='" + editorName + '\'' +
                ", editorId=" + editorId +
                ", editorAvatar='" + editorAvatar + '\'' +
                ", isCollect='" + isCollect + '\'' +
                ", category='" + category + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieDuration='" + movieDuration + '\'' +
                ", videoId='" + videoId + '\'' +
                ", userId='" + userId + '\'' +
                ", thumpUp=" + thumpUp +
                ", thumpDown=" + thumpDown +
                ", filmTheme='" + filmTheme + '\'' +
                ", coverPageUrl='" + coverPageUrl + '\'' +
                ", sampleUrl='" + sampleUrl + '\'' +
                ", editorRate='" + editorRate + '\'' +
                ", referencePrice='" + referencePrice + '\'' +
                ", sampleCategoryCode=" + sampleCategoryCode +
                ", star=" + star +
                '}';
    }
}
