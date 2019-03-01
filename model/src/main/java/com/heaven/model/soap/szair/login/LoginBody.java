package com.heaven.model.soap.szair.login;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * 作者:Heaven
 * 时间: on 2017/7/8 11:05
 * 邮箱:heavenisme@aliyun.com
 */
@Root(name = "soap:Body", strict = false)
@Namespace(reference = "")
public class LoginBody {
    @Element(name = "loginNew")
    @Namespace(reference = "http://com/szcares/member/webservice/login",prefix = "ns2")
    public LoginAction action;
}
