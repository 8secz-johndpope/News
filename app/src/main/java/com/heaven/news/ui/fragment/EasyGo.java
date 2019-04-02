package com.heaven.news.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.fragment.BaseSimpleBindFragment;
import com.heaven.base.ui.view.widget.banner.LoopRecyclerViewPager;
import com.heaven.base.ui.view.widget.banner.RecyclerViewPagerListener;
import com.heaven.news.R;
import com.heaven.news.databinding.EasygoBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.DataCore;
import com.heaven.news.ui.vm.holder.HomeBanner;
import com.heaven.news.ui.vm.model.base.HomeService;
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
public class EasyGo extends BaseSimpleBindFragment<MainViewModel,EasygoBinding> implements  Observer<DataCore.CoreDataWrapper> {
    BaseAdapter<ImageInfo> mBannerAdapter;

    @Override
    public void bindModel() {

    }


    @Override
    public int initLayoutResId() {
        return R.layout.easygo;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        initTopBanner();
        initGrid();
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
        if (mViewBinding != null) {
            if (homeImageInfo != null) {
                if (homeImageInfo.easygotop != null && homeImageInfo.easygotop.size() > 0) {
                    updateBannerData(homeImageInfo.easygotop);
                } else {
                    ArrayList<ImageInfo> bannerList = new ArrayList<>();
                    bannerList.add(new ImageInfo());
                    updateBannerData(bannerList);
                }
            }
        }
    }

    private void updateBannerData(List<ImageInfo> bannerList) {
        if (bannerList != null && bannerList.size() > 0) {
            mBannerAdapter.updateItems(bannerList);
            mViewBinding.indicators.setViewPager(mViewBinding.imageViewPager);
        }
    }

    private void initGrid() {
        HomeService allServiceItem = AppEngine.instance().dataCore().loadAllServiceItem(getContext());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public static  EasyGo newInstance(Bundle paramBundle) {
        EasyGo fragment = new EasyGo();
        fragment.setArguments(paramBundle);
        return fragment;
    }


    @Override
    public void onChanged(@Nullable DataCore.CoreDataWrapper coreDataWrapper) {
        if (coreDataWrapper != null && DataCore.HOME == coreDataWrapper.dataType) {
            updateHomeImageData();
        }
    }
}
