package com.heaven.model.ifilm;

/**
 * Created by LeoLu on 2017/1/10.
 */

public class WechatPay {


    /**
     * packageValue : Sign=WXPay
     * appid : wx4ed7a40bfad0f804
     * sign : 8C2E7BC2A4BCB768BA9752D57507C329
     * partnerid : 1360104302
     * prepayid : wx20170112110656a20c06765f0385428219
     * noncestr : 5K8264ILTKCH16CQ2502SI8ZNMTM67VS
     * timestamp : 1484190416676
     */

    private String packageValue;
    private String appid;
    private String sign;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
