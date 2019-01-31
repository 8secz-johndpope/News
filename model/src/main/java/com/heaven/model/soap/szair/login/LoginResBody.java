package com.heaven.model.soap.szair.login;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 作者:Heaven
 * 时间: on 2017/7/17 17:42
 * 邮箱:heavenisme@aliyun.com
 */
@Root(name = "Body",strict = false)
public class LoginResBody {
    @Element(name = "loginResponse")
    public LoginResAction loginResponse;
}
