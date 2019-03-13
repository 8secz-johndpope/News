package com.heaven.news.utils;


import com.heaven.data.net.DataResponse;
import com.heaven.data.net.ExceptionHandle;
import com.heaven.news.ui.vm.model.ConfigData;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者:Heaven
 * 时间: on 2017/3/13 11:15
 * 邮箱:heavenisme@aliyun.com
 */

public class RxRepUtils {
    private static final FlowableTransformer<?, ?> M_IO_MAIN_TRANSFORMER
            = flowable -> flowable
            .onErrorReturn((Function<Throwable, DataResponse>) ExceptionHandle::handleException)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    private static final FlowableTransformer<?, ?> M_IO_MAIN_TRANSFORMER_CONFIG
            = flowable -> flowable
            .onErrorReturn((Function<Throwable, ConfigData>) throwable -> {
                ConfigData configData = new ConfigData();
                DataResponse dataResponse = ExceptionHandle.handleException(throwable);
                configData.netCode = dataResponse.code;
                configData.message = dataResponse.reason;
                return configData;
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    @SuppressWarnings("unchecked")
    public static <T> FlowableTransformer<T, T> ioMain() {
        return (FlowableTransformer<T, T>) M_IO_MAIN_TRANSFORMER;
    }

    @SuppressWarnings("unchecked")
    public static <T> FlowableTransformer<T, T> ioMainConfig() {
        return (FlowableTransformer<T, T>) M_IO_MAIN_TRANSFORMER_CONFIG;
    }


    public static <T> Disposable getResult(Flowable<T> resultFlowable,Consumer<T> consumer) {
        return resultFlowable.compose(RxRepUtils.ioMain()).subscribe(consumer);
    }

    public static <T> Disposable getConfigResult(Flowable<T> resultFlowable,Consumer<T> consumer) {
        return resultFlowable.compose(RxRepUtils.ioMainConfig()).subscribe(consumer);
    }
}
