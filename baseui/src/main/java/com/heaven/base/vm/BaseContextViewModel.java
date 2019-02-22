package com.heaven.base.vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * FileName: com.heaven.base.vm.BaseContextViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-21 14:17
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class BaseContextViewModel<T> extends AndroidViewModel {
    public LiveData<T> liveData = new MutableLiveData<>();
    public  SingleLiveEvent<T>  singleLiveEvent = new SingleLiveEvent<>();

    public BaseContextViewModel(@NonNull Application application) {
        super(application);
    }
}
