package com.heaven.news.ui.model.bean.base;

import com.neusoft.szair.model.city.cityListVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.heaven.news.ui.vm.model.base.CityGroup.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-05-09 16:45
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class CityGroup implements Serializable {
    public boolean isTitle;

    public boolean isFirstInGroup = true;

    public boolean isLastInGroup = true;

    public String groupTitle = null;

    public String groupTitleEn = null;

    public int groupFlag = 0;//0:正常城市 1:当前城市 2:常用城市 3：热门城市

    public ArrayList<cityListVO> citysList = new ArrayList<>();

    public void addCity(cityListVO city) {
        citysList.add(city);
    }

    public void addCityAll(List<cityListVO> citys) {
        citysList.addAll(citys);
    }
}
