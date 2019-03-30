package com.heaven.news.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.BaseMultAdapter;
import com.heaven.base.ui.fragment.BaseSimpleBindFragment;
import com.heaven.base.ui.view.widget.banner.RecyclerViewPagerListener;
import com.heaven.base.ui.view.widget.banner.LoopRecyclerViewPager;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.databinding.HomeBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.DataCore;
import com.heaven.news.ui.adapter.FragmentPagerAdapter;
import com.heaven.news.ui.view.AutofitHeightViewPager;
import com.heaven.news.ui.vm.holder.HomeBanner;
import com.heaven.news.ui.vm.holder.HomeHotHolder;
import com.heaven.news.ui.vm.holder.HomeHotTitleHoler;
import com.heaven.news.ui.vm.holder.ItemDecoration;
import com.heaven.news.ui.vm.model.HomeHotTitle;
import com.heaven.news.ui.vm.holder.HomeRecHolder;
import com.heaven.news.ui.vm.holder.HomeServiceHolder;
import com.heaven.news.ui.vm.model.AllServiceItem;
import com.heaven.news.ui.vm.model.HomeImageInfo;
import com.heaven.news.ui.vm.model.HomeServiceItem;
import com.heaven.news.ui.vm.model.ImageInfo;
import com.heaven.news.ui.vm.model.ServiceItem;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;

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
    BaseAdapter<ImageInfo> mBannerAdapter;
    BaseAdapter<HomeServiceItem> mServiceAdapter;
    private List<Fragment> mainList = new ArrayList<>();

    @Override
    public int initLayoutResId() {
        return R.layout.home;
    }


    @Override
    public void initView(View rootView) {
        AppEngine.instance().dataCore().registerDataTypeObaserver(this, this);
        initTopBanner();
        initBookTab();
        initService();
        initRecommends();
    }

    private void initTopBanner() {
        LoopRecyclerViewPager mRecyclerView = mViewBinding.imageViewPager;
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mBannerAdapter = new BaseAdapter<>(getContext());
        mBannerAdapter.register(new HomeBanner(ImageInfo.class, R.layout.banner_item));

        mRecyclerView.setLayoutManager(layout);
        mRecyclerView.setAdapter(mBannerAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);
        RecyclerViewPagerListener bannerListener = new RecyclerViewPagerListener(mRecyclerView);
        mRecyclerView.addOnScrollListener(bannerListener);
        mRecyclerView.addOnLayoutChangeListener(bannerListener);
        mRecyclerView.addOnPageChangedListener((oldPosition, newPosition) -> {
                    if (mBannerAdapter.getItemCount() != 0) {
                        Log.d("test", "oldPosition:" + oldPosition % mBannerAdapter.getItemCount() + " newPosition:" + newPosition % mBannerAdapter.getItemCount());
                    }
                }
        );
        updateHomeImageData();
        mRecyclerView.setAutoLoop(true);
        mRecyclerView.startLoop();
    }

    private void initBookTab() {
        AutofitHeightViewPager viewPager = mViewBinding.bookArea.findViewById(R.id.viewpager);
        TabLayout tabLayout = mViewBinding.bookArea.findViewById(R.id.book_tab);
        viewPager.setOffscreenPageLimit(3);

        final String[] bottomBarList = getResources().getStringArray(R.array.book_type);
        Bundle paramBundleGo = new Bundle();
        paramBundleGo.putInt("wx_type", 1);
        Bundle paramBundleGoBack = new Bundle();
        paramBundleGoBack.putInt("wx_type", 1);
        Bundle paramBundleMult = new Bundle();
        paramBundleMult.putInt("wx_type", 1);

        AutofitHeightViewPager.ViewPosition viewPosition = viewPager::setViewPosition;
        BookGo go = BookGo.newInstance(paramBundleGo);
        BookGoBack goBack = BookGoBack.newInstance(paramBundleGoBack);
        BookMult mult = BookMult.newInstance(paramBundleMult);
        go.setViewPosition(viewPosition);
        goBack.setViewPosition(viewPosition);
        mult.setViewPosition(viewPosition);

        mainList.add(go);
        mainList.add(goBack);
        mainList.add(mult);


        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getActivity(), getChildFragmentManager());
        adapter.insertAll(mainList, Arrays.asList(bottomBarList));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        tabLayout.setupWithViewPager(viewPager);
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                viewPager.updateHeight(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initService() {
        AllServiceItem allServiceItem = AppEngine.instance().dataCore().loadAllServiceItem(getContext());
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mServiceAdapter = new BaseAdapter<>(getContext());
        mServiceAdapter.register(new HomeServiceHolder(HomeServiceItem.class, R.layout.home_service_item));

        mViewBinding.service.setLayoutManager(layout);
        mViewBinding.service.setAdapter(mServiceAdapter);
        mViewBinding.service.setHasFixedSize(true);
        mViewBinding.service.setLongClickable(true);
        if (allServiceItem != null && allServiceItem.homeServiceInfos != null) {
            mServiceAdapter.updateItems(allServiceItem.homeServiceInfos);
        }
        mViewBinding.service.setAutoLoop(false);

    }

    private void initRecommends() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mViewBinding.recommendService.setLayoutManager(gridLayoutManager);
        BaseMultAdapter adapter = new BaseMultAdapter(getContext(), organizeRecData());
        adapter.register(new HomeRecHolder(ServiceItem.class, 0));
        adapter.register(new HomeHotHolder(ImageInfo.class, R.layout.home_hot));
        adapter.register(new HomeHotTitleHoler(HomeHotTitle.class, R.layout.home_hot_title));
        mViewBinding.recommendService.addItemDecoration(new ItemDecoration(ScreenUtil.dip2px(getContext(), 5)));
        mViewBinding.recommendService.setAdapter(adapter);

    }

    private ArrayList organizeRecData() {
        ArrayList dataList = new ArrayList();
        AllServiceItem allServiceItem = AppEngine.instance().dataCore().loadAllServiceItem(getContext());
        HomeImageInfo homeImageInfo = AppEngine.instance().dataCore().getHomeConfigData();
        if (allServiceItem != null) {
            if (allServiceItem.homeRecommendInfos != null && allServiceItem.homeRecommendInfos.size() > 0) {
                dataList.addAll(allServiceItem.homeRecommendInfos);
            }

            if (allServiceItem.homeEasyGoInfos != null && allServiceItem.homeEasyGoInfos.size() > 0) {
                HomeHotTitle title = new HomeHotTitle(1, R.string.sz_easy_go, "", R.mipmap.home_easy_title);
                dataList.add(title);
                dataList.addAll(allServiceItem.homeEasyGoInfos);
            }
        }

        if (homeImageInfo != null) {
            if (homeImageInfo.hot != null && homeImageInfo.hot.size() > 0) {
                HomeHotTitle title = new HomeHotTitle(2, R.string.sz_hot, "", R.mipmap.home_hot_city);
                dataList.add(title);
                dataList.addAll(homeImageInfo.hot);
            }
        }
        return dataList;
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
                if (homeDataReqCount <= 3) {
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
            updateTopImages(bannerList);
            mViewBinding.indicators.setViewPager(mViewBinding.imageViewPager);
        }
    }

    private void updateTopImages(List<ImageInfo> tops) {
        mBannerAdapter.updateItems(tops);
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
        if (coreDataWrapper != null && DataCore.HOME == coreDataWrapper.dataType) {
            updateHomeImageData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppEngine.instance().dataCore().removeForeverObserve(this);
    }

}
