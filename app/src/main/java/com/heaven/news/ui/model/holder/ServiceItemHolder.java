package com.heaven.news.ui.model.holder;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

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
public class ServiceItemHolder extends BaseMultItem<ServiceItem> {

    public ServiceItemHolder(@NonNull Class<ServiceItem> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceItem serviceItem) {
        holder.setImageResource(R.id.service_icon, ScreenUtil.getImageResId(holder.context,serviceItem.iconID));
        holder.setText(R.id.service_name,ScreenUtil.getStringResId(holder.context,serviceItem.name));
        if(!TextUtils.isEmpty(serviceItem.recIcon)) {
            holder.getView(R.id.recommend_icon).setVisibility(View.VISIBLE);
            holder.setImageResource(R.id.recommend_icon, ScreenUtil.getImageResId(holder.context,serviceItem.recIcon));
        } else {
            holder.getView(R.id.recommend_icon).setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceItem serviceItem, Object payload) {

    }
}
