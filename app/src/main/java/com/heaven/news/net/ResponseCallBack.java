package com.heaven.news.net;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.heaven.data.net.DataResponse;

import io.reactivex.functions.Consumer;

/**
 * FileName: com.heaven.news.net.ResponseCallBack.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-25 11:25
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class ResponseCallBack<T> implements Observer<T> , Consumer<DataResponse> {
    @Override
    public void onChanged(@Nullable T result) {

    }

    @Override
    public void accept(DataResponse dataResponse) throws Exception {

    }
}
