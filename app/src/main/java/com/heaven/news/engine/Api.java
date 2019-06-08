package com.heaven.news.engine;

import android.support.v4.util.LongSparseArray;

import com.heaven.data.manager.DataSource;
import com.heaven.data.net.DataResponse;
import com.heaven.data.net.ExceptionHandle;
import com.heaven.news.ui.vm.model.base.ConfigData;
import com.orhanobut.logger.Logger;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * FileName: com.heaven.flybetter.engine.IApi.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-06-23 21:51
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class Api {
   private DataSource mDataSource;

    private static long currentTaskId = 1000000000000000L;
    private static LongSparseArray<Disposable> reqTasks = new LongSparseArray<>();


    Api(DataSource dataSource){
        this.mDataSource = dataSource;
    }


    public  <T> T getApi(Class<T> apiClass){
        return mDataSource.getNetApi(apiClass);
    }

    public <T> T getApi(String url,Class<T> apiClass){
        T targetApi = null;
        try {
            targetApi = mDataSource.getNetApi(url,apiClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetApi;
    }
}
