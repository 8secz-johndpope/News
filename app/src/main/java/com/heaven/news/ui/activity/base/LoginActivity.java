package com.heaven.news.ui.activity.base;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.heaven.news.R;
import com.heaven.news.consts.Constants;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.databinding.LoginBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.manager.DataCoreManager;
import com.heaven.news.ui.base.BaseToolBarBindActivity;
import com.heaven.news.ui.vm.vmmodel.LoginViewModel;

/**
 * @author heaven
 */
@Route(path = RouterUrl.ROUTER_URL_LOGIN)
public class LoginActivity extends BaseToolBarBindActivity<LoginViewModel, LoginBinding> implements Observer<DataCoreManager.CoreDataWrapper> {

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

    @Override
    public void onChanged(@Nullable DataCoreManager.CoreDataWrapper coreDataWrapper) {
        if (coreDataWrapper != null && DataCoreManager.LOGIN == coreDataWrapper.dataType) {
            if (coreDataWrapper.isSuccess) {
                if (AppEngine.instance().dataCore().isLogin()) {
                    AppEngine.instance().getDataSource().setSharePreBoolean(Constants.ISAUTOLOGIN, true);
                    finish();
                }
            }
        }
    }
}
