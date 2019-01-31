package com.heaven.model.ifilm;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LeoLu on 2016/12/9.
 */
public class OrderItem {


    /**
     * orderNo : 20161209110822193729961162
     * userId : 100183
     * filmTitle : 和我
     * filmTheme : 2
     * filmThemeTitle : 亲子
     * cycle : 2
     * message : null
     * editorId : 17
     * editorName : 吴世东
     * editorAvatar : http://hk-editoravatar.oss-cn-hongkong.aliyuncs.com/Z2aw7H3WX4.jpg
     * orderStatus : 5
     * coverUrl : http://hk-usermaterial.oss-cn-hongkong.aliyuncs.com/d8ed22e3bd682b3709270077bcff27b3.PNG
     * totalMaterialDuration : 6000
     * totalMaterialQuantity : 2
     * price : 0.01
     * createTime : 1481252902000
     * updateTime : 1481252913000
     * commitSampleTimes : 0
     * isAnimatic : null
     * paymentTime : null
     * finishTime : null
     * isUpload : null
     * latestSampleId : null
     * unreadMessageCount : null
     * sampleCoverPageUrl : null
     * materialNumber : null
     * materialUrlsNumber : null
     * orderSampleList : null
     */

    String orderNo;  //订单编号
    String userId;   //用户ID
    String filmTitle;  //影片名称
    int filmTheme;   //影片类型
    String filmThemeTitle;  //影片类型文字
    int cycle;     //影片周期(比如4天完成)
    String message;
    int editorId;
    String editorName;
    float editorStar;
    String editorAvatar;
    String isCollect; //0 代表未收藏  1 代表已收藏
    int orderStatus;  //订单状态 1：保存订单   2：提交    3：已支付  4: 剪辑师待确认  5:剪辑师已确认开始执行 6：上传样稿 7：验收通过  11：反向预留
    String coverUrl;
    Long totalMaterialDuration;
    Long totalMaterialQuantity;
    double price;
    String userBudget; //后来修改的 预算
    Long createTime;
    Long updateTime;
    int commitSampleTimes;
    int isAnimatic; //是否同意成为爱影样片
    Long paymentTime;
    Long finishTime;
    int isUpload;  //是否允许上传／素材
    int latestSampleId;
    int unreadMessageCount;
    String sampleCoverPageUrl;
    int modifyTimes;
    int materialNumber;
    int materialUrlsNumber;
    ArrayList<HistoryFilm> orderSampleList;
    int picNum;  //图片数量
    long capacity;//素材总大小
    boolean hasFriend;
    boolean share;
    long videoLength;  //视频时长 7000 毫秒
    String videoNum;  //视频个数
    String leadTime; //交付时间
    int budget; //影片预算
    int filmTime; //成片时长
    String alipayInfo;//支付宝支付信息
    ArrayList<HashMap<String, String>> serviceRels; //增值服务id 集合
    String commentStatus;
    String sampleUrl;
    String userName;
    ArrayList<Integer> editorStyle; //剪辑风格
    String userPhone; //联系方式
    String userEmail;//邮箱
    ArrayList<Integer> materialFrom;//素材来源
    ArrayList<Integer> serviceTypes;//增值服务
    ArrayList<Integer> moviePurpose; //影片目的
    ArrayList<Integer> distribution; //发行渠道
    ArrayList<String> resourceLink; //参考资料

    ArrayList<HashMap<String, String>> preferences;          //剪辑风格 relevanceId 返回的 dictCode    preferenceName
    ArrayList<HashMap<String, String>> materialSource;   //素材来源 relevanceId 返回的 dictCode    preferenceName
    ArrayList<HashMap<String, String>> distributionChannels;  //发行渠道 relevanceId 返回的 dictCode   preferenceName
    ArrayList<HashMap<String, String>> filmPurpose;    //影片目的 relevanceId 返回的 dictCode   preferenceName
    ArrayList<HashMap<String, String>> orderResourceLinks; //参考资料 linkUrl

    public OrderItem() {
    }

    public OrderItem(String orderNo, String userId, String filmTitle, Integer filmTheme, String filmThemeTitle, Integer cycle,
                     String message, String userBudget, int editorId, String editorName, String editorAvatar, Integer orderStatus, String coverUrl,
                     Long totalMaterialDuration, Long totalMaterialQuantity, double price, Long createTime, Long updateTime, String isCollect,
                     Integer commitSampleTimes, Integer isAnimatic, Long paymentTime, Long finishTime, Integer isUpload, Integer latestSampleId,
                     Integer unreadMessageCount, String sampleCoverPageUrl, Integer modifyTimes, Integer materialNumber, Integer materialUrlsNumber,
                     ArrayList<HistoryFilm> orderSampleList, int picNum, long capacity, boolean hasFriend, long videoLength, String leadTime, Integer budget,
                     Integer filmTime, String alipayInfo, ArrayList<HashMap<String, String>> serviceRels, String commentStatus, String sampleUrl, String userName,
                     ArrayList<Integer> editorStyle, String userPhone, String userEmail, ArrayList<Integer> materialFrom, ArrayList<Integer> serviceTypes,
                     ArrayList<Integer> moviePurpose, String videoNum, ArrayList<Integer> distribution, ArrayList<String> resourceLink, ArrayList<HashMap<String, String>> preferences,
                     ArrayList<HashMap<String, String>> materialSource, ArrayList<HashMap<String, String>> distributionChannels, float editorStar,
                     ArrayList<HashMap<String, String>> filmPurpose,boolean share, ArrayList<HashMap<String, String>> orderResourceLinks) {
        this.orderNo = orderNo;
        this.userId = userId;
        this.filmTitle = filmTitle;
        this.filmTheme = filmTheme;
        this.filmThemeTitle = filmThemeTitle;
        this.cycle = cycle;
        this.message = message;
        this.editorId = editorId;
        this.editorName = editorName;
        this.editorAvatar = editorAvatar;
        this.orderStatus = orderStatus;
        this.coverUrl = coverUrl;
        this.totalMaterialDuration = totalMaterialDuration;
        this.totalMaterialQuantity = totalMaterialQuantity;
        this.price = price;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.videoNum = videoNum;
        this.commitSampleTimes = commitSampleTimes;
        this.isAnimatic = isAnimatic;
        this.paymentTime = paymentTime;
        this.isCollect = isCollect;
        this.finishTime = finishTime;
        this.isUpload = isUpload;
        this.latestSampleId = latestSampleId;
        this.unreadMessageCount = unreadMessageCount;
        this.sampleCoverPageUrl = sampleCoverPageUrl;
        this.modifyTimes = modifyTimes;
        this.materialNumber = materialNumber;
        this.materialUrlsNumber = materialUrlsNumber;
        this.editorStar = editorStar;
        this.orderSampleList = orderSampleList;
        this.picNum = picNum;
        this.capacity = capacity;
        this.hasFriend = hasFriend;
        this.videoLength = videoLength;
        this.leadTime = leadTime;
        this.budget = budget;
        this.filmTime = filmTime;
        this.alipayInfo = alipayInfo;
        this.serviceRels = serviceRels;
        this.commentStatus = commentStatus;
        this.userBudget = userBudget;
        this.sampleUrl = sampleUrl;
        this.userName = userName;
        this.editorStyle = editorStyle;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.materialFrom = materialFrom;
        this.serviceTypes = serviceTypes;
        this.moviePurpose = moviePurpose;
        this.distribution = distribution;
        this.resourceLink = resourceLink;
        this.preferences = preferences;
        this.materialSource = materialSource;
        this.distributionChannels = distributionChannels;
        this.filmPurpose = filmPurpose;
        this.orderResourceLinks = orderResourceLinks;
        this.share=share;
    }

    public int getPicNum() {
        return picNum;
    }

    public void setPicNum(int picNum) {
        this.picNum = picNum;
    }

    public boolean isHasFriend() {
        return hasFriend;
    }

    public void setHasFriend(boolean hasFriend) {
        this.hasFriend = hasFriend;
    }

    public long getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(long videoLength) {
        this.videoLength = videoLength;
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

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public Integer getFilmTheme() {
        return filmTheme;
    }

    public void setFilmTheme(Integer filmTheme) {
        this.filmTheme = filmTheme;
    }

    public String getFilmThemeTitle() {
        return filmThemeTitle;
    }

    public void setFilmThemeTitle(String filmThemeTitle) {
        this.filmThemeTitle = filmThemeTitle;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getEditorId() {
        return editorId;
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
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

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Long getTotalMaterialDuration() {
        return totalMaterialDuration;
    }

    public void setTotalMaterialDuration(Long totalMaterialDuration) {
        this.totalMaterialDuration = totalMaterialDuration;
    }

    public Long getTotalMaterialQuantity() {
        return totalMaterialQuantity;
    }

    public void setTotalMaterialQuantity(Long totalMaterialQuantity) {
        this.totalMaterialQuantity = totalMaterialQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCommitSampleTimes() {
        return commitSampleTimes;
    }

    public void setCommitSampleTimes(Integer commitSampleTimes) {
        this.commitSampleTimes = commitSampleTimes;
    }

    public Integer getIsAnimatic() {
        return isAnimatic;
    }

    public void setIsAnimatic(Integer isAnimatic) {
        this.isAnimatic = isAnimatic;
    }

    public Long getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Long paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(Integer isUpload) {
        this.isUpload = isUpload;
    }

    public Integer getLatestSampleId() {
        return latestSampleId;
    }

    public void setLatestSampleId(Integer latestSampleId) {
        this.latestSampleId = latestSampleId;
    }

    public Integer getUnreadMessageCount() {
        return unreadMessageCount;
    }

    public void setUnreadMessageCount(Integer unreadMessageCount) {
        this.unreadMessageCount = unreadMessageCount;
    }

    public String getSampleCoverPageUrl() {
        return sampleCoverPageUrl;
    }

    public void setSampleCoverPageUrl(String sampleCoverPageUrl) {
        this.sampleCoverPageUrl = sampleCoverPageUrl;
    }

    public Integer getModifyTimes() {
        return modifyTimes;
    }

    public void setModifyTimes(Integer modifyTimes) {
        this.modifyTimes = modifyTimes;
    }

    public Integer getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(Integer materialNumber) {
        this.materialNumber = materialNumber;
    }

    public Integer getMaterialUrlsNumber() {
        return materialUrlsNumber;
    }

    public void setMaterialUrlsNumber(Integer materialUrlsNumber) {
        this.materialUrlsNumber = materialUrlsNumber;
    }

    public ArrayList<HistoryFilm> getOrderSampleList() {
        return orderSampleList;
    }

    public void setOrderSampleList(ArrayList<HistoryFilm> orderSampleList) {
        this.orderSampleList = orderSampleList;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getFilmTime() {
        return filmTime;
    }

    public void setFilmTime(Integer filmTime) {
        this.filmTime = filmTime;
    }

    public String getAlipayInfo() {
        return alipayInfo;
    }

    public void setAlipayInfo(String alipayInfo) {
        this.alipayInfo = alipayInfo;
    }

    public ArrayList<HashMap<String, String>> getServiceRels() {
        return serviceRels;
    }

    public void setServiceRels(ArrayList<HashMap<String, String>> serviceRels) {
        this.serviceRels = serviceRels;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getSampleUrl() {
        return sampleUrl;
    }

    public void setSampleUrl(String sampleUrl) {
        this.sampleUrl = sampleUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Integer> getEditorStyle() {
        return editorStyle;
    }

    public void setEditorStyle(ArrayList<Integer> editorStyle) {
        this.editorStyle = editorStyle;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public ArrayList<Integer> getMaterialFrom() {
        return materialFrom;
    }

    public void setMaterialFrom(ArrayList<Integer> materialFrom) {
        this.materialFrom = materialFrom;
    }

    public ArrayList<Integer> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(ArrayList<Integer> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public ArrayList<Integer> getMoviePurpose() {
        return moviePurpose;
    }

    public void setMoviePurpose(ArrayList<Integer> moviePurpose) {
        this.moviePurpose = moviePurpose;
    }

    public ArrayList<Integer> getDistribution() {
        return distribution;
    }

    public void setDistribution(ArrayList<Integer> distribution) {
        this.distribution = distribution;
    }

    public ArrayList<String> getResourceLink() {
        return resourceLink;
    }

    public void setResourceLink(ArrayList<String> resourceLink) {
        this.resourceLink = resourceLink;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public ArrayList<HashMap<String, String>> getPreferences() {
        return preferences;
    }

    public void setPreferences(ArrayList<HashMap<String, String>> preferences) {
        this.preferences = preferences;
    }

    public ArrayList<HashMap<String, String>> getMaterialSource() {
        return materialSource;
    }

    public void setMaterialSource(ArrayList<HashMap<String, String>> materialSource) {
        this.materialSource = materialSource;
    }

    public ArrayList<HashMap<String, String>> getDistributionChannels() {
        return distributionChannels;
    }

    public void setDistributionChannels(ArrayList<HashMap<String, String>> distributionChannels) {
        this.distributionChannels = distributionChannels;
    }

    public ArrayList<HashMap<String, String>> getFilmPurpose() {
        return filmPurpose;
    }

    public void setFilmPurpose(ArrayList<HashMap<String, String>> filmPurpose) {
        this.filmPurpose = filmPurpose;
    }

    public ArrayList<HashMap<String, String>> getOrderResourceLinks() {
        return orderResourceLinks;
    }

    public void setOrderResourceLinks(ArrayList<HashMap<String, String>> orderResourceLinks) {
        this.orderResourceLinks = orderResourceLinks;
    }

    public float getEditorStar() {
        return editorStar;
    }

    public void setEditorStar(float editorStar) {
        this.editorStar = editorStar;
    }

    public String getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
    }

    public String getUserBudget() {
        return userBudget;
    }

    public void setUserBudget(String userBudget) {
        this.userBudget = userBudget;
    }

    public String getVideoNum() {
        return videoNum;
    }

    public void setVideoNum(String videoNum) {
        this.videoNum = videoNum;
    }

    public void setShare(boolean share){
        this.share=share;
    }

    public boolean getShare(){
        return share;
    }

}
