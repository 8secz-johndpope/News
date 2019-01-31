package com.heaven.model.ifilm;

import java.util.List;

/**
 * Created by LeoLu on 2017/1/19.
 */

public class WxUserInfo {


    /**
     * openid : o9nsDwASyJZh4akRCSUiayJOuZdI
     * nickname : Leo
     * sex : 1
     * language : zh_CN
     * city : Dalian
     * province : Liaoning
     * country : CN
     * headimgurl : http://wx.qlogo.cn/mmopen/H2HBxiaIKia2GAEvYh6JRSVVD4DnFghggvuiaibD0KibMC5Ieib4yhTNUJoez0n2dPUHd68Pic7fZx3MXD6Jhias6vnKNJAUUvwPSMGia/0
     * privilege : []
     * unionid : oDC81wxAcUHgUx4flm-hvkHdw7cI
     */

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<?> privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }
}
