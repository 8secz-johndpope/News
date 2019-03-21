package com.heaven.news.ui.activity;

import android.content.Intent;
import android.view.View;

import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.news.R;
import com.heaven.news.databinding.WelcomeBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.DataCore;
import com.heaven.news.ui.vm.viewmodel.WelecomModel;
import com.orhanobut.logger.Logger;

/**
 * FileName: com.heaven.news.ui.activity.Welcome.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-07 17:57
 *
 * @author heaven
 * @version V1.0 欢迎页
 */
public class Welcome extends BaseSimpleBindActivity<WelecomModel, WelcomeBinding> implements DataCore.DataReadyObserver {

    @Override
    public int initLayoutResId() {
        return R.layout.welcome;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        DataCore.UpdateInfo updateInfo = AppEngine.getInstance().dataCore().getUpdateInfo();
        if(updateInfo != null) {
            processNext(updateInfo);
        } else {
            AppEngine.getInstance().dataCore().addDataObserver(this);
        }
    }

    @Override
    protected void initMmersionTitleBar() {
    }

    @Override
    public void makeContentView(View rootView) {
        setContentView(rootView);
    }

    @Override
    public void bindModel() {
        mViewBinding.setViewmodel(mViewModel);
    }

    private void processNext(DataCore.UpdateInfo updateInfo) {
        if (updateInfo.isNetError) {
            toNextPage(updateInfo);
        } else {
            if (updateInfo.isServiceMainta) {
                toNextPage(updateInfo);
            } else if (updateInfo.isForceUpdate) {
                toNextPage(updateInfo);
            } else if (updateInfo.needUpdate) {
                toNextPage(updateInfo);
            } else {
                toNextPage(updateInfo);
            }
        }
        Logger.i("test---------" + updateInfo.toString());
    }


    private void toNextPage(DataCore.UpdateInfo updateInfo) {
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
        startActivity(new Intent(Welcome.this, MainActivity.class));
        finish();
    }

    private void toGuidePage() {
        startActivity(new Intent(Welcome.this, GuideActivity.class));
        finish();
    }

    @Override
    public void dataReady(int dataType) {
        if(DataCore.VERSION == dataType) {
            DataCore.UpdateInfo updateInfo = AppEngine.getInstance().dataCore().getUpdateInfo();
            processNext(updateInfo);
        }
    }
}
