package com.heaven.news.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.heaven.base.ui.fragment.BaseSimpleBindFragment;
import com.heaven.base.ui.view.rlview.OnLoadMoreListener;
import com.heaven.base.ui.view.rlview.OnRefreshListener;
import com.heaven.news.R;
import com.heaven.news.databinding.RouteBinding;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;

/**
 * FileName: com.heaven.news.ui.fragment.Home.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-17 14:16
 *
 * @version V1.0 行程
 */
public class Route extends BaseSimpleBindFragment<MainViewModel,RouteBinding> implements OnRefreshListener, OnLoadMoreListener {
    @Override
    public void initView(View rootView) {

    }

    @Override
    public void bindModel() {

    }

    @Override
    public int initLayoutResId() {
        return R.layout.route;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public static  Route newInstance(Bundle paramBundle) {
        Route fragment = new Route();
        fragment.setArguments(paramBundle);
        return fragment;
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
