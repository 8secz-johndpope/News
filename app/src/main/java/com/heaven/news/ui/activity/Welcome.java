package com.heaven.news.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.data.manager.DataSource;
import com.heaven.news.R;
import com.heaven.news.consts.Constants;
import com.heaven.news.databinding.WelcomeBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.AppInfo;
import com.heaven.news.engine.DataCore;
import com.heaven.news.ui.vm.model.AdInfo;
import com.heaven.news.ui.vm.model.ConfigData;
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
public class Welcome extends BaseSimpleBindActivity<WelecomModel, WelcomeBinding> implements Observer<ConfigData> {

    @Override
    public int initLayoutResId() {
        return R.layout.welcome;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        ConfigData configData = AppEngine.getInstance().dataCore().getConfigData();
        if(configData != null) {
            updateConfig(configData);
        } else {
            AppEngine.getInstance().dataCore().registConfigObserver(this,this);
        }
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

    private void updateConfig(ConfigData configData) {
        if(configData != null) {
            if (configData.netCode == 0 && configData.androidversionnew != null) {
                checkVersion(configData.androidversionnew);
            } else {
                DataCore.UpdateInfo updateInfo = new DataCore.UpdateInfo();
                updateInfo.isNetError = true;
                updateInfo.reason = configData.message;
                processNextStep(updateInfo);
            }
        } else {
            DataCore.UpdateInfo updateInfo = new DataCore.UpdateInfo();
            updateInfo.isNetError = true;
            processNextStep(updateInfo);
        }
    }

    private void checkVersion(Version version) {
        AppInfo appInfo = AppEngine.getInstance().getAppConfig();
        DataCore.UpdateInfo updateInfo = new DataCore.UpdateInfo();
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
        checkAdInfo(updateInfo);
        processNextStep(updateInfo);
    }

    private void checkAdInfo(DataCore.UpdateInfo updateInfo) {
        updateInfo.adInfo = getTestAdInfoData();
//        updateInfo.isShowAd = true;
        if (updateInfo.isShowAd && updateInfo.adInfo != null) {
            AppEngine.getInstance().cacheData(DataSource.DB, Constants.ADINFO, updateInfo.adInfo);
        } else {
            updateInfo.isShowAd = false;
        }
    }


    private void processNextStep(DataCore.UpdateInfo updateInfo) {
        boolean isOldUser = AppEngine.getInstance().getDataSource().getSharePreBoolean(Constants.ISOLDUSER);
        if (isOldUser) {
            updateInfo.nextGuidePage = false;
        } else {
            updateInfo.nextGuidePage = true;
        }
        AppEngine.getInstance().getDataSource().setSharePreBoolean(Constants.ISOLDUSER, true);
        processNext(updateInfo);
    }

    private AdInfo getTestAdInfoData() {
        AdInfo adInfo = new AdInfo();
        adInfo.isVideo = false;
        adInfo.urlImage = "http://img0.imgtn.bdimg.com/it/u=1344159241,3681424911&fm=26&gp=0.jpg";
        adInfo.urlVideo = "";
        adInfo.content = "百思不得姐减肥肯定是怕几点睡激动是怕";

        return adInfo;
    }

    private void processNext(DataCore.UpdateInfo updateInfo) {
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


    private void toNextPage(DataCore.UpdateInfo updateInfo) {
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


    @Override
    public void onChanged(@Nullable ConfigData configData) {
        updateConfig(configData);
    }
}
