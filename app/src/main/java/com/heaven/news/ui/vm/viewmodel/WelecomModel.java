package com.heaven.news.ui.vm.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.heaven.base.vm.BaseViewModel;
import com.heaven.data.manager.DataSource;
import com.heaven.news.BuildConfig;
import com.heaven.news.api.ConfigApi;
import com.heaven.news.consts.Constants;
import com.heaven.news.engine.ApiManager;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.AppInfo;
import com.heaven.news.ui.vm.model.AdInfo;
import com.heaven.news.utils.RxRepUtils;
import com.heaven.news.ui.vm.model.Version;
import com.orhanobut.logger.Logger;

/**
 * FileName: com.heaven.news.ui.vm.viewmodel.WelecomModel.java
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
        requestVersion();
    }


    private void requestVersion() {
        Logger.i("---------------------requestVersion start---------------------");
        long startTime = System.currentTimeMillis();
        RxRepUtils.getConfigResult(ApiManager.getApi(BuildConfig.CONFIG_URL, ConfigApi.class).getConfig(), configData -> {
            Logger.i("---------------------requestVersion end---------------------" + configData.toString());
            long endTime = System.currentTimeMillis();
            long requestTime = endTime - startTime;
            if (configData.netCode == 0 && configData.androidversionnew != null) {
                checkVersion(configData.androidversionnew,requestTime);
            } else {
                UpdateInfo updateInfo = new UpdateInfo();
                updateInfo.requestTime = requestTime;
                updateInfo.isNetError = true;
                updateInfo.reason = configData.message;
                processNextStep(updateInfo);
            }
        });
    }

    private void checkVersion(Version version,long requestTime) {
        AppInfo appInfo = AppEngine.getInstance().getAppConfig();
        UpdateInfo updateInfo = new UpdateInfo();
        updateInfo.requestTime = requestTime;
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

    private void checkAdInfo(UpdateInfo updateInfo) {
        updateInfo.adInfo = getTestAdInfoData();
//        updateInfo.isShowAd = true;
        if(updateInfo.isShowAd && updateInfo.adInfo != null) {
            AppEngine.getInstance().cacheData(DataSource.DB, Constants.ADINFO, updateInfo.adInfo );
        } else {
            updateInfo.isShowAd = false;
        }
    }


    private void processNextStep(UpdateInfo updateInfo) {
        boolean isOldUser = AppEngine.getInstance().getDataSource().getSharePreBoolean(Constants.ISOLDUSER);
        if (isOldUser) {
            updateInfo.nextGuidePage = false;
        } else {
            updateInfo.nextGuidePage = true;
        }
        versionLive.setValue(updateInfo);
        AppEngine.getInstance().getDataSource().setSharePreBoolean(Constants.ISOLDUSER, true);
    }

    public static class UpdateInfo {
        public boolean isNetError;
        public String reason;
        public long requestTime;
        public boolean isServiceMainta;
        public boolean needUpdate;
        public boolean isForceUpdate;
        public String updateUrl;
        public String updateMessage;
        public boolean nextGuidePage = false;
        public boolean isShowAd = false;
        public AdInfo adInfo;

        @Override
        public String toString() {
            return "UpdateInfo{" +
                    "isNetError=" + isNetError +
                    ", reason='" + reason + '\'' +
                    ", requestTime=" + requestTime +
                    ", isServiceMainta=" + isServiceMainta +
                    ", needUpdate=" + needUpdate +
                    ", isForceUpdate=" + isForceUpdate +
                    ", updateUrl='" + updateUrl + '\'' +
                    ", updateMessage='" + updateMessage + '\'' +
                    ", nextGuidePage=" + nextGuidePage +
                    ", isShowAd=" + isShowAd +
                    ", adInfo=" + adInfo +
                    '}';
        }
    }

    private AdInfo getTestAdInfoData() {
        AdInfo adInfo = new AdInfo();
        adInfo.isVideo = false;
        adInfo.urlImage = "http://img0.imgtn.bdimg.com/it/u=1344159241,3681424911&fm=26&gp=0.jpg";
        adInfo.urlVideo = "";
        adInfo.content = "百思不得姐减肥肯定是怕几点睡激动是怕";

        return adInfo;
    }
}
