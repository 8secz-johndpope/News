package com.heaven.news.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.heaven.base.ui.fragment.BaseSimpleBindFragment;
import com.heaven.news.R;
import com.heaven.news.databinding.MineBinding;
import com.heaven.news.ui.view.AutofitHeightViewPager;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;

/**
 * FileName: com.heaven.news.ui.fragment.Home.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-17 14:16
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class BookMult extends BaseSimpleBindFragment<MainViewModel,MineBinding> {

    AutofitHeightViewPager.ViewPosition viewPosition;

    @Override
    public void bindModel() {

    }

    @Override
    public int initLayoutResId() {
        return R.layout.book_mult;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        if(viewPosition != null ) {
            viewPosition.setViewPosition(rootView,2);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public static BookMult newInstance(Bundle paramBundle) {
        BookMult fragment = new BookMult();
        if(paramBundle != null) {
            paramBundle.putInt(AutofitHeightViewPager.POSITION, 2);
            fragment.setArguments(paramBundle);
        }
        return fragment;
    }

    public void setViewPosition(AutofitHeightViewPager.ViewPosition viewPosition) {
        this.viewPosition = viewPosition;
    }
}
