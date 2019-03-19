package com.heaven.news.manyData.adapter;

import android.support.annotation.NonNull;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.news.R;
import com.heaven.news.manyData.bean.Bean01;

/**
 * <p/>
 * Author: 温利东 on 2017/6/14 11:52.
 * http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */

public class ItemVIew01 extends BaseMultItem<Bean01> {

    public ItemVIew01(@NonNull Class<Bean01> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull Bean01 bean01) {
        holder.setText(R.id.tv_item01, bean01.title);
        if (holder.getItemPosition()  == 0 || holder.getItemPosition() == 8 || holder.getItemPosition() == 15|| holder.getItemPosition() == 21 || holder.getItemPosition() == 28) {
            holder.setBackgroundColor(R.id.tv_item01, 0xFF48F745);
        } else {
            holder.setBackgroundColor(R.id.tv_item01, 0xFFFF4081);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull Bean01 bean01, Object payload) {

    }

//    @NonNull
//    @Override
//    public int getLayoutId() {
//        return R.layout.item_one;
//    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Bean01 item, int position) {
//        holder.setText(R.id.tv_item01, item.title);
//        if (position  == 0 || position == 8 || position == 15|| position == 21 || position == 28) {
//            holder.setBackgroundColor(R.id.tv_item01, 0xFF48F745);
//        } else {
//            holder.setBackgroundColor(R.id.tv_item01, 0xFFFF4081);
//        }
//    }
}