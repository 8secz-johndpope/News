package com.heaven.news.ui;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.heaven.news.BR;
import com.orhanobut.logger.Logger;

/**
 * FileName: com.heaven.news.ui.UserInfoWrapper.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-08 19:43
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class UserInfoWrapper extends BaseObservable {
    private  String count;
    private  String password;

    @Bindable
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
        notifyPropertyChanged(BR.count);
        Logger.i("set userinfowrapper count--" + count);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
        Logger.i("set userinfowrapper count--" + password);
    }

    @Override
    public String toString() {
        return "UserInfoWrapper{" +
                "count='" + count + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
