package com.heaven.news.ui.vm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.text.TextUtils;

import com.heaven.base.vm.BaseViewModel;
import com.heaven.news.BR;
import com.heaven.news.consts.Constants;
import com.heaven.news.engine.AppEngine;
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
public class LoginViewModel extends BaseViewModel {

    public UserInfo userInfo;

    //    @Permission(value = Manifest.permission.READ_CONTACTS)
//    @Permission(value = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void login() {
        AppEngine.instance().getDataSource().addHeader("Content-Type", "text/xml;charset=UTF-8");
        String count = userInfo.count;
        String passwords = userInfo.password;
        if(TextUtils.isEmpty(count) || TextUtils.isEmpty(passwords)) {
            return;
        }
        AppEngine.instance().dataCore().login(count,Constants.getPassword(passwords));
    }

    public static class UserInfo extends BaseObservable implements Serializable {
      private  String count;
      private  String password;

        @Bindable
        public String getCount() {
            return count;
        }

        @Bindable
        public void setCount(String count) {
            this.count = count;
            notifyPropertyChanged(BR.count);
            Logger.i("set userinfowrapper count--" + count);
        }

        @Bindable
        public String getPassword() {
            return password;
        }

        @Bindable
        public void setPassword(String password) {
            this.password = password;
            notifyPropertyChanged(BR.password);
            Logger.i("set userinfowrapper count--" + password);
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "count='" + count + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    @Override
    public void initModel() {
        userInfo = new UserInfo();
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
