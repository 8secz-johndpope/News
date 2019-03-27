package com.heaven.base.ui.view.widget.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;

import java.util.List;

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

    @Override
    public int getItemViewType(int position) {
        int realPosition = getRealPosition(position);
        Object item = dataItems.get(realPosition);
        return multTypeManager.getItemType(item, realPosition);
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder, int postion) {
        if (postion < getItemCount()) {
            int realPosition = getRealPosition(postion);
            multTypeManager.getMultItemByType(multTypeManager.getItemType(dataItems.get(realPosition),realPosition)).onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            //GridLayoutManager时设置每行的span
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int realPosition = getRealPosition(position);
                    BaseMultItem multItem = multTypeManager.getMultItemByType(getItemViewType(realPosition));
                    return multItem.getSpanSize(gridManager.getSpanCount());
                }
            });
            isStaggeredGridLayout = false;
        } else if (manager instanceof StaggeredGridLayoutManager) {
            isStaggeredGridLayout = true;
        } else if (manager instanceof LinearLayoutManager) {
            isStaggeredGridLayout = false;
        }
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
        int realPosition = getRealPosition(position);
        final Object item = dataItems.get(realPosition);
        BaseMultItem binder = multTypeManager.getMultItemByType(holder.getItemViewType());
        if (binder != null) {
            binder.onBindViewHolder(holder, item, realPosition);

            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(v, holder, item, realPosition));
                holder.itemView.setOnLongClickListener(v -> onItemClickListener.onItemLongClick(v, holder, item, realPosition));
            }
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        int realPosition = getRealPosition(position);
        final Object item = dataItems.get(realPosition);
        BaseMultItem binder = multTypeManager.getMultItemByType(holder.getItemViewType());
        holder.itemData = item;
        if (binder != null) {
            if(payloads.isEmpty()) {
                binder.onBindViewHolder(holder, item);
            } else {
                binder.onBindViewHolder(holder, item, payloads);
            }
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(v, holder, item, realPosition));
                holder.itemView.setOnLongClickListener(v -> onItemClickListener.onItemLongClick(v, holder, item, realPosition));
            }
        }
    }

    private int getRealPosition(int position) {
        return position%dataItems.size();
    }
}
