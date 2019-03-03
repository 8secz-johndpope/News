package com.heaven.model.soap.szair.login;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Path;

/**
 * FileName: com.heaven.model.soap.szair.login.LoginMethod.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-03 22:47
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class LoginMethod {
    @Path("loginNew")
    @Namespace(reference = "http://com/szcares/member/webservice/login",prefix = "ns2")
    public Object LOGIN_PARAM;
}
