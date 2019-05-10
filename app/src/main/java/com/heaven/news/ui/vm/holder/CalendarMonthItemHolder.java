package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.ui.view.calendar.Calendar;
import com.heaven.base.ui.view.calendar.Month;
import com.heaven.news.R;
import com.neusoft.szair.model.city.cityListVO;
import com.orhanobut.logger.Logger;

/**
 * FileName: com.heaven.news.ui.vm.holder.RouteItemHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-26 11:23
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class CalendarMonthItemHolder extends BaseMultItem<Month> {

    public CalendarMonthItemHolder(@NonNull Class<Month> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull Month month) {
        RecyclerView daysList = holder.getView(R.id.days_list);
        if(daysList.getAdapter() == null) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.context, 7);
            daysList.setLayoutManager(gridLayoutManager);
            BaseAdapter<Calendar> routeAdapter = new BaseAdapter<>(holder.context,month.days);
            routeAdapter.register(new CalendarDayItemHolder(Calendar.class, R.layout.calendar_item));
            routeAdapter.groupPosition = holder.getItemPosition();
            if(holder.onItemClickListener != null) {
                routeAdapter.setOnItemClickListener(holder.onItemClickListener);
            }
            daysList.setAdapter(routeAdapter);
            Logger.i("onBindViewHolder--" + "create_new");
        } else {
            if(daysList.getAdapter() instanceof BaseAdapter) {
                BaseAdapter<Calendar> adapter = (BaseAdapter<Calendar>) daysList.getAdapter();
                adapter.groupPosition = holder.getItemPosition();
                adapter.updateItems(month.days);
                Logger.i("onBindViewHolder--" + "refresh");
            }
        }

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull Month month, Object payload) {
        TextView normal = holder.getView(R.id.day_mouth);
        normal.setText(String.valueOf(month.title));
    }

    @Override
    public int getSpanSize(int spanCount) {
        return 7;
    }

    @Override
    public int getMaxRecycleCount() {
        return 10;
    }
}
