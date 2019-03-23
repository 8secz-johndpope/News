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
    //内存优化界面复用
    private List<View> mConvertView = new ArrayList<>();;
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
        return  (mDataItems != null && mDataItems.size() > 0)? Integer.MAX_VALUE : 0;
    }

    public int getRealCount() {
        return mDataItems != null? mDataItems.size() : 0;
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
        int realPositionn = position % mDataItems.size();

        bindView(itemView,mDataItems.get(realPositionn),realPositionn);
        itemView.setOnClickListener(this);
        container.addView(itemView);
        return itemView;
    }

    public abstract int initLayoutRes();

    public abstract void bindView(View viewItem,T t,int position);


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        mConvertView.add(( View ) object);
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
