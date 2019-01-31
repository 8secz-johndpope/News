package com.heaven.model.soap.szair;

import org.simpleframework.xml.Element;

/**
 * 作者:Heaven
 * 时间: on 2017/7/17 17:45
 * 邮箱:heavenisme@aliyun.com
 */

public class BaseConstResParam {
    @Element(name = "CODE")
    public String CODE;
    @Element(name = "MSG")
    public String MSG;
}
