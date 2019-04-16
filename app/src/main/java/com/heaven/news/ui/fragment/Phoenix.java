package com.heaven.news.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.fragment.BaseBindFragment;
import com.heaven.base.ui.view.widget.banner.XBanner;
import com.heaven.base.ui.view.widget.banner.transformers.Transformer;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.databinding.PhoenixBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.DataCore;
import com.heaven.news.ui.vm.holder.PhoenixServiceHolder;
import com.heaven.news.ui.vm.model.base.HomeImageInfo;
import com.heaven.news.ui.vm.model.base.ImageInfo;
import com.heaven.news.ui.vm.model.base.PhoenixService;
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
public class Phoenix extends BaseBindFragment<MainViewModel, PhoenixBinding> implements Observer<DataCore.CoreDataWrapper> {
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
        AppEngine.instance().dataCore().registerDataTypeObaserver(this, this);
        DataCore.CoreDataWrapper coreDataWrapper = AppEngine.instance().dataCore().getCoreDataWrapper();
        initBaner();
        updateUserInfo(coreDataWrapper);
        initService();
    }

    private void initBaner() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtil.getScreenWidth(getContext()) / 2);
        mViewBinding.banner.setLayoutParams(layoutParams);
        //修改切换动画
        mViewBinding.banner.setPageTransformer(Transformer.Default);
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
            if (model instanceof ImageInfo) {
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

    private void updateBannerData() {
        HomeImageInfo homeImageInfo = AppEngine.instance().dataCore().getHomeConfigData();
        List<ImageInfo> bannerList = new ArrayList<>();
        if (mViewBinding != null) {
            if (homeImageInfo != null) {
                if (homeImageInfo.phoenix != null && homeImageInfo.phoenix.size() > 0) {
                    bannerList = homeImageInfo.phoenix;
                }
            }
            if (bannerList.size() == 0) {
                bannerList.add(new ImageInfo());
            }
            //刷新数据之后，需要重新设置是否支持自动轮播
            mViewBinding.banner.setAutoPlayAble(bannerList.size() > 1);
            mViewBinding.banner.setBannerData(R.layout.phoenix_banner_item, bannerList);
        }
    }

    private void initService() {
        PhoenixService phoenixService = AppEngine.instance().dataCore().loadPhoenixService(getContext());
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        BaseAdapter<ServiceInfo> mServiceAdapter = new BaseAdapter<>(getContext());
        mServiceAdapter.register(new PhoenixServiceHolder(ServiceInfo.class, R.layout.home_service_item));

        mViewBinding.service.setLayoutManager(layout);
        mViewBinding.service.setAdapter(mServiceAdapter);
        mViewBinding.service.setHasFixedSize(true);
        mViewBinding.service.setLongClickable(true);
        if (phoenixService != null && phoenixService.phoenixServiceInfos != null) {
            mServiceAdapter.updateItems(phoenixService.phoenixServiceInfos);
        }
        mViewBinding.service.setAutoLoop(false);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public static Phoenix newInstance(Bundle paramBundle) {
        Phoenix fragment = new Phoenix();
        fragment.setArguments(paramBundle);
        return fragment;
    }

    @Override
    public void onChanged(@Nullable DataCore.CoreDataWrapper coreDataWrapper) {
        if (coreDataWrapper != null) {
            if (DataCore.LOGIN == coreDataWrapper.dataType) {
                updateUserInfo(coreDataWrapper);
            } else if (DataCore.MILE == coreDataWrapper.dataType) {
                updateUserInfo(coreDataWrapper);
            } else if (DataCore.HOME == coreDataWrapper.dataType) {
                updateBannerData();
            }
        }
    }

    private void updateUserInfo(DataCore.CoreDataWrapper coreDataWrapper) {
        if(coreDataWrapper == null) {
            return;
        }
        mViewBinding.userName.setText(coreDataWrapper.userName);
        mViewBinding.userId.setText(coreDataWrapper.idNumber);
        if(coreDataWrapper.cardLevelImgRes != 0) {
            mViewBinding.cardLevel.setImageResource(coreDataWrapper.cardLevelImgRes);
        }

        if(coreDataWrapper.sexHeaderRes != 0) {
            mViewBinding.userSexHeader.setImageResource(coreDataWrapper.sexHeaderRes);
        }

        mViewBinding.totalMile.setText(coreDataWrapper.userMile);
        mViewBinding.currMouthInvalid.setText(coreDataWrapper.expiredMiles);
        mViewBinding.nextMouthInvalid.setText(coreDataWrapper.nextExpiredMiles);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppEngine.instance().dataCore().removeForeverObserve(this);
    }
}
