package com.heaven.data.util;



import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者:Heaven
 * 时间: on 2017/3/13 11:15
 * 邮箱:heavenisme@aliyun.com
 */

public class RxDataSchedulers {
    public static final FlowableTransformer<?, ?> mIoMainTransformer
            = flowable -> flowable
//            .onErrorReturn(new Function<Throwable, DataResponse>() {
//                @Override
//                public DataResponse apply(Throwable e) {
//                    return ExceptionHandle.handleException(e);
//                }
//            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    @SuppressWarnings("unchecked")
    public static <T> FlowableTransformer<T, T> io_main() {
        return (FlowableTransformer<T, T>) mIoMainTransformer;
    }
}
