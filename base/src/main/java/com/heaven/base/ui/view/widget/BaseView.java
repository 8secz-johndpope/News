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
        int baseWidth = getMeasuredWidth();
        int baseHeight = getMeasuredHeight();
        int basePaddingLeft = getPaddingLeft();
        int basePaddingRight = getPaddingRight();
        int basePaddingTop = getPaddingTop();
        int basePaddingBottom = getPaddingBottom();

        if (getChildCount() == 1) {
            int measureWidth = MeasureSpec.makeMeasureSpec(baseWidth - basePaddingLeft - basePaddingRight, MeasureSpec.EXACTLY);
            int measureHeight = MeasureSpec.makeMeasureSpec(baseHeight - basePaddingTop - basePaddingBottom, MeasureSpec.EXACTLY);
            getChildAt(0).measure(measureWidth, measureHeight);
        } else if (getChildCount() == 2) {
            int measureWidth = MeasureSpec.makeMeasureSpec(baseWidth - basePaddingLeft - basePaddingRight, MeasureSpec.EXACTLY);

            int measureTitleHeight = MeasureSpec.makeMeasureSpec(baseHeight - basePaddingTop - basePaddingBottom, MeasureSpec.AT_MOST);

            View childTitle = getChildAt(0);
            View childBody = getChildAt(1);

            childTitle.measure(measureWidth,measureTitleHeight);

            int childTitleHeight = childTitle.getMeasuredHeight();
            int childBodyHeight = childBody.getMeasuredHeight();

            int measureBodyHeight = MeasureSpec.makeMeasureSpec(baseHeight- childTitleHeight - basePaddingTop - basePaddingBottom, MeasureSpec.EXACTLY);
            childBody.measure(measureWidth,measureBodyHeight);
        } else {
            throw new IllegalStateException("BaseView must contains only one or two direct child.");
        }
    }


    @Override
    protected void onLayout(boolean changed, int left, int t, int right, int b) {
        int baseWidth = getMeasuredWidth();
        int baseHeight = getMeasuredHeight();
        int basePaddingLeft = getPaddingLeft();
        int basePaddingRight = getPaddingRight();
        int basePaddingTop = getPaddingTop();
        int basePaddingBottom = getPaddingBottom();

        if (getChildCount() == 0) {
            return;
        }

        if(getChildCount() == 1) {
            View child = getChildAt(0);
            int childWidth = baseWidth - basePaddingLeft - basePaddingRight;
            int childHeight = baseHeight - basePaddingTop - basePaddingBottom;
            int childLeft = getPaddingLeft();
            int childTop = getPaddingTop();
            int childRight = childLeft + childWidth;
            int childBottom = childTop + childHeight;
            child.layout(childLeft, childTop, childRight, childBottom);
        } else if(getChildCount() == 2) {
            View childTitle = getChildAt(0);
            View childBody = getChildAt(1);
            int childWidth = baseWidth - getPaddingLeft() - getPaddingRight();
            int childHeight = baseHeight - getPaddingTop() - getPaddingBottom();
            int childLeft = getPaddingLeft();
            int childTop = getPaddingTop();
            int childRight = childLeft + childWidth;


            int childTitleBottom = childTop + childTitle.getMeasuredHeight();
            int childBodyTop = childTop + childTitle.getMeasuredHeight();
            int childBodyBottom = childTitleBottom + childBody.getMeasuredHeight();
            childTitle.layout(childLeft, childTop, childRight, childTitleBottom);
            childBody.layout(childLeft, childBodyTop, childRight, childBodyBottom);
        }
    }
}
