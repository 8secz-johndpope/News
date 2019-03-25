package com.heaven.base.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.heaven.base.ui.adapter.BaseLoopBannerAdapter.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-23 15:21
 *
 * @version V1.0 循环banner
 */
public abstract class BasePageAdapter<T> extends PagerAdapter{
    private Context mContext;
    protected View.OnClickListener itemClickListener;
    private int currentPosition = 0;
    //内存优化界面复用
    private List<View> mConvertView = new ArrayList<>();;
    private List<T> mDataItems;

    public BasePageAdapter(Context context) {
        mContext = context;
    }

    public void setItemClickListener(View.OnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void updatePagerData(List<T> dataItems) {
        mDataItems = dataItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return  (mDataItems != null && mDataItems.size() > 0)? mDataItems.size() : 0;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View convertView = getConvertView();

        View itemView = convertView == null? LayoutInflater.from(mContext).inflate(initLayoutRes(),null) : convertView;

        bindView(itemView,mDataItems.get(position),position);
        if(itemClickListener != null) {
            itemView.setOnClickListener(itemClickListener);
        }
        container.addView(itemView);
        return itemView;
    }

    public abstract int initLayoutRes();

    public abstract void bindView(View viewItem,T t,int position);


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        if(mConvertView.size() <= getCount()) {
            mConvertView.add(( View ) object);
        }
    }

    /**
     * 处理页面复用
     *
     * @return
     */
    public View getConvertView() {
        for (int i = 0; i < mConvertView.size(); i++) {
            if (mConvertView.get(i).getParent() == null) {
                return mConvertView.get(i);
            }
        }
        return null;
    }

}
