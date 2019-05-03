package com.heaven.news.ui.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
public class StickySectionDecoration extends RecyclerView.ItemDecoration {


    private GroupInfoCallback mCallback;
    private int mHeaderHeight;
    private int mDividerHeight;


    //用来绘制Header上的文字
    private TextPaint mTextPaint;
    private Paint mPaint;
    private float mTextSize;
    private Paint.FontMetrics mFontMetrics;

    private float mTextOffsetX = 15;

    public StickySectionDecoration(Context context, int dividerColor, GroupInfoCallback callback) {
        this.mCallback = callback;
        mDividerHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_divider_height);
        mHeaderHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_height);
        mTextSize = context.getResources().getDimensionPixelOffset(R.dimen.header_textsize);
        mTextOffsetX = context.getResources().getDimensionPixelOffset(R.dimen._15dp);

        mHeaderHeight = (int) Math.max(mHeaderHeight,mTextSize);

        mTextPaint = new TextPaint();
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
            cityListVO groupInfo = mCallback.getGroupInfo(position);

            //如果是组内的第一个则将间距撑开为一个Header的高度，或者就是普通的分割线高度
            if ( groupInfo != null && groupInfo.isFirstInGroup ) {
                outRect.top = mHeaderHeight;
            } else {
                outRect.top = mDividerHeight;
            }
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

                cityListVO groupinfo = mCallback.getGroupInfo(index);
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();

                //屏幕上第一个可见的 ItemView 时，i == 0;
                if ( i != 0 ) {


                    //只有组内的第一个ItemView之上才绘制
                    if ( groupinfo.isFirstInGroup ) {

                        int top = view.getTop() - mHeaderHeight;

                        int bottom = view.getTop();
                        drawHeaderRect(c, groupinfo, left, top, right, bottom);

                    }

                } else {

                    //当 ItemView 是屏幕上第一个可见的View 时，不管它是不是组内第一个View
                    //它都需要绘制它对应的 StickyHeader。

                    // 还要判断当前的 ItemView 是不是它组内的最后一个 View

                    int top = parent.getPaddingTop();


                    if ( groupinfo.isLastInGroup ) {
                        int suggestTop = view.getBottom() - mHeaderHeight;
                        // 当 ItemView 与 Header 底部平齐的时候，判断 Header 的顶部是否小于
                        // parent 顶部内容开始的位置，如果小于则对 Header.top 进行位置更新，
                        //否则将继续保持吸附在 parent 的顶部
                        if ( suggestTop < top ) {
                            top = suggestTop;
                        }
                    }

                    int bottom = top + mHeaderHeight;

                    drawHeaderRect(c, groupinfo, left, top, right, bottom);
                }

            }
        }
    }

    private void drawHeaderRect(Canvas c, cityListVO groupinfo, int left, int top, int right, int bottom) {
        //绘制Header
        c.drawRect(left,top,right,bottom,mPaint);

        float titleX =  left + mTextOffsetX;
        float titleY =  bottom - mFontMetrics.descent;
        //绘制Title
        if(!TextUtils.isEmpty(groupinfo.groupTitle)) {
            c.drawText(groupinfo.groupTitle,titleX,titleY,mTextPaint);
        }
    }

    public GroupInfoCallback getCallback() {
        return mCallback;
    }

    public void setCallback(GroupInfoCallback callback) {
        this.mCallback = callback;
    }

    public interface GroupInfoCallback {

        cityListVO getGroupInfo(int position);
    }
}
