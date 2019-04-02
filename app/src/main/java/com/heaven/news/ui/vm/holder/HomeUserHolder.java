package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.ui.vm.model.base.ServiceItem;

/**
 * FileName: com.heaven.news.ui.vm.holder.HomeUserHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-30 10:21
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class HomeUserHolder extends BaseMultItem<ServiceItem> {

    public HomeUserHolder(@NonNull Class<ServiceItem> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
        setSpanSize(2);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceItem serviceItem) {
        holder.setImageResource(R.id.user_info_bg, ScreenUtil.getImageResId(holder.context,serviceItem.iconID));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceItem serviceItem, Object payload) {

    }

    @Override
    public boolean isTargetViewType(ServiceItem item, int position) {
        return 102 == item.id;
    }
}
