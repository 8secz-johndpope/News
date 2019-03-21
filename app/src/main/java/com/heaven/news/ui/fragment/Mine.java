package com.heaven.news.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.heaven.base.ui.fragment.BaseSimpleBindFragment;
import com.heaven.news.R;
import com.heaven.news.databinding.MineBinding;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;

/**
 * FileName: com.heaven.news.ui.fragment.Home.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-17 14:16
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class Mine extends BaseSimpleBindFragment<MainViewModel,MineBinding> {
    @Override
    public void initView(View rootView) {

    }

    @Override
    public void bindModel() {

    }

    @Override
    public int initLayoutResId() {
        return R.layout.mine;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public static  Mine newInstance(Bundle paramBundle) {
        Mine fragment = new Mine();
        fragment.setArguments(paramBundle);
        return fragment;
    }
}
