package com.heaven.news.ui.model.bean.base;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: com.heaven.news.ui.vm.model.base.HomeImageInfo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-17 21:35
 *
 * @author heaven
 * @version V1.0 首页轮播图片和推荐
 */
public class HomeImageInfo implements Serializable {
    private static final long serialVersionUID = 2691638900907257210L;
    public int netCode = 0;
    public String message;
    public List<ImageInfo> top;        //轮播图
    public List<ImageInfo> easygotop;        //易行轮播图
    public List<ImageInfo> phoenix;        //凤凰知音轮播图
    public List<ImageInfo> member;     //会员活动
    public List<ImageInfo> fly;     //飞享惠
    public List<ImageInfo> hot;//热门目的地
    public List<ImageInfo> cjbz;        //乘机帮助轮播图
}
