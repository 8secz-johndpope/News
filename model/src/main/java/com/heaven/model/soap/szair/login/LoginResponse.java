package com.heaven.model.soap.szair.login;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * 作者:Heaven
 * 时间: on 2017/7/17 17:35
 * 邮箱:heavenisme@aliyun.com
 */
@Root(name = "Envelope",strict = false)
@NamespaceList({
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"),
})
public class LoginResponse {
    @Element(name = "Body",type = LoginResBody.class)
    public LoginResBody body;
}
