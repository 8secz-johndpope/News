package com.heaven.news.ui.activity;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.view.View;

import com.heaven.annotation.aspect.TraceTime;
import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.news.R;
import com.heaven.news.databinding.LoginBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.base.BaseToolBarSimpleActivity;
import com.heaven.news.ui.vm.viewmodel.LoginViewModel;

/**
 * @author heaven
 */
public class LoginActivity extends BaseToolBarSimpleActivity<LoginViewModel, LoginBinding> {

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        showBackTitleBarTitle(R.string.welcom_login);
        AppEngine.getInstance().dataCore().registLoginObserver(this, s -> {
            finish();
        });
    }

    @Override
    public int initLayoutResId() {
        return R.layout.login;
    }

    public void login() {
        mViewModel.login();
    }

    @Override
    public void bindModel() {
        mViewBinding.setLoginHandlers(this);
        mViewBinding.setViewModel(mViewModel);
        mViewBinding.setUserInfo(mViewModel.userInfo);
    }
}
