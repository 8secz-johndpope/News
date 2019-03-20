package com.heaven.news.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.heaven.base.ui.adapter.BaseMultAdapter;
import com.heaven.base.ui.fragment.BaseFragment;
import com.heaven.news.R;
import com.heaven.news.databinding.HomeBinding;
import com.heaven.news.manyData.adapter.Bean04;
import com.heaven.news.manyData.adapter.ItemVIew01;
import com.heaven.news.manyData.adapter.ItemVIew02;
import com.heaven.news.manyData.adapter.ItemVIew03;
import com.heaven.news.manyData.adapter.ItemVIewNormal;
import com.heaven.news.manyData.bean.Bean01;
import com.heaven.news.manyData.bean.Bean02;
import com.heaven.news.manyData.bean.Bean03;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.heaven.news.ui.fragment.Home.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-17 14:16
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class Home extends BaseFragment<HomeBinding> {
    List<Object> items;
    @Override
    protected void initView() {
        BaseMultAdapter  adapter = new BaseMultAdapter(getContext());

        adapter.register(new ItemVIewNormal(String.class,R.layout.item_go));
        adapter.register( new ItemVIew01(Bean01.class,R.layout.item_one));
        adapter.register( new ItemVIew02(Bean02.class,R.layout.item_two));
        adapter.register( new ItemVIew03(Bean03.class,R.layout.item_three));

        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
//        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                Object item = items.get(position);
//                if (item instanceof Bean01) {
//                    return 1;
//                }
//                if (item instanceof Bean02) {
//                    return 2;
//                }
//                if (item instanceof Bean03) {
//                    return 4;
//                }
//                if(item instanceof Bean04){
//                    return 4;
//                }
//                if(item instanceof String){
//                    return 4;
//                }
//                return 4;
//            }
//        };
//        layoutManager.setSpanSizeLookup(spanSizeLookup);
        mViewBinding.recyclerview.setLayoutManager(layoutManager);
        mViewBinding.recyclerview.setAdapter(adapter);
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected int initLayoutResId() {
        return R.layout.home;
    }

    public static  BaseFragment newInstance(Bundle paramBundle) {
        Home fragment = new Home();
        fragment.setArguments(paramBundle);
        return fragment;
    }
}
