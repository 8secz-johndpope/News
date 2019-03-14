package com.heaven.news.ui.home;

import android.view.View;

import com.heaven.annotation.aspect.TraceTime;
import com.heaven.news.R;
import com.heaven.news.databinding.MainBinding;
import com.heaven.news.ui.base.BaseToolBarSimpleActivity;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;

/**
 * FileName: com.heaven.news.ui.home.MainActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-14 15:16
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
public class MainActivity extends BaseToolBarSimpleActivity<MainViewModel, MainBinding> {
    @Override
    public int initLayoutResId() {
        return R.layout.main;
    }

    @Override
    public void bindModel() {
//        mViewBinding.setLoginHandlers(this);
//        mViewBinding.setViewmodel(mViewModel);
    }

    @TraceTime
    public void loginAction(View view) {
//        String name = mViewBinding.name.getEditText().getText().toString();
//        String password = mViewBinding.password.getEditText().getText().toString();
//        mViewModel.login(name,password);
//        mViewModel.userName.observe(this, s -> {
//            mViewBinding.userName.setText(s);
//        });
    }
}
