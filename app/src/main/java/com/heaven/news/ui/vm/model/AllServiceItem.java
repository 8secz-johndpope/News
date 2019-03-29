package com.heaven.news.ui.vm.model;

import java.util.ArrayList;

/**
 * FileName: com.heaven.news.ui.vm.model.AllServiceItem.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-28 17:47
 *
 * @version V1.0 首页 易行 凤凰知音服务
 */
public class AllServiceItem {
    public ArrayList<HomeServiceItem> homeServiceInfos;
    public ArrayList<ServiceItem> easyGoServiceInfos;
    public ArrayList<ServiceItem> phoenixServiceInfos;

    @Override
    public String toString() {
        return "AllServiceItem{" +
                "homeServiceInfos=" + homeServiceInfos +
                ", easyGoServiceInfos=" + easyGoServiceInfos +
                ", phoenixServiceInfos=" + phoenixServiceInfos +
                '}';
    }
}
