package com.heaven.news.ui.base;

import android.databinding.ViewDataBinding;

import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.base.vm.BaseViewModel;
import com.heaven.news.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * FileName: com.heaven.news.ui.base.BaseToolBarActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-14 12:50
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class BaseToolBarSimpleActivity<VM extends BaseViewModel, B extends ViewDataBinding> extends BaseSimpleBindActivity {

    @Override
    public int initToolBarResId() {
        return R.layout.title_bar;
    }

    @Override
    protected void setBarColor(SystemBarTintManager tintManager) {
        tintManager.setStatusBarTintResource(R.mipmap.toolbar_bg);
    }
}
