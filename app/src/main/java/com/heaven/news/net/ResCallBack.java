package com.heaven.news.net;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.heaven.data.net.DataResponse;

import io.reactivex.functions.Consumer;

/**
 * FileName: com.heaven.news.net.ResCallBack.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-25 12:03
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class ResCallBack<T> extends LiveData<T> implements Observer<T> {

    private LifecycleOwner owner;

    public ResCallBack(LifecycleOwner owner) {
        this.owner = owner;
        observe(owner,this);
    }

    public void updatePostValue(T value) {
        postValue(value);
    }

    public void updatSetValue(T value) {
        setValue(value);
    }

}
