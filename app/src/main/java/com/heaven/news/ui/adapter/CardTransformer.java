package com.heaven.news.ui.adapter;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * FileName: com.heaven.news.ui.adapter.CardTransformer.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-23 15:55
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class CardTransformer implements ViewPager.PageTransformer{
    private static final float MAX_SCALE = 1.2f;
    private static final float MIN_SCALE = 1.0f;//0.85f

    @Override
    public void transformPage(View page, float position) {
        if (position <= 1) {
            //   1.2f + (1-1)*(1.2-1.0)
            float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);

            page.setScaleX(scaleFactor);  //缩放效果

            if (position > 0) {
                page.setTranslationX(-scaleFactor * 2);
            } else if (position < 0) {
                page.setTranslationX(scaleFactor * 2);
            }
            page.setScaleY(scaleFactor);
        } else {

            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }
    }
}
