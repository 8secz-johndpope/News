package com.heaven.news.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.BaseMultAdapter;
import com.heaven.base.ui.fragment.BaseBindFragment;
import com.heaven.base.ui.view.rlview.OnLoadMoreListener;
import com.heaven.base.ui.view.rlview.OnRefreshListener;
import com.heaven.news.R;
import com.heaven.news.databinding.RouteBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.DataCore;
import com.heaven.news.manyData.adapter.ItemVIew01;
import com.heaven.news.manyData.adapter.ItemVIew02;
import com.heaven.news.manyData.adapter.ItemVIew03;
import com.heaven.news.manyData.adapter.ItemVIewNormal;
import com.heaven.news.manyData.bean.Bean01;
import com.heaven.news.manyData.bean.Bean02;
import com.heaven.news.manyData.bean.Bean03;
import com.heaven.news.ui.decoration.RouteTimeDecorationn;
import com.heaven.news.ui.vm.holder.RouteItemHolder;
import com.heaven.news.ui.vm.vmmodel.MainViewModel;
import com.neusoft.szair.model.fullchannel.fullchannelVO;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.heaven.news.ui.fragment.Home.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-17 14:16
 *
 * @version V1.0 行程
 */
public class Route extends BaseBindFragment<MainViewModel, RouteBinding> implements OnRefreshListener, OnLoadMoreListener, Observer<DataCore.CoreDataWrapper> {
    BaseAdapter<fullchannelVO> routeAdapter;

    Handler handler = new Handler();

    @Override
    public int initLayoutResId() {
        return R.layout.route;
    }

    @Override
    public void bindModel() {

    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        AppEngine.instance().dataCore().registerDataTypeObaserver(this, this);
        initRoute();
    }

    private void initRoute() {
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
        RecyclerView recyclerView = mViewBinding.swipeToLoadLayout.findViewById(R.id.swipe_target);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new RouteTimeDecorationn(getContext()));
        routeAdapter = new BaseAdapter<>(getContext());
        routeAdapter.register(new RouteItemHolder(fullchannelVO.class, R.layout.route_item));
        recyclerView.setAdapter(routeAdapter);
        mViewModel.observeRouteList(this, fullchannelVOS -> {
            if (fullchannelVOS != null && fullchannelVOS.size() > 0) {
                if (mViewBinding.swipeToLoadLayout.isRefreshing()) {
                    if (routeAdapter.getItemCount() > 0) {
                        routeAdapter.diffUpdate(fullchannelVOS, false);
                    } else {
                        routeAdapter.updateBatch(fullchannelVOS, true);
                    }
                } else {
                    routeAdapter.updateBatch(fullchannelVOS, false);
//                    if(routeAdapter.getItemCount() > 0) {
////                        recyclerView.smoothScrollToPosition(routeAdapter.getItemCount()-1);
//                        handler.postDelayed(() -> recyclerView.smoothScrollToPosition(routeAdapter.getItemCount()-1),200);
//                    }

                }
            }
            mViewBinding.swipeToLoadLayout.setRefreshing(false);
            mViewBinding.swipeToLoadLayout.setLoadingMore(false);
        });

//        multAdapterTest();
    }


    private void multAdapterTest() {
        List<Object> items;
        RecyclerView recyclerView = mViewBinding.swipeToLoadLayout.findViewById(R.id.swipe_target);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        BaseMultAdapter adapter = new BaseMultAdapter(getContext());

        adapter.register(new ItemVIewNormal(String.class, R.layout.item_go));
        adapter.register(new ItemVIew01(Bean01.class, R.layout.item_one));
        adapter.register(new ItemVIew02(Bean02.class, R.layout.item_two));
        adapter.register(new ItemVIew03(Bean03.class, R.layout.item_three));

        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setFocusableInTouchMode(false);
        items = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            items.add(" 多数据 -> 多类型  ");
            for (int i = 0; i < 8; i++) {
                items.add(new Bean01("bean01_" + i));
            }
            for (int i = 0; i < 4; i++) {
                items.add(new Bean02("bean02_" + i));
            }
            for (int i = 0; i < 2; i++) {
                items.add(new Bean03("bean03_" + i));
            }
        }

        adapter.updateItems(items);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            if (AppEngine.instance().dataCore().isLogin()) {
                if (mViewBinding != null) {
                    mViewBinding.swipeToLoadLayout.setRefreshEnabled(true);
                    mViewBinding.swipeToLoadLayout.setLoadMoreEnabled(true);
                    mViewBinding.swipeToLoadLayout.setRefreshing(true);
                }
            } else {
                mViewBinding.swipeToLoadLayout.setRefreshEnabled(false);
                mViewBinding.swipeToLoadLayout.setLoadMoreEnabled(false);
            }
        } else {
            if (mViewBinding != null) {
                mViewBinding.swipeToLoadLayout.setRefreshing(false);
                mViewBinding.swipeToLoadLayout.setLoadingMore(false);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AppEngine.instance().dataCore().isLogin()) {
            mViewBinding.swipeToLoadLayout.setRefreshing(true);
            mViewModel.searchUserRoute(1);
        }
    }

    public static Route newInstance(Bundle paramBundle) {
        Route fragment = new Route();
        fragment.setArguments(paramBundle);
        return fragment;
    }

    @Override
    public void onLoadMore() {
        int index = routeAdapter.getItemCount() == 0 ? 1 : routeAdapter.getItemCount() + 1;
        mViewModel.searchUserRoute(1);
    }

    @Override
    public void onRefresh() {
        mViewModel.searchUserRoute(1);
    }

    @Override
    public void onChanged(@Nullable DataCore.CoreDataWrapper coreDataWrapper) {
        if (coreDataWrapper != null) {
            if (DataCore.LOGIN == coreDataWrapper.dataType) {

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppEngine.instance().dataCore().removeForeverObserve(this);
    }
}
