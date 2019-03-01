package com.heaven.news.vm;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.heaven.annotation.aspect.TraceTime;
import com.heaven.base.vm.BaseViewModel;
import com.heaven.model.soap.szair.SoapBaseRequest;
import com.heaven.model.soap.szair.login.LoginAction;
import com.heaven.model.soap.szair.login.LoginBody;
import com.heaven.model.soap.szair.login.LoginParam;
import com.heaven.model.soap.szair.login.LoginResponse;
import com.heaven.news.api.LoginApi;
import com.heaven.news.engine.ApiManager;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.pt.TestPt;
import com.heaven.news.utils.CryptUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * FileName: com.heaven.news.vm.TestViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-25 16:45
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class TestViewModel extends BaseViewModel<TestPt> {
    public final ObservableField<String> userCount = new ObservableField<>();

    public final ObservableField<String> passwordObserve = new ObservableField<>();

    public TestViewModel(@NonNull Application application) {
        super(application);
    }


    public void login(String count,String passwords) {
        AppEngine.getInstance().getDataSource().addHeader("Content-Type", "text/xml;charset=UTF-8");
//        RequestEnvelope requestEnvelop = new RequestEnvelope();
//        RequestBody requestBody = new RequestBody();
//        RequestModel requestModel = new RequestModel();
//        requestModel.theCityName = cityName;
//        requestModel.cityNameAttribute = "http://WebXml.com.cn/";
//        requestBody.getWeatherbyCityName = requestModel;
//        requestEnvelop.body = requestBody;

//        BaseXmlRequest baseRequest = new BaseXmlRequest();
        SoapBaseRequest baseRequest = new SoapBaseRequest();

        LoginBody body = new LoginBody();
        LoginAction action = new LoginAction();
        LoginParam login = new LoginParam();
        action.LOGIN_PARAM = login;
        body.action = action;
        baseRequest.body = body;

        String AES_KEY = "szair-";
        String password = null;
        String key = AES_KEY + new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        byte[] passwordByte = CryptUtility.encrypt(passwords.getBytes(), key.getBytes());
        password = CryptUtility.base64Encode(passwordByte);
        password = password.replaceAll("\n", "");

        login._USER_NAME = count;
        login.PASSWORD = password;

        Call<LoginResponse> call = ApiManager.getApi(LoginApi.class).login(baseRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                Logger.i(response.body());
                LoginResponse responseEnvelope = response.body();
//                if (responseEnvelope != null ) {
//                    List<String> weatherResult = responseEnvelope.body.getWeatherbyCityNameResponse.result;
//                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
}
