package com.heaven.news.ui.vm.vmmodel;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.view.calendar.FestivalDay;
import com.heaven.base.ui.view.calendar.FestivalDayGroup;
import com.heaven.base.ui.view.calendar.Month;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.vm.model.base.CityGroup;

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

    public void updateDateFestival(BaseAdapter<Month> monthAdapter, List<Month> months) {
        List<FestivalDay> festivalDays = AppEngine.instance().confManager().getFestival();
        if(festivalDays != null && festivalDays.size() > 0 && months != null && months.size() > 0) {
            final Disposable subscribe = Flowable.fromIterable(festivalDays).groupBy(festivalDay -> festivalDay.date.substring(0,festivalDay.date.lastIndexOf("-"))).subscribe(festivalDayGroupedFlowable -> {
                final Disposable subscribe1 = festivalDayGroupedFlowable.subscribe(new FestivalGroupConsume(festivalDayGroupedFlowable.getKey()));
            });

            Flowable.fromIterable(months).subscribe(new Consumer<Month>() {
                @Override
                public void accept(Month month) throws Exception {

                }
            });
        }
    }


    private class FestivalGroupConsume implements Consumer<FestivalDay>{
        private String date;

        public FestivalGroupConsume(String date) {
            this.date = date;
        }

        @Override
        public void accept(FestivalDay festivalDay) throws Exception {
            if (festivalGroupMap.containsKey(date)) {
                FestivalDayGroup festivalDayGroup = festivalGroupMap.get(date);
                if (festivalDayGroup != null) {
                    festivalDayGroup.addFestivalDay(festivalDay);
                }
            } else {
                FestivalDayGroup festivalDayGroup = new FestivalDayGroup();
                festivalDayGroup.date = date;
                festivalDayGroup.addFestivalDay(festivalDay);
                festivalGroupMap.put(date, festivalDayGroup);
            }
        }
    }
}
