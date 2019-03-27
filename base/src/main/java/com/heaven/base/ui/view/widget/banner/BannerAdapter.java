package com.heaven.base.ui.view.widget.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;

/**
 * FileName: com.heaven.base.ui.view.widget.banner.BannerAdapter.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-27 13:16
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
public class BannerAdapter<E> extends BaseAdapter<E> {
    private BannerAdapterHelper mBannerAdapterHelper = new BannerAdapterHelper();

    public BannerAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
       int count = super.getItemCount();
        return count == 0? 0 : Integer.MAX_VALUE;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        BaseMultItem multItem = multTypeManager.getMultItemByType(viewType);
        BaseViewHolder viewHolder;
        if (inflater != null) {
            if (multItem != null) {
                multTypeManager.setMaxRecycledViews(viewGroup, viewType);
                viewHolder = new BaseViewHolder(inflater.inflate(multItem.getItemLayoutId(), viewGroup, false));
                viewHolder.setContext(context);
                viewHolder.multItem = multItem;
            } else {
                viewHolder = new BaseViewHolder(new TextView(context));
                viewHolder.setContext(context);
                viewHolder.multItem = multItem;
            }
        } else {
            viewHolder = new BaseViewHolder(new TextView(context));
            viewHolder.setContext(context);
            viewHolder.multItem = multItem;
        }
        mBannerAdapterHelper.onCreateViewHolder(viewGroup, viewHolder.itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        final Object item = dataItems.get(position);
        BaseMultItem binder = multTypeManager.getMultItemByType(holder.getItemViewType());
        if (binder != null) {
            int realPosition = position/dataItems.size();
            binder.onBindViewHolder(holder, item, realPosition);

            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(v, holder, item, realPosition));
                holder.itemView.setOnLongClickListener(v -> onItemClickListener.onItemLongClick(v, holder, item, realPosition));
            }
        }
    }
}
