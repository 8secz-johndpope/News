package com.heaven.news.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.heaven.base.ui.adapter.BaseMultAdapter;
import com.heaven.base.ui.fragment.BaseSimpleBindFragment;
import com.heaven.news.R;
import com.heaven.news.databinding.HomeBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.manyData.adapter.ItemVIew01;
import com.heaven.news.manyData.adapter.ItemVIew02;
import com.heaven.news.manyData.adapter.ItemVIew03;
import com.heaven.news.manyData.adapter.ItemVIewNormal;
import com.heaven.news.manyData.bean.Bean01;
import com.heaven.news.manyData.bean.Bean02;
import com.heaven.news.manyData.bean.Bean03;
import com.heaven.news.ui.adapter.BannerAdapter;
import com.heaven.news.ui.adapter.CardTransformer;
import com.heaven.news.ui.vm.model.HomeImageInfo;
import com.heaven.news.ui.vm.model.ImageInfo;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.heaven.news.ui.fragment.Home.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-17 14:16
 *
 * @version V1.0 首页
 */
public class Home extends BaseSimpleBindFragment<MainViewModel, HomeBinding> {
    List<Object> items;
    BannerAdapter topAdapter;
    @Override
    public void initView(View rootView) {
        topAdapter = new BannerAdapter(getContext());

        mViewBinding.imageViewPager.setAdapter(topAdapter);
        mViewBinding.imageViewPager.setOffscreenPageLimit(3);//预加载2个
        mViewBinding.imageViewPager.setPageMargin(60);//设置viewpage之间的间距
        mViewBinding.imageViewPager.setPageTransformer(true, new CardTransformer());
        topAdapter.setItemClickListener(index -> {
//                ToastUtils.showToast("点击了图片" + index);
        });
        updateHomeImageData();

        BaseMultAdapter  adapter = new BaseMultAdapter(getContext());

        adapter.register(new ItemVIewNormal(String.class,R.layout.item_go));
        adapter.register( new ItemVIew01(Bean01.class,R.layout.item_one));
        adapter.register( new ItemVIew02(Bean02.class,R.layout.item_two));
        adapter.register( new ItemVIew03(Bean03.class,R.layout.item_three));

        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        mViewBinding.recyclerview.setLayoutManager(layoutManager);
        mViewBinding.recyclerview.setAdapter(adapter);
        mViewBinding.recyclerview.setFocusableInTouchMode(false);
        items = new ArrayList<>();
        for(int j=0;j<10;j++) {
            items.add(" 多数据 -> 多类型  ");
            for (int i = 0; i < 8; i++) {
                items.add(new Bean01("bean01_" + i));
            }
            for (int i = 0; i < 4; i++) {
                items.add(new Bean02("bean02_" + i));
            }
            for (int i = 0; i < 2; i++) {
                items.add(new Bean03("bean03_" + i));
            }
        }

        adapter.updateItems(items);
    }

    @Override
    public void bindModel() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }


    @Override
    public int initLayoutResId() {
        return R.layout.home;
    }

    public void updateHomeImageData() {
        HomeImageInfo homeImageInfo = AppEngine.instance().dataCore().getHomeConfigData();
        if(mViewBinding != null && homeImageInfo != null) {
            if(homeImageInfo.top != null && homeImageInfo.top.size() > 0) {
                mViewBinding.indicators.setViewPager(mViewBinding.imageViewPager,  homeImageInfo.top.size());
                updateTopImages(homeImageInfo.top);
                mViewBinding.imageViewPager.setCurrentItem(1000*homeImageInfo.top.size());
                mViewBinding.imageViewPager.startLoop();
//                mViewBinding.imageViewPager.setCurrentItem(mViewBinding.imageViewPager.getChildCount()/2);
            } else if(homeImageInfo.hot != null && homeImageInfo.hot.size() > 0) {
                updateHotImages(homeImageInfo.hot);
            }
        }
    }

    private void updateTopImages(List<ImageInfo> tops) {
        topAdapter.updatePagerData(tops);
    }

    private void updateHotImages(List<ImageInfo> hots) {

    }

    public static  Home newInstance(Bundle paramBundle) {
        Home fragment = new Home();
        fragment.setArguments(paramBundle);
        return fragment;
    }
}
