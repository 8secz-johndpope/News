package com.heaven.news.ui.vm.model;

import java.io.Serializable;

/**
 * FileName: com.heaven.news.ui.vm.model.UserLoginInfo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-09 01:05
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class UserLoginInfo implements Serializable {
    public String userCount;
    public String userPwd;
    public String userId;                       //用户

    public String giftType;                    //是否有礼包 礼包类型：新人礼包1，生日礼包0，没有礼包是空
    public String CRM_CARD_NUMBER;                       //常旅客卡号
    public String groupCode;                   //大客户码
    public String _group_flag = "1";//大客户的小红点
    public String idNumber;                    //证件号码
    public String identifyType;                //用户认证类型
    public String _CLKCRM_ID;                   //凤凰知音认证

    @Override
    public String toString() {
        return "UserLoginInfo{" +
                "userCount='" + userCount + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userId='" + userId + '\'' +
                ", giftType='" + giftType + '\'' +
                ", CRM_CARD_NUMBER='" + CRM_CARD_NUMBER + '\'' +
                ", groupCode='" + groupCode + '\'' +
                ", _group_flag='" + _group_flag + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", identifyType='" + identifyType + '\'' +
                ", _CLKCRM_ID='" + _CLKCRM_ID + '\'' +
                '}';
    }
}
