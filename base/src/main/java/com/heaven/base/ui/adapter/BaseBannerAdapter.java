package com.heaven.base.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.heaven.base.ui.adapter.BaseBannerAdapter.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-23 15:21
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class BaseBannerAdapter<T> extends PagerAdapter implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private Context mContext;
    private ItemClickListener itemClickListener;
    private int currentPosition = 0;

    private List<T> mDataItems;

    public BaseBannerAdapter(Context context, ViewPager viewPager) {
        mContext = context;
        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void updatePagerData(List<T> dataItems) {
        mDataItems = dataItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return  mDataItems != null ? mDataItems.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = bindView(mContext,mDataItems.get(position),position);
        view.setOnClickListener(this);
        container.addView(view);
        return view;
    }

    public abstract View bindView(Context context,T t,int position);


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View v) {
        if (null != itemClickListener) {
            itemClickListener.onItemClick(currentPosition);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface ItemClickListener {
        void onItemClick(int index);
    }
}
