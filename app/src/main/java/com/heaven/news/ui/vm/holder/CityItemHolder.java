package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.news.R;
import com.neusoft.szair.model.city.cityListVO;
import com.neusoft.szair.model.fullchannel.fullchannelVO;

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
        holder.setText(R.id.city_name,city._FULL_NAME);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull cityListVO city, Object payload) {
        holder.setText(R.id.city_name,city._FULL_NAME);
    }

}
