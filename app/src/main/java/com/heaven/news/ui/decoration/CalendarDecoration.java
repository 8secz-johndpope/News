package com.heaven.news.ui.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * FileName: com.heaven.news.ui.decoration.CalendarDecoration.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-05-09 09:53
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class CalendarDecoration extends RecyclerView.ItemDecoration {
    private StickyGridDecoration.StickHeaderCallback mCallback;
    private Context mContext;

    public CalendarDecoration(Context mContext, StickyGridDecoration.StickHeaderCallback mCallback) {
        this.mCallback = mCallback;
        this.mContext = mContext;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int firstVisiblePos = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        if (firstVisiblePos == RecyclerView.NO_POSITION) {
            return;
        }

        if(mCallback != null) {


        }

    }
}
