package com.heaven.base.ui.activity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.heaven.base.presenter.BasePresenter;

/**
 * FileName: com.heaven.base.ui.activity.BaseViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-25 13:56
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class BaseViewModel<P extends BasePresenter> extends AndroidViewModel {
    P mPresenter;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }
}
