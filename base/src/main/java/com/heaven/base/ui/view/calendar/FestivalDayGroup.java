package com.heaven.base.ui.view.calendar;

import android.util.ArrayMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,FestivalDay> festivalDayArrayMap = new HashMap<>();

    public void addFestivalDay(FestivalDay festival) {
        festivalDays.add(festival);
        festivalDayArrayMap.put(festival.date,festival);
    }

    public void updateCalendarFestival(List<Calendar> days) {
        if(days != null && days.size() > 0) {
            for(Calendar calendar : days) {
                String key = calendar.getFormatDate();
                if(festivalDayArrayMap.containsKey(key)) {
                    FestivalDay festival = festivalDayArrayMap.get(key);
                    if(festival != null) {
                        calendar.setFestival(festival.festival);
                        calendar.setHoliday(festival.holiday);
                    }
                }
            }
        }

    }

}
