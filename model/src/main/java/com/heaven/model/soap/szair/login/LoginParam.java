package com.heaven.model.soap.szair.login;

import com.heaven.model.soap.szair.BaseConstReqParam;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 作者:Heaven
 * 时间: on 2017/7/7 15:31
 * 邮箱:heavenisme@aliyun.com
 */
@Root(name="LOGIN_PARAM",strict = false)
public class LoginParam extends BaseConstReqParam {
    @Element(name = "DEVICE_NUMBER", required = false)
    public String DEVICE_NUMBER;
    @Element(name = "DEVICE_TOKEN", required = false)
    public String DEVICE_TOKEN;
    @Element(name = "DEVICE_TYPE", required = false)
    public String DEVICE_TYPE = "ANDROID";
    @Element(name = "LAST_LOGIN_ACCOUNT", required = false)
    public String LAST_LOGIN_ACCOUNT;
    @Element(name = "PASSWORD", required = false)
    public String PASSWORD;
    @Element(name = "USER_NAME", required = false)
    public String _USER_NAME;
    @Element(name = "AUTH_CODE", required = false)
    public String AUTH_CODE;
}
