package com.heaven.news.api;

import com.heaven.data.net.DataResponse;
import com.heaven.news.vm.model.ConfigData;
import com.neusoft.szair.model.memberbase.MemberLoginWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.memberbase.loginNewResponse;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * FileName: com.heaven.news.api.LoginApi.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-28 13:11
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public interface ConfigApi {
    @Headers({"Content-Type:Application/json; charset=utf-8"})
    @GET("config.json")
    Flowable<ConfigData> getConfig();

    @Headers({"Content-Type:Application/json; charset=utf-8"})
    @GET("config.json")
    Flowable<ConfigData> getAdInfo();
}
