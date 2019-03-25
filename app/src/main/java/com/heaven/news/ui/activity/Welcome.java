package com.heaven.news.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.news.R;
import com.heaven.news.consts.Constants;
import com.heaven.news.databinding.WelcomeBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.AppInfo;
import com.heaven.news.engine.DataCore;
import com.heaven.news.ui.vm.model.ConfigData;
import com.heaven.news.ui.vm.model.UpdateInfo;
import com.heaven.news.ui.vm.model.Version;
import com.heaven.news.ui.vm.viewmodel.WelecomModel;
import com.orhanobut.logger.Logger;

/**
 * FileName: com.heaven.news.ui.activity.Welcome.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-07 17:57
 *
 * @author heaven
 * @version V1.0 欢迎页
 */
public class Welcome extends BaseSimpleBindActivity<WelecomModel, WelcomeBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.welcome;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        AppEngine.instance().dataCore().registerDataTypeObaserver(this, coreDataWrapper -> {
            if (coreDataWrapper != null && DataCore.VERSION == coreDataWrapper.dataType) {
                checkVersion(coreDataWrapper.version);
            }
            Logger.i("Welcome----versionobserver--" + coreDataWrapper.toString());
        });
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

    private void checkVersion(Version version) {
        UpdateInfo updateInfo = new UpdateInfo();
        if(version != null) {
            AppInfo appInfo = AppEngine.instance().getAppConfig();
            updateInfo.updateUrl = version.url;
            updateInfo.updateMessage = version.txt;
            if (version.cversion > 65534) {
                updateInfo.isServiceMainta = true;
            } else {
                if (appInfo.verCode < version.cversion) {
                    updateInfo.needUpdate = true;
                    if (appInfo.verCode < version.fversion) {
                        updateInfo.isForceUpdate = true;
                    }
                }
            }
        } else {
            updateInfo.isNetError = true;
        }
        processNextStep(updateInfo);
    }


    private void processNextStep(UpdateInfo updateInfo) {
        boolean isOldUser = AppEngine.instance().getDataSource().getSharePreBoolean(Constants.ISOLDUSER);
        if (isOldUser) {
            updateInfo.nextGuidePage = false;
        } else {
            updateInfo.nextGuidePage = true;
        }
        AppEngine.instance().getDataSource().setSharePreBoolean(Constants.ISOLDUSER, true);
        processNext(updateInfo);
    }

    private void processNext(UpdateInfo updateInfo) {
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


    private void toNextPage(UpdateInfo updateInfo) {
        if (updateInfo.nextGuidePage) {
            toGuidePage();
        } else if (updateInfo.isShowAd) {
            if(updateInfo.adInfo != null) {
                toAdPage();
            } else {
                toMainPage();
            }
        } else {
            toMainPage();
        }
    }

    private void toAdPage() {
        Intent intent = new Intent(Welcome.this, AdActivity.class);
        startActivity(intent);
        finish();
    }

    private void toMainPage() {
        startActivity(new Intent(Welcome.this, MainActivity.class));
        finish();
    }

    private void toGuidePage() {
        startActivity(new Intent(Welcome.this, GuideActivity.class));
        finish();
    }

}
