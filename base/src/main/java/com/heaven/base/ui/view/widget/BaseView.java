package com.heaven.base.ui.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
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

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() == 1) {
            int measureWidth = MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), MeasureSpec.EXACTLY);
            int measureHeight = MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY);
            getChildAt(0).measure(measureWidth, measureHeight);
        } else if (getChildCount() == 2) {
            int measureWidth = MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), MeasureSpec.EXACTLY);
            int measureHeight = MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY);
            measureChildren(measureWidth,measureHeight);
            View childTitle = getChildAt(0);
            View childBody = getChildAt(1);

            int childTitleHeight = childTitle.getMeasuredHeight();
            int childBodyHeight = childBody.getMeasuredHeight();


            childTitle.measure(measureWidth, measureHeight);
        } else {
            throw new IllegalStateException("BaseView must contains only one or two direct child.");
        }
    }


    @Override
    protected void onLayout(boolean changed, int left, int t, int right, int b) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }

        if(getChildCount() == 1) {
            View child = getChildAt(0);
            View childTwo = getChildAt(1);
            int childWidth = width - getPaddingLeft() - getPaddingRight();
            int childHeight = height - getPaddingTop() - getPaddingBottom();
            int childLeft = getPaddingLeft();
            int childTop = getPaddingTop();
            int childRight = childLeft + childWidth;
            int childBottom = childTop + childHeight;
            child.layout(childLeft, childTop, childRight, childBottom);
        } else if(getChildCount() == 2) {
            View child = getChildAt(0);
            View childTwo = getChildAt(1);
            int childWidth = width - getPaddingLeft() - getPaddingRight();
            int childHeight = height - getPaddingTop() - getPaddingBottom();
            int childLeft = getPaddingLeft();
            int childTop = getPaddingTop();
            int childRight = childLeft + childWidth;


            int childBodyTop = childTop + child.getMeasuredHeight();

            int childTitleBottom = childTop + child.getMeasuredHeight();
            int childBodyBottom = childTitleBottom + childTwo.getMeasuredHeight();

            child.layout(childLeft, childTop, childRight, childTitleBottom);

            childTwo.layout(childLeft, childBodyTop, childRight, childBodyBottom);
        }


    }
}
