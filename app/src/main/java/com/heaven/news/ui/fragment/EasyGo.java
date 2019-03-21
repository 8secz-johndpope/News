package com.heaven.news.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.heaven.base.ui.fragment.BaseSimpleBindFragment;
import com.heaven.news.R;
import com.heaven.news.databinding.EasygoBinding;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;

/**
 * FileName: com.heaven.news.ui.fragment.Home.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-17 14:16
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class EasyGo extends BaseSimpleBindFragment<MainViewModel,EasygoBinding> {

    @Override
    public void bindModel() {

    }

    @Override
    public int initLayoutResId() {
        return R.layout.easygo;
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
}
