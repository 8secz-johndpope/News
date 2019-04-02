package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.news.ui.vm.model.base.BookData;

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

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull BookData bookData, Object payload) {

    }

    @Override
    public boolean isTargetViewType(BookData item, int position) {
        return BookData.BOOK_GO == item.bookType;
    }
}
