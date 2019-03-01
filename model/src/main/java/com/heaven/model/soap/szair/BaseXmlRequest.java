package com.heaven.model.soap.szair;

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
@Root()
@NamespaceList({
        @Namespace(reference = "?xml version='1.0' encoding='UTF-8' standalone='yes' ?"),
})
public class BaseXmlRequest {
   public BaseRequest baseRequest = new BaseRequest();
}
