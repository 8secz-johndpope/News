package com.heaven.news.ui.model.holder;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * FileName: com.heaven.news.ui.vm.bean.GrideDecoration.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-29 16:09
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class GrideDecoration extends RecyclerView.ItemDecoration {
    private int itemSpace;
    private int itemNum;

    /**
     *
     * @param itemSpace item间隔
     * @param itemNum 每行item的个数
     */
    public GrideDecoration(int itemSpace, int itemNum) {
        this.itemSpace = itemSpace;
        this.itemNum = itemNum;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = itemSpace;
        if (parent.getChildLayoutPosition(view)%itemNum == 0){  //parent.getChildLayoutPosition(view) 获取view的下标
            outRect.left = 0;
        } else {
            outRect.left = itemSpace;
        }

    }
}
