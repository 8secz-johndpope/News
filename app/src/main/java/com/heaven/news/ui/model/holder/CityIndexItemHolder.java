package com.heaven.news.ui.model.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.news.R;
import com.heaven.news.ui.decoration.ItemSpecialCityDecoration;

/**
 * FileName: com.heaven.news.ui.vm.holder.RouteItemHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-26 11:23
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class CityIndexItemHolder extends BaseMultItem<String> {
    ItemSpecialCityDecoration decoration = null;
    GridLayoutManager gridLayoutManager = null;

    public CityIndexItemHolder(@NonNull Class<String> inexName, int itemLayoutId) {
        super(inexName, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull String inexName) {
            holder.setText(R.id.city_group_index_name, inexName);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull String inexName, Object payload) {
        holder.setText(R.id.city_group_index_name, inexName);
    }

}
