package com.heaven.news.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.heaven.base.vm.BaseViewModel;
import com.heaven.news.pt.TestPt;

/**
 * FileName: com.heaven.news.vm.TestViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-25 16:45
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class TestViewModel extends BaseViewModel<TestPt> {

    public TestViewModel(@NonNull Application application) {
        super(application);
    }
}
