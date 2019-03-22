package com.heaven.news.ui.activity;

import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.news.R;
import com.heaven.news.databinding.GuideBinding;
import com.heaven.news.ui.vm.viewmodel.GuideNewModel;

/**
 * FileName: com.heaven.news.ui.activity.GuideActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-08 11:29
 *
 * @author heaven
 * @version V1.0 新用户首次进入的引导页
 */
public class GuideActivity extends BaseSimpleBindActivity<GuideNewModel, GuideBinding> {
    @Override
    public int initLayoutResId() {
        return R.layout.guide;
    }

    @Override
    public void bindModel() {
        mViewBinding.setGuideNew(this);
    }
}