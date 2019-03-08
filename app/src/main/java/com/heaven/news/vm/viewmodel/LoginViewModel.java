package com.heaven.news.vm.viewmodel;

import android.Manifest;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.widget.Toast;

import com.heaven.annotation.aspect.Permission;
import com.heaven.base.utils.RxSchedulers;
import com.heaven.base.vm.BaseViewModel;
import com.heaven.news.BR;
import com.heaven.news.api.LoginApi;
import com.heaven.news.engine.ApiManager;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.utils.CryptUtility;
import com.neusoft.szair.model.memberbase.MemberLoginWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.memberbase.loginNew;
import com.neusoft.szair.model.memberbase.loginReqVO;
import com.neusoft.szair.model.soap.SOAPConstants;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * FileName: com.heaven.news.vm.viewmodel.LoginViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-08 15:45
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class LoginViewModel extends BaseViewModel {

    public UserInfoWrapper userInfoWrapper;

    public final MutableLiveData<String> userName = new MutableLiveData<>();

    public final MutableLiveData<String> password = new MutableLiveData<>();

    public final ObservableField<String> passwordObserve = new ObservableField<>();

    //    @Permission(value = Manifest.permission.READ_CONTACTS)
//    @Permission(value = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void login() {
        Logger.i("login_action-----------" + userInfoWrapper.toString());
        AppEngine.getInstance().getDataSource().addHeader("Content-Type", "text/xml;charset=UTF-8");
        String count = userInfoWrapper.count;
        String passwords = userInfoWrapper.password;
        if(TextUtils.isEmpty(count) || TextUtils.isEmpty(passwords)) {
            return;
        }
        String AES_KEY = "szair-";
        String password = null;
        String key = AES_KEY + new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        byte[] passwordByte = CryptUtility.encrypt(passwords.getBytes(), key.getBytes());
        password = CryptUtility.base64Encode(passwordByte);
        password = password.replaceAll("\n", "");

        loginNew login = new loginNew();
        loginReqVO loginreqvo = new loginReqVO();
        loginreqvo._USER_NAME = count;
        loginreqvo._PASSWORD = password;

        loginreqvo._APP_ID = SOAPConstants.APP_ID;
        loginreqvo._APP_IP = SOAPConstants.APP_IP;
        loginreqvo._DEVICE_TYPE = SOAPConstants.DEVICE_TYPE;

        loginreqvo._DEVICE_TOKEN = "";
        login._LOGIN_PARAM = loginreqvo;


        MemberLoginWebServiceImplServiceSoapBinding bind = new MemberLoginWebServiceImplServiceSoapBinding("loginNew",login);//非短信验证码登陆，用户新接口

        RxSchedulers.getResult(ApiManager.getApi(LoginApi.class).login(bind), loginNewResponseDataResponse -> {
            Logger.i("heaven---" + loginNewResponseDataResponse.toString());
            if(loginNewResponseDataResponse.code == 0) {
                userName.postValue(loginNewResponseDataResponse.data._LOGIN_RESULT._VIP._VIPDETAILS._LOGIN_NAME);
            }
        });
//        RxRepUtils.getConfigResult(ApiManager.getApi(BuildConfig.CONFIG_URL,ConfigApi.class).getConfig(), configData -> {
//            Logger.i("heaven---" + configData.toString());
//        });
    }

    public static class UserInfoWrapper extends BaseObservable {
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
            return "UserInfoWrapper{" +
                    "count='" + count + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    @Override
    public void initModel() {
        userInfoWrapper = new UserInfoWrapper();
        userInfoWrapper.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
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
