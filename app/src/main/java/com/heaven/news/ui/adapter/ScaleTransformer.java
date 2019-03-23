package com.heaven.news.ui.adapter;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * FileName: com.heaven.news.ui.adapter.ScaleTransformer.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-23 20:10
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class ScaleTransformer  implements ViewPager.PageTransformer{
    private static final float MIN_SCALE = 0.70f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1 || position > 1) {
            page.setAlpha(MIN_ALPHA);
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        } else if (position <= 1) { // [-1,1]
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            if (position < 0) {
                float scaleX = 1 + 0.3f * position;
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            } else {
                float scaleX = 1 - 0.3f * position;
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            }
            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        }
    }
}
