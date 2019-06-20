package com.heaven.news.ui.model.bean.base;

/**
 * FileName: com.heaven.news.ui.vm.model.base.HomeHotTitle.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-30 10:35
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class HomeHotTitle {
    public int titleType;
    public int titleResId;
    public String title;
    public int icon;
    public int more;

    public HomeHotTitle(int titleType, int titleResId, String title, int icon) {
        this.titleType = titleType;
        this.titleResId = titleResId;
        this.title = title;
        this.icon = icon;
    }
}
