package com.heaven.news.api;

import com.heaven.news.ui.vm.model.ConfigData;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * FileName: com.heaven.news.api.LoginApi.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-28 13:11
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public interface ConfigApi {
    @Headers({"Content-Type:Application/json; charset=utf-8","Connection:close"})
    @GET("config.json")
    Flowable<ConfigData> getConfig();

    @GET("config.json")
    Flowable<ConfigData> getAdInfo();
}
