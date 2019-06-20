package com.heaven.news.ui.model.holder;

import android.support.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.news.R;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.ui.model.bean.base.BookData;

/**
 * FileName: com.heaven.news.ui.vm.holder.HomeBookGo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-01 20:08
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class HomeBookGo extends BaseMultItem<BookData> {

    public HomeBookGo(@NonNull Class<BookData> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull BookData bookData) {
        holder.setOnClickListener(R.id.book_start_city, v -> {
            ARouter.getInstance().build(RouterUrl.ROUTER_URL_CITY).navigation();
        });
        holder.setOnClickListener(R.id.date_info, v -> {
            ARouter.getInstance().build(RouterUrl.ROUTER_URL_DATE).navigation();
        });
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull BookData bookData, Object payload) {

    }

    @Override
    public boolean isTargetViewType(BookData item, int position) {
        return BookData.BOOK_GO == item.bookType;
    }
}
