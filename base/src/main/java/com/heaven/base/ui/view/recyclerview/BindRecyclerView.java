package com.heaven.base.ui.view.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.heaven.base.BR;
import com.heaven.base.R;
import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.adapter.viewholder.BaseLoadMoreFooter;
import com.heaven.base.ui.adapter.viewholder.ViewHolderManager;
import com.heaven.base.ui.adapter.viewholder.ViewHolderManagerGroup;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:Heaven
 * 时间: on 2017/4/13 14:23
 * 邮箱:heavenisme@aliyun.com
 */

public class BindRecyclerView<M> extends FrameLayout implements AdapterPresenter.IAdapterView<M> {
    public static int LINEAR_LAYOUT = 0;
    public static int GRIDE_LAYOUT = 1;
    public static int STAGGEREDGRID_LAYOUT = 2;
    public static int LAYOUT_TYPE = LINEAR_LAYOUT;

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerview;
    private LinearLayout ll_emptyView;
    private LinearLayoutManager mLayoutManager;
    private BaseAdapter<M> mCommAdapter;
    private AdapterPresenter<M> mCoreAdapterPresenter;
    private boolean isHasHeadView = false, isHasFootView = false, isEmpty = false, isReverse = false;
    private int headType, footType;


    boolean isRefreshable = false;
    boolean isLoadMoreable = false;
    private boolean isLoadingData = false;
    private boolean isNoMore = false;
    //    private BaseHeaderRefresh baseHeaderRefresh;
    private BaseLoadMoreFooter baseLoadMoreFooter;
    private float mLastY = -1;
    private LoadingListener mLoadingListener;
    private static final float DRAG_RATE = 3;


    public interface LoadingListener {

        void onRefresh();

        void onLoadMore();
    }

    public BindRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public BindRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BindRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public BindRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.HRecyclerView);
        headType = ta.getResourceId(R.styleable.HRecyclerView_headType, 0);
        int itemType = ta.getResourceId(R.styleable.HRecyclerView_itemType, 0);
        footType = ta.getResourceId(R.styleable.HRecyclerView_footType, 0);
        isReverse = ta.getBoolean(R.styleable.HRecyclerView_isReverse, false);
        isRefreshable = ta.getBoolean(R.styleable.HRecyclerView_isRefreshable, true);
        isLoadMoreable = ta.getBoolean(R.styleable.HRecyclerView_isLoadMoreable, true);
        int pageCount = ta.getInteger(R.styleable.HRecyclerView_pageCount, 10);
        int layoutType = ta.getInteger(R.styleable.HRecyclerView_layoutType, 0);
        int columnCount = ta.getInteger(R.styleable.HRecyclerView_layoutGridColumn, 0);
        ta.recycle();

        this.setClickable(true);

//        baseHeaderRefresh = new BaseHeaderRefresh(headType == 0? R.layout.list_head_view : headType);
        baseLoadMoreFooter = new BaseLoadMoreFooter(footType == 0 ? R.layout.list_footer_view : footType, BR.item_footer);

        mCommAdapter = new BaseAdapter<>(this.getContext());
        mCommAdapter.setPageCount(pageCount);
        mCommAdapter.setLoadingMoreEnabled(isLoadMoreable);
        mCommAdapter.setBaseLoadMoreFooter(baseLoadMoreFooter);

        View layout = inflate(context, R.layout.layout_list_recyclerview, this);
        swipeRefresh = layout.findViewById(R.id.swiperefresh);
        recyclerview = layout.findViewById(R.id.recyclerview);
        ll_emptyView = layout.findViewById(R.id.ll_emptyview);
        mCoreAdapterPresenter = new AdapterPresenter<>(this);
        mCoreAdapterPresenter.setPageCount(pageCount);

        swipeRefresh.setEnabled(isRefreshable);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright);
        swipeRefresh.setOnRefreshListener(this::reFetch);
        recyclerview.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(context);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        recyclerview.setAdapter(mCommAdapter);
        recyclerview.addOnScrollListener(onScrollListener);
        ll_emptyView.setOnClickListener((view -> {
            isEmpty = false;
            ll_emptyView.setVisibility(View.GONE);
            swipeRefresh.setVisibility(View.VISIBLE);
            reFetch();
        }));

        if (itemType != 0) setViewType(itemType);
        if (isReverse) {
            mLayoutManager.setStackFromEnd(true);//列表再底部开始展示，反转后由上面开始展示
            mLayoutManager.setReverseLayout(true);//列表翻转
            recyclerview.setLayoutManager(mLayoutManager);
        }
        if (isLoadMoreable) {
            mCommAdapter.notifyItemChanged(0);
        }
    }

    int firstVisibleItemPosition;
    int lastVisibleItemPosition;
    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && isLoadMoreable) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof GridLayoutManager) {
                    firstVisibleItemPosition = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                    ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(into);
                    ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
                    firstVisibleItemPosition = findFirst(into);
                    lastVisibleItemPosition = findMax(into);
                } else {
                    firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                }

                if (lastVisibleItemPosition + 1 == recyclerview.getAdapter()
                        .getItemCount() && mCommAdapter.isHasMore) {
                    isLoadingData = true;
                    footerVisiable(BaseLoadMoreFooter.STATE_LOADING);
                    mCoreAdapterPresenter.fetch();
                } else {
                    footerVisiable(BaseLoadMoreFooter.STATE_NOMORE);
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    private void footerVisiable(int footerState) {
        if (isLoadMoreable) {
            if (mCommAdapter.isHasMore) {
                baseLoadMoreFooter.setState(BaseLoadMoreFooter.STATE_LOADING);
            } else {
                baseLoadMoreFooter.setState(footerState);
            }
        } else {
            baseLoadMoreFooter.getBaseHolder().getViewItem().setVisibility(View.GONE);
        }
    }

    private int findFirst(int[] lastPositions) {
        int first = lastPositions[0];
        for (int value : lastPositions) {
            if (value < first) {
                first = value;
            }
        }
        return first;
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public void setPageCount(int pageCount) {
        if (mCommAdapter != null) {
            mCommAdapter.setPageCount(pageCount);
        }
    }

    public AdapterPresenter getPresenter() {
        return mCoreAdapterPresenter;
    }

    public void setPullRefreshEnabled(boolean pullRefreshEnabled) {
        swipeRefresh.setEnabled(pullRefreshEnabled);
    }

    public void setLoadingMoreEnabled(boolean loadingMoreEnabled) {
        mCommAdapter.setLoadingMoreEnabled(loadingMoreEnabled);
    }

    public <T> BindRecyclerView<M> bind(@NonNull ViewHolderManagerGroup<T> group) {
        mCommAdapter.bind(group);
        return this;
    }

    public <T> BindRecyclerView<M> bind(@NonNull ViewHolderManager<T> manager) {
        mCommAdapter.bind(manager);
        return this;
    }

    public BindRecyclerView<M> bindLayoutManager(@NonNull RecyclerView.LayoutManager manager) {
        recyclerview.setLayoutManager(manager);
        if (manager instanceof GridLayoutManager) {
            //GridLayoutManager时设置每行的span
            GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                ViewHolderManager holderManager;

                @Override
                public int getSpanSize(int position) {
                    holderManager = mCommAdapter.getGridSpan(position);
                    if (holderManager != null) {
                        return holderManager.getSpanSize(gridLayoutManager.getSpanCount());
                    } else {
                        Logger.i("getSpanSize:" + position + ":---null");
                        return gridLayoutManager.getSpanCount();
                    }
                }
            });

            mCommAdapter.setLayoutType(GRIDE_LAYOUT);
        } else if (manager instanceof StaggeredGridLayoutManager) {
            mCommAdapter.setLayoutType(STAGGEREDGRID_LAYOUT);
        } else if (manager instanceof LinearLayoutManager) {
            mCommAdapter.setLayoutType(LINEAR_LAYOUT);
        }
        return this;
    }

    public <T> BindRecyclerView<M> addTopFixedData(@NonNull ArrayList<T> data) {
        mCommAdapter.addTopFixed(data);
        return this;
    }

    public <T> BindRecyclerView<M> addTopFixedData(@NonNull T data) {
        mCommAdapter.addTopFixed(data);
        return this;
    }

    public <T> BindRecyclerView<M> addFootFixedData(@NonNull ArrayList<T> data) {
        mCommAdapter.addFootFixed(data);
        return this;
    }

    public <T> BindRecyclerView<M> addFootFixedData(@NonNull T data) {
        mCommAdapter.addFootFixed(data);
        return this;
    }

    public BindRecyclerView<M> setTypeSelector(TypeSelector<M> mTypeSelector) {
//        this.mCommAdapter.setTypeSelector(mTypeSelector);
        return this;
    }

    public BindRecyclerView<M> setFootData(Object data) {
        isHasFootView = footType != 0;
//        this.mCommAdapter.bind(footType, data);
        return this;
    }

    public BindRecyclerView<M> setHeadData(Object data) {
        isHasHeadView = headType != 0;
//        this.mCommAdapter.setHeadViewType(headType, data);
        return this;
    }

    public void setViewType(@LayoutRes int type) {
//        this.mCommAdapter.setViewType(type);
    }

    public BindRecyclerView<M> setData(List<M> data) {
        reSetEmpty();
        mCommAdapter.setBeans(data, 1);
        return this;
    }

    boolean isRefresh = false;

    public void reFetch() {
        mCoreAdapterPresenter.setBegin(0);
        isRefresh = true;
        mCommAdapter.setLoadingMoreEnabled(false);
        swipeRefresh.setRefreshing(true);
        mCoreAdapterPresenter.fetch();
    }

    @Override
    public void setEmpty() {
        if ((!isHasHeadView || isReverse && !isHasFootView) && !isEmpty) {
            isEmpty = true;
            ll_emptyView.setVisibility(View.VISIBLE);
            swipeRefresh.setVisibility(View.GONE);
        }
    }

    //    @DbRealm

    /**
     * 设置网络返回数据
     *
     * @param data
     *         数据
     * @param begin
     *         起始位置
     */
    @Override
    public void setNetData(@NonNull List<M> data, int begin) {
        swipeRefresh.setRefreshing(false);
        if (isRefresh) {
            isRefresh = false;
            diffUpdate(data, false, begin);
        } else {
            diffUpdate(data, true, begin);
        }
        footerVisiable(BaseLoadMoreFooter.STATE_COMPLETE);
        if (begin == 1 && data.size() == 0) {
            mCommAdapter.setLoadingMoreEnabled(false);
            setEmpty();
        } else {
            mCommAdapter.setLoadingMoreEnabled(true);
        }

        if (isReverse) {
            recyclerview.scrollToPosition(mCommAdapter.getItemCount() - data.size() - 2);
        }
    }

    /**
     * 使用缓存数据展示
     *
     * @param data
     *         缓存数据（memory db disk）
     */
    @Override
    public void setDBData(List<M> data) {
        swipeRefresh.setRefreshing(false);
        mCommAdapter.setBeans(data, -1);
        if ((data == null || data.size() == 0)) {
            mCommAdapter.setLoadingMoreEnabled(false);
            setEmpty();
        } else if (isReverse) {
            recyclerview.scrollToPosition(mCommAdapter.getItemCount() - data.size() - 2);
        } else {

        }
    }

    @Override
    public void setServiceData(List<M> data) {
        swipeRefresh.setRefreshing(false);
        mCommAdapter.setBeans(data, -1);
        if ((data == null || data.size() == 0))
            setEmpty();
        else if (isReverse)
            recyclerview.scrollToPosition(mCommAdapter.getItemCount() - data.size() - 2);
    }

    @Override
    public void reSetEmpty() {
        if (isEmpty) {
            ll_emptyView.setVisibility(View.GONE);
            swipeRefresh.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setLimitTotal(int limitTotal) {
        mCommAdapter.setLimitTotal(limitTotal);
    }

    @Override
    public void footerState(int state) {
        footerVisiable(state);
    }

    @Override
    public void headerState(int state) {

    }

    public void diffUpdate(List<M> diffNewdataItems, boolean append, int begin) {
        mCommAdapter.diffUpdate(diffNewdataItems, append, begin);
    }
}
