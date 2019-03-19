package com.heaven.base.ui.adapter.viewholder;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者:Heaven
 * 时间: on 2017/4/12 12:24
 * 邮箱:heavenisme@aliyun.com
 */

public abstract class BaseMultItem<T> implements IMultManager<T> {
    private Class<T> modelBean;
    @LayoutRes
    private int itemLayoutId;
    private boolean fullSpan;
    private int spanSize;
    private List<BaseMultItem<T>> childList = new ArrayList<>();

    public BaseMultItem(@NonNull Class<T> modelBean, @LayoutRes int itemLayoutId) {
        this.modelBean = modelBean;
        this.itemLayoutId = itemLayoutId;
    }


    public Class<T> getLayoutBindModel() {
        return modelBean;
    }

    public BaseMultItem<T> addChild(BaseMultItem<T> multItem) {
        childList.add(multItem);
        return this;
    }

    public List<BaseMultItem<T>> getChildList() {
        return childList;
    }

    public boolean containChild() {
        if(childList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {

    }

    @Override
    public boolean isTargetViewType(T item, int position) {
        return true;
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

    @Override
    public int getMaxRecycleCount() {
        return 5;
    }
}
