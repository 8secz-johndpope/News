package com.heaven.news.ui.vm.vmmodel;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.view.calendar.Calendar;
import com.heaven.base.ui.view.calendar.FestivalDay;
import com.heaven.base.ui.view.calendar.FestivalDayGroup;
import com.heaven.base.ui.view.calendar.Month;
import com.heaven.news.BuildConfig;
import com.heaven.news.api.ConfigApi;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.vm.model.base.CalendarPriceInfo;
import com.heaven.news.ui.vm.model.base.ConfigData;
import com.heaven.news.utils.RxRepUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private HashMap<String, CalendarPriceGroup> priceGroupMap = new HashMap<>();

    @Override
    public void initModel() {

    }

    private void getCalendarPrice(BaseAdapter<Month> adapter,String depCity,String orgCity,String date) {
        ConfigData configData = AppEngine.instance().confManager().loadConfigData();
        if(configData != null && !TextUtils.isEmpty(configData.jgrlurl)) {
            String priceUrl = configData.jgrlurl;
            if(priceUrl.contains("?")) {
                priceUrl = priceUrl + "&dptcity=" + depCity + "&arrcity=" + orgCity + "&dptdate=" + date;
            } else {
                priceUrl = priceUrl + "?dptcity=" + depCity + "&arrcity=" + orgCity + "&dptdate=" + date;
            }

            priceUrl = "https://mobile.shenzhenair.com/develop/szairMobileWS/FetchCalendarPriceServlet?m=getCalendarPrice&dptcity=SZX&arrcity=PEK&dptdate=2019-06-05";

            RxRepUtils.getConfigResult(AppEngine.instance().api().getApi(BuildConfig.CONFIG_URL, ConfigApi.class).getCalenarPrice(priceUrl), new Consumer<String>() {
                @Override
                public void accept(String jsonstr) throws Exception {
                    if(!TextUtils.isEmpty(jsonstr)) {
                        int index = jsonstr.indexOf("jsoncallback(");
                        if(index != -1){
                            jsonstr = jsonstr.replace("jsoncallback(", "").replace(");", "");
                        }
                        ArrayList<CalendarPriceInfo> calendarpriceinfolist = (ArrayList<CalendarPriceInfo>) JSONObject.parseArray(jsonstr, CalendarPriceInfo.class);
                        mergeCalendarPrice(adapter,calendarpriceinfolist );
                        Logger.i("getCalendarPrice--" + jsonstr);
                    }
                }
            });
        }
    }


    @SuppressLint("CheckResult")
    private void mergeCalendarPrice(BaseAdapter<Month> adapter,ArrayList<CalendarPriceInfo> priceInfos) {
        priceGroupMap.clear();
        if(priceInfos != null && priceInfos.size() > 0) {
            final Disposable subscribe = Flowable.fromIterable(priceInfos).groupBy(CalendarPriceInfo::getFlightDate).subscribe(calendarPriceInfoGroupedFlowable -> {
                final Disposable subscribe1 = calendarPriceInfoGroupedFlowable.subscribe(new PriceGroupConsume(calendarPriceInfoGroupedFlowable.getKey()));
            });
            List<Month> months = adapter.getDataList();
            if(months != null && months.size() > 0 && priceGroupMap.size() > 0) {
                int index = 0;
                for(Month month : months) {
                    String key = month.getFormatDate();
                    if(priceGroupMap.containsKey(key)) {
                        CalendarPriceGroup calendarPriceGroup =  priceGroupMap.get(key);
                        if(calendarPriceGroup != null) {
                            calendarPriceGroup.updateCalendarPrice(month);
                        }
                        adapter.notifyItemChanged(index);
                    }
                    index++;
                }
            }

        }
    }

    public void updatePrice(BaseAdapter<Month> adapter,String depCity,String orgCity,String date) {
        getCalendarPrice(adapter,"","","");
    }

    private class CalendarPriceGroup{
        public String date;
        public Map<String,CalendarPriceInfo> priceMap = new HashMap<>();

        private CalendarPriceInfo lowestPrice;

        public void addMonthPrice(CalendarPriceInfo calendarPriceInfo) {
            priceMap.put(calendarPriceInfo.getFlightDate(),calendarPriceInfo);
            if(lowestPrice != null) {
                int lowestIntPrice = TextUtils.isEmpty(lowestPrice.getLowerPrice())? 0 : Integer.parseInt(lowestPrice.getLowerPrice());
                int newIntPrice = TextUtils.isEmpty(calendarPriceInfo.getLowerPrice())? 0 : Integer.parseInt(calendarPriceInfo.getLowerPrice());
                if(lowestIntPrice > newIntPrice)  {
                    lowestPrice = calendarPriceInfo;
                }
            } else {
                lowestPrice = calendarPriceInfo;
            }
        }

        public void updateCalendarPrice(Month month) {
            if(month != null && month.days != null && month.days.size() > 0 && priceMap.size() > 0) {
                int lowestIntPrice = 0;
                if(lowestPrice != null) {
                    lowestIntPrice = TextUtils.isEmpty(lowestPrice.getLowerPrice())? 0 : Integer.parseInt(lowestPrice.getLowerPrice());
                    lowestPrice.isMinPrice = true;
                }
                for(Calendar calendar : month.days) {
                    String key = calendar.getFormatDate();
                    if(priceMap.containsKey(key)) {
                        CalendarPriceInfo priceInfo =  priceMap.get(key);
                        if(priceInfo != null) {
                            int currentIntPrice = TextUtils.isEmpty(priceInfo.getLowerPrice())? 0 : Integer.parseInt(priceInfo.getLowerPrice());
                            calendar.price = priceInfo.getLowerPrice();
                            if(lowestIntPrice == currentIntPrice) {
                                calendar.isLowestPrice = true;
                            }
                        }
                    } else {
                        if(calendar.isCurrentMonth()) {
                            calendar.price = "查看";
                        }
                    }
                }
            }

        }
    }

    private class PriceGroupConsume implements Consumer<CalendarPriceInfo> {
        private String yearMonth;

        PriceGroupConsume(String date) {
            if (!TextUtils.isEmpty(date) && date.contains("-")) {
                yearMonth = date.substring(0,date.lastIndexOf("-"));
            }
        }

        @Override
        public void accept(CalendarPriceInfo priceInfo) throws Exception {
            if (priceGroupMap.containsKey(yearMonth)) {
                CalendarPriceGroup group = priceGroupMap.get(yearMonth);
                if (group != null) {
                    group.addMonthPrice(priceInfo);
                }
            } else {
                CalendarPriceGroup group = new CalendarPriceGroup();
                group.addMonthPrice(priceInfo);
                priceGroupMap.put(yearMonth, group);
            }
        }
    }
}
