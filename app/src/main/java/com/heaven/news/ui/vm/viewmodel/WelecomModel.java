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
    @Override
    public void initModel() {
    }

}
