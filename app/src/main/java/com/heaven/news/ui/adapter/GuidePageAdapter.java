package com.heaven.news.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.heaven.base.ui.adapter.BasePageAdapter;
import com.heaven.news.R;

/**
 * FileName: com.heaven.news.ui.adapter.GuidePageAdapter.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-25 21:54
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class GuidePageAdapter extends BasePageAdapter<Integer> {

    public GuidePageAdapter(Context context) {
        super(context);
    }

    @Override
    public int initLayoutRes() {
        return R.layout.guide_item;
    }

    @Override
    public void bindView(View viewItem, Integer integer, int position) {
        ImageView img = viewItem.findViewById(R.id.guide_img);
        TextView tryNow = viewItem.findViewById(R.id.trynow);
        img.setImageResource(integer);
        if(position == getCount() -1) {
            tryNow.setVisibility(View.VISIBLE);
            tryNow.setOnClickListener(itemClickListener);
        } else {
            tryNow.setVisibility(View.GONE);
        }
    }
}
