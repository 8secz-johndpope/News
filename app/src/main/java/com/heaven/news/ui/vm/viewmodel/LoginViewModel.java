package com.heaven.news.ui.vm.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.heaven.base.utils.RxSchedulers;
import com.heaven.base.vm.BaseViewModel;
import com.heaven.data.manager.DataSource;
import com.heaven.news.BR;
import com.heaven.news.api.LoginApi;
import com.heaven.news.consts.Constants;
import com.heaven.news.engine.ApiManager;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.DataCore;
import com.heaven.news.ui.vm.model.UserLoginInfo;
import com.heaven.news.utils.RxRepUtils;
import com.neusoft.szair.model.memberbase.MemberLoginWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.memberbase.loginNew;
import com.neusoft.szair.model.memberbase.loginReqVO;
import com.neusoft.szair.model.soap.SOAPConstants;
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

    public final MutableLiveData<String> userName = new MutableLiveData<>();

    public final MutableLiveData<String> password = new MutableLiveData<>();

    public final ObservableField<String> passwordObserve = new ObservableField<>();

    //    @Permission(value = Manifest.permission.READ_CONTACTS)
//    @Permission(value = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void login() {
        UserLoginInfo userLoginInfo =  AppEngine.getInstance().getCacheData(DataSource.DISK, Constants.USERINFO);
        if(userLoginInfo != null) {
            Logger.i("login_action-----------" + "userLoginInfo--" + userLoginInfo.toString());
        }
        AppEngine.getInstance().getDataSource().addHeader("Content-Type", "text/xml;charset=UTF-8");
        String count = userInfo.count;
        String passwords = userInfo.password;
        if(TextUtils.isEmpty(count) || TextUtils.isEmpty(passwords)) {
            return;
        }

        loginNew login = new loginNew();
        loginReqVO loginreqvo = new loginReqVO();
        loginreqvo._USER_NAME = count;
        loginreqvo._PASSWORD = Constants.getPassword(passwords);

        loginreqvo._APP_ID = SOAPConstants.APP_ID;
        loginreqvo._APP_IP = SOAPConstants.APP_IP;
        loginreqvo._DEVICE_TYPE = SOAPConstants.DEVICE_TYPE;

        loginreqvo._DEVICE_TOKEN = "";
        login._LOGIN_PARAM = loginreqvo;


        MemberLoginWebServiceImplServiceSoapBinding bind = new MemberLoginWebServiceImplServiceSoapBinding("loginNew",login);//非短信验证码登陆，用户新接口

        RxRepUtils.getResult(ApiManager.getApi(LoginApi.class).login(bind), loginNewResponseDataResponse -> {
            if(loginNewResponseDataResponse.code == 0) {
                UserLoginInfo loginInfo = new UserLoginInfo();
                loginInfo.userCount = userInfo.count;
                loginInfo.userPwd = passwords;
                AppEngine.getInstance().getDataSource().cacheData(DataSource.DISK,Constants.USERINFO,loginInfo);
                AppEngine.getInstance().getDataSource().setSharePreBoolean(Constants.ISAUTOLOGIN,true);
                AppEngine.getInstance().dataCore().initLoginData(loginNewResponseDataResponse.data);
                AppEngine.getInstance().getCurActivity().finish();
            }
        });
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
                    Logger.i("sonPropertyChanged count--" + password);
                } else if(BR.password == propertyId) {
                    Logger.i("sonPropertyChanged password--" + password);
                }
            }
        });
    }
}
