package com.heaven.news.vm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.heaven.base.vm.BaseViewModel;
import com.heaven.news.BuildConfig;
import com.heaven.news.api.ConfigApi;
import com.heaven.news.engine.ApiManager;
import com.heaven.news.utils.RxRepUtils;
import com.heaven.news.vm.model.Version;
import com.orhanobut.logger.Logger;

/**
 * FileName: com.heaven.news.vm.viewmodel.WelecomModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-07 15:22
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class WelecomModel extends BaseViewModel {
    public MutableLiveData<Version> version = new MutableLiveData<>();

    public WelecomModel(@NonNull Application application) {
        super(application);
    }

    private void checkVersion() {
        RxRepUtils.getConfigResult(ApiManager.getApi(BuildConfig.CONFIG_URL, ConfigApi.class).getConfig(), configData -> {
            if(configData.netCode == 0 && configData.androidversion != null) {
                version.setValue(configData.androidversion);
            }
            Logger.i("heaven---" + configData.toString());
        });
    }

    @Override
    public void initModel() {
        checkVersion();
    }
}
