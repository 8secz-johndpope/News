package com.heaven.news.ui.model.bean.base;

import java.util.ArrayList;

/**
 * FileName: com.heaven.news.ui.vm.model.base.HomeService.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-28 17:47
 *
 * @version V1.0 首页 易行 凤凰知音服务
 */
public class HomeService {
    public ArrayList<ServiceInfo> homeServiceInfos;
    public ArrayList<ServiceItem> homeRecommendInfos;
    public ArrayList<ServiceItem> homeEasyGoInfos;

    @Override
    public String toString() {
        return "HomeService{" +
                "serviceItems=" + homeServiceInfos +
                ", homeRecommendInfos=" + homeRecommendInfos +
                ", homeEasyGoInfos=" + homeEasyGoInfos +
                '}';
    }
}
