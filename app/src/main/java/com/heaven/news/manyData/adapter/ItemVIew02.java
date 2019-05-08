package com.heaven.news.manyData.adapter;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.news.R;
import com.heaven.news.manyData.bean.Bean02;

/**
 * <p/>
 * Author: 温利东 on 2017/6/14 11:52.
 * http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */

public class ItemVIew02 extends BaseMultItem<Bean02> {
    public ItemVIew02(@NonNull Class<Bean02> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull Bean02 bean02) {
        holder.setText(R.id.tv_item02, bean02.title);
        ImageView iv = holder.getView(R.id.iv_item02);

        Glide.with(iv.getContext())
                .load(bean02.imgUrl)
//                .placeholder(R.mipmap.ic_launcher) // can also be a drawable
//                .error(R.mipmap.cheese) // will be displayed if the image cannot be loaded
                .centerCrop()
                .into(iv);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull Bean02 bean02, Object payload) {

    }

    @Override
    public int getSpanSize(int spanCount) {
        return 2;
    }


    //    @NonNull
//    @Override
//    public int getLayoutId() {
//        return R.layout.item_two;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Bean02 item, int position) {
//        holder.setText(R.id.tv_item02, item.groupTitle);
//        ImageView iv = holder.getView(R.id.iv_item02);
//
//        Glide.with(iv.getContext())
//                .load(item.imgUrl)
////                .placeholder(R.mipmap.ic_launcher) // can also be a drawable
////                .error(R.mipmap.cheese) // will be displayed if the image cannot be loaded
//                .centerCrop()
//                .into(iv);
//
//    }

}
