package com.heaven.base.ui.view.calendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.heaven.base.ui.view.calendar.Month.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-05-08 14:10
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class Month implements Serializable {
    public String title;
    public int year;
    public int month;

    public List<Calendar> days = new ArrayList<>();

    public void addDayInMonth(List<Calendar> dayList) {
        days.clear();
        days.addAll(dayList);
    }
}
