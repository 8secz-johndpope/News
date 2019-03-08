package com.heaven.news.vm.model;

/**
 * FileName: com.heaven.news.vm.model.Version.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-07 18:33
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class Version {
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
