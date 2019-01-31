package com.heaven.model.soap.szair;

import org.simpleframework.xml.Element;

/**
 * 作者:Heaven
 * 时间: on 2017/7/7 15:30
 * 邮箱:heavenisme@aliyun.com
 */

public class BaseConstReqParam {
    @Element(name = "APP_ID", required = false)
    public String APP_ID = "5";
    @Element(name = "APP_IP", required = false)
    public String APP_IP = "127.0.0.1";
}
