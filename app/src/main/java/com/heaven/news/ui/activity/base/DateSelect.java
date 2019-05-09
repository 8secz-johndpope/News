package com.heaven.news.ui.activity.base;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.BaseMultAdapter;
import com.heaven.base.ui.view.calendar.Calendar;
import com.heaven.base.ui.view.calendar.Month;
import com.heaven.base.ui.view.rlview.OnLoadMoreListener;
import com.heaven.base.ui.view.rlview.OnRefreshListener;
import com.heaven.news.R;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.databinding.DateSelectBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.base.BaseToolBarBindActivity;
import com.heaven.news.ui.decoration.StickyGridDecoration;
import com.heaven.news.ui.decoration.StickySectionDecoration;
import com.heaven.news.ui.vm.holder.CalendarDayItemHolder;
import com.heaven.news.ui.vm.holder.CalendarMonthItemHolder;
import com.heaven.news.ui.vm.holder.CalendarTitleItemHolder;
import com.heaven.news.ui.vm.vmmodel.SelectDateViewModel;

/**
 * FileName: com.heaven.news.ui.activity.base.DateSelect.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-05-07 20:08
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
@Route(path = RouterUrl.ROUTER_URL_DATE)
public class DateSelect extends BaseToolBarBindActivity<SelectDateViewModel, DateSelectBinding> implements OnRefreshListener, OnLoadMoreListener {
    @Override
    public int initLayoutResId() {
        return R.layout.date_select;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        initCityViewNew();
//        initCityView();
//        new Handler().postDelayed(this::initCityView,50);
    }

    @Override
    public void bindModel() {

    }

    private void initCityView() {

        mViewBinding.swipeTarget.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        gridLayoutManager.setAutoMeasureEnabled(true);
        mViewBinding.swipeTarget.setHasFixedSize(true);
        mViewBinding.swipeTarget.setNestedScrollingEnabled(false);
        mViewBinding.swipeTarget.setLayoutManager(gridLayoutManager);
        BaseMultAdapter routeAdapter = new BaseMultAdapter(this,AppEngine.instance().confManager().loadCalendar());
        routeAdapter.setAnimationEnable(false);
        routeAdapter.register(new CalendarTitleItemHolder(Month.class, R.layout.calendar_month_item));
        routeAdapter.register(new CalendarDayItemHolder(Calendar.class, R.layout.calendar_item));
        mViewBinding.swipeTarget.addItemDecoration(new StickyGridDecoration(this, R.color.textColor, new StickyGridDecoration.StickHeaderCallback() {
            @Override
            public boolean isFirstInGroup(int position) {
                Object item = routeAdapter.getItemData(position);
                return item instanceof Month;
            }

            @Override
            public boolean isLastInGroup(int position) {
                Object item = routeAdapter.getItemData(position);
                if(item instanceof Calendar) {
                    return ((Calendar)item).isLastInGroup;
                }
                return false;
            }

            @Override
            public String getTitle(int position) {
                Object item = routeAdapter.getItemData(position);
                if(item instanceof Calendar) {
                    return ((Calendar)item).groupTitle;
                } else {
                    return ((Month)item).title;
                }
            }
        }));
        mViewBinding.swipeTarget.setAdapter(routeAdapter);
    }

    private void initCityViewNew() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        mViewBinding.swipeTarget.setHasFixedSize(true);
        mViewBinding.swipeTarget.setNestedScrollingEnabled(false);
        mViewBinding.swipeTarget.setLayoutManager(linearLayoutManager);

        BaseAdapter<Month> routeAdapter = new BaseAdapter<>(this,AppEngine.instance().confManager().loadMonth());
        routeAdapter.register(new CalendarMonthItemHolder(Month.class, R.layout.calendar_month_item));
        mViewBinding.swipeTarget.addItemDecoration(new StickySectionDecoration(this, R.color.textColor, new StickySectionDecoration.StickHeaderCallback() {
            @Override
            public boolean isFirstInGroup(int position) {
                Month item = routeAdapter.getItemData(position);
                return true;
            }

            @Override
            public boolean isLastInGroup(int position) {
                Month item = routeAdapter.getItemData(position);
                return true;
            }

            @Override
            public String getTitle(int position) {
                Month item = routeAdapter.getItemData(position);
                return item.title;
            }
        }));
        mViewBinding.swipeTarget.setAdapter(routeAdapter);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
