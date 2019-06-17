package com.heaven.news.net;

import android.text.TextUtils;

import com.heaven.news.BuildConfig;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * FileName: com.heaven.news.net.DynamicIntercepter.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-08 20:18
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class DynamicIntercepter implements Interceptor {
    private Headers oriHeaders;
    private static final Charset UTF8 = Charset.forName("UTF-8");

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
                .add("Content-Type", "text/xml")//内容数据格式application/json text/xml
                .add("Connection", "close");
        oriHeaders = headerBuilder.build();
    }

    @Override
    @Nonnull
    public Response intercept(@Nonnull Chain chain) throws IOException {
        Request originalReq = chain.request();
        Request.Builder requestBuilder = originalReq.newBuilder();
        Headers headers = originalReq.headers();
        Request reequest = null;
        if (headers.size() > 0) {
            reequest = requestBuilder.headers(refreshHeader(headers)).build();
        } else {
            reequest = requestBuilder.headers(oriHeaders).build();
        }
        String questUrl = reequest.url().toString();
        if (!TextUtils.isEmpty(questUrl) && questUrl.contains(BuildConfig.CONFIG_URL)) {
            chain.withConnectTimeout(3, TimeUnit.SECONDS)
                    .withReadTimeout(3, TimeUnit.SECONDS);
        }
        if (BuildConfig.DEBUG) {
            printRequestLog(reequest);
        }
        return chain.proceed(reequest);
    }

    private Headers refreshHeader(Headers headers) {
        Headers.Builder newHeader = oriHeaders.newBuilder();
        for (String name : headers.names()) {
            String value = headers.get(name);
            if (!TextUtils.isEmpty(value)) {
                newHeader.add(name, value);
            }
        }
        return newHeader.build();
    }

    private void printRequestLog(Request request) {
        String contentType = request.header("Content-Type");
        StringBuilder requestLog = new StringBuilder();
        String reqBody = printReqBody(request.body());
        requestLog
                .append("******************************************\n")
                .append("RequestHeader:\n")
                .append(request.headers().toString())
                .append("\n")
                .append(request.url())
                .append("\n")
                .append("******************************************\n");
        Logger.i(requestLog.toString());
        try{
            if (!TextUtils.isEmpty(contentType)) {
                if (contentType.contains("json")) {
                    Logger.json(reqBody);
                } else if (contentType.contains("xml")) {
                    Logger.xml(reqBody);
                } else {
                    Logger.i(reqBody);
                }
            } else {
                Logger.i(reqBody);
            }
        } catch (Exception e) {
            Logger.i(e.getMessage());
        }
    }


    private String printReqBody(RequestBody requestBody) {
        String requestJson = "";
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            try {
                requestBody.writeTo(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            requestJson = buffer.readString(charset);
        }
        return requestJson;
    }
}
