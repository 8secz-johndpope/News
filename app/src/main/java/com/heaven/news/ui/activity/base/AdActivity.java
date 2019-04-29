package com.heaven.news.ui.activity.base;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.heaven.base.ui.activity.BaseActivity;
import com.heaven.data.manager.DataSource;
import com.heaven.news.R;
import com.heaven.news.consts.Constants;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.databinding.AdBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.vm.model.base.AdInfo;

/**
 * FileName: com.heaven.news.ui.activity.base.AdActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-13 12:14
 *
 * @author heaven
 * @version V1.0 ad page
 */
@Route(path = RouterUrl.ROUTER_URL_AD)
public class AdActivity extends BaseActivity<AdBinding> {
    @Override
    public int initLayoutResId() {
        return R.layout.ad;
    }

    @Override
    public void initView(View rootView) {
        AdInfo adInfo = AppEngine.instance().getCacheData(DataSource.DB,Constants.ADINFO);
        if(adInfo == null) {
            toMainPage();
        } else {
            processAd(adInfo);
            new Handler().postDelayed(this::toMainPage,4000);
        }
    }

    private void processAd(AdInfo adInfo) {
        Glide.with(this).load(adInfo.urlImage).placeholder(R.mipmap.splash_bg).into(mViewBinding.adBackground);
    }

    private void toMainPage() {
        startActivity(new Intent(AdActivity.this, LoginActivity.class));
        finish();
    }


    @Override
    public void bindModel() {

    }
}
