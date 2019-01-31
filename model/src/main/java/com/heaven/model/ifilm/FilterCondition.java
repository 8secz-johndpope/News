package com.heaven.model.ifilm;


/**
 * Created by LeoLu on 2017/1/10.
 */
public class FilterCondition {

    String serviceIds;
    String minMoney;
    String maxMoney;
    String isBookmark;
    String orderType;

    public FilterCondition() {
    }

    public FilterCondition(String serviceIds, String minMoney, String maxMoney, String isBookmark, String orderType) {
        this.serviceIds = serviceIds;
        this.minMoney = minMoney;
        this.maxMoney = maxMoney;
        this.isBookmark = isBookmark;
        this.orderType = orderType;
    }

    public String getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(String serviceIds) {
        this.serviceIds = serviceIds;
    }

    public String getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(String minMoney) {
        this.minMoney = minMoney;
    }

    public String getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(String maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String isBookmark() {
        return isBookmark;
    }

    public void setBookmark(String bookmark) {
        isBookmark = bookmark;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
