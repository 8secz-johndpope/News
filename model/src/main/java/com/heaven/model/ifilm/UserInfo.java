package com.heaven.model.ifilm;

import java.io.Serializable;

/**
 * Created by LeoLu on 2016/12/10.
 */

public class UserInfo implements Serializable {

    /**
     * userId : 100048
     * userName : Mark
     * avatar : http://wx.qlogo.cn/mmopen/ajNVdqHZLLBXica9UiaXdFuExx1LIwmWsBLWFlNku2pPAKzpp5KXpMXhibKzNY0aj7XFvxtmvCge7B7YlI7OV2PcA/0
     * email : zhuxiwang2008@163.com
     * phone : null
     * password : e10adc3949ba59abbe56e057f20f883e
     * oldPassword : null
     * thirdUserId : null
     * openId : null
     * token : null
     * unionId : null
     * sourceFrom : null
     * enterpriseName :
     * code : null
     * weChatOpenId : null
     * weiboOpenId : null
     * qqOpenId : null
     * platform : null
     */

    private String userId;
    private String userName;
    private String avatar; //登录时用的
    private String email;
    private String phone;
    private String password;
    private String oldPassword;
    private String thirdUserId;
    private String openId;
    private String token;
    private String unionId;
    private String sourceFrom; //sourceFrom(1account,2wechat,3webo,4qq)
    private String enterpriseName;
    private String code;
    private String weChatOpenId;
    private String weiboOpenId;
    private String qqOpenId;
    private String platform;
    private String userAvator; //聊天时  拉取用的
    private boolean owner;  //是否是拥有者
    private boolean showDelete;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getThirdUserId() {
        return thirdUserId;
    }

    public void setThirdUserId(String thirdUserId) {
        this.thirdUserId = thirdUserId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWeChatOpenId() {
        return weChatOpenId;
    }

    public void setWeChatOpenId(String weChatOpenId) {
        this.weChatOpenId = weChatOpenId;
    }

    public String getWeiboOpenId() {
        return weiboOpenId;
    }

    public void setWeiboOpenId(String weiboOpenId) {
        this.weiboOpenId = weiboOpenId;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public String getUserAvator() {
        return userAvator;
    }

    public void setUserAvator(String userAvator) {
        this.userAvator = userAvator;
    }

    public boolean isShowDelete() {
        return showDelete;
    }

    public void setShowDelete(boolean showDelete) {
        this.showDelete = showDelete;
    }
}
