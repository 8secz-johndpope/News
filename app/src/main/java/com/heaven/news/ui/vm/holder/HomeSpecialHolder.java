package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.ui.vm.model.ServiceItem;

/**
 * FileName: com.heaven.news.ui.vm.bean.ServiceItemHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-28 22:54
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class HomeSpecialHolder extends BaseMultItem<ServiceItem> {

    public HomeSpecialHolder(@NonNull Class<ServiceItem> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceItem serviceItem) {
        holder.setImageResource(R.id.special_img, ScreenUtil.getImageResId(holder.context,serviceItem.iconID));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceItem serviceItem, Object payload) {

    }


    @Override
    public boolean isTargetViewType(ServiceItem item, int position) {
        return 100 == item.id || 101 == item.id;
    }
}
