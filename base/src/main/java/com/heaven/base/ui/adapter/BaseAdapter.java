package com.heaven.base.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.ArrayMap;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.heaven.base.ui.adapter.adapteranimation.AlphaInAnimation;
import com.heaven.base.ui.adapter.adapteranimation.BaseAnimation;
import com.heaven.base.ui.adapter.adapteranimation.ScaleInAnimation;
import com.heaven.base.ui.adapter.viewholder.BaseHeaderRefresh;
import com.heaven.base.ui.adapter.viewholder.BaseLoadMoreFooter;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.ui.adapter.viewholder.ItemTypeManager;
import com.heaven.base.ui.adapter.viewholder.ViewHolderManager;
import com.heaven.base.ui.adapter.viewholder.ViewHolderManagerGroup;
import com.heaven.base.ui.view.recyclerview.BindRecyclerView;
import com.heaven.base.utils.RxSchedulers;
import com.heaven.data.util.RxDataSchedulers;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者:Heaven
 * 时间: on 2017/4/14 09:43
 * 邮箱:heavenisme@aliyun.com
 */

public class BaseAdapter<E> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<E> dataItems = new ArrayList<>();
    private List<E> diffNewDataItems = new ArrayList<>();
    private List<Object> headItems = new ArrayList<>();
    private List<Object> footItems = new ArrayList<>();
    private ItemTypeManager itemTypeManager;
    public boolean isHasMore = true;
    private int pageCount = 10;
    private int mLimitTotal = -1;
    private Context context;
    private boolean isStaggeredGridLayout;
    private int layoutType = 0;

    private static final int TYPE_FOOTER = 10001;
    private boolean loadingMoreEnabled = true;
    private BaseLoadMoreFooter baseLoadMoreFooter;
    private DiffCallBack diffCallBack;


    private BaseAnimation mCustomAnimation;
    private BaseAnimation mSelectAnimation = new ScaleInAnimation();
    private boolean mFirstOnlyEnable = true;
    private boolean mOpenAnimationEnable = true;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mDuration = 300;
    private int mLastPosition = -1;
    /**
     */
    public static final int ALPHAIN = 0x00000001;
    /**
     */
    public static final int SCALEIN = 0x00000002;
    /**
     */
    public static final int SLIDEIN_BOTTOM = 0x00000003;
    /**
     */
    public static final int SLIDEIN_LEFT = 0x00000004;
    /**
     */
    public static final int SLIDEIN_RIGHT = 0x00000005;

    public BaseAdapter(Context context) {
        itemTypeManager = new ItemTypeManager();
        this.context = context;
        diffCallBack = new DiffCallBack();
    }


    public <T> BaseAdapter bind(@NonNull ViewHolderManagerGroup<T> group) {
        itemTypeManager.register(group);
        return this;
    }

    public <T> BaseAdapter bind(@NonNull ViewHolderManager<T> manager) {
        itemTypeManager.register(manager);
        return this;
    }

    public void setBaseHeaderRefresh(BaseHeaderRefresh baseHeaderRefresh) {
//        this.baseHeaderRefresh = baseHeaderRefresh;
    }

    public void setBaseLoadMoreFooter(BaseLoadMoreFooter baseLoadMoreFooter) {
        this.baseLoadMoreFooter = baseLoadMoreFooter;
    }

    public void addTopFixed(Object header) {
        headItems.add(header);
        notifyItemInserted(headItems.size());
    }

    public void addFootFixed(Object foot) {
        int position = getItemCount();
        footItems.add(foot);
        notifyItemInserted(position);
    }

    public void addTopFixed(List headerList) {
        int start = getHeadCount();
        headItems.addAll(headerList);
        notifyItemRangeChanged(start, headerList.size());
    }

    public void addFootFixed(List footList) {
        int start = getItemCount();
        footItems.addAll(footList);
        notifyItemRangeChanged(start, footList.size());
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            //GridLayoutManager时设置每行的span
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                ViewHolderManager holderManager;

                @Override
                public int getSpanSize(int position) {
                    holderManager = itemTypeManager.getViewHolderManager(getItemViewType(position));
                    return holderManager.getSpanSize(gridManager.getSpanCount());
                }
            });
            isStaggeredGridLayout = false;
        } else if (manager instanceof StaggeredGridLayoutManager) {
            isStaggeredGridLayout = true;
        } else if (manager instanceof LinearLayoutManager) {
            isStaggeredGridLayout = false;
        }
    }

    public ViewHolderManager getGridSpan(int position) {
        return itemTypeManager.getViewHolderManager(getItemViewType(position));
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (layoutType == BindRecyclerView.STAGGEREDGRID_LAYOUT) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            if (holder.getViewHolderManager() != null) {
                if (holder.getViewHolderManager().isFullSpan()) {
                    p.setFullSpan(true);
                } else {
                    p.setFullSpan(false);
                }
            }
        }
        addAnimation(holder);
    }

    /**
     * add animation when you want to show time
     *
     * @param holder
     */
    private void addAnimation(RecyclerView.ViewHolder holder) {
        if (mOpenAnimationEnable) {
            if (!mFirstOnlyEnable || holder.getLayoutPosition() > mLastPosition) {
                BaseAnimation animation = null;
                if (mCustomAnimation != null) {
                    animation = mCustomAnimation;
                } else {
                    animation = mSelectAnimation;
                }
                for (Animator anim : animation.getAnimators(holder.itemView)) {
                    startAnim(anim, holder.getLayoutPosition());
                }
                mLastPosition = holder.getLayoutPosition();
            }
        }
    }

    /**
     * set anim to start when loading
     *
     * @param anim
     * @param index
     */
    protected void startAnim(Animator anim, int index) {
        anim.setDuration(mDuration).start();
        anim.setInterpolator(mInterpolator);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderManager provider = viewType == TYPE_FOOTER ? baseLoadMoreFooter : itemTypeManager.getViewHolderManager(viewType);
        BaseViewHolder viewHolder;
        if (provider != null) {
            viewHolder = provider.onCreateViewHolder(parent);
            viewHolder.viewHolderManager = provider;
            viewHolder.setContext(context);
        } else {
            viewHolder = new BaseViewHolder(parent);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Object item = getItem(position);
        ViewHolderManager manager = holder.viewHolderManager;
        holder.itemData = item;
        if (manager != null) {
            manager.onBindViewHolder(holder, item);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        Object item = getItem(position);
        ViewHolderManager manager = holder.viewHolderManager;
        holder.itemData = item;
        if (manager != null) {
            manager.onBindViewHolder(holder, item, payloads);
        }
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    public void setBeans(List<E> data, int begin) {
        Logger.i("current:" + Thread.currentThread());
        if (data == null) {
            data = new ArrayList<>();
        }
        this.isHasMore = (data.size() >= pageCount && (data.size() > 0 && begin > 0) && mLimitTotal != -1) && getDataCount() < mLimitTotal;
        int position = getDataCount();
        if(isHasMore) {
            this.setLoadingMoreEnabled(true);
        }
        if (begin > 1) {
            this.dataItems.addAll(data);
        } else {
            dataItems.clear();
            position = 0;
            this.dataItems.addAll(data);
        }
        notifyItemRangeChanged(position, data.size());
    }

    public void setLimitTotal(int limitTotal) {
        this.mLimitTotal = limitTotal;
    }

    public void clear() {
        this.dataItems.clear();
        this.isHasMore = false;
    }

    /**
     * 设置Item list
     */
    public void setDataItems(@NonNull List<E> dataItems) {
        this.dataItems = dataItems;
        notifyDataSetChanged();
    }

    /**
     * 添加Item
     */
    public void addDataItem(@NonNull E item) {
        addDataItem(dataItems.size(), item);
    }

    /**
     * 在指定位置添加Item
     */
    public void addDataItem(int position, @NonNull E item) {
        addDataItems(position, Collections.singletonList(item));
    }

    /**
     * 添加ItemList
     */
    public void addDataItems(@NonNull List<E> items) {
        addDataItems(dataItems.size(), items);
    }

    /**
     * 在指定位置添加ItemList
     */
    public void addDataItems(int position, @NonNull List<E> items) {
        dataItems.addAll(position, items);
        notifyItemRangeInserted(position + getHeadCount(), items.size());
    }

    /**
     * diff工具插入在指定位置添加ItemList
     */
    private void diffInsertDataItems(int position, int count) {
        if (diffNewDataItems != null && diffNewDataItems.size() > 0) {
            for (int i = 0; i < count && i < diffNewDataItems.size(); i++) {
                dataItems.add(position, diffNewDataItems.get(i));
            }
        }
        notifyItemRangeInserted(position + getHeadCount(), count);
    }

    /**
     * 移动Item的位置 包括数据源和界面的移动
     *
     * @param fromPosition
     *         Item之前所在位置
     * @param toPosition
     *         Item新的位置
     */
    public void moveDataItem(int fromPosition, int toPosition) {
        //考虑到跨position(如0->2)移动的时候处理不能Collections.swap
        // if(from<to) to = to + 1 - 1;//+1是因为add的时候应该是to位置后1位，-1是因为先remove了from所以位置往前挪了1位
        dataItems.add(toPosition, dataItems.remove(fromPosition));
        notifyItemMoved(fromPosition + getHeadCount(), toPosition + getHeadCount());
    }


    /**
     * 移除Item 包括数据源和界面的移除
     *
     * @param position
     *         需要被移除Item的position
     */
    public void removeDataItem(int position) {
        removeDataItem(position, 1);
    }

    /**
     * 改变Item 包括数据源和界面的移除
     *
     * @param position
     *         需要被移除第一个Item的position
     * @param position
     *         需要被移除Item的个数
     */
    public void removeDataItem(int position, int itemCount) {
        for (int i = 0; i < itemCount; i++) {
            dataItems.remove(position);
        }
        notifyItemRangeRemoved(position + getHeadCount(), itemCount);
    }

    @Override
    public int getItemViewType(int position) {
        int type = -1;
        if (loadingMoreEnabled && position == getItemCount() - 1) {
            type = TYPE_FOOTER;
        } else {
            Object target = getItem(position);
            if (target != null) {
                type = itemTypeManager.getItemType(target);
            }
        }
        return type;
    }


    private Object getItem(int position) {
        if (loadingMoreEnabled && position == getItemCount() - 1) {
            return isHasMore;
        } else {
            if (position < headItems.size()) {
                return headItems.get(position);
            }

            position -= headItems.size();
            if (position < dataItems.size()) {
                return dataItems.get(position);
            }

            position -= dataItems.size();
            if (position < footItems.size()) {
                return footItems.get(position);
            }
        }
        return null;
    }

    public void diffUpdate(List<E> diffNewDataItems, boolean append, int begin) {
        this.diffNewDataItems = diffNewDataItems;
        if (append) {
            setBeans(diffNewDataItems, begin);
        } else {
            Flowable.just(DiffUtil.calculateDiff(diffCallBack, true))
                    .compose(RxDataSchedulers.io_main())
                    .subscribe(diffResult -> {
                        diffResult.dispatchUpdatesTo(this);
//                        diffResult.dispatchUpdatesTo(this);
                    });
            dataItems = diffNewDataItems;
            this.isHasMore = true;
        }

    }

    @Override
    public int getItemCount() {
        return getDataCount() + getHeadCount() + getFootCount() + (loadingMoreEnabled ? 1 : 0);
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return head view个数
     */
    public int getHeadCount() {
        return headItems.size();
    }

    /**
     * @return foot view个数
     */
    public int getFootCount() {
        return footItems.size();
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
    public List<E> getDataList() {
        return dataItems;
    }

    /**
     * 清空Item数据不含head 和 foot
     */
    public void clearData() {
        dataItems.clear();
    }

    public boolean isLoadingMoreEnabled() {
        return loadingMoreEnabled;
    }

    public void setLoadingMoreEnabled(boolean loadingMoreEnabled) {
        this.loadingMoreEnabled = loadingMoreEnabled;
    }

    /**
     * 清空数据
     */
    public void clearAllData() {
        dataItems.clear();
        headItems.clear();
        footItems.clear();
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
            if (itemType == TYPE_FOOTER) {
                isSame = true;
            } else {
                ViewHolderManager provider = itemTypeManager.getViewHolderManager(itemType);
                if (provider != null) {
                    isSame = provider.isItemSame(dataItems.get(oldItemPosition), diffNewDataItems.get(newItemPosition));
                }
            }
            return isSame;
        }


        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            boolean isSame = false;
            int itemType = getItemViewType(oldItemPosition);
            if (itemType == TYPE_FOOTER) {
                isSame = true;
            } else {
                ViewHolderManager provider = itemTypeManager.getViewHolderManager(itemType);
                if (provider != null) {
                    isSame = provider.isContentSame(dataItems.get(oldItemPosition), diffNewDataItems.get(newItemPosition));
                }
            }
            return isSame;
        }

    }
}
