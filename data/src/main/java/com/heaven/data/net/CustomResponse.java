package com.heaven.data.net;

/**
 * 作者：Heaven
 * 时间: on 2016/10/8 16:23
 * 邮箱：heavenisme@aliyun.com
 * 自定义回应报文封装简化ui层处理逻辑
 */

public class CustomResponse<T> {
    /**
     * if the request got correct response
     */
    public boolean isSuccess = false;
    /**
     * request action
     */
    public String action = null;
    /**
     * response code
     */
    public int code;
    /**
     * response data package
     */
    public T resData = null;
    /**
     * response string data
     */
    public String content = null;
    /**
     * request error type
     */
    public int errorType = -1;
    /**
     * error detail
     */
    public String detail = null;

    @Override
    public String toString() {
        return "CustomResponse{" +
                "isSuccess=" + isSuccess +
                ", action='" + action + '\'' +
                ", code='" + code + '\'' +
                ", resData=" + resData +
                ", content='" + content + '\'' +
                ", errorType=" + errorType +
                ", detail='" + detail + '\'' +
                '}';
    }
}
