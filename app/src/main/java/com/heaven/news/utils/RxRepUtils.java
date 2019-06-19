package com.heaven.news.utils;


import android.support.v4.util.LongSparseArray;

import com.heaven.data.net.DataResponse;
import com.heaven.data.net.ExceptionHandle;
import com.heaven.news.BuildConfig;
import com.heaven.news.api.IApi;
import com.heaven.news.engine.AppEngine;
import com.orhanobut.logger.Logger;


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
    private static RxRepUtils instance = new RxRepUtils();

    private static long currentTaskId = 1000000000000000L;
    private static LongSparseArray<Disposable> reqTasks = new LongSparseArray<>();

    private static synchronized long getTaskId() {
        currentTaskId += 10;
        Logger.i("createTaskid----" + currentTaskId);
        return currentTaskId;
    }

    private static final FlowableTransformer<?, ?> M_IO_MAIN_TRANSFORMER
            = flowable -> flowable
            .onErrorReturn((Function<Throwable, DataResponse>) ExceptionHandle::handleException)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    private static final FlowableTransformer<?, ?> M_IO_MAIN_TRANSFORMER_THREAD
            = flowable -> flowable
            .onErrorReturn((Function<Throwable, DataResponse>) ExceptionHandle::handleException)
            .subscribeOn(Schedulers.io());

    @SuppressWarnings("unchecked")
    private static <T> FlowableTransformer<T, T> ioMain() {
        return (FlowableTransformer<T, T>) M_IO_MAIN_TRANSFORMER;
    }

    @SuppressWarnings("unchecked")
    private static <T> FlowableTransformer<T, T> ioThread() {
        return (FlowableTransformer<T, T>) M_IO_MAIN_TRANSFORMER_THREAD;
    }

    public static  <T> long getConfigResult(Flowable<T> resultFlowable, Consumer<T> consumer) {
        long taskId = getTaskId();
        Disposable disposable = resultFlowable.onErrorReturn(throwable -> (T) "").subscribeOn(Schedulers.io()).subscribe(t -> {
            if (consumer != null) {
                consumer.accept(t);
                cancelTask(taskId);
            }
        });
        reqTasks.put(taskId, disposable);
        return createTask(resultFlowable,consumer,ioThread()).startTask();
    }

    public static  <T> long getResultInThred(Flowable<T> resultFlowable, Consumer<T> consumer) {
        return createTask(resultFlowable,consumer,ioThread()).startTask();
    }

    public static  <T> long getResult(Flowable<T> resultFlowable, Consumer<T> consumer) {
        return createTask(resultFlowable,consumer,ioMain()).startTask();
    }

    private static <T> Task<T> createTask(Flowable<T> resultFlowable, Consumer<T> consumer,FlowableTransformer<T, T> transformer) {
        return new Task<>(resultFlowable,consumer,transformer);
    }

    static class Task<T> {
        Flowable<T> resultFlowable;
        Consumer<T> consumer;
        FlowableTransformer<T, T> transformer;
        Task(Flowable<T> resultFlowable, Consumer<T> consumer,FlowableTransformer<T, T> transformer) {
            this.resultFlowable = resultFlowable;
            this.consumer = consumer;
            this.transformer = transformer;
        }

        long startTask() {
            long taskId = getTaskId();
            Disposable disposable =  resultFlowable.compose(transformer).subscribe(t -> {
                if (consumer != null) {
                    consumer.accept(t);
                    cancelTask(taskId);
                }
            });
            reqTasks.put(taskId, disposable);
            return taskId;
        }
    }


    public static void cancelCurrentTask() {
        if (reqTasks.containsKey(currentTaskId)) {
            cancelTask(currentTaskId);
        }
    }

    public static void cancelTask(long taskId) {
        if (reqTasks.containsKey(taskId)) {
            Disposable disposable = reqTasks.get(taskId);
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
            reqTasks.remove(taskId);
            Logger.i("cancelTask-----" + taskId);
        }
    }
}
