package com.heaven.model.soap.szair.login;

import com.heaven.model.soap.szair.SoapBaseRequest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

/**
 * FileName: com.heaven.model.soap.szair.login.LoginRequest.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-03 23:03
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
@Root(name = "soap:Envelope")
@NamespaceList({
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"),
})
public class LoginRequest extends SoapBaseRequest {
    @Path("soap:Body")
    @Element(name = "loginNew")
    @Namespace(reference = "http://com/szcares/member/webservice/login",prefix = "ns2")
    public Object body;
}
