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
import com.heaven.news.databinding.EasygoBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.manager.UserManager;
import com.heaven.news.ui.view.RecyclerViewDivider;
import com.heaven.news.ui.model.holder.EasyGoServiceHolder;
import com.heaven.news.ui.model.bean.base.EasyGoService;
import com.heaven.news.ui.model.bean.base.HomeImageInfo;
import com.heaven.news.ui.model.bean.base.ImageInfo;
import com.heaven.news.ui.model.bean.base.ServiceInfo;
import com.heaven.news.ui.model.vm.MainVm;

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
public class EasyGo extends BaseBindFragment<MainVm,EasygoBinding> implements  Observer<UserManager.CoreDataWrapper> {
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

    private void updateBannerData() {
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
            //刷新数据之后，需要重新设置是否支持自动轮播
            mViewBinding.banner.setAutoPlayAble(bannerList.size() > 1);
            mViewBinding.banner.setBannerData(R.layout.easygo_banner_item, bannerList);
        }
    }



    private void initGrid() {
        EasyGoService easyGoService = AppEngine.instance().confManager().loadEasyGoService(getContext());
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
    public void onChanged(@Nullable UserManager.CoreDataWrapper coreDataWrapper) {
        if (coreDataWrapper != null && UserManager.HOME == coreDataWrapper.dataType) {
            updateBannerData();
        }
    }
}
