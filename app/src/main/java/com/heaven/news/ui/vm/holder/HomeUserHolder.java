package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.manager.UserManager;
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
        UserManager.CoreDataWrapper coreDataWrapper = AppEngine.instance().dataCore().getCoreDataWrapper();
        holder.setImageResource(R.id.user_info_bg, ScreenUtil.getImageResId(holder.context,serviceItem.iconID));
        holder.setImageResource(R.id.user_header, coreDataWrapper.sexHeaderRes);
        holder.setText(R.id.user_name,coreDataWrapper.userName);
        holder.setText(R.id.user_level,String.format(holder.context.getString(R.string.user_level),coreDataWrapper.cardLevelRes == 0? "--" : holder.context.getString(coreDataWrapper.cardLevelRes)));
        holder.setText(R.id.user_mile,String.format(holder.context.getString(R.string.user_level),coreDataWrapper.userMile));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceItem serviceItem, Object payload) {
        UserManager.CoreDataWrapper coreDataWrapper = AppEngine.instance().dataCore().getCoreDataWrapper();
        holder.setImageResource(R.id.user_header, coreDataWrapper.sexHeaderRes);
        holder.setText(R.id.user_name,coreDataWrapper.userName);
        holder.setText(R.id.user_level,String.format(holder.context.getString(R.string.user_level),coreDataWrapper.cardLevelRes == 0? "--" : holder.context.getString(coreDataWrapper.cardLevelRes)));
        holder.setText(R.id.user_mile,String.format(holder.context.getString(R.string.user_level),coreDataWrapper.userMile));
    }

    @Override
    public boolean isTargetViewType(ServiceItem item, int position) {
        return 102 == item.id;
    }
}
