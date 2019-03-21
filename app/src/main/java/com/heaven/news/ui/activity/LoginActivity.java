package com.heaven.news.ui.activity;

import android.view.View;

import com.heaven.annotation.aspect.TraceTime;
import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.news.R;
import com.heaven.news.databinding.LoginBinding;
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
    }

    @Override
    public int initLayoutResId() {
        return R.layout.login;
    }


    @Override
    public void bindModel() {
        mViewBinding.setLoginHandlers(this);
        mViewBinding.setViewModel(mViewModel);
        mViewBinding.setUserInfo(mViewModel.userInfo);
    }
}
