package com.heaven.news.ui;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.heaven.annotation.aspect.Permission;
import com.heaven.annotation.aspect.TraceTime;
import com.heaven.base.ui.activity.BaseBindActivity;
import com.heaven.news.R;
import com.heaven.news.databinding.ActivityMainBinding;
import com.heaven.news.vm.present.TestPt;
import com.heaven.news.vm.viewmodel.TestContextViewModel;

public class MainActivity extends BaseBindActivity<TestPt, TestContextViewModel, ActivityMainBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initLayoutResId() {
        return R.layout.activity_main;
    }


    @Override
    public void initView() {
        mViewBinding.setLoginHandlers(this);
    }

    @Override
    public void bindModel() {

    }


    @TraceTime
    public void loginAction(View view) {
        String name = mViewBinding.name.getEditText().getText().toString();
        String password = mViewBinding.password.getEditText().getText().toString();
        mViewModel.login(name,password);
//        startVideo();
//        virtualApk();
//        mPresenter.login(name,password);
//        getWeatherbyCityName(name);
        mViewModel.getUserName().observe(this, s -> {
            mViewBinding.userName.setText(s);
        });
    }

}
