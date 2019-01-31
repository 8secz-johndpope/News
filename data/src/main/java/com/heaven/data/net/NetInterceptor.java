package com.heaven.data.net;



import com.heaven.data.BuildConfig;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 作者：Heaven
 * 时间: on 2016/10/12 10:16
 * 邮箱：heavenisme@aliyun.com
 * 自定义网络请求拦截器
 */

public class NetInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final String MD5_KEY = "szair_mobile_app";


    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取原始Request
        Request request = chain.request();
        long startReqTime = System.nanoTime();
        Response response = chain.proceed(request);
        long endReqTime = System.nanoTime();
        if (BuildConfig.DEBUG) {
            printLog(request, response, startReqTime, endReqTime);
        }
        return response;
    }


    private void printLog(Request request, Response response, long startReqTime, long endReqTime) throws IOException {
        StringBuilder requestLog = new StringBuilder();

       String contentType = response.header("Content-Type");
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";

        String responseBodyStr = "";
        BufferedSource source = responseBody.source();
        if (contentLength != 0) {
            String responseBodySize = "\n" + "bodySize:" + bodySize + "\n";

            if(contentLength < 102400) {
                Buffer buffer = source.buffer();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                responseBodyStr = responseBodySize + formatJson(buffer.clone().readString(UTF8));
            } else {
                responseBodyStr = responseBodySize;
            }

        }

        requestLog
                .append("******************************************\n")
                .append("RequestHeader:\n")
                .append(request.headers().toString())
                .append("\n")
                .append(request.url())
                .append("\n")
                .append("******************************************\n")
                .append("RequestBody:\n")
                .append(formatJson(printReqBody(request.body())))
                .append("\n")
                .append("******************************************\n")
                .append("ResponseHeader:\n")
                .append(response.headers())
                .append("\n")
                .append("******************************************\n")
                .append("ResponseBody:\n")
                .append(responseBodyStr)
                .append("\n")
                .append("******************************************\n")
                .append(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n",
                        response.request().url(), (endReqTime - startReqTime) / 1e6d));
        Logger.i(requestLog.toString());
    }



    private String printReqBody(RequestBody requestBody) throws IOException {
        String requestJson = "" ;
        if(requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            requestJson = buffer.readString(charset);
        }
        return requestJson;
    }

    private String formatJson(String json) {
        if (json != null) {
            try {
                if (json.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(json);
                    return jsonObject.toString(4);
                }
                if (json.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(json);
                    return jsonArray.toString(4);
                }
            } catch (JSONException e) {
                return json;
            }
        }
        return json;
    }
}
