package com.heaven.news.ui;

import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.news.R;
import com.heaven.news.databinding.GuideNewBinding;
import com.heaven.news.vm.viewmodel.GuideNewModelContext;

/**
 * FileName: com.heaven.news.ui.GuideNewActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-08 11:29
 *
 * @author heaven
 * @version V1.0 新用户首次进入的引导页
 */
public class GuideNewActivity extends BaseSimpleBindActivity<GuideNewModelContext, GuideNewBinding> {
    @Override
    public int initLayoutResId() {
        return R.layout.guide_new;
    }

    @Override
    public void bindModel() {
        mViewBinding.setGuideNew(this);
    }
}
