package com.heaven.news.ui.fragment;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heaven.base.ui.adapter.BaseMultAdapter;
import com.heaven.base.ui.fragment.BaseSimpleBindFragment;
import com.heaven.news.R;
import com.heaven.news.databinding.HomeBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.DataCore;
import com.heaven.news.manyData.adapter.ItemVIew01;
import com.heaven.news.manyData.adapter.ItemVIew02;
import com.heaven.news.manyData.adapter.ItemVIew03;
import com.heaven.news.manyData.adapter.ItemVIewNormal;
import com.heaven.news.manyData.bean.Bean01;
import com.heaven.news.manyData.bean.Bean02;
import com.heaven.news.manyData.bean.Bean03;
import com.heaven.news.ui.adapter.BannerAdapter;
import com.heaven.news.ui.adapter.CardTransformer;
import com.heaven.news.ui.adapter.FragmentPagerAdapter;
import com.heaven.news.ui.vm.model.HomeImageInfo;
import com.heaven.news.ui.vm.model.ImageInfo;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;
import com.heaven.news.utils.UIUtil;
import com.heaven.news.utils.UiPxUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FileName: com.heaven.news.ui.fragment.Home.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-17 14:16
 *
 * @version V1.0 首页
 */
public class Home extends BaseSimpleBindFragment<MainViewModel, HomeBinding> implements ViewPager.OnPageChangeListener, Observer<DataCore.CoreDataWrapper> {
    List<Object> items;
    BannerAdapter topAdapter;
    private List<Fragment> mainList = new ArrayList<>();

    @Override
    public int initLayoutResId() {
        return R.layout.home;
    }


    @Override
    public void initView(View rootView) {
        AppEngine.instance().dataCore().registerDataTypeObaserver(this,this);
        initTopBanner();
        initBookTab();
        initMult();
    }

    private void initTopBanner() {
        topAdapter = new BannerAdapter(getContext());
        mViewBinding.imageViewPager.setAdapter(topAdapter);
        mViewBinding.imageViewPager.setOffscreenPageLimit(3);//预加载2个
        mViewBinding.imageViewPager.setPageMargin(UiPxUtil.dip2px(getContext(), 40));//设置viewpage之间的间距
        mViewBinding.imageViewPager.setPageTransformer(true, new CardTransformer());
        topAdapter.setItemClickListener(index -> {
//                ToastUtils.showToast("点击了图片" + index);
        });
        updateHomeImageData();
    }

    private void initBookTab() {
        final String[] bottomBarList = getResources().getStringArray(R.array.book_type);
        Bundle paramBundle = new Bundle();
        paramBundle.putInt("wx_type", 1);

        mainList.add(BookGo.newInstance(paramBundle));
        mainList.add(BookGoBack.newInstance(paramBundle));
        mainList.add(BookMult.newInstance(null));
        ViewPager viewPager = mViewBinding.bookArea.findViewById(R.id.viewpager);
        TabLayout tabLayout = mViewBinding.bookArea.findViewById(R.id.book_tab);
        viewPager.setOffscreenPageLimit(3);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getActivity(), getChildFragmentManager());
        adapter.insertAll(mainList, Arrays.asList(bottomBarList));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
//        tabLayout.setSelectedTabIndicatorHeight(0);
        int tabCount = tabLayout.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            View barItem = LayoutInflater.from(getContext()).inflate(R.layout.bottom_tab_item, null);
           ViewGroup.LayoutParams params = barItem.getLayoutParams();
            TextView barName = barItem.findViewById(R.id.bottom_bar_name);
            barName.setText(bottomBarList[i]);
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(barItem);
            }
        }
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }


    private void initMult() {
        BaseMultAdapter adapter = new BaseMultAdapter(getContext());
        adapter.register(new ItemVIewNormal(String.class, R.layout.item_go));
        adapter.register(new ItemVIew01(Bean01.class, R.layout.item_one));
        adapter.register(new ItemVIew02(Bean02.class, R.layout.item_two));
        adapter.register(new ItemVIew03(Bean03.class, R.layout.item_three));

        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        mViewBinding.recyclerview.setLayoutManager(layoutManager);
        mViewBinding.recyclerview.setAdapter(adapter);
        mViewBinding.recyclerview.setFocusableInTouchMode(false);
        items = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
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


    int homeDataReqCount = 0;
    public void updateHomeImageData() {
        HomeImageInfo homeImageInfo = AppEngine.instance().dataCore().getHomeConfigData();
        if (mViewBinding != null) {
            if (homeImageInfo != null) {
                if (homeImageInfo.top != null && homeImageInfo.top.size() > 0) {
                    updateBannerData(homeImageInfo.top);
                } else {
                    ArrayList<ImageInfo> bannerList = new ArrayList<>();
                    bannerList.add(new ImageInfo());
                    updateBannerData(bannerList);
                }

                if (homeImageInfo.hot != null && homeImageInfo.hot.size() > 0) {
                    updateHotImages(homeImageInfo.hot);
                }
            } else {
                if(homeDataReqCount <=3) {
                    AppEngine.instance().dataCore().requestHomeConfig();
                    homeDataReqCount++;
                }
                ArrayList<ImageInfo> bannerList = new ArrayList<>();
                bannerList.add(new ImageInfo());
                updateBannerData(bannerList);
            }
        }
    }

    private void updateBannerData(List<ImageInfo> bannerList) {
        if (bannerList != null && bannerList.size() > 0) {
            mViewBinding.indicators.setViewPager(mViewBinding.imageViewPager, bannerList.size());
            updateTopImages(bannerList);
            mViewBinding.imageViewPager.setCurrentItem(bannerList.size());
            mViewBinding.imageViewPager.startLoop();
        }
    }

    private void updateTopImages(List<ImageInfo> tops) {
        topAdapter.updatePagerData(tops);
    }

    private void updateHotImages(List<ImageInfo> hots) {

    }

    public static Home newInstance(Bundle paramBundle) {
        Home fragment = new Home();
        fragment.setArguments(paramBundle);
        return fragment;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onChanged(@Nullable DataCore.CoreDataWrapper coreDataWrapper) {
        if(coreDataWrapper != null && DataCore.HOME == coreDataWrapper.dataType) {
            updateHomeImageData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppEngine.instance().dataCore().removeForeverObserve(this);
    }
}
