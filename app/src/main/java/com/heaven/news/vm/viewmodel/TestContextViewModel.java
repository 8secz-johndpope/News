package com.heaven.news.vm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.heaven.base.vm.BaseViewModel;
import com.heaven.news.BuildConfig;
import com.heaven.news.api.ConfigApi;
import com.heaven.news.engine.ApiManager;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.utils.RxRepUtils;
import com.heaven.news.vm.present.TestPt;
import com.heaven.news.utils.CryptUtility;
import com.neusoft.szair.model.memberbase.loginNew;
import com.neusoft.szair.model.memberbase.loginReqVO;
import com.neusoft.szair.model.soap.SOAPConstants;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * FileName: com.heaven.news.vm.viewmodel.TestContextViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-25 16:45
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class TestContextViewModel extends BaseViewModel<TestPt> {
    public final MutableLiveData<String> userName = new MutableLiveData<>();

    public final ObservableField<String> passwordObserve = new ObservableField<>();

    public void login(String count, String passwords) {
        AppEngine.getInstance().getDataSource().addHeader("Content-Type", "text/xml;charset=UTF-8");

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


//        MemberLoginWebServiceImplServiceSoapBinding bind = new MemberLoginWebServiceImplServiceSoapBinding("loginNew",login);//非短信验证码登陆，用户新接口
//
//        RxSchedulers.getResult(ApiManager.getApi(LoginApi.class).login(bind), loginNewResponseDataResponse -> {
//            Logger.i("heaven---" + loginNewResponseDataResponse.toString());
//            if(loginNewResponseDataResponse.code == 0) {
//                userName.postValue(loginNewResponseDataResponse.data._LOGIN_RESULT._VIP._VIPDETAILS._LOGIN_NAME);
//            }
//        });
        RxRepUtils.getConfigResult(ApiManager.getApi(BuildConfig.CONFIG_URL,ConfigApi.class).getConfig(), configData -> {
            Logger.i("heaven---" + configData.toString());
        });
    }


    public MutableLiveData<String> getUserName() {
        return userName;
    }

    @Override
    public void initModel() {

    }
}
