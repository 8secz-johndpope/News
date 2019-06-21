package com.heaven.news.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.fragment.BaseBindFragment;
import com.heaven.base.ui.view.rlview.OnLoadMoreListener;
import com.heaven.base.ui.view.rlview.OnRefreshListener;
import com.heaven.news.R;
import com.heaven.news.databinding.RouteBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.manager.UserManager;
import com.heaven.news.ui.decoration.RouteTimeDecorationn;
import com.heaven.news.ui.model.holder.RouteItemHolder;
import com.heaven.news.ui.model.vm.MainVm;
import com.neusoft.szair.model.fullchannel.fullchannelVO;

/**
 * FileName: com.heaven.news.ui.fragment.Home.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-17 14:16
 *
 * @version V1.0 行程
 */
public class Route extends BaseBindFragment<MainVm, RouteBinding> implements OnRefreshListener, OnLoadMoreListener, Observer<UserManager.CoreDataWrapper> {
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
                } else if(mViewBinding.swipeToLoadLayout.isLoadingMore()) {
                    routeAdapter.updateBatch(fullchannelVOS, false);
                    if(routeAdapter.getItemCount() > 0) {
//                        recyclerView.smoothScrollToPosition(routeAdapter.getItemCount()-1);
                        handler.postDelayed(() -> recyclerView.smoothScrollToPosition(routeAdapter.getItemCount()-1),200);
                    }

                }
            }
            mViewBinding.swipeToLoadLayout.setRefreshing(false);
            mViewBinding.swipeToLoadLayout.setLoadingMore(false);
        });

//        multAdapterTest();
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
                if(routeAdapter != null) {
                    routeAdapter.clearData();
                }
            }
        } else {
            if (mViewBinding != null) {
                mViewBinding.swipeToLoadLayout.setRefreshing(false);
                mViewBinding.swipeToLoadLayout.setLoadingMore(false);
                if(routeAdapter != null && !AppEngine.instance().dataCore().isLogin()) {
                    routeAdapter.clearData();
                }
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
    public void onChanged(@Nullable UserManager.CoreDataWrapper coreDataWrapper) {
        if (coreDataWrapper != null) {
            if (UserManager.LOGIN == coreDataWrapper.dataType) {

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppEngine.instance().dataCore().removeForeverObserve(this);
    }
}
