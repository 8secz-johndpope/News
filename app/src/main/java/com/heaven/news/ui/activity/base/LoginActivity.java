package com.heaven.news.ui.activity.base;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.heaven.news.R;
import com.heaven.news.consts.Constants;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.databinding.LoginBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.manager.UserManager;
import com.heaven.news.ui.base.BaseToolBarBindActivity;
import com.heaven.news.ui.model.vm.LoginVm;

/**
 * @author heaven
 */
@Route(path = RouterUrl.ROUTER_URL_LOGIN)
public class LoginActivity extends BaseToolBarBindActivity<LoginVm, LoginBinding> implements Observer<UserManager.CoreDataWrapper> {

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        showBackTitleBarTitle(R.string.welcom_login);
        AppEngine.instance().dataCore().registerDataTypeObaserver(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    //    @Permission(value = Manifest.permission.READ_CONTACTS)
//    @Permission(value = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void login(View view) {
        String count = mViewModel.userInfo.count;
        String passwords = mViewModel.userInfo.password;
        if(TextUtils.isEmpty(count) || TextUtils.isEmpty(passwords)) {
            return;
        }
        long taskId = AppEngine.instance().dataCore().login(count,passwords);
        mViewModel.mNetManager.showLoadingDialog(this,true,taskId);

    }

    @Override
    public void onChanged(@Nullable UserManager.CoreDataWrapper coreDataWrapper) {
        if (coreDataWrapper != null && UserManager.LOGIN == coreDataWrapper.dataType) {
            mViewModel.mNetManager.disMassLoading();
            if (coreDataWrapper.isSuccess) {
                if (AppEngine.instance().dataCore().isLogin()) {
                    AppEngine.instance().getDataSource().setSharePreBoolean(Constants.ISAUTOLOGIN, true);
                    finish();
                }
            }
        }
    }
}
