package com.heaven.news.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.heaven.base.ui.adapter.BaseBannerAdapter;
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
public class BannerAdapter extends BaseBannerAdapter<ImageInfo> {

    public BannerAdapter(Context context, ViewPager viewPager) {
        super(context, viewPager);
    }

    @Override
    public View bindView(Context context, ImageInfo imageInfo, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
        ImageView imageView = view.findViewById(R.id.banner_image);
        Glide.with(context)
                .load(imageInfo.url)
//                .placeholder(R.mipmap.ic_launcher) // can also be a drawable
//                .error(R.mipmap.cheese) // will be displayed if the image cannot be loaded
                .centerCrop()
                .into(imageView);
        return view;
    }
}
