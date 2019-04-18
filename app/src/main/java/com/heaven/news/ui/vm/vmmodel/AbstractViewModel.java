package com.heaven.news.ui.vm.vmmodel;

import com.heaven.base.vm.BaseViewModel;
import com.heaven.news.engine.Api;
import com.heaven.news.engine.App;

import javax.inject.Inject;

/**
 * FileName: com.heaven.news.ui.vm.viewmodel.AbstractViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-16 13:37
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class AbstractViewModel extends BaseViewModel {
    @Inject
    Api api;

    @Override
    public void inject() {
        super.inject();
        App.getCoreComponent().inject(this);
    }
}
