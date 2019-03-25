package com.heaven.news.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.news.R;
import com.heaven.news.databinding.GuideBinding;
import com.heaven.news.ui.adapter.CardTransformer;
import com.heaven.news.ui.adapter.GuidePageAdapter;
import com.heaven.news.ui.vm.viewmodel.GuideNewModel;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * FileName: com.heaven.news.ui.activity.GuideActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-08 11:29
 *
 * @author heaven
 * @version V1.0 新用户首次进入的引导页
 */
public class GuideActivity extends BaseSimpleBindActivity<GuideNewModel, GuideBinding> {
    @Override
    public int initLayoutResId() {
        return R.layout.guide;
    }

    @Override
    public void makeContentView(View rootView) {
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        super.makeContentView(rootView);
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        initGuide();
    }

    private void initGuide() {
        ArrayList<Integer> guidePicList = getResourceId(R.array.guide_img);
        GuidePageAdapter pagerAdapter = new GuidePageAdapter(this);
        mViewBinding.guidViewpager.setAdapter(pagerAdapter);
        mViewBinding.guidViewpager.setPageTransformer(true, new CardTransformer());
        pagerAdapter.setItemClickListener(v -> {
            if(R.id.trynow == v.getId()) {
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mViewBinding.indicators.setViewPager(mViewBinding.guidViewpager, guidePicList.size());
        pagerAdapter.updatePagerData(guidePicList);
    }

    @Override
    public void bindModel() {
        mViewBinding.setGuideNew(this);
    }

    private ArrayList<Integer> getResourceId(int arrayId) {
        Resources res = getResources();
        TypedArray ar = res.obtainTypedArray(arrayId);
        int len = ar.length();
        ArrayList<Integer> resIdList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            resIdList.add(i,ar.getResourceId(i, 0));
        }
        ar.recycle();
        return resIdList;
    }
}
