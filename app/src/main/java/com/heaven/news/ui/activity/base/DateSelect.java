package com.heaven.news.ui.activity.base;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.ui.view.calendar.Calendar;
import com.heaven.base.ui.view.calendar.Month;
import com.heaven.base.ui.view.rlview.OnLoadMoreListener;
import com.heaven.base.ui.view.rlview.OnRefreshListener;
import com.heaven.news.R;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.databinding.DateSelectBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.base.BaseToolBarBindActivity;
import com.heaven.news.ui.decoration.StickySectionDecoration;
import com.heaven.news.ui.vm.holder.CalendarMonthItemHolder;
import com.heaven.news.ui.vm.vmmodel.SelectDateViewModel;
import com.orhanobut.logger.Logger;

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
        initCityView();
    }

    @Override
    public void bindModel() {

    }


    private void initCityView() {

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
                return true;
            }

            @Override
            public boolean isLastInGroup(int position) {
                return true;
            }

            @Override
            public String getTitle(int position) {
                Month item = routeAdapter.getItemData(position);
                return item.title;
            }
        }));

        routeAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<Calendar>() {
            @Override
            public void onItemClick(View view, BaseViewHolder holder, Calendar t) {
                Logger.i("onItemClick--" + t);
            }

            @Override
            public boolean onItemLongClick(View view, BaseViewHolder holder, Calendar t) {
                Logger.i("onItemClick--" + t);
                return false;
            }
        });
        mViewBinding.swipeTarget.setAdapter(routeAdapter);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
