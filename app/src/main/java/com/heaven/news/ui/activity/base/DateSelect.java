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
import com.heaven.base.ui.view.calendar.Calendar;
import com.heaven.base.ui.view.rlview.OnLoadMoreListener;
import com.heaven.base.ui.view.rlview.OnRefreshListener;
import com.heaven.news.R;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.databinding.DateSelectBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.decoration.StickyGridDecoration;
import com.heaven.news.ui.decoration.StickySectionDecoration;
import com.heaven.news.ui.vm.holder.CalendarItemHolder;
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
public class DateSelect extends BaseBindActivity<SelectDateViewModel, DateSelectBinding>  implements OnRefreshListener, OnLoadMoreListener {
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
        mViewBinding.swipeToLoadLayout.setOnLoadMoreListener(this);
        mViewBinding.swipeToLoadLayout.setOnRefreshListener(this);
        ((RecyclerView) mViewBinding.swipeToLoadLayout.findViewById(R.id.swipe_target)).addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        RecyclerView recyclerView = mViewBinding.swipeToLoadLayout.findViewById(R.id.swipe_target);
        recyclerView.setLayoutManager(gridLayoutManager);
        BaseAdapter<Calendar> routeAdapter = new BaseAdapter<>(this);
        routeAdapter.register(new CalendarItemHolder(Calendar.class, R.layout.calendar_item));
        recyclerView.setAdapter(routeAdapter);
        recyclerView.addItemDecoration(new StickyGridDecoration(this, R.color.textColor, new StickyGridDecoration.StickHeaderCallback() {
            @Override
            public boolean isFirstInGroup(int position) {
                return routeAdapter.getItemData(position).isFirstInGroup;
            }

            @Override
            public boolean isLastInGroup(int position) {
                return routeAdapter.getItemData(position).isLastInGroup;
            }

            @Override
            public String getTitle(int position) {
                return routeAdapter.getItemData(position).groupTitle;
            }
        }));
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return routeAdapter.getItemData(position).isTitle? 7 : 1;
            }
        });
        List<Calendar> calendars = AppEngine.instance().confManager().loadCalendar();
        routeAdapter.updateItems(calendars);

        mViewBinding.swipeToLoadLayout.setRefreshing(false);
        mViewBinding.swipeToLoadLayout.setLoadingMore(false);
        mViewBinding.swipeToLoadLayout.setRefreshEnabled(false);
        mViewBinding.swipeToLoadLayout.setLoadMoreEnabled(false);
//        multAdapterTest();
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
