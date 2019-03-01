package com.heaven.data.net;


import android.text.TextUtils;

import com.google.common.io.ByteStreams;
import com.heaven.data.BuildConfig;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.zip.GZIPInputStream;

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
        String contentType = response.header("Content-Type");
        String contectEncode = response.header("Content-Encoding");
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";

        String responseBodyStr = "";
        BufferedSource source = responseBody.source();
        if (contentLength != 0) {
            String responseBodySize = "\n" + "bodySize:" + bodySize + "\n";

            if (contentLength < 102400) {
                Buffer buffer = source.buffer();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                responseBodyStr = getResponseBody(contentType, contectEncode, buffer.clone());
            } else {
                responseBodyStr = responseBodySize;
            }

        }
        StringBuilder requestLog = new StringBuilder();
        StringBuilder responseLog = new StringBuilder();
        StringBuilder traceTime = new StringBuilder();
        requestLog
                .append("******************************************\n")
                .append("RequestHeader:\n")
                .append(request.headers().toString())
                .append("\n")
                .append(request.url())
                .append("\n")
                .append("******************************************\n")
                .append("RequestBody:\n");
        Logger.i(requestLog.toString());
        Logger.i(formatJson(printReqBody(request.body())));
        responseLog
                .append("\n")
                .append("******************************************\n")
                .append("ResponseHeader:\n")
                .append(response.headers())
                .append("\n")
                .append("******************************************\n")
                .append("ResponseBody:\n")
                .append("bodySize:")
                .append(bodySize);

        Logger.i(responseLog.toString());
        if (!TextUtils.isEmpty(contentType)) {
            if (contentType.contains("json")) {
                Logger.json(responseBodyStr);
            } else if (contentType.contains("xml")) {
                Logger.xml(responseBodyStr);
            } else {
                Logger.i(responseBodyStr);
            }
        } else {
            Logger.i(responseBodyStr);
        }
        traceTime
                .append("\n")
                .append("******************************************\n")
                .append(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n",
                        response.request().url(), (endReqTime - startReqTime) / 1e6d));
        Logger.i(traceTime.toString());
    }

    private String getResponseBody(String protoType, String repEncodeType, Buffer repBuffer) {
        String repBody = "";
        if (!TextUtils.isEmpty(repEncodeType) && repEncodeType.contains("gzip")) {
            try {
                repBody = new String(ByteStreams.toByteArray(new GZIPInputStream(repBuffer.inputStream())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            repBody = repBuffer.readString(UTF8);
        }
        return repBody;
    }


    private String printReqBody(RequestBody requestBody) throws IOException {
        String requestJson = "";
        if (requestBody != null) {
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
