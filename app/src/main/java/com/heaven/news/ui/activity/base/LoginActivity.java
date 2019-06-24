package com.heaven.news.ui.activity.base;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

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

    int currentTab = 0;

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        showBackTitleBarTitle(R.string.welcom_login);
        AppEngine.instance().dataCore().registerDataTypeObaserver(this, this);
        initTab();
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


    private void initTab() {
        final String[] topBarList = getResources().getStringArray(R.array.login_item_name);
        if(topBarList.length > 0) {
            for(String tabName : topBarList) {
                View barItem = LayoutInflater.from(this).inflate(R.layout.login_tab_item, null);
                TextView barName = barItem.findViewById(R.id.bottom_bar_name);
                barName.setText(tabName);
                mViewBinding.loginTab.addTab(mViewBinding.loginTab.newTab().setCustomView(barItem));
            }
            mViewBinding.loginTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    updateLoginType(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    updateLoginType(tab.getPosition());

                }
            });
        }
    }

    boolean hasCancelTip;
    private void updateLoginType(int type) {
        currentTab = type;
        if(type == 0) {
            mViewBinding.count.setHint(R.string.login_phone_hint);
            mViewBinding.password.setHint(R.string.login_phone_code_hint);
            mViewBinding.cancelTip.setVisibility(View.GONE);
            mViewBinding.nameIcon.setImageResource(R.drawable.login_phone);
            mViewBinding.passwordIcon.setImageResource(R.drawable.login_code);
            mViewBinding.loginForgetPassword.setVisibility(View.GONE);
            mViewBinding.sendCode.setVisibility(View.VISIBLE);
            mViewBinding.cancelTip.setOnClickListener(v -> {
                mViewBinding.cancelTip.setVisibility(View.GONE);
                hasCancelTip = true;
            });
        } else {
            if(hasCancelTip) {
                mViewBinding.cancelTip.setVisibility(View.GONE);
            } else {
                mViewBinding.cancelTip.setVisibility(View.VISIBLE);
            }
            mViewBinding.count.setHint(R.string.login_phoenix_count_hint);
            mViewBinding.password.setHint(R.string.login_phoenix_password_hint);
            mViewBinding.count.clearFocus();
            mViewBinding.cancelTip.requestFocus();
            mViewBinding.cancelTip.setFocusableInTouchMode(true);
            mViewBinding.nameIcon.setImageResource(R.drawable.login_count);
            mViewBinding.passwordIcon.setImageResource(R.drawable.login_password);
            mViewBinding.loginForgetPassword.setVisibility(View.VISIBLE);
            mViewBinding.sendCode.setVisibility(View.GONE);
            mViewBinding.cancelTip.setOnClickListener(v -> {
                mViewBinding.cancelTip.setVisibility(View.GONE);
                hasCancelTip = true;
            });
        }
    }

    //    @Permission(value = Manifest.permission.READ_CONTACTS)
//    @Permission(value = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void login(View view) {
        String count = mViewModel.userInfo.count;
        String passwords = mViewModel.userInfo.password;
        if(TextUtils.isEmpty(count) || TextUtils.isEmpty(passwords)) {
            return;
        }
        if(currentTab == 0) {
            long taskId = AppEngine.instance().dataCore().loginByVerifyCode(count,passwords);
            mViewModel.mNetManager.showLoadingDialog(this,true,taskId);
        } else {
            long taskId = AppEngine.instance().dataCore().login(count,passwords);
            mViewModel.mNetManager.showLoadingDialog(this,true,taskId);
        }

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
