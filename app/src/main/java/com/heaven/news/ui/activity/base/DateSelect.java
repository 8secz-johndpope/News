package com.heaven.news.ui.activity.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.heaven.base.ui.activity.BaseBindActivity;
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
import com.heaven.news.ui.vm.holder.CalendarItemHolder;
import com.heaven.news.ui.vm.holder.CalendarTitleItemHolder;
import com.heaven.news.ui.vm.holder.CityItemHolder;
import com.heaven.news.ui.vm.vmmodel.SelectDateViewModel;
import com.neusoft.szair.model.city.cityListVO;

import java.util.List;

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

        mViewBinding.swipeTarget.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
//                        mViewBinding.swipeToLoadLayout.setLoadingMore(true);
//                    }
                }
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        mViewBinding.swipeTarget.setLayoutManager(gridLayoutManager);
        BaseMultAdapter routeAdapter = new BaseMultAdapter(this,AppEngine.instance().confManager().loadCalendar());
        routeAdapter.setAnimationEnable(false);
        routeAdapter.register(new CalendarTitleItemHolder(Month.class, R.layout.calendar_title_item));
        routeAdapter.register(new CalendarItemHolder(Calendar.class, R.layout.calendar_item));
        mViewBinding.swipeTarget.setAdapter(routeAdapter);

//        List calendars = AppEngine.instance().confManager().loadCalendar();
//        routeAdapter.updateItems(calendars);

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
