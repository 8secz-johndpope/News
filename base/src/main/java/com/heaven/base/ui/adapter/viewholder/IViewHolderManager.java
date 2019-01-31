package com.heaven.base.ui.adapter.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者:Heaven
 * 时间: on 2017/4/12 12:27
 * 邮箱:heavenisme@aliyun.com
 */

interface IViewHolderManager<T> {
    @NonNull
    BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent);

    void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull T t);

    void onBindViewHolder(@NonNull BaseViewHolder holder,@NonNull T t,Object payload);

    View getItemView(ViewGroup parent);

    void setFullSpan(boolean fullSpan);

    boolean isFullSpan();

    int getSpanSize(int spanCount);

    void setSpanSize(int spanSize);

     boolean isClickable();
}
