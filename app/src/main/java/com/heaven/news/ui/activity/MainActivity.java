package com.heaven.news.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.heaven.news.R;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.databinding.MainBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.DataCore;
import com.heaven.news.ui.adapter.FragmentPagerAdapter;
import com.heaven.news.ui.base.BaseToolBarBindActivity;
import com.heaven.news.ui.fragment.EasyGo;
import com.heaven.news.ui.fragment.Home;
import com.heaven.news.ui.fragment.Mine;
import com.heaven.news.ui.fragment.Phoenix;
import com.heaven.news.ui.fragment.Route;
import com.heaven.news.ui.vm.vmmodel.MainViewModel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FileName: com.heaven.news.ui.activity.MainActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-14 15:16
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
@com.alibaba.android.arouter.facade.annotation.Route(path= RouterUrl.ROUTER_URL_MAIN)
public class MainActivity extends BaseToolBarBindActivity<MainViewModel, MainBinding> implements Observer<DataCore.CoreDataWrapper>,ViewPager.OnPageChangeListener, View.OnClickListener {
    private List<Fragment> mainList = new ArrayList<>();

    @Override
    public int initLayoutResId() {
        return R.layout.main;
    }

    @Override
    public void bindModel() {
        mViewBinding.setLoginHandlers(this);
        mViewBinding.setViewmodel(mViewModel);
        mViewModel.requestNotice();
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        setTitle(R.string.toobar_home);
        initViewPager();
        initBottomTabLayout();
        showHomeTitle(false);
        addToolBarListener(this);
        updateData();
        AppEngine.instance().dataCore().registerDataTypeObaserver(this, this);
    }

    private void initViewPager() {
        final String[] bottomBarList = getResources().getStringArray(R.array.bottom_bar_name);
        Bundle paramBundle = new Bundle();
        paramBundle.putInt("wx_type", 1);

        mainList.add(Home.newInstance(paramBundle));
        mainList.add(Route.newInstance(paramBundle));
        mainList.add(EasyGo.newInstance(null));
        mainList.add(Phoenix.newInstance(null));
        mainList.add(Mine.newInstance(null));
        mViewBinding.viewpager.setOffscreenPageLimit(4);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(this, getSupportFragmentManager());
        adapter.insertAll(mainList, Arrays.asList(bottomBarList));
        mViewBinding.viewpager.setAdapter(adapter);
        mViewBinding.viewpager.addOnPageChangeListener(this);
        mViewBinding.tablayout.setupWithViewPager(mViewBinding.viewpager);
        mViewBinding.tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mViewBinding.tablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initBottomTabLayout() {
        final String[] bottomBarList = getResources().getStringArray(R.array.bottom_bar_name);
        int[] mBottomBarImageIds = getResourceId(R.array.bottom_bar_img);
        int tabCount = mViewBinding.tablayout.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            TextView barItem = (TextView) LayoutInflater.from(this).inflate(R.layout.main_tab_item, null);
            barItem.setText(bottomBarList[i]);
            Drawable image = getResources().getDrawable(mBottomBarImageIds[i]);
            if (image != null) {
                image.setBounds(0, 0, image.getMinimumWidth(), image.getMinimumHeight());
                barItem.setCompoundDrawables(null, image, null, null);
            }
            TabLayout.Tab tab = mViewBinding.tablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(barItem);
            }
        }
        mViewBinding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mViewBinding.tablayout));
    }

    private int[] getResourceId(int arrayId) {
        Resources res = getResources();
        TypedArray ar = res.obtainTypedArray(arrayId);
        int len = ar.length();
        int[] resIdList = new int[len];
        for (int i = 0; i < len; i++) {
            resIdList[i] = ar.getResourceId(i, 0);
        }
        ar.recycle();
        return resIdList;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (0 == i) {
            showHomeTitleBarTitle(R.string.toobar_home, false);
        } else if (1 == i) {
            showTitleBarOnlyTitle(R.string.toobar_route);
        } else if (2 == i) {
            showTitleBarOnlyTitle(R.string.toobar_easygo);
        } else if (3 == i) {
            showTitleBarOnlyTitle(R.string.toobar_phoenix);
        } else if (4 == i) {
            showMineTitleBarTitle("");
        }
        Logger.i("position---" + i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    @Override
    public void onClick(View v) {
        if (R.id.extra_function == v.getId()) {
            if (AppEngine.instance().dataCore().isLogin()) {

            } else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        }
    }


    private void updateData() {
        if (AppEngine.instance().dataCore().isLogin()) {
            setExtaTitle(AppEngine.instance().dataCore().getUserName());
        } else {
            setExtaTitle(R.string.login_regist);
        }
    }

    @Override
    public void onChanged(@Nullable DataCore.CoreDataWrapper coreDataWrapper) {
        if(coreDataWrapper != null && DataCore.LOGIN == coreDataWrapper.dataType) {
            updateData();
        }
    }

}
