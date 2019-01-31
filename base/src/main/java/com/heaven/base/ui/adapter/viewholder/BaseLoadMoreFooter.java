package com.heaven.base.ui.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者:Heaven
 * 时间: on 2017/4/12 13:49
 * 邮箱:heavenisme@aliyun.com
 */

public class BaseLoadMoreFooter<T> extends ViewHolderManager<T> {
    public final static int STATE_LOADING = 0;
    public final static int STATE_COMPLETE = 1;
    public final static int STATE_NOMORE = 2;
    private FooterItemBindView<T> itemBindView;
    private BaseViewHolder baseHolder;
    public BaseLoadMoreFooter(@LayoutRes int itemLayoutId) {
        super(itemLayoutId);
    }

    @Override
    public boolean isItemSame(T oldItem, T newItem) {
        return false;
    }

    @Override
    public boolean isContentSame(T oldItem, T newItem) {
        return false;
    }

    /**
     * @param itemLayoutId item 布局文件资源id
     * @param bindVariableId item数据绑定
     */
    public BaseLoadMoreFooter( @LayoutRes final int itemLayoutId, final int bindVariableId) {
        super(itemLayoutId);
        this.itemBindView = (dataBinding, data) -> dataBinding.setVariable(bindVariableId, data);
    }


    /**
     * @param itemLayoutId item 布局文件资源id
     * @param itemBindView item数据绑定回调 可以自定义绑定逻辑
     */
    public BaseLoadMoreFooter(@LayoutRes final int itemLayoutId, @NonNull final FooterItemBindView<T> itemBindView) {
        super(itemLayoutId);
        this.itemBindView = itemBindView;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        if(itemBindView != null) {
            baseHolder = new BaseViewHolder(getItemViewBinding(parent).getRoot());
        } else {
            baseHolder = new BaseViewHolder(getItemView(parent));
        }
        return baseHolder;
    }

    /**
     * 生成ViewDataBinding
     *
     * @param viewGroup ViewGroup
     * @return ViewDataBinding
     */
    private ViewDataBinding getItemViewBinding(ViewGroup viewGroup) {
        return DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                itemLayoutId, viewGroup, false);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull T data) {
        if(itemBindView != null) {
            ViewDataBinding dataBinding = DataBindingUtil.getBinding(holder.itemView);
            onBindViewHolder(dataBinding, data);
        } else {
            dataViewBindHolder(holder,data);
        }


//        dataBinding.executePendingBindings();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull T data, Object payload) {
        if(itemBindView != null) {
            ViewDataBinding dataBinding = DataBindingUtil.getBinding(holder.itemView);
            onBindViewHolder(dataBinding, data);
        } else {
            dataViewBindHolder(holder,data);
        }
    }


    /**
     * 绑定数据到视图 {@link FooterItemBindView}
     *
     * @param dataBinding item视图对应dataBinding类
     * @param data        数据源
     */
    private void onBindViewHolder(ViewDataBinding dataBinding, T data) {
        itemBindView.onBindViewHolder(dataBinding, data);
    }

    /**
     * 非databinding邦定
     * @param holder BaseViewHolder
     * @param data 数据
     */
    public void  dataViewBindHolder(@NonNull BaseViewHolder holder, @NonNull T data){

    }

    public BaseViewHolder getBaseHolder() {
        return baseHolder;
    }

    /**
     * item数据绑定回调接口，在回调方法中自定义绑定逻辑
     */
    @FunctionalInterface
    public interface FooterItemBindView<T> {
        void onBindViewHolder(ViewDataBinding dataBinding, T data);
    }

    public void  setState(int state) {
        if(baseHolder != null) {
            switch(state) {
                case STATE_LOADING:
                    ViewDataBinding dataBinding = DataBindingUtil.getBinding(baseHolder.itemView);
                    itemBindView.onBindViewHolder(dataBinding, (T) new Boolean(true));
//                progressCon.setVisibility(View.VISIBLE);
//                mText.setText(loadingHint);
//                this.setVisibility(View.VISIBLE);
                    break;
                case STATE_COMPLETE:
                    itemBindView.onBindViewHolder(DataBindingUtil.getBinding(baseHolder.itemView), (T) new Boolean(false));
//                mText.setText(loadingDoneHint);
//                this.setVisibility(View.GONE);
                    break;
                case STATE_NOMORE:
                    itemBindView.onBindViewHolder(DataBindingUtil.getBinding(baseHolder.itemView), (T) new Boolean(false));
//                mText.setText(noMoreHint);
//                progressCon.setVisibility(View.GONE);
//                this.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

}
