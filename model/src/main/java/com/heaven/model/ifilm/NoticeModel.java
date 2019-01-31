package com.heaven.model.ifilm;

/**
 * Created by LeoLu on 2017/2/6.
 */

public class NoticeModel {
    /**
     * editorId : 100000001
     * editorName : 坤坤
     * avator : http://dadfadfasdfadfaf
     * bidPrice : 0.01
     * orderNo : 12345678912354
     */

    private int editorId;
    private String editorName;
    private String avator;
    private float bidPrice;
    private String orderNo;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
