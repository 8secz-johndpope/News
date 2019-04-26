package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.neusoft.szair.model.fullchannel.fullchannelVO;

/**
 * FileName: com.heaven.news.ui.vm.holder.RouteItemHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-26 11:23
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class RouteItemHolder extends BaseMultItem<fullchannelVO> {

    public RouteItemHolder(@NonNull Class<fullchannelVO> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull fullchannelVO fullchannelVO) {

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull fullchannelVO fullchannelVO, Object payload) {

    }


    @Override
    public boolean isItemSame(fullchannelVO oldItem, fullchannelVO newItem) {
        return true;
    }

    @Override
    public boolean isContentSame(fullchannelVO oldItem, fullchannelVO newItem) {
        return true;
    }
}
