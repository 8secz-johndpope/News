package com.heaven.news.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.fragment.BaseSimpleBindFragment;
import com.heaven.base.ui.view.widget.banner.LoopRecyclerViewPager;
import com.heaven.base.ui.view.widget.banner.RecyclerViewPagerListener;
import com.heaven.news.R;
import com.heaven.news.databinding.PhoenixBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.vm.holder.HomeBanner;
import com.heaven.news.ui.vm.model.base.HomeImageInfo;
import com.heaven.news.ui.vm.model.base.ImageInfo;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.heaven.news.ui.fragment.Home.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-17 14:16
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class Phoenix extends BaseSimpleBindFragment<MainViewModel,PhoenixBinding>{
    BaseAdapter<ImageInfo> mBannerAdapter;
    @Override
    public void bindModel() {

    }

    @Override
    public int initLayoutResId() {
        return R.layout.phoenix;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        initTopBanner();
    }

    private void initTopBanner() {
        LoopRecyclerViewPager mRecyclerView = mViewBinding.imageViewPager;
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mBannerAdapter = new BaseAdapter<>(getContext());
        mBannerAdapter.register(new HomeBanner(ImageInfo.class, R.layout.easygo_banner_item));
        mRecyclerView.setLayoutManager(layout);
        mRecyclerView.setAdapter(mBannerAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);
        RecyclerViewPagerListener bannerListener = new RecyclerViewPagerListener(mRecyclerView);
        mRecyclerView.addOnScrollListener(bannerListener);
        mRecyclerView.addOnLayoutChangeListener(bannerListener);
        updateHomeImageData();
        mRecyclerView.setAutoLoop(true);
        mRecyclerView.startLoop();
    }

    public void updateHomeImageData() {
        HomeImageInfo homeImageInfo = AppEngine.instance().dataCore().getHomeConfigData();
        List<ImageInfo> bannerList = new ArrayList<>();
        if (mViewBinding != null) {
            if (homeImageInfo != null) {
                if (homeImageInfo.phoenix != null && homeImageInfo.phoenix.size() > 0) {
                    bannerList = homeImageInfo.phoenix;
                }
            }
            if(bannerList.size() == 0) {
                bannerList.add(new ImageInfo());
            }
            mBannerAdapter.updateItems(bannerList);
            mViewBinding.indicators.setViewPager(mViewBinding.imageViewPager);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public static  Phoenix newInstance(Bundle paramBundle) {
        Phoenix fragment = new Phoenix();
        fragment.setArguments(paramBundle);
        return fragment;
    }
}
