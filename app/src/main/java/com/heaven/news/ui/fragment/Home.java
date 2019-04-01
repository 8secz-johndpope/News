package com.heaven.news.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.BaseMultAdapter;
import com.heaven.base.ui.fragment.BaseSimpleBindFragment;
import com.heaven.base.ui.view.widget.banner.RecyclerViewPager;
import com.heaven.base.ui.view.widget.banner.RecyclerViewPagerListener;
import com.heaven.base.ui.view.widget.banner.LoopRecyclerViewPager;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.databinding.HomeBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.DataCore;
import com.heaven.news.ui.vm.holder.HomeBanner;
import com.heaven.news.ui.vm.holder.HomeBookGo;
import com.heaven.news.ui.vm.holder.HomeBookGoBack;
import com.heaven.news.ui.vm.holder.HomeBookMult;
import com.heaven.news.ui.vm.holder.HomeHotHolder;
import com.heaven.news.ui.vm.holder.HomeHotTitleHoler;
import com.heaven.news.ui.vm.holder.HomeNoticeHolder;
import com.heaven.news.ui.vm.holder.ItemDecoration;
import com.heaven.news.ui.vm.model.BookData;
import com.heaven.news.ui.vm.model.HomeHotTitle;
import com.heaven.news.ui.vm.holder.HomeRecHolder;
import com.heaven.news.ui.vm.holder.HomeServiceHolder;
import com.heaven.news.ui.vm.model.AllServiceItem;
import com.heaven.news.ui.vm.model.HomeImageInfo;
import com.heaven.news.ui.vm.model.HomeServiceItem;
import com.heaven.news.ui.vm.model.ImageInfo;
import com.heaven.news.ui.vm.model.ServiceItem;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;
import com.neusoft.szair.model.noticelist.noticeInfoListVO;

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
public class Home extends BaseSimpleBindFragment<MainViewModel, HomeBinding> implements ViewPager.OnPageChangeListener, Observer<DataCore.CoreDataWrapper> {
    BaseAdapter<ImageInfo> mBannerAdapter;
    BaseAdapter<HomeServiceItem> mServiceAdapter;

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
        initHomeNotice();
    }

    private void initTopBanner() {
        LoopRecyclerViewPager mRecyclerView = mViewBinding.imageViewPager;
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mBannerAdapter = new BaseAdapter<>(getContext());
        mBannerAdapter.register(new HomeBanner(ImageInfo.class, R.layout.banner_item));
        mRecyclerView.setLayoutManager(layout);
//        mRecyclerView.addItemDecoration(new BannerItemDecoration(4));
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

    private void initBookTab() {
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        TabLayout tabLayout = mViewBinding.bookArea.findViewById(R.id.book_tab);
        final String[] bottomBarList = getResources().getStringArray(R.array.book_type);
        RecyclerViewPager bookPager = mViewBinding.bookArea.findViewById(R.id.viewpager);
        BookData go = new BookData(1,mViewModel);
        BookData goBack = new BookData(2,mViewModel);
        BookData mult = new BookData(3,mViewModel);
        ArrayList<BookData>  bookList = new ArrayList<>();
        bookList.add(go);
        bookList.add(goBack);
        bookList.add(mult);
        BaseAdapter<BookData> adapter = new BaseAdapter<>(getContext(),bookList);
        adapter.register(new HomeBookGo(BookData.class,R.layout.book_go));
        adapter.register(new HomeBookGoBack(BookData.class,R.layout.book_go_back));
        adapter.register(new HomeBookMult(BookData.class,R.layout.book_mult));
        bookPager.setLayoutManager(layout);
        bookPager.setAdapter(adapter);
        tabLayout.setScrollPosition(0, 0f, true);
        bookPager.bindTabLayout(tabLayout);
        bookPager.addOnPageChangedListener((oldPosition, newPosition) -> {
//            tabLayout.selectTab(tabLayout.getTabAt(newPosition), 1);
        });
//        TabBindRecyclerUtil.bind(bookPager,tabLayout);

        if(bottomBarList.length > 0) {
            for(String tabName : bottomBarList) {
                View barItem = LayoutInflater.from(getContext()).inflate(R.layout.bottom_tab_item, null);
                ViewGroup.LayoutParams params = barItem.getLayoutParams();
                TextView barName = barItem.findViewById(R.id.bottom_bar_name);
                barName.setText(tabName);
                tabLayout.addTab(tabLayout.newTab().setCustomView(barItem));
            }
        }

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

    private void initHomeNotice() {
        BaseAdapter<noticeInfoListVO> adapter = new BaseAdapter<>(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        adapter.register(new HomeNoticeHolder(noticeInfoListVO.class,R.layout.home_notice_item));
        mViewBinding.noticeList.setLayoutManager(manager);
        mViewBinding.noticeList.setAdapter(adapter);
        mViewModel.observeNoticeList(this, noticeInfoListVOS -> {
            adapter.updateItems(noticeInfoListVOS);
            mViewBinding.noticeArea.setVisibility(View.VISIBLE);
        });
        if(mViewModel.noticeList == null) {
            mViewBinding.noticeArea.setVisibility(View.GONE);
            mViewModel.requestNotice();
        } else {
            mViewBinding.noticeArea.setVisibility(View.VISIBLE);
            adapter.updateItems(mViewModel.noticeList);
        }
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
