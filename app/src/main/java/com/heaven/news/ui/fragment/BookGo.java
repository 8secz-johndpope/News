package com.heaven.news.ui.fragment;

import android.os.Bundle;

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
public class BookGo extends BaseSimpleBindFragment<MainViewModel,MineBinding> {

    @Override
    public void bindModel() {

    }

    @Override
    public int initLayoutResId() {
        return R.layout.book_info;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public static BookGo newInstance(Bundle paramBundle) {
        BookGo fragment = new BookGo();
        fragment.setArguments(paramBundle);
        return fragment;
    }
}
