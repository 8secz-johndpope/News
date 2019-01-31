package com.heaven.model.ifilm;


import java.util.ArrayList;

/**
 * Created by LeoLu on 2016/12/10.
 */
public class FilmEditor {


    /**
     * editorId : 1
     * editorName : Mark
     * password : e10adc3949ba59abbe56e057f20f883e
     * avator : www.baidu.com
     * phone : 13478712801
     * email : null
     * wehcatNo : null
     * description : Greate Editor
     * stayAddress : null
     * editorLevel : 5
     * editorStatus : 1
     * auditComment : null
     * isFrozen : 1
     * isApprove : 1
     * editorType : null
     * cashoutPwd : null
     * cashoutRule : null
     * createTime : null
     * updateTime : 1480589939000
     * isTob : 1
     * unitPrice : 0.01
     * autoBidMessage : TRUST ME - 你好，这只是一个测试, god save iii -- test --gege
     * isAutoBid : 1
     * identityCard : null
     * lastLoginTime : 1481096032000
     * loginTimes : null
     * star : 4.5
     */

    int editorId;
    String editorName;
    String password;
    String avator;
    String phone;
    String email;
    String wehcatNo;
    String description;
    String stayAddress;
    int editorLevel;
    int editorStatus;
    String auditComment;
    int isFrozen;
    int isApprove;
    String editorType;
    String cashoutPwd;
    String cashoutRule;
    String createTime;
    long updateTime;
    int isTob;
    double unitPrice;
    String autoBidMessage;
    int isAutoBid;
    String identityCard;
    long lastLoginTime;
    String loginTimes;
    double star;
    ArrayList<EditorAbilityExList> editorAbilityExList;
    String isCollect; //0 代表未收藏  1 代表已收藏

    public FilmEditor() {


    }

    public FilmEditor(int editorId, String editorName, String password, String avator,
                      String phone, String email, String wehcatNo, String description,
                      String stayAddress, int editorLevel, int editorStatus, String auditComment,
                      int isFrozen, int isApprove, String editorType, String cashoutPwd,
                      String cashoutRule, String createTime, long updateTime, int isTob,
                      double unitPrice, String autoBidMessage, int isAutoBid, String identityCard,
                      long lastLoginTime, String loginTimes, double star, ArrayList<EditorAbilityExList> editorAbilityExList,
                      String isCollect) {
        this.editorId = editorId;
        this.editorName = editorName;
        this.password = password;
        this.avator = avator;
        this.phone = phone;
        this.email = email;
        this.wehcatNo = wehcatNo;
        this.description = description;
        this.stayAddress = stayAddress;
        this.editorLevel = editorLevel;
        this.editorStatus = editorStatus;
        this.auditComment = auditComment;
        this.isFrozen = isFrozen;
        this.isApprove = isApprove;
        this.editorType = editorType;
        this.cashoutPwd = cashoutPwd;
        this.cashoutRule = cashoutRule;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isTob = isTob;
        this.unitPrice = unitPrice;
        this.autoBidMessage = autoBidMessage;
        this.isAutoBid = isAutoBid;
        this.identityCard = identityCard;
        this.lastLoginTime = lastLoginTime;
        this.loginTimes = loginTimes;
        this.star = star;
        this.editorAbilityExList = editorAbilityExList;
        this.isCollect = isCollect;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWehcatNo() {
        return wehcatNo;
    }

    public void setWehcatNo(String wehcatNo) {
        this.wehcatNo = wehcatNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStayAddress() {
        return stayAddress;
    }

    public void setStayAddress(String stayAddress) {
        this.stayAddress = stayAddress;
    }

    public int getEditorLevel() {
        return editorLevel;
    }

    public void setEditorLevel(int editorLevel) {
        this.editorLevel = editorLevel;
    }

    public int getEditorStatus() {
        return editorStatus;
    }

    public void setEditorStatus(int editorStatus) {
        this.editorStatus = editorStatus;
    }

    public String getAuditComment() {
        return auditComment;
    }

    public void setAuditComment(String auditComment) {
        this.auditComment = auditComment;
    }

    public int getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(int isFrozen) {
        this.isFrozen = isFrozen;
    }

    public int getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(int isApprove) {
        this.isApprove = isApprove;
    }

    public String getEditorType() {
        return editorType;
    }

    public void setEditorType(String editorType) {
        this.editorType = editorType;
    }

    public String getCashoutPwd() {
        return cashoutPwd;
    }

    public void setCashoutPwd(String cashoutPwd) {
        this.cashoutPwd = cashoutPwd;
    }

    public String getCashoutRule() {
        return cashoutRule;
    }

    public void setCashoutRule(String cashoutRule) {
        this.cashoutRule = cashoutRule;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsTob() {
        return isTob;
    }

    public void setIsTob(int isTob) {
        this.isTob = isTob;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getAutoBidMessage() {
        return autoBidMessage;
    }

    public void setAutoBidMessage(String autoBidMessage) {
        this.autoBidMessage = autoBidMessage;
    }

    public int getIsAutoBid() {
        return isAutoBid;
    }

    public void setIsAutoBid(int isAutoBid) {
        this.isAutoBid = isAutoBid;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(String loginTimes) {
        this.loginTimes = loginTimes;
    }

    public double getStar() {

        return Math.round(star);
    }

    public void setStar(double star) {
        this.star = star;
    }

    public ArrayList<EditorAbilityExList> getEditorAbilityExList() {
        return editorAbilityExList;
    }

    public void setEditorAbilityExList(ArrayList<EditorAbilityExList> editorAbilityExList) {
        this.editorAbilityExList = editorAbilityExList;
    }

    public String getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
    }
}
