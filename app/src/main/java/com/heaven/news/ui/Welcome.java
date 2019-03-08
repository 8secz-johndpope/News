package com.heaven.news.ui;

import android.Manifest;
import android.content.Intent;

import com.heaven.annotation.aspect.Permission;
import com.heaven.annotation.aspect.Share;
import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.news.R;
import com.heaven.news.databinding.WelcomeBinding;
import com.heaven.news.vm.viewmodel.WelecomModelContext;
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
public class Welcome extends BaseSimpleBindActivity<WelecomModelContext, WelcomeBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.welcome;
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

    private void processNext(WelecomModelContext.UpdateInfo updateInfo) {
        if(updateInfo.isMaintaiService) {

        } else if(updateInfo.isForceUpdate) {

        } else if(updateInfo.needUpdate) {

        } else {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        Logger.i("test---------" + updateInfo.toString());
    }

}
