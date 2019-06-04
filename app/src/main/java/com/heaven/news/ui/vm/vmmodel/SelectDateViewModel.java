package com.heaven.news.ui.vm.vmmodel;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.view.calendar.FestivalDay;
import com.heaven.base.ui.view.calendar.FestivalDayGroup;
import com.heaven.base.ui.view.calendar.Month;
import com.heaven.news.BuildConfig;
import com.heaven.news.api.Api;
import com.heaven.news.api.ConfigApi;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.vm.model.base.CalendarPriceInfo;
import com.heaven.news.ui.vm.model.base.CityGroup;
import com.heaven.news.ui.vm.model.base.ConfigData;
import com.heaven.news.utils.RxRepUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.flowables.GroupedFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * FileName: com.heaven.news.ui.vm.vmmodel.SelectDateViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-05-07 20:09
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class SelectDateViewModel extends AbstractViewModel {
    private HashMap<String, FestivalDayGroup> festivalGroupMap = new HashMap<>();

    @Override
    public void initModel() {

    }

    public void getCalendarPrice(String depCity,String orgCity,String date) {
        ConfigData configData = AppEngine.instance().confManager().loadConfigData();
        if(configData != null && !TextUtils.isEmpty(configData.jgrlurl)) {
            String priceUrl = configData.jgrlurl;
            if(priceUrl.contains("?")) {
                priceUrl = priceUrl + "&dptcity=" + depCity + "&arrcity=" + orgCity + "&dptdate=" + date;
            } else {
                priceUrl = priceUrl + "?dptcity=" + depCity + "&arrcity=" + orgCity + "&dptdate=" + date;
            }

            priceUrl = "https://mobile.shenzhenair.com/develop/szairMobileWS/FetchCalendarPriceServlet?m=getCalendarPrice&dptcity=SZX&arrcity=PEK&dptdate=2019-06-04";

            RxRepUtils.getConfigResult(AppEngine.instance().api().getApi(BuildConfig.CONFIG_URL, ConfigApi.class).getCalenarPrice(priceUrl), new Consumer<String>() {
                @Override
                public void accept(String jsonstr) throws Exception {
                    if(!TextUtils.isEmpty(jsonstr)) {
                        int index = jsonstr.indexOf("jsoncallback(");
                        if(index != -1){
                            jsonstr = jsonstr.replace("jsoncallback(", "").replace(");", "");
                        }
                        ArrayList<CalendarPriceInfo> CalendarPriceInfoList= (ArrayList<CalendarPriceInfo>) JSONObject.parseArray(jsonstr, CalendarPriceInfo.class);
                        Logger.i("getCalendarPrice--" + jsonstr);
                    }
                }
            });
        }
    }


    private class FestivalGroupConsume implements Consumer<FestivalDay> {
        private String yearMonth;
        private int year;
        private int month;

        FestivalGroupConsume(String date) {
            if (!TextUtils.isEmpty(date) && date.contains("-")) {
                yearMonth = date.substring(0,date.lastIndexOf("-"));
                String[] dates = date.split("-");
                try {
                    year = Integer.parseInt(dates[0]);
                    month = Integer.parseInt(dates[1]);
                } catch (Exception e) {
                    Logger.i(e.getMessage());
                }
            }
        }

        @Override
        public void accept(FestivalDay festivalDay) throws Exception {
            festivalDay.year = year;
            festivalDay.month = month;
            if (festivalGroupMap.containsKey(yearMonth)) {
                FestivalDayGroup festivalDayGroup = festivalGroupMap.get(yearMonth);
                if (festivalDayGroup != null) {
                    festivalDayGroup.addFestivalDay(festivalDay);
                }
            } else {
                FestivalDayGroup festivalDayGroup = new FestivalDayGroup();
                festivalDayGroup.date = yearMonth;
                festivalDayGroup.addFestivalDay(festivalDay);
                festivalGroupMap.put(yearMonth, festivalDayGroup);
            }
        }
    }
}
