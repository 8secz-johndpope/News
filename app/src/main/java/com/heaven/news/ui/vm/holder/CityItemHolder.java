package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.decoration.ItemDecoration;
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

    public CityItemHolder(@NonNull Class<cityListVO> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull cityListVO city) {
        RecyclerView specialRecycler =  holder.getView(R.id.special_citys);
        TextView normal =  holder.getView(R.id.city_name);
        if(city.groupFlag == 2 || city.groupFlag == 3) {
            specialRecycler.setVisibility(View.VISIBLE);
            normal.setVisibility(View.GONE);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.context, 3);
            specialRecycler.setLayoutManager(gridLayoutManager);
//            specialRecycler.addItemDecoration(new ItemDecoration(ScreenUtil.dip2px(holder.context, 5)));
            BaseAdapter<cityListVO> routeAdapter = new BaseAdapter<>(holder.context);
            routeAdapter.register(new CitySpecialItemHolder(cityListVO.class, R.layout.city_special_item));
            specialRecycler.setAdapter(routeAdapter);
            if(city.specialCitys != null) {
                routeAdapter.updateItems(city.specialCitys);
            }

        }else {
            specialRecycler.setVisibility(View.GONE);
            normal.setVisibility(View.VISIBLE);
            holder.setText(R.id.city_name,city._FULL_NAME);
            holder.setOnClickListener(R.id.city_name, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppEngine.instance().confManager().saveCityOften(city);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull cityListVO city, Object payload) {
        holder.setText(R.id.city_name,city._FULL_NAME);
    }

}
