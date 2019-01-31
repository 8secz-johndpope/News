package com.heaven.model.soap.szair;

import com.heaven.model.soap.request.RequestBody;
import com.heaven.model.soap.szair.encrypt.KeyGenerator;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

/**
 * 作者:Heaven
 * 时间: on 2017/7/7 15:34
 * 邮箱:heavenisme@aliyun.com
 */
@Root(name = "soap:Envelope")
@NamespaceList({
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"),
})
public class BaseRequest {

    @Attribute(name = "MOBILE_TYPE")
    @Path("soap:Header")
    public String MOBILE_TYPE = new KeyGenerator().getKey();
    @Element(name = "soap:Body", required = false)
    public Object body;
}
