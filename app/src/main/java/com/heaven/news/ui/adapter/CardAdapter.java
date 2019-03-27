package com.heaven.news.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.heaven.base.ui.view.widget.banner.BannerAdapterHelper;
import com.heaven.news.R;
import com.heaven.news.ui.vm.model.ImageInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jameson on 8/30/16.
 * changed by 二精-霁雪清虹 on 2017/11/19
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<ImageInfo> mList = new ArrayList<>();
    private BannerAdapterHelper mBannerAdapterHelper = new BannerAdapterHelper();


    public List<ImageInfo> getList() {
        return mList;
    }

    public void updateList(List<ImageInfo> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_item, parent, false);
        mBannerAdapterHelper.onCreateViewHolder(parent, itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mBannerAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
        Glide.with(holder.itemView.getContext())
                .load(mList.get(position%mList.size()).pic)
                .placeholder(R.mipmap.hint_banner) // can also be a drawable
                .error(R.mipmap.hint_banner) // will be displayed if the image cannot be loaded
                .centerCrop()
                .into(holder.mImageView);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RecyclerView) holder.itemView.getParent()).smoothScrollToPosition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null? 0 : Integer.MAX_VALUE;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

    }

}
