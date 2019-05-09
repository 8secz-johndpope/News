package com.heaven.news.ui.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;

import com.heaven.news.R;
import com.neusoft.szair.model.city.cityListVO;

/**
 * Created by frank on 2017/4/11.
 */
public class StickyGridDecoration extends RecyclerView.ItemDecoration {


    private StickHeaderCallback mCallback;
    private int mHeaderHeight;
    private int mDividerHeight;


    //用来绘制Header上的文字
    private TextPaint mTextPaint;
    private Paint mPaint;
    private float mTextSize;
    private Paint.FontMetrics mFontMetrics;

    private float mTextOffsetX = 15;

    public StickyGridDecoration(Context context, int dividerColor, StickHeaderCallback callback) {
        this.mCallback = callback;
        mDividerHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_divider_height);
        mHeaderHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_height);
        mTextSize = context.getResources().getDimensionPixelOffset(R.dimen.header_textsize);
        mTextOffsetX = context.getResources().getDimensionPixelOffset(R.dimen._15dp);

        mHeaderHeight = (int) Math.max(mHeaderHeight,mTextSize);

        mTextPaint = new TextPaint();
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(context.getResources().getColor(dividerColor));
        mTextPaint.setTextSize(mTextSize);
        mFontMetrics = mTextPaint.getFontMetrics();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.TRANSPARENT);

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);

        if ( mCallback != null ) {
            //如果是组内的第一个则将间距撑开为一个Header的高度，或者就是普通的分割线高度
//            if (mCallback.isFirstInGroup(position)) {
//                outRect.top = mHeaderHeight;
//            } else {
//                outRect.top = mDividerHeight;
//            }
        }
    }


    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int childCount = parent.getChildCount();

        for ( int i = 0; i < childCount; i++ ) {
            View view = parent.getChildAt(i);

            int index = parent.getChildAdapterPosition(view);


            if ( mCallback != null ) {

                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();

                //屏幕上第一个可见的 ItemView 时，i == 0;
                if ( i != 0 ) {


                    //只有组内的第一个ItemView之上才绘制
                    if ( mCallback.isFirstInGroup(index) ) {

                        int top = view.getTop() - mHeaderHeight;

                        int bottom = view.getTop();
                        drawHeaderRect(c, mCallback.getTitle(index), left, top, right, bottom);

                    }

                } else {

                    //当 ItemView 是屏幕上第一个可见的View 时，不管它是不是组内第一个View
                    //它都需要绘制它对应的 StickyHeader。

                    // 还要判断当前的 ItemView 是不是它组内的最后一个 View

                    int top = parent.getPaddingTop();


                    if (mCallback.isLastInGroup(index)) {
                        int suggestTop = view.getBottom() - mHeaderHeight;
                        // 当 ItemView 与 Header 底部平齐的时候，判断 Header 的顶部是否小于
                        // parent 顶部内容开始的位置，如果小于则对 Header.top 进行位置更新，
                        //否则将继续保持吸附在 parent 的顶部
                        if ( suggestTop < top ) {
                            top = suggestTop;
                        }
                    }

                    int bottom = top + mHeaderHeight;

                    drawHeaderRect(c, mCallback.getTitle(index), left, top, right, bottom);
                }

            }
        }
    }

    private void drawHeaderView(Canvas canvas) {
    }

    private void drawHeaderRect(Canvas c, String title, int left, int top, int right, int bottom) {
        RectF rectF = new RectF(left,top,right,bottom);
        //绘制Header
        c.drawRect(rectF,mPaint);
        float titleX =  left + mTextOffsetX;
        float distance=(mFontMetrics.bottom - mFontMetrics.top)/2 - mFontMetrics.bottom;
        float baseline=rectF.centerY()+distance;
        if(!TextUtils.isEmpty(title)) {
            c.drawText(title, titleX, baseline, mTextPaint);
        }
    }

    public StickHeaderCallback getCallback() {
        return mCallback;
    }

    public void setCallback(StickHeaderCallback callback) {
        this.mCallback = callback;
    }

    public interface StickHeaderCallback {

        boolean isFirstInGroup(int position);

        boolean isLastInGroup(int position);

        String  getTitle(int position);
    }
}
