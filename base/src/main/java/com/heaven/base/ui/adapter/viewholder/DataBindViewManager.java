package com.heaven.base.ui.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * 作者:Heaven
 * 时间: on 2017/4/12 13:49
 * 邮箱:heavenisme@aliyun.com
 */

public class DataBindViewManager<T> extends ViewHolderManager<T> {
    private ItemBindView<T> itemBindView;

    /**
     * @param modelBean 和layout绑定的model
     * @param itemLayoutId item 布局文件资源id
     * @param bindVariableId item数据绑定
     */
    public DataBindViewManager(@NonNull final Class<T> modelBean, @LayoutRes final int itemLayoutId, final int bindVariableId) {
        super(modelBean,itemLayoutId);
        this.itemBindView = (dataBinding, data) -> dataBinding.setVariable(bindVariableId, data);
    }

    /**
     * @param modelBean 和layout绑定的model
     * @param itemLayoutId item 布局文件资源id
     * @param itemBindView item数据绑定回调 可以自定义绑定逻辑
     */
    public DataBindViewManager(@NonNull final Class<T> modelBean, @LayoutRes final int itemLayoutId, @NonNull final ItemBindView<T> itemBindView) {
        super(modelBean,itemLayoutId);
        this.itemBindView = itemBindView;
    }

    public static <T> DataBindViewManager build(@NonNull final Class<T> modelBean, @LayoutRes final int itemLayoutId, final int bindVariableId) {
        return  new DataBindViewManager<>(modelBean,itemLayoutId,bindVariableId);
    }

    public static <T> DataBindViewManager build(@NonNull final Class<T> modelBean, @LayoutRes final int itemLayoutId, @NonNull final ItemBindView<T> itemBindView) {
        return  new DataBindViewManager<>(modelBean,itemLayoutId,itemBindView);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new BaseViewHolder(getItemViewBinding(parent).getRoot());
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
        ViewDataBinding dataBinding = DataBindingUtil.getBinding(holder.itemView);
        onBindViewHolder(dataBinding, data);
//        dataBinding.executePendingBindings();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull T data, Object payload) {
        ViewDataBinding dataBinding = DataBindingUtil.getBinding(holder.itemView);
        onBindViewHolder(dataBinding, data);
//        dataBinding.executePendingBindings();
    }


    /**
     * 绑定数据到视图 {@link ItemBindView}
     *
     * @param dataBinding item视图对应dataBinding类
     * @param data        数据源
     */
    private void onBindViewHolder(ViewDataBinding dataBinding, T data) {
        itemBindView.onBindViewHolder(dataBinding, data);
    }


    /**
     * item数据绑定回调接口，在回调方法中自定义绑定逻辑
     */
    @FunctionalInterface
    public interface ItemBindView<T> {
        void onBindViewHolder(ViewDataBinding dataBinding, T data);
    }

}
