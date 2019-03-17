package com.heaven.news.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.heaven.annotation.aspect.TraceTime;
import com.heaven.news.R;
import com.heaven.news.databinding.MainBinding;
import com.heaven.news.ui.base.BaseToolBarSimpleActivity;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;

import java.util.ArrayList;
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
public class MainActivity extends BaseToolBarSimpleActivity<MainViewModel, MainBinding> {
    private List<Fragment> mainList = new ArrayList<>();

    @Override
    public int initLayoutResId() {
        return R.layout.main;
    }

    @Override
    public void bindModel() {
//        mViewBinding.setLoginHandlers(this);
//        mViewBinding.setViewmodel(mViewModel);
    }

    @Override
    public void initView(View rootView) {
        initViewPager();
        initBottomTabLayout();
    }

    private void initViewPager() {
//        final String[] bottomBarList = getResources().getStringArray(R.array.bottom_bar_name);
//        Bundle paramBundle = new Bundle();
//        paramBundle.putInt("wx_type",1);
//
//        mainList.add(HomeItem.newInstance(paramBundle));
//        mainList.add(Order.newInstance(paramBundle));
//        mainList.add(Chat.newInstance(null));
//        mainList.add(Mine.newInstance(null));
//        mViewBinding.viewpager.setOffscreenPageLimit(4);
//        FragmentPagerAdapter adapter = new FragmentPagerAdapter(this,getSupportFragmentManager());
//        adapter.insertAll(mainList, Arrays.asList(bottomBarList));
//        mViewBinding.viewpager.setAdapter(adapter);
//        mViewBinding.tablayout.setupWithViewPager(mViewBinding.viewpager);
//        mViewBinding.tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        mViewBinding.tablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initBottomTabLayout() {
//        final String[] bottomBarList = getResources().getStringArray(R.array.bottom_bar_name);
//        int[] mBottomBarImageIds = getResourceId(R.array.bottom_bar_img);
//        int tabCount = mViewBinding.tablayout.getTabCount();
//        for (int i = 0; i < tabCount; i++) {
//            TextView barItem = (TextView) LayoutInflater.from(this).inflate(R.layout.bottom_tab_item, null);
//            barItem.setText(bottomBarList[i]);
//            Drawable image = this.getDrawable(mBottomBarImageIds[i]);
//            if(image != null) {
//                image.setBounds(0, 0, image.getMinimumWidth(), image.getMinimumHeight());
//                barItem.setCompoundDrawables(null,image, null, null);
//            }
//            TabLayout.Tab tab = mViewBinding.tablayout.getTabAt(i);
//            if(tab != null) {
//                tab.setCustomView(barItem);
//            }
//        }
//        mViewBinding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mViewBinding.tablayout));
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
}
