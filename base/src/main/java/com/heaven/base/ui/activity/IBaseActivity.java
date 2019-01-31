package com.heaven.base.ui.activity;

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

    void initView();

    void onInitPresenters();
}
