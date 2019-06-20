package com.heaven.news.ui.model.holder;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.ui.view.calendar.Month;
import com.heaven.news.R;

/**
 * FileName: com.heaven.news.ui.vm.holder.RouteItemHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-26 11:23
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class CalendarTitleItemHolder extends BaseMultItem<Month> {

    public CalendarTitleItemHolder(@NonNull Class<Month> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull Month month) {
        TextView normal = holder.getView(R.id.day_mouth);
        normal.setText(String.valueOf(month.title));
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
