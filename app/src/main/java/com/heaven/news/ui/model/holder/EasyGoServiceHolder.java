package com.heaven.news.ui.model.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.ui.model.bean.base.ServiceInfo;
import com.heaven.news.ui.model.bean.base.ServiceItem;

/**
 * FileName: com.heaven.news.ui.vm.bean.HomeServiceHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-29 15:04
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class EasyGoServiceHolder extends BaseMultItem<ServiceInfo> {

    public EasyGoServiceHolder(@NonNull Class<ServiceInfo> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceInfo easyGoServiceItem) {
        if(easyGoServiceItem.serviceItems != null && easyGoServiceItem.serviceItems.size() > 0) {
            holder.setText(R.id.title,ScreenUtil.getStringResId(holder.context,easyGoServiceItem.serviceName));
            RecyclerView recyclerView = holder.getView(R.id.service_list);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.context,4);
            BaseAdapter<ServiceItem> adapter = new BaseAdapter<>(holder.context,easyGoServiceItem.serviceItems);
            adapter.register(new ServiceItemHolder(ServiceItem.class,R.layout.service_item));
//            recyclerView.addItemDecoration(new GrideDecoration(ScreenUtil.dip2px(holder.context,5),4));
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ServiceInfo homeServiceItem, Object payload) {

    }
}
