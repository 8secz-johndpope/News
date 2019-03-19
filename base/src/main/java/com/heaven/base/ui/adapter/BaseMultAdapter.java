package com.heaven.base.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.heaven.base.ui.adapter.anim.BaseAnimation;
import com.heaven.base.ui.adapter.anim.ScaleInAnimation;
import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.ui.adapter.viewholder.MultTypeManager;
import com.heaven.data.util.RxDataSchedulers;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

/**
 * FileName: com.heaven.base.ui.adapter.BaseMultAdapter.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-19 17:14
 *
 * @version V1.0 多类型适配器
 */
public class BaseMultAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<?> dataItems;
    private List<?> diffNewDataItems = new ArrayList<>();
    private MultTypeManager multTypeManager = new MultTypeManager();
    protected Context context;
    protected @Nullable
    LayoutInflater inflater;

    private DiffCallBack diffCallBack;
    private boolean openAnimationEnable = true;
    private boolean firstOnlyEnable = true;
    private int lastPosition = 15;
    private int duration = 300;
    private boolean isStaggeredGridLayout;
    private Interpolator interpolator = new LinearInterpolator();
    private BaseAnimation mCustomAnimation;
    private BaseAnimation mSelectAnimation = new ScaleInAnimation();

    private OnItemClickListener onItemClickListener;

    public BaseMultAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        diffCallBack = new DiffCallBack();
    }

    public <T> BaseMultAdapter register(@NonNull BaseMultItem<T> multiItem) {
        multTypeManager.register(multiItem.getLayoutBindModel(), multiItem);
        return this;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = dataItems.get(position);
        return multTypeManager.getItemType(item, position);
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
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        final Object item = dataItems.get(position);
        BaseMultItem binder = multTypeManager.getMultItemByType(holder.getItemViewType());
        if (binder != null) {
            binder.onBindViewHolder(holder, item, position);

            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(v, holder, item, position));
                holder.itemView.setOnLongClickListener(v -> onItemClickListener.onItemLongClick(v, holder, item, position));
            }
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        final Object item = dataItems.get(position);
        BaseMultItem binder = multTypeManager.getMultItemByType(holder.getItemViewType());
        holder.itemData = item;
        if (binder != null) {
            if(payloads.isEmpty()) {
                binder.onBindViewHolder(holder, item);
            } else {
                binder.onBindViewHolder(holder, item, payloads);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (dataItems != null) {
            return dataItems.size();
        } else {
            return 0;
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
                    BaseMultItem multItem = multTypeManager.getMultItemByType(getItemViewType(position));
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

    @Override
    public void onViewAttachedToWindow(@NonNull BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isStaggeredGridLayout) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            if (holder.getMultItem() != null) {
                if (holder.getMultItem().isFullSpan()) {
                    p.setFullSpan(true);
                } else {
                    p.setFullSpan(false);
                }
            }
        }
        onViewAttachedToWindow(holder,holder.getLayoutPosition());
        addAnimation(holder);
    }

    public void onViewAttachedToWindow(BaseViewHolder holder, int postion) {
        if (postion < getItemCount()) {
            multTypeManager.getMultItemByType(multTypeManager.getItemType(dataItems.get(postion),postion)).onViewAttachedToWindow(holder);
        }
    }

    public void diffUpdate(List<?> diffNewDataItems) {
        this.diffNewDataItems = diffNewDataItems;
        Disposable disposable = Flowable.just(DiffUtil.calculateDiff(diffCallBack, true))
                .compose(RxDataSchedulers.io_main())
                .subscribe(diffResult -> {
                    diffResult.dispatchUpdatesTo(this);
                });
        dataItems = diffNewDataItems;
    }

    public void updateItems(List<?> items) {
        this.dataItems = items;
        notifyDataSetChanged();
    }

    /**
     * @return 除去header footer的个数
     */
    public int getDataCount() {
        return dataItems.size();
    }

    /**
     * @return 获取当前数据源List，不包含head和foot
     */
    public List<?> getDataList() {
        return dataItems;
    }

    /**
     * 清空Item数据不含head 和 foot
     */
    public void clearData() {
        dataItems.clear();
    }

    public class DiffCallBack extends DiffUtil.Callback {

        @Override
        public int getOldListSize() {
            return dataItems != null ? dataItems.size() : 0;
        }

        @Override
        public int getNewListSize() {
            return diffNewDataItems != null ? diffNewDataItems.size() : 0;
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            boolean isSame = false;
            int itemType = getItemViewType(oldItemPosition);
            BaseMultItem multItem = multTypeManager.getMultItemByType(itemType);
            if (multItem != null) {
                isSame = multItem.isItemSame(dataItems.get(oldItemPosition), diffNewDataItems.get(newItemPosition));
            }
            return isSame;
        }


        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            boolean isSame = false;
            int itemType = getItemViewType(oldItemPosition);
            BaseMultItem multItem = multTypeManager.getMultItemByType(itemType);
            if (multItem != null) {
                isSame = multItem.isContentSame(dataItems.get(oldItemPosition), diffNewDataItems.get(newItemPosition));
            }
            return isSame;
        }

    }

    /**
     * add animation when you want to show time
     *
     * @param holder
     */
    private void addAnimation(RecyclerView.ViewHolder holder) {
        if (openAnimationEnable) {
            if (!firstOnlyEnable || holder.getLayoutPosition() > lastPosition) {
                BaseAnimation animation = null;
                if (mCustomAnimation != null) {
                    animation = mCustomAnimation;
                } else {
                    animation = mSelectAnimation;
                }
                for (Animator anim : animation.getAnimators(holder.itemView)) {
                    startAnim(anim, holder.getLayoutPosition());
                }
                lastPosition = holder.getLayoutPosition();
            }
        }
    }

    /**
     * set anim to start when loading
     *
     * @param anim
     * @param index
     */
    private void startAnim(Animator anim, int index) {
        anim.setDuration(duration).start();
        anim.setInterpolator(interpolator);
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, RecyclerView.ViewHolder holder, T t, int position);

        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, T t, int position);
    }
}
