package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.ui.vm.model.ServiceInfo;
import com.heaven.news.ui.vm.model.ServiceItem;

/**
 * FileName: com.heaven.news.ui.vm.bean.HomeServiceHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-29 15:04
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class HomeServiceHolder  extends BaseMultItem<ServiceInfo> {

    public HomeServiceHolder(@NonNull Class<ServiceInfo> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceInfo homeServiceItem) {
        if(homeServiceItem.homeServiceInfos != null && homeServiceItem.homeServiceInfos.size() > 0) {
            RecyclerView recyclerView = holder.getView(R.id.service_list);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.context,4);
            BaseAdapter<ServiceItem> adapter = new BaseAdapter<>(holder.context,homeServiceItem.homeServiceInfos);
            adapter.register(new ServiceItemHolder(ServiceItem.class,R.layout.service_item));
            recyclerView.addItemDecoration(new GrideDecoration(ScreenUtil.dip2px(holder.context,10),4));
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceInfo homeServiceItem, Object payload) {

    }
}
