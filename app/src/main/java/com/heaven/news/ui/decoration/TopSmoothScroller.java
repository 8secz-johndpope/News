package com.heaven.news.ui.decoration;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;

/**
 * FileName: com.heaven.news.ui.decoration.TopSmoothScroller.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-05-03 13:24
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class TopSmoothScroller extends LinearSmoothScroller {
    public TopSmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }

    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }

}
