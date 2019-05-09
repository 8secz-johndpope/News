package com.heaven.news.utils;

import com.heaven.base.ui.view.calendar.Calendar;
import com.heaven.base.ui.view.calendar.CalendarUtil;
import com.heaven.base.ui.view.calendar.LunarCalendar;

import java.util.Date;

/**
 * FileName: com.heaven.news.utils.DateUtil.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-05-09 12:12
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
public class DateUtil {

    public static Calendar getCurrentDate() {
        Date d = new Date();
        Calendar currentDate = new Calendar();
        currentDate.setYear(CalendarUtil.getDate("yyyy", d));
        currentDate.setMonth(CalendarUtil.getDate("MM", d));
        currentDate.setDay(CalendarUtil.getDate("dd", d));
        LunarCalendar.setupLunarCalendar(currentDate);
        return currentDate;
    }
}
