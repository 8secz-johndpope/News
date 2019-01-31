package com.heaven.model.soap.szair.login;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

/**
 * 作者:Heaven
 * 时间: on 2017/7/16 14:56
 * 邮箱:heavenisme@aliyun.com
 */
@Root(name = "login",strict = false)
@Namespace(reference = "http://com/szcares/member/webservice/login",prefix = "ns0")
public class LoginAction {
    @Element(name="LOGIN_PARAM",required = false)
    public LoginParam LOGIN_PARAM;
}
