package com.heaven.base.ui.adapter.viewholder;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

/**
 * FileName: com.heaven.base.ui.adapter.viewholder.BaseViewHolderManager.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-04-15 18:34
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class BaseViewHolderManager<T> extends ViewHolderManager<T> {
    BaseViewHolder viewHolder;

    public BaseViewHolderManager(@NonNull Class<T> modelBean, @LayoutRes int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        viewHolder = new BaseViewHolder(getItemView(parent));
        return viewHolder;
    }

    @Override
    public abstract void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull T t);

}
