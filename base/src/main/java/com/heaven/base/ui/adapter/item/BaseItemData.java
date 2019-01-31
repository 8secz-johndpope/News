package com.heaven.base.ui.adapter.item;

import android.view.View;

/**
 * 作者:Heaven
 * 时间: on 2017/4/12 12:59
 * 邮箱:heavenisme@aliyun.com
 */

public class BaseItemData implements ItemData {
    protected int visibility = View.VISIBLE;

    @Override
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    @Override
    public int getVisibility() {
        return visibility;
    }
}
