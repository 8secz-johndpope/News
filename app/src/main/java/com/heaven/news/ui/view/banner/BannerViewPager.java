package com.heaven.news.ui.view.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.heaven.base.ui.adapter.BaseLoopBannerAdapter;

import java.lang.reflect.Field;

/**
 * FileName: com.heaven.news.ui.view.banner.BannerViewPagerT.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-23 23:13
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class BannerViewPager extends ViewPager {
    private static final int SCROLL_MSG = 0x011;
    private int mCutDownTime = 3000;
    private BannerScroller mBannerScroller;
    private BaseLoopBannerAdapter mAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SCROLL_MSG:
                    setCurrentItem(getCurrentItem() + 1);
                    startLoop();
                    break;
            }
        }
    };

    public BannerViewPager(@NonNull Context context) {
        this(context, null);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //改变ViewPager切换的速率
        try {
            //获取ViewPager的私有的属性mScroller
            Field field = ViewPager.class.getDeclaredField("mScroller");
            mBannerScroller = new BannerScroller(context);
            //设置强制改变
            field.setAccessible(true);
            //设置参数 第一个参数object当前属性的那个类 第二参数需要设置的值
            field.set(this, mBannerScroller);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启轮播
     */
    public void startLoop() {
        mHandler.removeMessages(SCROLL_MSG);
        mHandler.sendEmptyMessageDelayed(SCROLL_MSG, mCutDownTime);
    }

    /**
     * 销毁Handler
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeMessages(SCROLL_MSG);
        mHandler = null;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mHandler.removeMessages(SCROLL_MSG);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                //左滑动到第一张，跳转到最后一张
                if (this.getCurrentItem() == 0) {
                    if (ev.getX() - mDownX > 0) {
                        PagerAdapter pagerAdapter = getAdapter();
                        if(pagerAdapter instanceof  BaseLoopBannerAdapter) {
                            this.setCurrentItem(((BaseLoopBannerAdapter)pagerAdapter).getRealCount() - 1);
                        }
                        Log.i("BannerViewPager", "onTouchEvent: " + this.getCurrentItem());
                    }
                }
                mHandler.sendEmptyMessageDelayed(SCROLL_MSG, mCutDownTime);

                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private float mDownX;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mHandler.removeMessages(SCROLL_MSG);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                //左滑动到第一张，跳转到最后一张
                if (this.getCurrentItem() == 0) {
                    if (ev.getX() - mDownX > 0) {
                        PagerAdapter pagerAdapter = getAdapter();
                        if(pagerAdapter instanceof  BaseLoopBannerAdapter) {
                            this.setCurrentItem(((BaseLoopBannerAdapter)pagerAdapter).getRealCount() - 1);
                        }
                        Log.i("BannerViewPager", "onTouchEvent: " + this.getCurrentItem());
                    }
                }
                mHandler.sendEmptyMessageDelayed(SCROLL_MSG, mCutDownTime);

                break;
        }
        return super.onTouchEvent(ev);
    }
}
