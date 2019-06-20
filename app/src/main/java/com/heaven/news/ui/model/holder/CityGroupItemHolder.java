package com.heaven.news.ui.model.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.ui.decoration.ItemSpecialCityDecoration;
import com.heaven.news.ui.decoration.VerticalDecoration;
import com.heaven.news.ui.model.bean.base.CityGroup;
import com.neusoft.szair.model.city.cityListVO;

/**
 * FileName: com.heaven.news.ui.vm.holder.RouteItemHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-26 11:23
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class CityGroupItemHolder extends BaseMultItem<CityGroup> {

    public CityGroupItemHolder(@NonNull Class<CityGroup> cityGroup, int itemLayoutId) {
        super(cityGroup, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull CityGroup cityGroup) {
        RecyclerView cityGroupSpecialRecycler = holder.getView(R.id.city_group_special_list);
        RecyclerView cityGroupRecycler = holder.getView(R.id.city_group_list);
        TextView normal = holder.getView(R.id.city_name);
        if (cityGroup.groupFlag == 1 || cityGroup.groupFlag == 2 || cityGroup.groupFlag == 3) {
            cityGroupSpecialRecycler.setVisibility(View.VISIBLE);
            cityGroupRecycler.setVisibility(View.GONE);

            if(cityGroupSpecialRecycler.getAdapter() == null) {
                BaseAdapter<cityListVO> routeAdapter = new BaseAdapter<>(holder.context);
                cityGroupSpecialRecycler.setAdapter(routeAdapter);
                routeAdapter.register(new CitySpecialItemHolder(cityListVO.class, R.layout.city_special_item));
                cityGroupSpecialRecycler.setLayoutManager(new GridLayoutManager(holder.context, 3));
                cityGroupSpecialRecycler.addItemDecoration(new ItemSpecialCityDecoration(3, ScreenUtil.dip2px(holder.context, 5)));
                routeAdapter.groupPosition = holder.getItemPosition();
                if(holder.onItemClickListener != null) {
                    routeAdapter.setOnItemClickListener(holder.onItemClickListener);
                }

                if(cityGroup.citysList != null) {
                    routeAdapter.updateItems(cityGroup.citysList);
                }
            } else {
                if(cityGroup.citysList != null) {
                    BaseAdapter<cityListVO> adapter = (BaseAdapter<cityListVO>)cityGroupSpecialRecycler.getAdapter();
                    adapter.groupPosition = holder.getItemPosition();
                    adapter.updateItems(cityGroup.citysList);
                }
            }
        } else {
            cityGroupSpecialRecycler.setVisibility(View.GONE);
            cityGroupRecycler.setVisibility(View.VISIBLE);
            if(cityGroupRecycler.getAdapter() == null) {
                BaseAdapter<cityListVO> routeAdapter = new BaseAdapter<>(holder.context);
                cityGroupRecycler.setAdapter(routeAdapter);
                routeAdapter.register(new CityItemHolder(cityListVO.class, R.layout.city_item));
                cityGroupRecycler.setLayoutManager(new LinearLayoutManager(holder.context));
                cityGroupRecycler.addItemDecoration(new VerticalDecoration(holder.context,1));
                routeAdapter.groupPosition = holder.getItemPosition();
                if(holder.onItemClickListener != null) {
                    routeAdapter.setOnItemClickListener(holder.onItemClickListener);
                }
                if(cityGroup.citysList != null) {
                    routeAdapter.updateItems(cityGroup.citysList);
                }
            } else {
                if(cityGroup.citysList != null) {
                    BaseAdapter<cityListVO> adapter = (BaseAdapter<cityListVO>)cityGroupRecycler.getAdapter();
                    adapter.groupPosition = holder.getItemPosition();
                    adapter.updateItems(cityGroup.citysList);
                }
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull CityGroup cityGroup, Object payload) {
//        holder.setText(R.id.city_name, city._FULL_NAME);
    }

}
