package com.heaven.news.ui.model.vm;

import android.arch.lifecycle.MutableLiveData;

import com.heaven.news.ui.model.bean.base.VersionUpdate;

/**
 * FileName: com.heaven.news.ui.vm.viewmodel.WelecomVm.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-07 15:22
 *
 * @version V1.0 欢迎页版本检查
 */
public class WelecomVm extends AbstractVm {
    public VersionUpdate updateInfo;
    public final MutableLiveData<VersionUpdate> updateInfoLive = new MutableLiveData<>();

    @Override
    public void initModel() {
//        requestVersion();
    }
}
