package com.heaven.news.ui.model.holder;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.ui.model.bean.base.ServiceItem;

/**
 * FileName: com.heaven.news.ui.vm.holder.HomeEasyGoHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-30 16:11
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class HomeEasyGoHolder extends BaseMultItem<ServiceItem> {

    public HomeEasyGoHolder(@NonNull Class<ServiceItem> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceItem serviceItem) {
        if (!TextUtils.isEmpty(serviceItem.iconID)) {
            holder.setImageResource(R.id.image, ScreenUtil.getImageResId(holder.context,serviceItem.iconID));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceItem serviceItem, Object payload) {

    }

    @Override
    public boolean isTargetViewType(ServiceItem item, int position) {
        return 100 != item.id && 101 != item.id && 102 != item.id;
    }
}
