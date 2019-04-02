package com.heaven.news.ui.vm.model.base;

import java.io.Serializable;

/**
 * FileName: com.heaven.news.ui.vm.model.base.Version.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-07 18:33
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class Version implements Serializable {
    public String url;
    public int fversion;
    public int cversion;
    public String txt;


    @Override
    public String toString() {
        return "Version{" +
                "url='" + url + '\'' +
                ", fversion='" + fversion + '\'' +
                ", cversion='" + cversion + '\'' +
                ", txt='" + txt + '\'' +
                '}';
    }
}