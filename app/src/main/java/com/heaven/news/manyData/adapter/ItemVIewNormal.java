package com.heaven.news.manyData.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.news.R;

/**
 * <p/>
 * Author: 温利东 on 2017/6/14 11:52.
 * http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */

public class ItemVIewNormal extends BaseMultItem<String> {

   public ItemVIewNormal(@NonNull Class<String> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

//    @NonNull
//    @Override
//    public int getLayoutId() {
//        return R.layout.item_go;
//    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull String item) {
        holder.setText(R.id.tv, item);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull String s, Object payload) {

    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder viewHolder) {
        ViewGroup.LayoutParams lp = viewHolder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    @Override
    public int getSpanSize(int spanCount) {
        return 4;
    }
}
