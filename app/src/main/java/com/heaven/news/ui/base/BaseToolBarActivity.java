package com.heaven.news.ui.base;

import android.databinding.ViewDataBinding;

import com.heaven.base.presenter.BasePresenter;
import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.base.vm.BaseViewModel;

/**
 * FileName: com.heaven.news.ui.base.BaseToolBarActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-14 12:50
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class BaseToolBarActivity<P extends BasePresenter, VM extends BaseViewModel<P>, B extends ViewDataBinding> extends BaseSimpleBindActivity<VM,B> {

    @Override
    public int iniTitleBarResId() {
        return super.iniTitleBarResId();
    }
}
