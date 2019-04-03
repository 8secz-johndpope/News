package com.heaven.news.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.fragment.BaseSimpleBindFragment;
import com.heaven.base.ui.view.widget.banner.XBanner;
import com.heaven.base.ui.view.widget.banner.transformers.Transformer;
import com.heaven.base.ui.view.widget.recyclerPager.LoopRecyclerViewPager;
import com.heaven.base.ui.view.widget.recyclerPager.RecyclerViewPagerListener;
import com.heaven.base.utils.ScreenUtil;
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
        initBaner();
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
                if (homeImageInfo.phoenix != null && homeImageInfo.phoenix.size() > 0) {
                    bannerList = homeImageInfo.phoenix;
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
