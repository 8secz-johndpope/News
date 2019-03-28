package com.heaven.base.ui.view.widget.banner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * FileName: com.heaven.base.ui.view.widget.banner.BannerListener.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-28 09:20
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class BannerListener extends RecyclerView.OnScrollListener implements View.OnLayoutChangeListener {
    private RecyclerViewPager mRecyclerView;
    public BannerListener(RecyclerViewPager recyclerView) {
        super();
        this.mRecyclerView = recyclerView;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                mPositionText.setText("First: " + mRecyclerViewPager.getFirstVisiblePosition());
        int childCount = mRecyclerView.getChildCount();
        int width = mRecyclerView.getChildAt(0).getWidth();
        int padding = (mRecyclerView.getWidth() - width) / 2;
//                mCountText.setText("Count: " + childCount);

        for (int j = 0; j < childCount; j++) {
            View v = recyclerView.getChildAt(j);
            //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
            float rate = 0;
            ;
            if (v.getLeft() <= padding) {
                if (v.getLeft() >= padding - v.getWidth()) {
                    rate = (padding - v.getLeft()) * 1f / v.getWidth();
                } else {
                    rate = 1;
                }
                v.setScaleY(1 - rate * 0.1f);
                v.setScaleX(1 - rate * 0.1f);

            } else {
                //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                if (v.getLeft() <= recyclerView.getWidth() - padding) {
                    rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                }
                v.setScaleY(0.9f + rate * 0.1f);
                v.setScaleX(0.9f + rate * 0.1f);
            }
        }
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (mRecyclerView.getChildCount() < 3) {
            if (mRecyclerView.getChildAt(1) != null) {
                if (mRecyclerView.getCurrentPosition() == 0) {
                    View v1 = mRecyclerView.getChildAt(1);
                    v1.setScaleY(0.9f);
                    v1.setScaleX(0.9f);
                } else {
                    View v1 = mRecyclerView.getChildAt(0);
                    v1.setScaleY(0.9f);
                    v1.setScaleX(0.9f);
                }
            }
        } else {
            if (mRecyclerView.getChildAt(0) != null) {
                View v0 = mRecyclerView.getChildAt(0);
                v0.setScaleY(0.9f);
                v0.setScaleX(0.9f);
            }
            if (mRecyclerView.getChildAt(2) != null) {
                View v2 = mRecyclerView.getChildAt(2);
                v2.setScaleY(0.9f);
                v2.setScaleX(0.9f);
            }
        }
    }
}
