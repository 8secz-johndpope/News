package com.heaven.model.ifilm;

import java.util.List;

/**
 * Created by LeoLu on 2017/1/10.
 */

public class GroupChat {

    /**
     * orderNo : 20170104201104958909598312
     * title : 朱希望测试
     * orderReq : null
     * editorId : 17
     * editorName : 吴世东
     * avator : http://hk-editoravatar.oss-cn-hongkong.aliyuncs.com/Z2aw7H3WX4.jpg
     * users : [{"userId":100222,"userName":"陆景强","userAvator":"","owner":true},{"userId":100048,"userName":"Mark","userAvator":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLBXica9UiaXdFuExx1LIwmWsBLWFlNku2pPAKzpp5KXpMXhibKzNY0aj7XFvxtmvCge7B7YlI7OV2PcA/0","owner":false},{"userId":100219,"userName":"执念","userAvator":"http://wx.qlogo.cn/mmopen/vaOGI2Rw1gh1PtyO2wbZSSG2vNicqhRKNWKIW3icaiaWYib9WfYNQP5TxlnR6vN7Bsla64GiaDgdj41DXqSmtwtUMPgnWm8RZicEoD/0","owner":false},{"userId":100211,"userName":"兆熙","userAvator":"http://wx.qlogo.cn/mmopen/H2HBxiaIKia2GquvicHN28zsmnboLQ16jL4Ir7Sz1aUH8XybGCEzD0xXTpCQoSYQ97oR4RVm2wevLKR6WOeiaeia3Xo2jXdLoqE0r/0","owner":false}]
     */

    private String orderNo;
    private String title;
    private String orderReq;
    private String otherReq;
    private int editorId;
    private String editorName;
    private String avator;
    private List<UserInfo> users;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrderReq() {
        return orderReq;
    }

    public void setOrderReq(String orderReq) {
        this.orderReq = orderReq;
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

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    public String getOtherReq() {
        return otherReq;
    }

    public void setOtherReq(String otherReq) {
        this.otherReq = otherReq;
    }
}
