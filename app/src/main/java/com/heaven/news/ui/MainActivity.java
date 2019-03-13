package com.heaven.news.ui;

import android.view.View;

import com.heaven.annotation.aspect.TraceTime;
import com.heaven.base.ui.activity.BaseBindActivity;
import com.heaven.news.R;
import com.heaven.news.databinding.ActivityMainBinding;
import com.heaven.news.ui.vm.present.MainPt;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;

public class MainActivity extends BaseBindActivity<MainPt, MainViewModel, ActivityMainBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.activity_main;
    }


    @Override
    public void bindModel() {
        mViewBinding.setLoginHandlers(this);
        mViewBinding.setViewmodel(mViewModel);
    }


    @TraceTime
    public void loginAction(View view) {
        String name = mViewBinding.name.getEditText().getText().toString();
        String password = mViewBinding.password.getEditText().getText().toString();
        mViewModel.login(name,password);
        mViewModel.userName.observe(this, s -> {
            mViewBinding.userName.setText(s);
        });
    }

}
