package com.heaven.news.ui.vm.vmmodel;

import android.arch.lifecycle.MutableLiveData;

import com.heaven.news.ui.vm.model.base.VersionUpdate;

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
