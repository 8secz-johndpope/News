package com.heaven.news.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.BaseMultAdapter;
import com.heaven.base.ui.fragment.BaseBindFragment;
import com.heaven.base.ui.view.widget.banner.XBanner;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.databinding.HomeBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.manager.UserManager;
import com.heaven.news.ui.view.RecyclerViewDivider;
import com.heaven.news.ui.model.holder.HomeBookGo;
import com.heaven.news.ui.model.holder.HomeBookGoBack;
import com.heaven.news.ui.model.holder.HomeBookMult;
import com.heaven.news.ui.model.holder.HomeHotHolder;
import com.heaven.news.ui.model.holder.HomeHotTitleHoler;
import com.heaven.news.ui.model.holder.HomeNoticeHolder;
import com.heaven.news.ui.decoration.ItemDecoration;
import com.heaven.news.ui.model.bean.base.BookData;
import com.heaven.news.ui.model.bean.base.HomeHotTitle;
import com.heaven.news.ui.model.holder.HomeRecHolder;
import com.heaven.news.ui.model.holder.HomeServiceHolder;
import com.heaven.news.ui.model.bean.base.HomeService;
import com.heaven.news.ui.model.bean.base.HomeImageInfo;
import com.heaven.news.ui.model.bean.base.ServiceInfo;
import com.heaven.news.ui.model.bean.base.ImageInfo;
import com.heaven.news.ui.model.bean.base.ServiceItem;
import com.heaven.news.ui.model.vm.MainVm;
import com.neusoft.szair.model.flightsearch.flightSearchDomestic;
import com.neusoft.szair.model.flightsearch.flightSearchDomesticConditionVO;
import com.neusoft.szair.model.flightsearch.tripInfoVO;
import com.neusoft.szair.model.noticelist.noticeInfoListVO;
import com.orhanobut.logger.Logger;

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
public class Home extends BaseBindFragment<MainVm, HomeBinding> implements Observer<UserManager.CoreDataWrapper> {
    BaseAdapter<ServiceInfo> mServiceAdapter;
    BaseMultAdapter recommendAdapter;
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

    @Override
    public void bindModel() {
        mViewBinding.setMainViewModel(mViewModel);
        mViewBinding.setHomeFragment(this);
    }

    private void initTopBanner() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtil.getScreenWidth(getContext()) / 2);
        mViewBinding.banner.setLayoutParams(layoutParams);
        //修改切换动画
//        mViewBinding.banner.setPageTransformer(Transformer.Default);
        mViewBinding.banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(getActivity(), "点击了第" + (position + 1) + "图片", Toast.LENGTH_SHORT).show();
            }
        });

        //加载广告图片
        mViewBinding.banner.loadImage((banner, model, view, position) -> {
            ImageView imageView = view.findViewById(R.id.banner_image);
            ImageInfo iamgeInfo;
            if(model instanceof ImageInfo) {
                iamgeInfo = (ImageInfo) model;
            } else {
                return;
            }
            if (TextUtils.isEmpty(iamgeInfo.pic)) {
                imageView.setImageResource(R.mipmap.hint_banner);
            } else {
                Glide.with(imageView.getContext())
                        .load(iamgeInfo.pic)
                        .placeholder(R.mipmap.hint_banner) // can also be a drawable
                        .error(R.mipmap.hint_banner) // will be displayed if the image cannot be loaded
                        .into(imageView);
            }
        });
        updateBannerData();
    }

    private void initBookTab() {
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        final String[] bottomBarList = getResources().getStringArray(R.array.book_type);
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
        mViewBinding.bookArea.viewpager.setLayoutManager(layout);
        mViewBinding.bookArea.viewpager.setAdapter(adapter);
        mViewBinding.bookArea.bookTab.setScrollPosition(0, 0f, true);
        mViewBinding.bookArea.viewpager.bindTabLayout(mViewBinding.bookArea.bookTab);
        mViewBinding.bookArea.viewpager.addOnPageChangedListener((oldPosition, newPosition) -> {
//            tabLayout.selectTab(tabLayout.getTabAt(newPosition), 1);
        });
//        TabBindRecyclerUtil.bind(bookPager,tabLayout);

        if(bottomBarList.length > 0) {
            for(String tabName : bottomBarList) {
                View barItem = LayoutInflater.from(getContext()).inflate(R.layout.bottom_tab_item, null);
                TextView barName = barItem.findViewById(R.id.bottom_bar_name);
                barName.setText(tabName);
                mViewBinding.bookArea.bookTab.addTab(mViewBinding.bookArea.bookTab.newTab().setCustomView(barItem));
            }
        }

    }

    private void initService() {
        HomeService allServiceItem = AppEngine.instance().confManager().loadHomeService(getContext());
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mServiceAdapter = new BaseAdapter<>(getContext());
        mServiceAdapter.register(new HomeServiceHolder(ServiceInfo.class, R.layout.home_service_item));

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
        recommendAdapter = new BaseMultAdapter(getContext(), organizeRecData());
        recommendAdapter.register(new HomeRecHolder(ServiceItem.class, 0));
        recommendAdapter.register(new HomeHotHolder(ImageInfo.class, R.layout.home_hot));
        recommendAdapter.register(new HomeHotTitleHoler(HomeHotTitle.class, R.layout.home_hot_title));
        mViewBinding.recommendService.addItemDecoration(new ItemDecoration(ScreenUtil.dip2px(getContext(), 5)));
        mViewBinding.recommendService.setAdapter(recommendAdapter);

    }

    private void initHomeNotice() {
        BaseAdapter<noticeInfoListVO> adapter = new BaseAdapter<>(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        adapter.register(new HomeNoticeHolder(noticeInfoListVO.class,R.layout.home_notice_item));
        mViewBinding.noticeList.addItemDecoration(new RecyclerViewDivider(getContext(),2,R.color.gray_e4));
        mViewBinding.noticeList.setLayoutManager(manager);
        mViewBinding.noticeList.setAdapter(adapter);
        if(mViewModel.noticeList == null) {
            mViewBinding.noticeArea.setVisibility(View.GONE);
            mViewModel.requestNotice(noticeInfoListVOS -> {
                adapter.updateItems(noticeInfoListVOS);
                mViewBinding.noticeArea.setVisibility(View.VISIBLE);
            });
        } else {
            mViewBinding.noticeArea.setVisibility(View.VISIBLE);
            adapter.updateItems(mViewModel.noticeList);
        }
    }

    private ArrayList organizeRecData() {
        ArrayList dataList = new ArrayList();
        HomeService allServiceItem = AppEngine.instance().confManager().loadHomeService(getContext());
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public void flightSearch(View view) {
        List<tripInfoVO> flightLIst = new ArrayList<>();
        tripInfoVO flightVo = new tripInfoVO();
        flightVo._ORG_CITY = "SZX";
        flightVo._DST_CITY = "PEK";
        flightVo._DEPARTURE_DATE = "2019-06-25";
        flightVo._INDEX = "1";
        flightLIst.add(flightVo);
        flightSearchDomestic req = new flightSearchDomestic();
        req._FLIGHT_SEARCH_CONDITION = new flightSearchDomesticConditionVO();
        req._FLIGHT_SEARCH_CONDITION._TRIP_INFO_LIST = flightLIst;
        req._FLIGHT_SEARCH_CONDITION._QUERY_FLAG = "DC";
        req._FLIGHT_SEARCH_CONDITION._USER_ID = AppEngine.instance().dataCore().getCoreDataWrapper().userId;
        req._FLIGHT_SEARCH_CONDITION._CRM_MEMBER_ID = AppEngine.instance().dataCore().getCoreDataWrapper().crmId;

        long taskId = mViewModel.flightSearch(req, response -> {
            Logger.d(response);
            AppEngine.instance().getNetManager().disMassLoading();
        });
        AppEngine.instance().getNetManager().showLoadingDialog(getContext(),true,taskId);

    }

    private void updateBannerData() {
        HomeImageInfo homeImageInfo = AppEngine.instance().dataCore().getHomeConfigData();
        List<ImageInfo> bannerList = new ArrayList<>();
        if (mViewBinding != null) {
            if (homeImageInfo != null) {
                if (homeImageInfo.top != null && homeImageInfo.top.size() > 0) {
                    bannerList = homeImageInfo.top;
                }
            }
            if(bannerList.size() == 0) {
                bannerList.add(new ImageInfo());
            }
            //刷新数据之后，需要重新设置是否支持自动轮播
            mViewBinding.banner.setIsClipChildrenMode(true);
            mViewBinding.banner.setAutoPlayAble(bannerList.size() > 1);
            mViewBinding.banner.setBannerData(R.layout.home_banner_item, bannerList);
        }
    }

    public static Home newInstance(Bundle paramBundle) {
        Home fragment = new Home();
        fragment.setArguments(paramBundle);
        return fragment;
    }

    @Override
    public void onChanged(@Nullable UserManager.CoreDataWrapper coreDataWrapper) {
        if (coreDataWrapper != null) {
            if(UserManager.HOME == coreDataWrapper.dataType) {
                updateBannerData();
            } else if(UserManager.LOGIN == coreDataWrapper.dataType) {
                recommendAdapter.notifyItemChanged(2,1);
            } else if(UserManager.MILE == coreDataWrapper.dataType) {
                recommendAdapter.notifyItemChanged(2,1);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppEngine.instance().dataCore().removeForeverObserve(this);
    }

}
