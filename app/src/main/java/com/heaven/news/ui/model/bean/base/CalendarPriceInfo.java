package com.heaven.news.ui.model.bean.base;

import java.io.Serializable;

/**
 * Created by liuzhiwen on 2017/2/3.
 */

public class CalendarPriceInfo implements Serializable,Comparable<CalendarPriceInfo> {

    private String flightDate;

    private String lowerPrice;

    private String airLine;

    public boolean isMinPrice;

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getLowerPrice() {
        return lowerPrice;
    }

    public void setLowerPrice(String lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    public String getAirLine() {
        return airLine;
    }

    public void setAirLine(String airLine) {
        this.airLine = airLine;
    }

    /**
     * 比较日期
     *
     * @param calendar 日期
     * @return -1 0 1
     */
    @Override
    public int compareTo(CalendarPriceInfo calendarPriceInfo) {
        if (calendarPriceInfo == null) {
            return 1;
        }
        return flightDate.compareTo(calendarPriceInfo.flightDate);
    }
}
