package com.heaven.news.ui.vm.vmmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.heaven.annotation.aspect.TraceTime;
import com.heaven.news.BuildConfig;
import com.heaven.news.api.ConfigApi;
import com.heaven.news.consts.Constants;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.AppInfo;
import com.heaven.news.ui.vm.model.base.VersionUpdate;
import com.heaven.news.ui.vm.model.base.Version;
import com.heaven.news.utils.RxRepUtils;
import com.orhanobut.logger.Logger;

/**
 * FileName: com.heaven.news.ui.vm.viewmodel.WelecomModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-07 15:22
 *
 * @version V1.0 欢迎页版本检查
 */
public class WelecomModel extends AbstractViewModel {
    public VersionUpdate updateInfo;
    public final MutableLiveData<VersionUpdate> updateInfoLive = new MutableLiveData<>();

    @Override
    public void initModel() {
//        requestVersion();
    }

    @TraceTime
    private void requestVersion() {
        RxRepUtils.getConfigResult(api.getApi(BuildConfig.CONFIG_URL, ConfigApi.class).getConfig(), configData -> {
            if (configData.netCode == 0 && configData.androidversionnew != null) {
                checkVersion(configData.androidversionnew);
                Logger.i("requestVersion--" + configData.toString());
            } else {
                checkVersion(null);
                Logger.i("requestVersion--" + configData.toString());
            }
        });
    }

    private void checkVersion(Version version) {
        VersionUpdate updateInfo = new VersionUpdate();
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


    private void processNextStep(VersionUpdate updateInfo) {
        boolean isOldUser = AppEngine.instance().getDataSource().getSharePreBoolean(Constants.ISOLDUSER);
        if (isOldUser) {
            updateInfo.nextGuidePage = false;
        } else {
            updateInfo.nextGuidePage = true;
        }
        AppEngine.instance().getDataSource().setSharePreBoolean(Constants.ISOLDUSER, true);
        this.updateInfo = updateInfo;
        updateInfoLive.setValue(updateInfo);
    }

    public void obserUpdateInfo(LifecycleOwner owner, Observer<VersionUpdate> observer) {
        updateInfoLive.observeForever(observer);
    }

    public void removeUpdateInfoObserver(Observer<VersionUpdate> observer) {
        updateInfoLive.removeObserver(observer);
    }

}
