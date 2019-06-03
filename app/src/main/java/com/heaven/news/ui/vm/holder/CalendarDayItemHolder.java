package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.TextView;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.ui.view.calendar.Calendar;
import com.heaven.news.R;

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
        if(!TextUtils.isEmpty(calendar.getHoliday())) {
            holder.setVisible(R.id.holiday,true);
            holder.setText(R.id.holiday,calendar.getHoliday());
        } else {
            holder.setVisible(R.id.holiday,false);
            holder.setText(R.id.holiday,"休");
        }

        if(!TextUtils.isEmpty(calendar.getFestival())) {
            holder.setText(R.id.day_mouth,calendar.getFestival());
        } else {
            holder.setText(R.id.day_mouth,String.valueOf(calendar.getDay()));
        }
        if(calendar.isCurrentMonth()) {

            if(!TextUtils.isEmpty(calendar.getFestival())) {
                holder.setTextColor(R.id.day_mouth,R.color.colorAccent);
            } else {
                holder.setTextColor(R.id.day_mouth,R.color.black);
            }

            holder.itemView.setOnClickListener(v -> {
                if(holder.onItemClickListener != null) {
                    holder.onItemClickListener.onItemClick(v,holder,calendar);
                }
            });
        } else {
            holder.setTextColor(R.id.day_mouth,R.color.divider_light);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull Calendar calendar, Object payload) {
        if(!TextUtils.isEmpty(calendar.getHoliday())) {
            holder.setVisible(R.id.holiday,true);
            holder.setText(R.id.holiday,calendar.getHoliday());
        } else {
            holder.setVisible(R.id.holiday,false);
            holder.setText(R.id.holiday,"休");
        }

        if(!TextUtils.isEmpty(calendar.getFestival())) {
            holder.setText(R.id.day_mouth,calendar.getFestival());
        } else {
            holder.setText(R.id.day_mouth,String.valueOf(calendar.getDay()));
        }
        if(calendar.isCurrentMonth()) {
//            if(!TextUtils.isEmpty(calendar.getFestival())) {
//                holder.setTextColor(R.id.day_mouth,R.color.colorAccent);
//            } else {
//                holder.setTextColor(R.id.day_mouth,R.color.black);
//            }

            holder.itemView.setOnClickListener(v -> {
                if(holder.onItemClickListener != null) {
                    holder.onItemClickListener.onItemClick(v,holder,calendar);
                }
            });
        } else {
//            holder.setTextColor(R.id.day_mouth,R.color.divider_light);
        }
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
