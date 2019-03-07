package com.heaven.news.ui;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.news.R;
import com.heaven.news.databinding.WelcomeBinding;
import com.heaven.news.vm.model.Version;
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

    @Override
    public void bindModel() {
        mViewBinding.setViewmodel(mViewModel);
        mViewModel.version.observe(this, version -> {
            if(version != null) {
                Logger.i("test---------" + version.toString());
            }
        });
    }

}
