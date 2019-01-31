package com.heaven.data.net;

/**
 * 作者:Heaven
 * 时间: on 2017/2/23 16:14
 * 邮箱: heavenisme@aliyun.com
 */

public class DataResponse<T> {
    public String reason;
    // 身份id
    public String sessionID;
    //请求状态码
    public int code;
    public int error_code;
    public String resultcode;
    //请求结果描述
    public Object detail;

    public T data;
}
