package com.heaven.news.ui.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.heaven.news.R;

/**
 * FileName: com.heaven.news.ui.decoration.RouteTimeDecorationn.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-26 11:09
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class RouteTimeDecorationn extends RecyclerView.ItemDecoration {
    private Context mContext;
    private Drawable mRouteIndexFlag;
    private Drawable mRouteTimeFlag;
    private Drawable mRouteInfoFlag;

    private Paint mLinePaint;
    private Paint mGapLinePaint;

    private int paddingLeft;
    private int firstPaddingTop;
    Paint test;
    public RouteTimeDecorationn(Context mContext) {
        this.mContext = mContext;
        mRouteIndexFlag = getDrawable(R.mipmap.route_start_flag);
        mRouteTimeFlag = getDrawable(R.mipmap.route_time_flag);
        mRouteInfoFlag = getDrawable(R.mipmap.route_info_flag);

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth((float) 0.5); //时间轴线的宽度。
        mLinePaint.setColor(Color.BLUE); //时间轴线的颜色。

        mGapLinePaint = new Paint();
        mGapLinePaint.setStyle(Paint.Style.STROKE);
        mGapLinePaint.setAntiAlias(true);
        mGapLinePaint.setStrokeWidth(1);
        mGapLinePaint.setColor(Color.parseColor("#4d7aad"));
        mGapLinePaint.setPathEffect(new DashPathEffect(new float[]{4, 4}, 0));
        //绘制长度为4的实线后再绘制长度为4的空白区域，0位间隔
        firstPaddingTop = dip2px(20);
        paddingLeft = dip2px(10);

        test = new Paint(Paint.ANTI_ALIAS_FLAG);
        test.setStyle(Paint.Style.STROKE);
        test.setColor(Color.RED);
        test.setStrokeWidth(1);
        PathEffect effects = new DashPathEffect(new float[] { 1, 2, 4, 8}, 1);
        test.setPathEffect(effects);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        parent.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            drawStrokLine(c,parent,view);
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        if(pos == 0) {
            outRect.top = firstPaddingTop;
        }
        outRect.left = paddingLeft;
    }

    private void drawStrokLine(Canvas c,RecyclerView parent,View view) {

        //int index = parent.getChildAdapterPosition(view);

        float paddingLef = view.getPaddingLeft();
        float paddingRight = view.getPaddingRight();
        float paddingTop = view.getPaddingTop();
        float paddingBottom = view.getPaddingBottom();

        float left = view.getLeft() - paddingLeft;
        float top = view.getTop() + paddingTop;
        float right = view.getLeft() - paddingLeft;
        float bottom = view.getBottom();

       View titleIndex = view.findViewById(R.id.route_index_title);
       int w = titleIndex.getWidth();
       int h = titleIndex.getHeight();
       titleIndex.getY();

       int meW = titleIndex.getMeasuredHeight();
       int meH = titleIndex.getMeasuredWidth();

        c.drawLine(left,top,right,bottom,test);
    }

    private void drawDashLine(Canvas c,RecyclerView parent,View view) {

    }

    private void drawIndexFlag(Canvas c,RecyclerView parent,View view) {

    }

    private void drawRouteTimeFlag(Canvas c,RecyclerView parent,View view) {

    }

    private void drawRouteInfoFlag(Canvas c,RecyclerView parent,View view) {

    }




    private Drawable getDrawable(@DrawableRes int resId) {
        return ContextCompat.getDrawable(mContext, resId);
    }

    private int dip2px(float dpValue) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
