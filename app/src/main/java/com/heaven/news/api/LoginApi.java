package com.heaven.news.api;

import com.heaven.model.soap.szair.BaseRequest;
import com.heaven.model.soap.szair.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * FileName: com.heaven.news.api.LoginApi.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-28 13:11
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public interface LoginApi {
    @POST("memberLoginWebService?wsdl")
    Call<LoginResponse> login(@Body BaseRequest requestEnvelope);
}
