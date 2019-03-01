package com.heaven.model.soap.szair;

import com.heaven.model.soap.szair.login.LoginResBody;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

/**
 * 作者:Heaven
 * 时间: on 2017/7/17 17:35
 * 邮箱:heavenisme@aliyun.com
 */
@Root(name = "soap:Envelope")
@NamespaceList({
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"),
})
public class SoapBaseResponse<T> {
    @Path("soap:Body")
    public LoginResBody body;
}
