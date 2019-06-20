package com.heaven.news.ui.model.holder;

import android.support.annotation.NonNull;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.news.R;
import com.heaven.news.ui.model.bean.base.SettingItem;

/**
 * FileName: com.heaven.news.ui.model.holder.SettingItemHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-20 15:08
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class SettingItemHolder extends BaseMultItem<SettingItem> {

    public SettingItemHolder(@NonNull Class<SettingItem> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull SettingItem settingItem) {
        if(SettingItem.SWITCH_LANGUAGE == settingItem.type || SettingItem.VERSION == settingItem.type) {
            holder.setVisible(R.id.switch_button,false);
        } else {
            holder.setVisible(R.id.go_next,false);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull SettingItem settingItem, Object payload) {

    }
}
