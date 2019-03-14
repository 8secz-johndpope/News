package com.heaven.base.ui.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * FileName: com.heaven.base.ui.view.widget.BaseView.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-14 17:40
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class BaseView extends LinearLayout {
    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, @androidx.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseView(Context context, @androidx.annotation.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //先取出BaseView的父view 对SimpleFlowLayout 的测量限制 这一步很重要噢。
        //你只有知道自己的宽高 才能限制你子view的宽高
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        for (int i = 0; i < getChildCount(); i++) {

        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
