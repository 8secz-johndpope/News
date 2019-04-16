package com.heaven.news.ui.vm.viewmodel;

import android.app.Application;

import com.heaven.base.vm.BaseViewModel;
import com.heaven.data.manager.DataSource;
import com.heaven.news.engine.Api;
import com.heaven.news.engine.App;

import javax.inject.Inject;

/**
 * FileName: com.heaven.news.ui.vm.viewmodel.AbsViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-16 13:37
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class AbsViewModel extends BaseViewModel {
    @Inject
    Api api;

    @Override
    public void setApplication(Application application) {
        super.setApplication(application);
        App.getCoreComponent().inject(this);
    }
}
