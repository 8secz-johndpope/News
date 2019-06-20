package com.heaven.news.ui.activity.setting;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.news.R;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.databinding.SettingBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.base.BaseToolBarBindActivity;
import com.heaven.news.ui.decoration.VerticalDecoration;
import com.heaven.news.ui.model.bean.base.SettingItem;
import com.heaven.news.ui.model.bean.base.SettingService;
import com.heaven.news.ui.model.holder.SettingItemHolder;
import com.heaven.news.ui.model.vm.SettingVm;

import java.util.List;

/**
 * FileName: com.heaven.news.ui.activity.setting.SettingActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-20 13:44
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
@Route(path = RouterUrl.ROUTER_URL_SETTING)
public class SettingActivity extends BaseToolBarBindActivity<SettingVm, SettingBinding> {
    @Override
    public int initLayoutResId() {
        return R.layout.setting;
    }

    @Override
    public void bindModel() {

    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        initSettingList();
    }

    private void initSettingList() {
        SettingService settings = AppEngine.instance().confManager().loadSettingData(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mViewBinding.settingList.setLayoutManager(layoutManager);
        mViewBinding.settingList.addItemDecoration(new VerticalDecoration(this,1));
        BaseAdapter<SettingItem> routeAdapter = new BaseAdapter<>(this,settings.settingItems);
        mViewBinding.settingList.setAdapter(routeAdapter);
        routeAdapter.register(new SettingItemHolder(SettingItem.class, R.layout.setting_item));
        routeAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<SettingItem>() {
            @Override
            public void onItemClick(View view, BaseViewHolder holder, SettingItem t) {

            }

            @Override
            public boolean onItemLongClick(View view, BaseViewHolder holder, SettingItem t) {
                return false;
            }
        });
    }

}
