package com.heaven.news.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.BindingAdapter;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.heaven.base.ui.SpUtil;
import com.heaven.news.R;

/**
 * 数据绑定工具类
 * Created by free46000 on 2017/4/6.
 */
public class DataBindUtil {

    @BindingAdapter({"imageUrl"})
    public static void loadImg(ImageView v, String url) {
        v.setColorFilter(v.getContext().getResources().getColor(SpUtil.isNight() ? R.color.CoverColor : R.color.colorWhite), PorterDuff.Mode.MULTIPLY);
        Glide.with(v.getContext())
                .load(getFuckUrl(url))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(v);
    }

    @BindingAdapter({"roundImageUrl"})
    public static void loadRoundImg(ImageView v, String url) {
        v.setColorFilter(v.getContext().getResources().getColor(SpUtil.isNight() ? R.color.CoverColor : R.color.colorWhite), PorterDuff.Mode.MULTIPLY);
        Glide.with(v.getContext())
                .load(getFuckUrl(url))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .transform(new GlideCircleTransform(v.getContext()))
                .error(R.mipmap.ic_launcher)
                .into(v);
    }


    @BindingAdapter({"article"})
    public static void setArticle(TextView tv, String url) {
        if (TextUtils.isEmpty(url)) return;
        String article = url.replace("<br>", "\n").replaceAll(" ", "").replaceAll("//", "");
        if (!TextUtils.isEmpty(article) && article.indexOf("&gt;") > 0) {
            article = article.substring(article.indexOf("&gt;") + 4, article.length());
        }
        tv.setText(article);
    }


    public static String getFuckUrl(String url) {
        if (url != null && url.startsWith("http://ear.duomi.com/wp-description/themes/headlines/thumb.php?src=")) {
            url = url.substring(url.indexOf("=") + 1, url.indexOf("jpg") > 0 ? url.indexOf("jpg") + 3 : url.indexOf("png") > 0 ? url.indexOf("png") + 3 : url.length());
            url = url.replace("kxt.fm", "ear.duomi.com");
        }
        return url;
    }

    public static void loadRoundAndBgImg(ImageView v, String url, ImageView im_header) {
        v.setColorFilter(v.getContext().getResources().getColor(SpUtil.isNight() ? R.color.CoverColor : R.color.colorWhite), PorterDuff.Mode.MULTIPLY);
        Glide.with(v.getContext())
                .load(getFuckUrl(url))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .transform(new GlideCircleTransform(v.getContext()))
                .error(R.mipmap.ic_launcher)
                .into(v);
        im_header.setColorFilter(v.getContext().getResources().getColor(SpUtil.isNight() ? R.color.CoverColor : R.color.colorWhite), PorterDuff.Mode.MULTIPLY);
//        Glide.with(v.getContext()).load(getFuckUrl(url))
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .transform(new BlurTransformation(v.getContext(), 100))
//                .crossFade()
//                .into(im_header);
    }

    public static String getUrlByIntent(Context mContext, Intent mdata) {
        Uri uri = mdata.getData();
        String scheme = uri.getScheme();
        String data = "";
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = mContext.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA},
                    null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(
                            MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
