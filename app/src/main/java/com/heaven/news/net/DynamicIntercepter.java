package com.heaven.news.net;

import android.text.TextUtils;

import com.heaven.news.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.internal.EverythingIsNonNull;

/**
 * FileName: com.heaven.news.net.DynamicIntercepter.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-08 20:18
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class DynamicIntercepter implements Interceptor {
    private Headers headers;


    {
        Headers.Builder headerBuilder = new Headers.Builder();
        //请求头部
        headerBuilder
                .add("User-Agent", "Android")
                .add("APP-Key", "")//应用的key值
                .add("APP-Secret", "")//应用的密钥
                .add("Charset", "UTF-8")//字符编码格式
                .add("Accept", "*/*")//能够接受的数据格式
                .add("Accept-Language", "zh-cn")//接受的语言
                .add("Content-Type", "application/json")//内容数据格式application/json text/xml
                .add("Connection", "close");
        headers = headerBuilder.build();
    }

    @Override
    @Nonnull
    public Response intercept(@Nonnull Chain chain) throws IOException {
        Request originalReq = chain.request();
        Request.Builder requestBuilder = originalReq.newBuilder();
        Headers headers = originalReq.headers();
        Request reequest = chain.request().newBuilder().headers(headers).build();
        String questUrl = reequest.url().toString();
        if(!TextUtils.isEmpty(questUrl) && questUrl.contains(BuildConfig.CONFIG_URL)) {
            return chain.withConnectTimeout(3, TimeUnit.SECONDS)
                    .withReadTimeout(3,TimeUnit.SECONDS)
                    .proceed(reequest);
        } else {
           return chain.proceed(reequest);
        }
    }

}
