package com.heaven.news.vm.model;

/**
 * FileName: com.heaven.news.vm.model.UserLoginInfo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-09 01:05
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class UserLoginInfo {
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
}
