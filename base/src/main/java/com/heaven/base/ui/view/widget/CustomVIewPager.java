package com.heaven.base.ui.view.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * FileName: com.heaven.base.ui.view.widget.CustomVIewPager.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-01 19:42
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class CustomVIewPager extends ViewPager {
    public CustomVIewPager(@NonNull Context context) {
        super(context);
    }

    public CustomVIewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height) height = h;
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
