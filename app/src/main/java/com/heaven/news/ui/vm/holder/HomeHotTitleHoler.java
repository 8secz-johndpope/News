package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.utils.ScreenUtil;
import com.heaven.news.R;
import com.heaven.news.ui.vm.model.HomeHotTitle;

/**
 * FileName: com.heaven.news.ui.vm.holder.HomeHotTitleHoler.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-30 11:41
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class HomeHotTitleHoler extends BaseMultItem<HomeHotTitle> {

    public HomeHotTitleHoler(@NonNull Class<HomeHotTitle> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
        setSpanSize(2);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull HomeHotTitle homeHotTitle) {
        holder.setImageResource(R.id.icon, homeHotTitle.icon);
        holder.setText(R.id.title,homeHotTitle.titleResId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull HomeHotTitle homeHotTitle, Object payload) {

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
        return 2;
    }
}
