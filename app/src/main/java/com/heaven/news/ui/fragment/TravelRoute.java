package com.heaven.news.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.heaven.base.ui.adapter.BaseMultAdapter;
import com.heaven.base.ui.fragment.BaseBindFragment;
import com.heaven.base.ui.view.rlview.OnLoadMoreListener;
import com.heaven.base.ui.view.rlview.OnRefreshListener;
import com.heaven.news.R;
import com.heaven.news.databinding.RouteBinding;
import com.heaven.news.manyData.adapter.ItemVIew01;
import com.heaven.news.manyData.adapter.ItemVIew02;
import com.heaven.news.manyData.adapter.ItemVIew03;
import com.heaven.news.manyData.adapter.ItemVIewNormal;
import com.heaven.news.manyData.bean.Bean01;
import com.heaven.news.manyData.bean.Bean02;
import com.heaven.news.manyData.bean.Bean03;
import com.heaven.news.ui.vm.vmmodel.MainViewModel;

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
public class TravelRoute extends BaseBindFragment<MainViewModel,RouteBinding> implements OnRefreshListener, OnLoadMoreListener {
    List<Object> items;
    @Override
    public void bindModel() {

    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
       RecyclerView recyclerView = mViewBinding.swipeToLoadLayout.findViewById(R.id.swipe_target);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        BaseMultAdapter adapter = new BaseMultAdapter(getContext());

        adapter.register(new ItemVIewNormal(String.class,R.layout.item_go));
        adapter.register( new ItemVIew01(Bean01.class,R.layout.item_one));
        adapter.register( new ItemVIew02(Bean02.class,R.layout.item_two));
        adapter.register( new ItemVIew03(Bean03.class,R.layout.item_three));

        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setFocusableInTouchMode(false);
        items = new ArrayList<>();
        for(int j=0;j<10;j++) {
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
    public int initLayoutResId() {
        return R.layout.route;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser) {

        }
    }

    public static TravelRoute newInstance(Bundle paramBundle) {
        TravelRoute fragment = new TravelRoute();
        fragment.setArguments(paramBundle);
        return fragment;
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
