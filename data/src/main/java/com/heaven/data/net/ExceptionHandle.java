package com.heaven.data.net;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.net.ConnectException;

import retrofit2.HttpException;


/**
 * 作者:Heaven
 * 时间: on 2017/3/3 09:52
 * 邮箱:heavenisme@aliyun.com
 */

public class ExceptionHandle {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static DataResponse handleException(Throwable e) {
        DataResponse ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new DataResponse();
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.code = httpException.code();
                    ex.reason = "网络错误";
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new DataResponse();
            ex.code = resultException.code;
            ex.reason = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
                || e instanceof XmlPullParserException) {
            ex = new DataResponse();
            ex.code = ERROR.PARSE_ERROR;
            ex.reason = "解析错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new DataResponse();
            ex.code = ERROR.NETWORD_ERROR;
            ex.reason = "连接失败";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new DataResponse();
            ex.code = ERROR.SSL_ERROR;
            ex.reason = "证书验证失败";
            return ex;
        } else if(e instanceof Exception) {
            ex = new DataResponse();
            ex.reason = e.getMessage();
            return ex;
        } else {
            ex = new DataResponse();
            ex.code = ERROR.UNKNOWN;
            ex.reason = "未知错误";
            return ex;
        }
    }

    /**
     * 约定异常
     */
    class ERROR {
        /**
         * 未知错误
         */
        static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        static final int SSL_ERROR = 1005;
    }

    public class ServerException extends RuntimeException {
        public int code;
        public String message;
    }
}
