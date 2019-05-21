package com.heaven.base.ui.view.calendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.heaven.base.ui.view.calendar.FestivalDay.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-05-09 12:36
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
public class FestivalDayGroup implements Serializable {


    private static final long serialVersionUID = -2683489258210930053L;

    public String date;
    public List<FestivalDay> festivalDays = new ArrayList<>();

    public void addFestivalDay(FestivalDay festival) {
        festivalDays.add(festival);
    }

}
