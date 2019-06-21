package com.heaven.news.ui.model.vm;

import com.heaven.base.vm.BaseViewModel;
import com.heaven.news.api.IApi;
import com.heaven.news.engine.App;
import com.heaven.news.engine.manager.NetManager;

import javax.inject.Inject;

/**
 * FileName: com.heaven.news.ui.vm.viewmodel.AbstractVm.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-16 13:37
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class AbstractVm extends BaseViewModel {
    @Inject
    IApi mApi;

    @Inject
    public NetManager mNetManager;

    @Override
    public void inject() {
        super.inject();
        App.getCoreComponent().inject(this);
    }
}
