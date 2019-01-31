package com.heaven.base.ui.adapter.viewholder;

import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 作者:Heaven
 * 时间: on 2017/4/12 13:49
 * 邮箱:heavenisme@aliyun.com
 */

public class BaseHeaderRefresh<T> extends ViewHolderManager<T> implements BaseRefreshHeader{
    private HeaderItemBindView<T> itemBindView;

    private BaseViewHolder baseHolder;
    private int mState = STATE_NORMAL;
    public BaseHeaderRefresh(@LayoutRes int itemLayoutId) {
        super(itemLayoutId);
    }

    /**
     * @param itemLayoutId item 布局文件资源id
     * @param bindVariableId item数据绑定
     */
    public BaseHeaderRefresh(@LayoutRes final int itemLayoutId, final int bindVariableId) {
        super(itemLayoutId);
        this.itemBindView = (dataBinding, data) -> dataBinding.setVariable(bindVariableId, data);
    }


    /**
     * @param itemLayoutId item 布局文件资源id
     * @param itemBindView item数据绑定回调 可以自定义绑定逻辑
     */
    public BaseHeaderRefresh(@LayoutRes final int itemLayoutId, @NonNull final HeaderItemBindView<T> itemBindView) {
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
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull T t, Object payload) {

    }


    /**
     * 绑定数据到视图 {@link HeaderItemBindView}
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

    @Override
    public void onMove(float delta) {
        if(getVisibleHeight() > 0 || delta > 0) {
            setVisibleHeight((int) delta + getVisibleHeight());
            if (mState <= STATE_RELEASE_TO_REFRESH) { // 未处于刷新状态，更新箭头
                if (getVisibleHeight() > baseHolder.getViewItem().getMeasuredHeight()) {
                    setState(STATE_RELEASE_TO_REFRESH);
                }else {
                    setState(STATE_NORMAL);
                }
            }
        }
    }

    private void smoothScrollTo(int destHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(getVisibleHeight(), destHeight);
        animator.setDuration(300).start();
        animator.addUpdateListener((animation)-> setVisibleHeight((int) animation.getAnimatedValue()));
        animator.start();
    }

    public void setState(int state) {
        if (state == mState) return ;

        if (state == STATE_REFRESHING) {	// 显示进度
//            mArrowImageView.clearAnimation();
//            mArrowImageView.setVisibility(View.INVISIBLE);
//            mProgressBar.setVisibility(View.VISIBLE);
            smoothScrollTo(baseHolder.getViewItem().getMeasuredHeight());
        } else if(state == STATE_DONE) {
//            mArrowImageView.setVisibility(View.INVISIBLE);
//            mProgressBar.setVisibility(View.INVISIBLE);
        } else {	// 显示箭头图片
//            mArrowImageView.setVisibility(View.VISIBLE);
//            mProgressBar.setVisibility(View.INVISIBLE);
        }

        switch(state){
            case STATE_NORMAL:
                if (mState == STATE_RELEASE_TO_REFRESH) {
//                    mArrowImageView.startAnimation(mRotateDownAnim);
                }
                if (mState == STATE_REFRESHING) {
//                    mArrowImageView.clearAnimation();
                }
//                mStatusTextView.setText(R.string.listview_header_hint_normal);
                break;
            case STATE_RELEASE_TO_REFRESH:
                if (mState != STATE_RELEASE_TO_REFRESH) {
//                    mArrowImageView.clearAnimation();
//                    mArrowImageView.startAnimation(mRotateUpAnim);
//                    mStatusTextView.setText(R.string.listview_header_hint_release);
                }
                break;
            case     STATE_REFRESHING:
//                mStatusTextView.setText(R.string.refreshing);
                break;
            case    STATE_DONE:
//                mStatusTextView.setText(R.string.refresh_done);
                break;
            default:
        }

        mState = state;
    }

    @Override
    public boolean releaseAction() {
        return false;
    }

    @Override
    public void refreshComplete() {

    }

    public int getVisibleHeight() {
        int height = 0;
        if(baseHolder != null && baseHolder.getViewItem() != null) {
            height =  baseHolder.getViewItem().getLayoutParams().height;
        }
        return height;
    }

    public void setVisibleHeight(int height) {
        if (height < 0) height = 0;
        ViewGroup.LayoutParams lp =  baseHolder.getViewItem().getLayoutParams();
        lp.height = height;
        baseHolder.getViewItem().setLayoutParams(lp);
    }

    /**
     * item数据绑定回调接口，在回调方法中自定义绑定逻辑
     */
    @FunctionalInterface
    public interface HeaderItemBindView<T> {
        void onBindViewHolder(ViewDataBinding dataBinding, T data);
    }

}
