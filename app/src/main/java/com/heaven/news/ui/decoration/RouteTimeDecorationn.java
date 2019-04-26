package com.heaven.news.ui.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
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

    public RouteTimeDecorationn(Context mContext) {
        this.mContext = mContext;
        mRouteIndexFlag = getDrawable(R.mipmap.route_start_flag);
        mRouteTimeFlag = getDrawable(R.mipmap.route_time_flag);
        mRouteInfoFlag = getDrawable(R.mipmap.route_info_flag);

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(2); //时间轴线的宽度。
        mLinePaint.setColor(Color.BLUE); //时间轴线的颜色。

        mGapLinePaint = new Paint();
        mGapLinePaint.setStyle(Paint.Style.STROKE);
        mGapLinePaint.setAntiAlias(true);
        mGapLinePaint.setStrokeWidth(2);
        mGapLinePaint.setColor(Color.parseColor("#4d7aad"));
        mGapLinePaint.setPathEffect(new DashPathEffect(new float[]{4, 4}, 0));
        //绘制长度为4的实线后再绘制长度为4的空白区域，0位间隔

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }


    private Drawable getDrawable(@DrawableRes int resId) {
        return ContextCompat.getDrawable(mContext, resId);
    }
}
