package com.heaven.news.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.news.R;
import com.heaven.news.constant.Constants;
import com.heaven.news.databinding.WelcomeBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.vm.model.AdInfo;
import com.heaven.news.ui.vm.viewmodel.WelecomModel;
import com.orhanobut.logger.Logger;

/**
 * FileName: com.heaven.news.ui.Welcome.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-07 17:57
 *
 * @author heaven
 * @version V1.0 欢迎页
 */
public class Welcome extends BaseSimpleBindActivity<WelecomModel, WelcomeBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.welcome;
    }

    @Override
    public void initView(View rootView) {
    }

    @Override
    public void makeContentView(View rootView) {
        setContentView(rootView);
    }

    @Override
    public void bindModel() {
        mViewBinding.setViewmodel(mViewModel);
        mViewModel.versionLive.observe(this, updateInfo -> {
            if (updateInfo != null) {
                processNext(updateInfo);
            }
        });
    }

    private void processNext(WelecomModel.UpdateInfo updateInfo) {
        if (updateInfo.isNetError) {
            toNextPage(updateInfo);
        } else {
            if (updateInfo.isServiceMainta) {

            } else if (updateInfo.isForceUpdate) {

            } else if (updateInfo.needUpdate) {

            } else {
                toNextPage(updateInfo);
            }
        }
        Logger.i("test---------" + updateInfo.toString());
    }


    private void toNextPage(WelecomModel.UpdateInfo updateInfo) {
        if (updateInfo.nextGuidePage) {
            toGuidePage();
        } else if (updateInfo.isShowAd) {
            if(updateInfo.adInfo != null) {
                toAdPage();
            } else {
                toMainPage();
            }
        } else {
            toMainPage();
        }
    }

    private void toAdPage() {
        Intent intent = new Intent(Welcome.this, AdActivity.class);
        startActivity(intent);
        finish();
    }

    private void toMainPage() {
        startActivity(new Intent(Welcome.this, LoginActivity.class));
        finish();
    }

    private void toGuidePage() {
        startActivity(new Intent(Welcome.this, GuideActivity.class));
        finish();
    }

}
