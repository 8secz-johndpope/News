package com.heaven.news.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.heaven.base.ui.adapter.BaseLoopBannerAdapter;
import com.heaven.news.R;
import com.heaven.news.ui.vm.model.ImageInfo;

/**
 * FileName: com.heaven.news.ui.adapter.BannerAdapter.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-23 15:42
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class BannerAdapter extends BaseLoopBannerAdapter<ImageInfo> {

    public BannerAdapter(Context context) {
        super(context);
    }

    @Override
    public int initLayoutRes() {
        return R.layout.banner_item;
    }

    @Override
    public void bindView(View viewItem, ImageInfo imageInfo, int position) {
        ImageView imageView = viewItem.findViewById(R.id.banner_image);
        Glide.with(viewItem.getContext())
                .load(imageInfo.pic)
//                .placeholder(R.mipmap.ic_launcher) // can also be a drawable
//                .error(R.mipmap.cheese) // will be displayed if the image cannot be loaded
                .centerCrop()
                .into(imageView);
    }
}
