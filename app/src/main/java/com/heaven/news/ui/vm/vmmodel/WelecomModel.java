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
}
