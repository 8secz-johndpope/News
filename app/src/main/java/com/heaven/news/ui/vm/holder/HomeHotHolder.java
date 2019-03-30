package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.news.R;
import com.heaven.news.ui.vm.model.ImageInfo;

/**
 * FileName: com.heaven.news.ui.vm.holder.HomeHotHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-30 11:35
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class HomeHotHolder extends BaseMultItem<ImageInfo> {

    public HomeHotHolder(@NonNull Class<ImageInfo> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
        setSpanSize(1);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ImageInfo imageInfo) {
        ImageView image = holder.getView(R.id.image);
        if (TextUtils.isEmpty(imageInfo.pic)) {
            image.setImageResource(R.mipmap.hint_banner);
        } else {
            Glide.with(holder.itemView.getContext())
                    .load(imageInfo.pic)
                    .placeholder(R.mipmap.hint_banner) // can also be a drawable
                    .error(R.mipmap.hint_banner) // will be displayed if the image cannot be loaded
                    .centerCrop()
                    .into(image);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull ImageInfo imageInfo, Object payload) {

    }

    @Override
    public int getSpanSize(int spanCount) {
        return 1;
    }
}
