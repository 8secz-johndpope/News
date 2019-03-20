package com.heaven.news.ui.home;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.heaven.annotation.aspect.TraceTime;
import com.heaven.news.R;
import com.heaven.news.databinding.MainBinding;
import com.heaven.news.ui.adapter.FragmentPagerAdapter;
import com.heaven.news.ui.base.BaseToolBarSimpleActivity;
import com.heaven.news.ui.fragment.EasyGo;
import com.heaven.news.ui.fragment.Home;
import com.heaven.news.ui.fragment.Mine;
import com.heaven.news.ui.fragment.Phoenix;
import com.heaven.news.ui.fragment.Route;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FileName: com.heaven.news.ui.home.MainActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-14 15:16
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
public class MainActivity extends BaseToolBarSimpleActivity<MainViewModel, MainBinding> implements ViewPager.OnPageChangeListener {
    private List<Fragment> mainList = new ArrayList<>();

    @Override
    public int initLayoutResId() {
        return R.layout.main;
    }

    @Override
    public void bindModel() {
        mViewBinding.setLoginHandlers(this);
        mViewBinding.setViewmodel(mViewModel);
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        setTitle(R.string.toobar_home);
        setExtaTitle(R.string.login_regist);
        initViewPager();
        initBottomTabLayout();
        showHomeTitle(false);
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
            TextView barItem = (TextView) LayoutInflater.from(this).inflate(R.layout.bottom_tab_item, null);
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

    @TraceTime
    public void loginAction(View view) {
//        String name = mViewBinding.name.getEditText().getText().toString();
//        String password = mViewBinding.password.getEditText().getText().toString();
//        mViewModel.login(name,password);
//        mViewModel.userName.observe(this, s -> {
//            mViewBinding.userName.setText(s);
//        });
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (0 == i) {
            showHomeTitleBarTitle(R.string.toobar_home,false);
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
}
