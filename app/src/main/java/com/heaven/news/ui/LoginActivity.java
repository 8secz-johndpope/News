package com.heaven.news.ui;

import android.view.View;

import com.heaven.annotation.aspect.TraceTime;
import com.heaven.base.ui.activity.BaseBindActivity;
import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.news.R;
import com.heaven.news.databinding.ActivityMainBinding;
import com.heaven.news.databinding.LoginBinding;
import com.heaven.news.vm.present.MainPt;
import com.heaven.news.vm.viewmodel.LoginViewModel;
import com.heaven.news.vm.viewmodel.MainViewModel;

/**
 * @author heaven
 */
public class LoginActivity extends BaseSimpleBindActivity<LoginViewModel, LoginBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.login;
    }


    @Override
    public void bindModel() {
        mViewBinding.setLoginHandlers(this);
        mViewBinding.setViewModel(mViewModel);
        mViewBinding.setUserInfoWrapper(mViewModel.userInfoWrapper);
    }


    @TraceTime
    public void loginAction(View view) {
        String name = mViewBinding.name.getText().toString();
        String password = mViewBinding.password.getText().toString();
        mViewModel.login();
        mViewModel.userName.observe(this, s -> {
            mViewBinding.userName.setText(s);
        });
    }

}
