package com.heaven.base.ui.activity;

import android.view.View;

/**
 * 作者:Heaven
 * 时间: on 2017/8/30 16:53
 * 邮箱:heavenisme@aliyun.com
 */

public interface IBaseActivity {
    /**
     * 获取layout的id，具体由子类实现
     *
     * @return
     */
    int initLayoutResId();

    int iniTitleBarResId();

    void initTitle(View titleView);

    void setTitle(String title);

    void setTitle(int titleRes);

    void initView(View rootView);

    void bindModel();
}
