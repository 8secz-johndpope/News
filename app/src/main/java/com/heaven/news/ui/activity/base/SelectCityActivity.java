package com.heaven.news.ui.activity.base;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.view.rlview.OnLoadMoreListener;
import com.heaven.base.ui.view.rlview.OnRefreshListener;
import com.heaven.news.R;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.databinding.SelectCityBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.base.BaseToolBarBindActivity;
import com.heaven.news.ui.decoration.SmoothScrollLayoutManager;
import com.heaven.news.ui.decoration.StickySectionDecoration;
import com.heaven.news.ui.decoration.TopSmoothScroller;
import com.heaven.news.ui.vm.holder.CityIndexItemHolder;
import com.heaven.news.ui.vm.holder.CityItemHolder;
import com.heaven.news.ui.vm.vmmodel.SelectCityViewModel;
import com.neusoft.szair.model.city.cityListVO;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * FileName: com.heaven.news.ui.activity.base.SelectCityActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-29 16:00
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
@Route(path = RouterUrl.ROUTER_URL_CITY)
public class SelectCityActivity extends BaseToolBarBindActivity<SelectCityViewModel, SelectCityBinding> implements OnRefreshListener, OnLoadMoreListener {
    Handler handler = new Handler();

    @Override
    public int initLayoutResId() {
        return R.layout.select_city;
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
        LinearLayoutManager manager = new LinearLayoutManager(this);
        RecyclerView recyclerView = mViewBinding.swipeToLoadLayout.findViewById(R.id.swipe_target);
        recyclerView.setLayoutManager(manager);
        BaseAdapter<cityListVO> routeAdapter = new BaseAdapter<>(this);
        routeAdapter.register(new CityItemHolder(cityListVO.class, R.layout.city_item));
        recyclerView.setAdapter(routeAdapter);
        recyclerView.addItemDecoration(new StickySectionDecoration(this, R.color.textColor, routeAdapter::getItemData));
        Pair<List<cityListVO>, List<String>> citysIndex = AppEngine.instance().confManager().getAllCitys();
        routeAdapter.updateItems(citysIndex.first);
        initCityGroupIndex(citysIndex.second,manager);

        mViewBinding.swipeToLoadLayout.setRefreshing(false);
        mViewBinding.swipeToLoadLayout.setLoadingMore(false);
        mViewBinding.swipeToLoadLayout.setRefreshEnabled(false);
        mViewBinding.swipeToLoadLayout.setLoadMoreEnabled(false);
//        multAdapterTest();
    }

    private void initCityGroupIndex(List<String> indexCityName,LinearLayoutManager nameIndexManager) {
        if (indexCityName != null && indexCityName.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mViewBinding.cityGroupIndexList.setLayoutManager(manager);
            BaseAdapter<String> routeAdapter = new BaseAdapter<>(this);
            mViewBinding.cityGroupIndexList.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    Logger.i("onScrollStateChanged--" + newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    Logger.i("onScrolled--" + dx + "dy:" + dy);
                }
            });
            mViewBinding.cityGroupIndexList.setAdapter(routeAdapter);
            routeAdapter.register(new CityIndexItemHolder(String.class, R.layout.city_group_index_item));
            routeAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<String>() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, String o, int position) {
                    int index = AppEngine.instance().confManager().getCityGroupIndex(o);
                    nameIndexManager.scrollToPositionWithOffset(index, 0);
                    Logger.i(o);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, String o, int position) {
                    return false;
                }
            });
            routeAdapter.updateItems(indexCityName);

        }
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
