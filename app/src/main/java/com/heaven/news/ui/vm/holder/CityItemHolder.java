package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.heaven.news.ui.decoration.ItemDecoration;
import com.heaven.news.ui.decoration.ItemSpecialCityDecoration;
import com.heaven.news.ui.vm.model.base.CityGroup;
import com.neusoft.szair.model.city.cityListVO;

/**
 * FileName: com.heaven.news.ui.vm.holder.RouteItemHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-26 11:23
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class CityItemHolder extends BaseMultItem<cityListVO> {

    public CityItemHolder(@NonNull Class<cityListVO> cityListVO, int itemLayoutId) {
        super(cityListVO, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull cityListVO cityListVO) {
        holder.setText(R.id.city_name,cityListVO._FULL_NAME);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.onItemClickListener != null) {
                    holder.onItemClickListener.onItemClick(holder.itemView,holder,cityListVO);
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull cityListVO cityListVO, Object payload) {
//        holder.setText(R.id.city_name, city._FULL_NAME);
    }

}
