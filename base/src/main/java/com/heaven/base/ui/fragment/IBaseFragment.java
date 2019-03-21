package com.heaven.base.ui.fragment;

import android.view.View;

/**
 * 作者:Heaven
 * 时间: on 2017/8/30 16:53
 * 邮箱:heavenisme@aliyun.com
 */

public interface IBaseFragment {
    /**
     * 获取layout的id，具体由子类实现
     *
     * @return
     */
    int initLayoutResId();

    void initView(View rootView);

    void bindModel();
}
