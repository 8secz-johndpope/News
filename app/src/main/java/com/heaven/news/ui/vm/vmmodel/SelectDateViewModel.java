package com.heaven.news.ui.vm.vmmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.UiThread;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.view.calendar.Calendar;
import com.heaven.base.ui.view.calendar.Month;
import com.heaven.data.net.DataResponse;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.vm.model.base.CalendarPriceInfo;
import com.heaven.news.ui.vm.model.base.ConfigData;
import com.neusoft.szair.model.flightproto.FlightSearchDomesticResultVO;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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
    public final MutableLiveData<List<Integer>> targetListLive = new MutableLiveData<>();
    @Override
    public void initModel() {

    }

    private void getCalendarPrice(List<Month> months,String depCity,String orgCity,String date) {
        ConfigData configData = AppEngine.instance().confManager().loadConfigData();
        if(configData != null && !TextUtils.isEmpty(configData.jgrlurl)) {
            String priceUrl = configData.jgrlurl;
            if(priceUrl.contains("?")) {
                priceUrl = priceUrl + "&dptcity=" + depCity + "&arrcity=" + orgCity + "&dptdate=" + date;
            } else {
                priceUrl = priceUrl + "?dptcity=" + depCity + "&arrcity=" + orgCity + "&dptdate=" + date;
            }

            priceUrl = "https://mobile.shenzhenair.com/develop/szairMobileWS/FetchCalendarPriceServlet?m=getCalendarPrice&dptcity=SZX&arrcity=PEK&dptdate=2019-06-25";

            mNetManager.getResultInThred(mApi.getCalenarPrice(priceUrl), dataResponse -> {
                if(!TextUtils.isEmpty(dataResponse.data)) {
                    int index = dataResponse.data.indexOf("jsoncallback(");
                    if(index != -1){
                        dataResponse.data = dataResponse.data.replace("jsoncallback(", "").replace(");", "");
                    }
                    ArrayList<CalendarPriceInfo> calendarpriceinfolist = (ArrayList<CalendarPriceInfo>) JSONObject.parseArray(dataResponse.data, CalendarPriceInfo.class);
                    mergeCalendarPrice(months,calendarpriceinfolist );
                    Logger.i("getCalendarPrice--" + dataResponse.data);
                }
            });
        }
    }


    @SuppressLint("CheckResult")
    private void mergeCalendarPrice(List<Month> months,ArrayList<CalendarPriceInfo> priceInfos) {
        priceGroupMap.clear();
        if(priceInfos != null && priceInfos.size() > 0) {
            final Disposable subscribe = Flowable.fromIterable(priceInfos).groupBy(CalendarPriceInfo::getFlightDate).subscribe(calendarPriceInfoGroupedFlowable -> {
                final Disposable subscribe1 = calendarPriceInfoGroupedFlowable.subscribe(new PriceGroupConsume(calendarPriceInfoGroupedFlowable.getKey()));
            });
            if(months != null && months.size() > 0 && priceGroupMap.size() > 0) {
                int index = 0;
                List<Integer> targetList = new ArrayList<>();
                for(Month month : months) {
                    String key = month.getFormatDate();
                    if(priceGroupMap.containsKey(key)) {
                        CalendarPriceGroup calendarPriceGroup =  priceGroupMap.get(key);
                        if(calendarPriceGroup != null) {
                            calendarPriceGroup.updateCalendarPrice(month);
                        }
                        targetList.add(index);
                    }
                    index++;
                }
                if(targetList.size() > 0) {
                    targetListLive.postValue(targetList);
                }
            }

        }
    }

    public void updatePrice(Observer<List<Integer>> observer,List<Month> months, String depCity, String orgCity, String date) {
        targetListLive.observe(owner,observer);
        getCalendarPrice(months,"","","");
    }

    private class CalendarPriceGroup{
        public String date;
        Map<String,CalendarPriceInfo> priceMap = new HashMap<>();

        private CalendarPriceInfo lowestPrice;

        void addMonthPrice(CalendarPriceInfo calendarPriceInfo) {
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

        void updateCalendarPrice(Month month) {
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
                            calendar.price = "￥"+priceInfo.getLowerPrice();
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
