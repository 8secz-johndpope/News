package com.heaven.news.ui.vm.model.base;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.heaven.news.BR;
import com.orhanobut.logger.Logger;

import java.io.Serializable;

/**
 * FileName: com.heaven.news.ui.vm.model.base.UserInfo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-19 14:45
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
public class UserInfo extends BaseObservable implements Serializable {
    public  String count;
    public  String password;

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
