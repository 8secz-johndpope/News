package com.heaven.news.ui.model.bean.base;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.heaven.news.BR;
import com.heaven.news.consts.Constants;
import com.neusoft.szair.model.memberbase.queryRespVO;
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
    private static final long serialVersionUID = 7719902835531801103L;
    public static int LOGIN_COUNT = 0;
    public static int LOGIN_PHONE = 1;

    public int loginType = LOGIN_COUNT;
    public String key;
    public String userId;
    public String count;
    public String phone;
    public String password;
    public String secretPwd;
    public queryRespVO userInfo;

    public UserInfo() {

    }

    public UserInfo(String count, String password) {
        this.count = count;
        this.password = password;
        this.secretPwd = Constants.getPassword(password);
    }

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

    public void logOut() {
        key = "";
        userId = "";
        password = "";
        secretPwd = "";
        userInfo = null;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "count='" + count + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
