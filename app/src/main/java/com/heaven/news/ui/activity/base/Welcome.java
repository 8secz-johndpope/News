package com.heaven.news.ui.activity.base;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.heaven.news.R;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.databinding.WelcomeBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.manager.ConfigManager;
import com.heaven.news.engine.manager.UserManager;
import com.heaven.news.ui.base.BaseToolBarBindActivity;
import com.heaven.news.ui.model.bean.base.VersionUpdate;
import com.heaven.news.ui.model.vm.WelecomVm;
import com.orhanobut.logger.Logger;

/**
 * FileName: com.heaven.news.ui.activity.base.Welcome.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-07 17:57
 *
 * @author heaven
 * @version V1.0 欢迎页
 */
public class Welcome extends BaseToolBarBindActivity<WelecomVm, WelcomeBinding> implements Observer<ConfigManager.ConfigWrapper> {

    @Override
    public int iniTitleBarResId() {
        return 0;
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public int initLayoutResId() {
        return R.layout.welcome;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppEngine.instance().confManager().registerDataTypeObaserver(this, this);
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
    }

    @Override
    protected void initMmersionTitleBar() {
    }

    @Override
    public void makeContentView(View rootView) {
        setContentView(rootView);
    }

    @Override
    public void bindModel() {
        mViewBinding.setViewmodel(mViewModel);
    }

    private void processNext(VersionUpdate updateInfo) {
        if (updateInfo.isNetError) {
            toNextPage(updateInfo);
        } else {
            if (updateInfo.isServiceMainta) {
                toNextPage(updateInfo);
            } else if (updateInfo.isForceUpdate) {
                toNextPage(updateInfo);
            } else if (updateInfo.needUpdate) {
                toNextPage(updateInfo);
            } else {
                toNextPage(updateInfo);
            }
        }
        Logger.i("test---------" + updateInfo.toString());
    }


    private void toNextPage(VersionUpdate updateInfo) {
        if (updateInfo.nextGuidePage) {
            toGuidePage();
        } else if (updateInfo.isShowAd) {
            if (updateInfo.adInfo != null) {
                toAdPage();
            } else {
                toMainPage();
            }
        } else {
            toMainPage();
        }
//        mViewModel.removeUpdateInfoObserver(this);
        finish();
    }

    private void toAdPage() {
        ARouter.getInstance().build(RouterUrl.ROUTER_URL_AD).navigation();
    }

    private void toMainPage() {
        ARouter.getInstance().build(RouterUrl.ROUTER_URL_MAIN).navigation();
    }

    private void toGuidePage() {
        ARouter.getInstance().build(RouterUrl.ROUTER_URL_GUIDE).navigation();
    }

    @Override
    public void onChanged(@Nullable ConfigManager.ConfigWrapper configWrapper) {
        if(configWrapper != null && UserManager.VERSION == configWrapper.dataType) {
            if(configWrapper.versionUpdate != null) {
                processNext(configWrapper.versionUpdate);
            } else {
                toMainPage();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(AppEngine.instance().confManager().isRequestVersionFinish) {
            ConfigManager.ConfigWrapper configWrapper = AppEngine.instance().confManager().getConfigWrapper();
            configWrapper.dataType = UserManager.VERSION;
            onChanged(configWrapper);
        }

    }
}
