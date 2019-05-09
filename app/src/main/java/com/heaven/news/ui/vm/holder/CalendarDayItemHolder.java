package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.ui.view.calendar.Calendar;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.decoration.ItemSpecialCityDecoration;
import com.neusoft.szair.model.city.cityListVO;

/**
 * FileName: com.heaven.news.ui.vm.holder.RouteItemHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-26 11:23
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class CalendarDayItemHolder extends BaseMultItem<Calendar> {

    public CalendarDayItemHolder(@NonNull Class<Calendar> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull Calendar calendar) {
        TextView normal = holder.getView(R.id.day_mouth);
        normal.setText(String.valueOf(calendar.getDay()));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull Calendar calendar, Object payload) {
        TextView normal = holder.getView(R.id.day_mouth);
        normal.setText(String.valueOf(calendar.getDay()));
    }

    @Override
    public boolean isItemSame(Calendar oldItem, Calendar newItem) {
        return oldItem.compareTo(newItem) == 0;
    }

    @Override
    public boolean isContentSame(Calendar oldItem, Calendar newItem) {
        return oldItem.compareTo(newItem) == 0;
    }

    @Override
    public int getSpanSize(int spanCount) {
        return 1;
    }

    @Override
    public int getMaxRecycleCount() {
        return 50;
    }
}