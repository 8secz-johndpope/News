package com.heaven.news.vm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.heaven.base.vm.BaseViewModel;
import com.heaven.data.manager.DataSource;
import com.heaven.news.BuildConfig;
import com.heaven.news.api.ConfigApi;
import com.heaven.news.constant.Constants;
import com.heaven.news.engine.ApiManager;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.AppInfo;
import com.heaven.news.utils.RxRepUtils;
import com.heaven.news.vm.model.ConfigData;
import com.heaven.news.vm.model.Version;
import com.orhanobut.logger.Logger;

/**
 * FileName: com.heaven.news.vm.viewmodel.WelecomModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-07 15:22
 *
 * @version V1.0 欢迎页版本检查
 */
public class WelecomModel extends BaseViewModel {
    public MutableLiveData<UpdateInfo> versionLive = new MutableLiveData<>();

    @Override
    public void initModel() {
       ConfigData configData =  AppEngine.getInstance().getDataSource().getCacheEntity(DataSource.DISK, Constants.ADINFO);
       if(configData != null && configData.androidversion != null) {
           Logger.i("initModel---cache configData---" + configData.toString());
           checkVersion(configData.androidversion);
       } else {
           requestVersion();
       }
    }


    private void requestVersion() {
        RxRepUtils.getConfigResult(ApiManager.getApi(BuildConfig.CONFIG_URL, ConfigApi.class).getConfig(), configData -> {
            if (configData.netCode == 0 && configData.androidversion != null) {
                AppEngine.getInstance().cacheData(DataSource.DB, Constants.ADINFO, configData);
                checkVersion(configData.androidversion);
            }
        });
    }

    private void checkVersion(Version version) {
        AppInfo appInfo = AppEngine.getInstance().getAppConfig();
        UpdateInfo updateInfo = new UpdateInfo();
        updateInfo.updateUrl = version.url;
        updateInfo.updateMessage = version.txt;
        if (version.cversion > 65534) {
            updateInfo.isMaintaiService = true;
        } else {
            if (appInfo.verCode < version.cversion) {
                updateInfo.needUpdate = true;
                if (appInfo.verCode < version.fversion) {
                    updateInfo.isForceUpdate = true;
                }
            }
        }
        versionLive.setValue(updateInfo);
    }

    public static class UpdateInfo {
        public  boolean isMaintaiService;
        public  boolean needUpdate;
        public  boolean isForceUpdate;
        public  String updateUrl;
        public  String updateMessage;

        @Override
        public String toString() {
            return "UpdateInfo{" +
                    "isMaintaiService=" + isMaintaiService +
                    ", needUpdate=" + needUpdate +
                    ", isForceUpdate=" + isForceUpdate +
                    ", updateUrl='" + updateUrl + '\'' +
                    ", updateMessage='" + updateMessage + '\'' +
                    '}';
        }
    }
}
