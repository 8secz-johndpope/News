package com.heaven.news.ui.activity.base;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.BaseMultAdapter;
import com.heaven.base.ui.view.rlview.OnLoadMoreListener;
import com.heaven.base.ui.view.rlview.OnRefreshListener;
import com.heaven.news.R;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.databinding.SelectCityBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.base.BaseToolBarBindActivity;
import com.heaven.news.ui.decoration.RouteTimeDecorationn;
import com.heaven.news.ui.decoration.StickySectionDecoration;
import com.heaven.news.ui.view.RecyclerViewDivider;
import com.heaven.news.ui.vm.holder.CityItemHolder;
import com.heaven.news.ui.vm.vmmodel.SelectCityViewModel;
import com.neusoft.szair.model.city.cityListVO;

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
public class SelectCityActivity extends BaseToolBarBindActivity<SelectCityViewModel, SelectCityBinding>  implements OnRefreshListener, OnLoadMoreListener {
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
        RecyclerView recyclerView = mViewBinding.swipeToLoadLayout.findViewById(R.id.swipe_target);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BaseAdapter<cityListVO> routeAdapter = new BaseAdapter<>(this);
        routeAdapter.register(new CityItemHolder(cityListVO.class, R.layout.city_item));
        recyclerView.setAdapter(routeAdapter);
        recyclerView.addItemDecoration(new StickySectionDecoration(this, new StickySectionDecoration.GroupInfoCallback() {
            @Override
            public cityListVO getGroupInfo(int position) {
                return routeAdapter.getItemData(position);
            }
        }));
        routeAdapter.updateItems(AppEngine.instance().confManager().getAllCitys());

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
