package com.heaven.news.ui.model.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CompoundButton;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.utils.ScreenUtil;
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
        holder.setText(R.id.setting_item_name, ScreenUtil.getStringResId(holder.context, settingItem.name));
        if (SettingItem.SWITCH_LANGUAGE == settingItem.type || SettingItem.VERSION == settingItem.type) {
            holder.setVisible(R.id.switch_button, false);
        } else {
            holder.setVisible(R.id.switch_button, true);
            holder.setEnable(R.id.switch_button, true);
            holder.setChecked(R.id.switch_button, settingItem.isOpen);
            holder.setVisible(R.id.go_next, false);
            holder.setOnClickListener(R.id.switch_button, v -> {
                if (holder.onItemClickListener != null) {
                    settingItem.isOpen = !settingItem.isOpen;
                    holder.onItemClickListener.onItemClick(v, holder, settingItem);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull SettingItem settingItem, Object payload) {
        if (SettingItem.SWITCH_LANGUAGE == settingItem.type || SettingItem.VERSION == settingItem.type) {
            holder.setVisible(R.id.switch_button, false);
        } else {
            holder.setEnable(R.id.switch_button, true);
            holder.setChecked(R.id.switch_button, settingItem.isOpen);
        }
    }
}
