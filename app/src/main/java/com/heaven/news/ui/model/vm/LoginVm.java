package com.heaven.news.ui.model.vm;

import android.databinding.Observable;

import com.heaven.data.manager.DataSource;
import com.heaven.news.BR;
import com.heaven.news.consts.Constants;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.model.bean.base.UserInfo;
import com.orhanobut.logger.Logger;

/**
 * FileName: com.heaven.news.ui.vm.viewmodel.LoginVm.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-08 15:45
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class LoginVm extends AbstractVm {

    public UserInfo userInfo;

    @Override
    public void initModel() {
        UserInfo userSecret = AppEngine.instance().getDataSource().getCacheEntity(DataSource.DISK, Constants.USERINFO);
        userInfo = userSecret != null? userSecret : new UserInfo();
        userInfo.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(BR.count == propertyId) {
                    Logger.i("sonPropertyChanged count--");
                } else if(BR.password == propertyId) {
                    Logger.i("sonPropertyChanged password--");
                }
            }
        });
    }
}
