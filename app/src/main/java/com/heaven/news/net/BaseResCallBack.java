package com.heaven.news.net;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.heaven.data.net.DataResponse;
import com.neusoft.szair.model.noticelist.queryNoticeListResponse;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * FileName: com.heaven.news.net.BaseResCallBack.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-25 12:03
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class BaseResCallBack<T> extends LiveData<DataResponse<T>> implements Observer<DataResponse<T>> {
    protected BaseResCallBack(LifecycleOwner owner) {
        observe(owner,this);
    }

    public void updateSuccessPostValue(T value) {
        DataResponse<T>  result = new DataResponse<>();
        result.data = value;
        postValue(result);
    }

    public void updateErrorPostValue(int code,String errorMsg) {
        DataResponse<T>  result = new DataResponse<>();
        result.code = code;
        result.reason = errorMsg;
        postValue(result);
    }

    public void updateSuccessSetValue(T value) {
        DataResponse<T>  result = new DataResponse<>();
        result.data = value;
        postValue(result);
    }

    public void updateErrorSetValue(int code,String errorMsg) {
        DataResponse<T>  result = new DataResponse<>();
        result.code = code;
        result.reason = errorMsg;
        postValue(result);
    }

    @Override
    public abstract void onChanged(@NonNull DataResponse<T> tDataResponse);
}
