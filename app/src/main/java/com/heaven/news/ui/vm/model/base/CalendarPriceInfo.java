package com.heaven.news.ui.vm.model.base;

import java.io.Serializable;

/**
 * Created by liuzhiwen on 2017/2/3.
 */

public class CalendarPriceInfo implements Serializable {

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
}
