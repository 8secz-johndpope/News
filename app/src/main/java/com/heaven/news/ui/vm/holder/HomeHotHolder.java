package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.news.ui.vm.model.ImageInfo;

/**
 * FileName: com.heaven.news.ui.vm.holder.HomeHotHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-30 11:35
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class HomeHotHolder extends BaseMultItem<ImageInfo> {

    public HomeHotHolder(@NonNull Class<ImageInfo> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ImageInfo imageInfo) {

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ImageInfo imageInfo, Object payload) {

    }
}
