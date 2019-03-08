package com.heaven.news.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.news.R;
import com.heaven.news.databinding.WelcomeBinding;
import com.heaven.news.vm.viewmodel.WelecomModel;
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

    @SuppressLint("InlinedApi")
    @Override
    public void initView(View rootView) {
        rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void makeContentView(View rootView) {
        setContentView(rootView);
    }

    @Override
    public void bindModel() {
        mViewBinding.setViewmodel(mViewModel);
        mViewModel.versionLive.observe(this, updateInfo -> {
            if(updateInfo != null) {
                processNext(updateInfo);
            }
        });
    }

    private void processNext(WelecomModel.UpdateInfo updateInfo) {
        if(updateInfo.isMaintaiService) {

        } else if(updateInfo.isForceUpdate) {

        } else if(updateInfo.needUpdate) {

        } else {
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
        Logger.i("test---------" + updateInfo.toString());
    }

}
