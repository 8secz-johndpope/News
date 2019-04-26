package com.heaven.news.ui.decoration;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
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
    private Bitmap mRouteIndexFlag;
    private Bitmap mRouteTimeFlag;
    private Bitmap mRouteInfoFlag;

    private Paint mLinePaint;
    private Paint mGapLinePaint;
    private Paint bitmapPaint = new Paint();

    private int lefPadding;
    private int topPadding;
    private int bottomPadding;
    Paint test;

    public RouteTimeDecorationn(Context mContext) {
        this.mContext = mContext;
        mRouteIndexFlag = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.route_start_flag);
        mRouteTimeFlag = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.route_time_flag);
        mRouteInfoFlag = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.route_info_flag);

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
        topPadding = dip2px(20);
        bottomPadding = dip2px(40);
        lefPadding = dip2px(10);

        test = new Paint(Paint.ANTI_ALIAS_FLAG);
        test.setStyle(Paint.Style.STROKE);
        test.setColor(Color.RED);
        test.setStrokeWidth(1);
        PathEffect effects = new DashPathEffect(new float[]{1, 2, 4, 8}, 1);
        test.setPathEffect(effects);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        parent.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            drawIndexFlag(i, c, parent, view);
            drawRouteTimeFlag(i, c, parent, view);
            drawRouteInfoFlag(i, c, parent, view);
            drawStrokLine(i, c, parent, view);
            drawDashLine(i, c, parent, view);
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
        outRect.top = topPadding;
        outRect.left = lefPadding;
        outRect.bottom = bottomPadding;
    }

    private void drawStrokLine(int index, Canvas c, RecyclerView parent, View view) {

        //int index = parent.getChildAdapterPosition(view);

        View titleIndex = view.findViewById(R.id.route_index_title);
        View flighInfoView = view.findViewById(R.id.route_info_area);
        int titleMeasureH = titleIndex.getMeasuredHeight();
        int flightInfoH = flighInfoView.getMeasuredHeight();
        float left = view.getLeft() - lefPadding;
        float top = view.getTop();
        float right = view.getLeft() - lefPadding;
        float bottom = view.getBottom() - (flightInfoH >> 1);
        top = top + (titleMeasureH >> 1);

        c.drawLine(left, top, right, bottom, mLinePaint);
    }

    private void drawDashLine(int index, Canvas c, RecyclerView parent, View view) {
        View flighInfoView = view.findViewById(R.id.route_info_area);
        int titleMeasureH = view.findViewById(R.id.route_index_title).getMeasuredHeight();
        int flightInfoH = flighInfoView.getMeasuredHeight();
        float left = view.getLeft() - lefPadding;
        float top = view.getBottom() - (flightInfoH >> 1);
        float right = view.getLeft() - lefPadding;
        float bottom = view.getBottom() + bottomPadding + topPadding + (titleMeasureH >> 1);

        c.drawLine(left, top, right, bottom, test);
    }

    private void drawIndexFlag(int index, Canvas c, RecyclerView parent, View view) {
        int height = mRouteIndexFlag.getHeight();
        int width = mRouteIndexFlag.getWidth();

        View titleIndex = view.findViewById(R.id.route_index_title);
        int titleMeasureH = titleIndex.getMeasuredHeight();
        int left = view.getLeft() - lefPadding - width/2;
        int top = view.getTop() + (titleMeasureH >> 1) - height/2;

        c.save();
        c.drawBitmap(mRouteIndexFlag,left,top,bitmapPaint);
        c.restore();

    }

    private void drawRouteTimeFlag(int index, Canvas c, RecyclerView parent, View view) {
        int height = mRouteTimeFlag.getHeight();
        int width = mRouteTimeFlag.getWidth();

        View timeArea = view.findViewById(R.id.route_time_area);
        int timeMeasureH = timeArea.getMeasuredHeight();
        int left = view.getLeft() - lefPadding - width/2;
        int top = (int) (view.getTop() + timeArea.getY() + (timeMeasureH >> 1) - height/2);

        c.save();
        c.drawBitmap(mRouteTimeFlag,left,top,bitmapPaint);
        c.restore();
    }

    private void drawRouteInfoFlag(int index, Canvas c, RecyclerView parent, View view) {

    }


    private Drawable getDrawable(@DrawableRes int resId) {
        return ContextCompat.getDrawable(mContext, resId);
    }

    private int dip2px(float dpValue) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
