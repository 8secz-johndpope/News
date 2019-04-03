package com.heaven.news.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.fragment.BaseSimpleBindFragment;
import com.heaven.base.ui.view.widget.recyclerPager.LoopRecyclerViewPager;
import com.heaven.base.ui.view.widget.recyclerPager.RecyclerViewPagerListener;
import com.heaven.news.R;
import com.heaven.news.databinding.EasygoBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.DataCore;
import com.heaven.news.ui.view.RecyclerViewDivider;
import com.heaven.news.ui.vm.holder.EasyGoBanner;
import com.heaven.news.ui.vm.holder.EasyGoServiceHolder;
import com.heaven.news.ui.vm.model.base.EasyGoService;
import com.heaven.news.ui.vm.model.base.HomeImageInfo;
import com.heaven.news.ui.vm.model.base.ImageInfo;
import com.heaven.news.ui.vm.model.base.ServiceInfo;
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
        mViewBinding.setViewModel(mViewModel);
        mViewBinding.setSearch(mViewModel.easyGoSearch);
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
        mBannerAdapter.register(new EasyGoBanner(ImageInfo.class, R.layout.easygo_banner_item));
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
                if (homeImageInfo.easygotop != null && homeImageInfo.easygotop.size() > 0) {
                    bannerList = homeImageInfo.easygotop;
                }
            }
            if(bannerList.size() == 0) {
                bannerList.add(new ImageInfo());
            }
            mBannerAdapter.updateItems(bannerList);
            mViewBinding.indicators.setViewPager(mViewBinding.imageViewPager);
        }
    }


    private void initGrid() {
        EasyGoService easyGoService = AppEngine.instance().dataCore().loadEasyGoService(getContext());
        if(easyGoService != null && easyGoService.easyGoServiceInfos != null) {
            LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            RecyclerViewDivider divider = new RecyclerViewDivider(getContext(),30,R.color.transparent);
            BaseAdapter<ServiceInfo>  adapter = new BaseAdapter<>(getContext(),easyGoService.easyGoServiceInfos);
            adapter.register(new EasyGoServiceHolder(ServiceInfo.class,R.layout.easygo_service_item));
            mViewBinding.service.addItemDecoration(divider);
            mViewBinding.service.setLayoutManager(manager);
            mViewBinding.service.setAdapter(adapter);
        }
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
