package com.heaven.news.ui.model.holder;

import android.support.annotation.NonNull;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.ui.model.bean.base.ServiceItem;

/**
 * FileName: com.heaven.news.ui.vm.bean.ServiceItemHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-28 22:54
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class PhoenixServiceItemHolder extends BaseMultItem<ServiceItem> {

    public PhoenixServiceItemHolder(@NonNull Class<ServiceItem> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceItem serviceItem) {
        holder.setImageResource(R.id.service_icon, ScreenUtil.getImageResId(holder.context,serviceItem.iconID));
        holder.setText(R.id.service_name,ScreenUtil.getStringResId(holder.context,serviceItem.name));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceItem serviceItem, Object payload) {

    }
}
