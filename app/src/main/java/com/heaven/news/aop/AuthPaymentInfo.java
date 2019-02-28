package com.heaven.news.aop;

/**
 * FileName: com.heaven.flybetter.aop.AuthPaymentInfo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-09-26 20:20
 *
 * @version V1.0 支付信息
 */
public class AuthPaymentInfo {
    public AuthPaymentUtil.PayResultListener mPayResultListener;
    public String appId;
    public String partnerId;
    public String signData;
    public boolean isPaySuccess;
    public String errorMsg;
}
