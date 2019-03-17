package com.heaven.news.ui.fragment;

import android.os.Bundle;

import com.heaven.base.ui.fragment.BaseFragment;
import com.heaven.news.R;
import com.heaven.news.databinding.HomeBinding;

/**
 * FileName: com.heaven.news.ui.fragment.Home.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-17 14:16
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class Home extends BaseFragment<HomeBinding> {
    @Override
    protected void initView() {

    }

    @Override
    protected int initLayoutResId() {
        return R.layout.home;
    }

    public static  BaseFragment newInstance(Bundle paramBundle) {
        Home fragment = new Home();
        fragment.setArguments(paramBundle);
        return fragment;
    }
}
