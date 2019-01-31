package com.heaven.model.soap.szair.login;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 作者:Heaven
 * 时间: on 2017/7/17 17:41
 * 邮箱:heavenisme@aliyun.com
 */
@Root(name = "loginResponse",strict = false)
public class LoginResAction {
    @Element(name="LOGIN_RESULT",required = false)
    public LoginResParam LOGIN_RESULT;
}
