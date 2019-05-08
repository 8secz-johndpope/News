package com.heaven.news.manyData.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.news.R;
import com.heaven.news.manyData.bean.Bean03;

/**
 * <p/>
 * Author: 温利东 on 2017/6/14 11:52.
 * http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */

public class ItemVIew03 extends BaseMultItem<Bean03> {
    public ItemVIew03(@NonNull Class<Bean03> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull Bean03 bean03) {
        holder.setText(R.id.tv_item03, bean03.title);
        ImageView iv = holder.getView(R.id.iv_item03);


        Glide.with(iv.getContext())
                .load(bean03.imgUrl)
//                .placeholder(R.mipmap.ic_launcher) // can also be a drawable
//                .error(R.mipmap.cheese) // will be displayed if the image cannot be loaded
                .centerCrop()
                .into(iv);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull Bean03 bean03, Object payload) {

    }

    @Override
    public int getSpanSize(int spanCount) {
        return 4;
    }

    //    @NonNull
//    @Override
//    public int getLayoutId() {
//        return R.layout.item_three;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Bean03 item, int position) {
//        holder.setText(R.id.tv_item03, item.groupTitle);
//        ImageView iv = holder.getView(R.id.iv_item03);
//
//
//        Glide.with(iv.getContext())
//                .load(item.imgUrl)
////                .placeholder(R.mipmap.ic_launcher) // can also be a drawable
////                .error(R.mipmap.cheese) // will be displayed if the image cannot be loaded
//                .centerCrop()
//                .into(iv);
//
//    }
//
//    @Override
//    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
//        WrapperUtils.setFullSpan(viewHolder);
//    }
}
