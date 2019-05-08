package com.heaven.news.ui.activity.base;

import android.view.View;

import com.heaven.base.ui.activity.BaseBindActivity;
import com.heaven.news.R;
import com.heaven.news.databinding.DateSelectBinding;
import com.heaven.news.ui.vm.vmmodel.SelectDateViewModel;

/**
 * FileName: com.heaven.news.ui.activity.base.DateSelect.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-05-07 20:08
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
public class DateSelect extends BaseBindActivity<SelectDateViewModel, DateSelectBinding> {
    @Override
    public int initLayoutResId() {
        return R.layout.date_select;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);

    }

    @Override
    public void bindModel() {

    }
}
