package com.heaven.base.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * FileName: com.heaven.base.vm.BaseViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-21 14:16
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class BaseViewModel<T> extends ViewModel {
  public   LiveData<T> liveData = new MutableLiveData<>();
  public  SingleLiveEvent<T>  singleLiveEvent = new SingleLiveEvent<>();
}
