package com.heaven.news.ui.model.vm;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.engine.AppEngine;

/**
 * FileName: com.heaven.news.ui.model.vm.SettingVm.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-20 13:43
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class SettingVm extends AbstractVm {
    @Override
    public void initModel() {

    }

    public void logOut() {
        AppEngine.instance().dataCore().logOut();
        ARouter.getInstance().build(RouterUrl.ROUTER_URL_LOGIN).navigation();
    }

}
