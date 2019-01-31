package com.heaven.data.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者:Heaven
 * 时间: on 2017/8/15 17:46
 * 邮箱:heavenisme@aliyun.com
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder =  chain.request().newBuilder();
        Request request = builder.headers(NetGlobalConfig.HEADERS).build();

        return chain.proceed(request);
    }
}
