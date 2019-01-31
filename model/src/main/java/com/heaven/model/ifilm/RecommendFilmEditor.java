package com.heaven.model.ifilm;

import java.util.List;

/**
 * Created by LeoLu on 2016/12/26.
 */

public class RecommendFilmEditor {

    /**
     * abilities : []
     * orderServiceRels : []
     * bidId : 28
     * isAuto : 1
     * editorId : 14
     * editorName : 格格
     * avator : http://hk-editoravatar.oss-cn-hongkong.aliyuncs.com/QwBsiEicZS.jpg
     * bidPrice : 40
     * star : 3
     * editorLevel : 3
     * bidMessage : 一帧一帧拼凑你的人生，我想为您留下美好记忆。是否羡慕别人拥有唯美日系写真mv，是否羡慕别人拥有温馨美好旅行记录。选择我，你将成为那个被羡慕的人，调色是我的专长，美丽的画面希望能让您留下更深的记忆。
     * historyOrders : 20
     * modifyTimes : 4
     * minProductionCycle : 0
     * maxProductionCycle : 0
     * weights : null
     * aboutMe : 一帧一帧拼凑你的人生，我想为您留下美好记忆。调色是我的专长，美丽的画面希望能让您留下更深的记忆。
     * skills : null
     * validateForBidRequirement : true
     * notValidateReason : null
     * caculationExpression : [基础价] 40.00
     * bidType : 0
     * rate : 0
     * finalRankScore : 44.66177745183012
     */

    private int bidId;
    private int isAuto;
    private int editorId;
    private String editorName;
    private String avator;
    private float bidPrice;
    private float star;
    private int editorLevel;
    private String bidMessage;
    private int historyOrders;
    private int modifyTimes;
    private int minProductionCycle;
    private int maxProductionCycle;
    private Object weights;
    private String aboutMe;
    private Object skills;
    private boolean validateForBidRequirement;
    private String notValidateReason;
    private String caculationExpression;
    private int bidType;
    private float rate;
    private double finalRankScore;
    private List<Abilities> abilities;
    private List<ServiceType> orderServiceRels;
    private int editorStatus;
    private int likeCount;
    private String couponBidPrice;

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(int isAuto) {
        this.isAuto = isAuto;
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

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public float getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(float bidPrice) {
        this.bidPrice = bidPrice;
    }

    public float getStar() {
        return Math.round(star);
    }

    public void setStar(float star) {
        this.star = star;
    }

    public int getEditorLevel() {
        return editorLevel;
    }

    public void setEditorLevel(int editorLevel) {
        this.editorLevel = editorLevel;
    }

    public String getBidMessage() {
        return bidMessage;
    }

    public void setBidMessage(String bidMessage) {
        this.bidMessage = bidMessage;
    }

    public int getHistoryOrders() {
        return historyOrders;
    }

    public void setHistoryOrders(int historyOrders) {
        this.historyOrders = historyOrders;
    }

    public int getModifyTimes() {
        return modifyTimes;
    }

    public void setModifyTimes(int modifyTimes) {
        this.modifyTimes = modifyTimes;
    }

    public int getMinProductionCycle() {
        return minProductionCycle;
    }

    public void setMinProductionCycle(int minProductionCycle) {
        this.minProductionCycle = minProductionCycle;
    }

    public int getMaxProductionCycle() {
        return maxProductionCycle;
    }

    public void setMaxProductionCycle(int maxProductionCycle) {
        this.maxProductionCycle = maxProductionCycle;
    }

    public Object getWeights() {
        return weights;
    }

    public void setWeights(Object weights) {
        this.weights = weights;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Object getSkills() {
        return skills;
    }

    public void setSkills(Object skills) {
        this.skills = skills;
    }

    public boolean isValidateForBidRequirement() {
        return validateForBidRequirement;
    }

    public void setValidateForBidRequirement(boolean validateForBidRequirement) {
        this.validateForBidRequirement = validateForBidRequirement;
    }

    public String getNotValidateReason() {
        return notValidateReason;
    }

    public void setNotValidateReason(String notValidateReason) {
        this.notValidateReason = notValidateReason;
    }

    public String getCaculationExpression() {
        return caculationExpression;
    }

    public void setCaculationExpression(String caculationExpression) {
        this.caculationExpression = caculationExpression;
    }

    public int getBidType() {
        return bidType;
    }

    public void setBidType(int bidType) {
        this.bidType = bidType;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public double getFinalRankScore() {
        return finalRankScore;
    }

    public void setFinalRankScore(double finalRankScore) {
        this.finalRankScore = finalRankScore;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = abilities;
    }

    public List<ServiceType> getOrderServiceRels() {
        return orderServiceRels;
    }

    public void setOrderServiceRels(List<ServiceType> orderServiceRels) {
        this.orderServiceRels = orderServiceRels;
    }

    public int getEditorStatus() {
        return editorStatus;
    }

    public void setEditorStatus(int editorStatus) {
        this.editorStatus = editorStatus;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getCouponBidPrice() {
        return couponBidPrice;
    }

    public void setCouponBidPrice(String couponBidPrice) {
        this.couponBidPrice = couponBidPrice;
    }
}
