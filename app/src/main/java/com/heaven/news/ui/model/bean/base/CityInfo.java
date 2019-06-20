package com.heaven.news.ui.model.bean.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.heaven.news.ui.vm.model.base.CityInfo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-05-09 19:43
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class CityInfo implements Serializable {
    public List<String> chIndexs = new ArrayList<>();
    public List<String> enIndexs = new ArrayList<>();
    public List<CityGroup> chCityGroups = new ArrayList<>();
    public List<CityGroup> enCityGroups = new ArrayList<>();

    public void clearData() {
        chIndexs.clear();
        enIndexs.clear();
        chCityGroups.clear();
        enCityGroups.clear();
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "chIndexs=" + chIndexs +
                ", enIndexs=" + enIndexs +
                ", chCityGroups=" + chCityGroups +
                ", enCityGroups=" + enCityGroups +
                '}';
    }
}
