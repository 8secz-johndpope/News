package com.heaven.model.ifilm;

/**
 * Created by LeoLu on 2017/1/5.
 */

public class ServiceType {


    /**
     * serviceId : 21
     * serviceName : 3D
     * serviceDescription : 实现三维立体效果，增强视频的空间感
     * biddingOrder : 1
     * comboOrder : null
     * customOrder : null
     * serviceStatus : 1
     * createTime : 1475996566000
     * updateTime : null
     * iconName : 3D%401.5.png
     * iconNameColor : 3D_color%401.5.png
     */

    private int serviceId;
    private String serviceName;
    private String serviceDescription;
    private String biddingOrder;
    private Object comboOrder;
    private Object customOrder;
    private int serviceStatus;
    private long createTime;
    private long updateTime;
    private String iconName;
    private String iconNameColor;
    private int serviceTypeId;
    private String demoUrl;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getBiddingOrder() {
        return biddingOrder;
    }

    public void setBiddingOrder(String biddingOrder) {
        this.biddingOrder = biddingOrder;
    }

    public Object getComboOrder() {
        return comboOrder;
    }

    public void setComboOrder(Object comboOrder) {
        this.comboOrder = comboOrder;
    }

    public Object getCustomOrder() {
        return customOrder;
    }

    public void setCustomOrder(Object customOrder) {
        this.customOrder = customOrder;
    }

    public int getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(int serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getIconNameColor() {
        return iconNameColor;
    }

    public void setIconNameColor(String iconNameColor) {
        this.iconNameColor = iconNameColor;
    }

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public void setDemoUrl(String demoUrl) {
        this.demoUrl = demoUrl;
    }
}
