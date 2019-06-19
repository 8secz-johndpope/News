package com.heaven.news.ui.vm.vmmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.text.TextUtils;
import android.view.View;

import com.heaven.data.manager.DataSource;
import com.heaven.news.BR;
import com.heaven.news.consts.Constants;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.vm.model.base.UserInfo;
import com.orhanobut.logger.Logger;

import java.io.Serializable;

/**
 * FileName: com.heaven.news.ui.vm.viewmodel.LoginViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-08 15:45
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class LoginViewModel extends AbstractViewModel {

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
