package com.heaven.base.ui.adapter.viewholder;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 作者:Heaven
 * 时间: on 2017/4/12 12:24
 * 邮箱:heavenisme@aliyun.com
 */

public abstract class ViewHolderManager<T> implements IViewHolderManager<T> {
    private Class<T> modelBean;
    @LayoutRes
    int itemLayoutId;
    private boolean fullSpan;
    private int spanSize;

    ViewHolderManager(@LayoutRes int itemLayoutId) {
        this.itemLayoutId = itemLayoutId;
    }


    ViewHolderManager(@NonNull Class<T> modelBean, @LayoutRes int itemLayoutId) {
        this.modelBean = modelBean;
        this.itemLayoutId = itemLayoutId;
    }


    public Class<T> getLayoutBindModel() {
        return modelBean;
    }

    @Override
    public View getItemView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
    }

    @Override
    public void setFullSpan(boolean fullSpan) {
        this.fullSpan = fullSpan;
    }

    @Override
    public boolean isFullSpan() {
        return fullSpan;
    }

    @Override
    public int getSpanSize(int spanCount) {
        return spanSize > 0 ? spanSize : (isFullSpan() ? spanCount : 1);
    }

    @Override
    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    @Override
    public boolean isClickable() {
        return true;
    }

    public int getItemLayoutId() {
        return itemLayoutId;
    }

    public boolean isItemSame(T oldItem,T newItem) {
        return false;
    }

    public boolean isContentSame(T oldItem,T newItem) {
        return false;
    }
}
