package com.heaven.data.net;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者:Heaven
 * 时间: on 2017/8/15 17:46
 * 邮箱:heavenisme@aliyun.com
 */

public class HeaderInterceptor implements Interceptor {
    private Headers headers;

    public HeaderInterceptor(Headers headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder =  chain.request().newBuilder();
        Request request = builder.headers(headers).build();

        return chain.proceed(request);
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }
}
