package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.news.R;
import com.heaven.news.ui.vm.model.ImageInfo;

/**
 * FileName: com.heaven.news.ui.vm.adapterbean.HomeBanner.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-27 13:56
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class HomeBanner extends BaseMultItem<ImageInfo> {

    public HomeBanner(@NonNull Class<ImageInfo> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ImageInfo homeImageInfo) {
        ImageView imageView = holder.itemView.findViewById(R.id.banner_image);
        if (TextUtils.isEmpty(homeImageInfo.pic)) {
            imageView.setImageResource(R.mipmap.hint_banner);
        } else {
            Glide.with(holder.itemView.getContext())
                    .load(homeImageInfo.pic)
                    .placeholder(R.mipmap.hint_banner) // can also be a drawable
                    .error(R.mipmap.hint_banner) // will be displayed if the image cannot be loaded
                    .centerCrop()
                    .into(imageView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ImageInfo homeImageInfo, Object payload) {
        ImageView imageView = holder.itemView.findViewById(R.id.banner_image);
        if (TextUtils.isEmpty(homeImageInfo.pic)) {
            imageView.setImageResource(R.mipmap.hint_banner);
        } else {
            Glide.with(holder.itemView.getContext())
                    .load(homeImageInfo.pic)
                    .placeholder(R.mipmap.hint_banner) // can also be a drawable
                    .error(R.mipmap.hint_banner) // will be displayed if the image cannot be loaded
                    .centerCrop()
                    .into(imageView);
        }
    }
}
