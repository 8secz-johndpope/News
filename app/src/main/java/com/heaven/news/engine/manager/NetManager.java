package com.heaven.news.engine.manager;

import android.app.Activity;
import android.content.Context;
import android.support.v4.util.LongSparseArray;

import com.heaven.data.manager.DataSource;
import com.heaven.data.net.DataResponse;
import com.heaven.data.net.ExceptionHandle;
import com.heaven.news.api.IApi;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.view.dialog.NetReqDialog;
import com.orhanobut.logger.Logger;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * FileName: com.heaven.news.engine.manager.NetManager.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-08 17:40
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class NetManager {
    private IApi mApi;
    private DataSource dataSource;

    NetManager(DataSource dataSource, Context context) {
        this.dataSource = dataSource;
        this.mApi = dataSource.getNetApi(IApi.class);
    }

    private long currentTaskId = 1000000000000000L;
    private LongSparseArray<Disposable> reqTasks = new LongSparseArray<>();

    private synchronized long getTaskId() {
        currentTaskId += 10;
        Logger.i("createTaskid----" + currentTaskId);
        return currentTaskId;
    }

    private final FlowableTransformer<?, ?> M_IO_MAIN_TRANSFORMER
            = flowable -> flowable
            .onErrorReturn((Function<Throwable, DataResponse>) ExceptionHandle::handleException)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    private final FlowableTransformer<?, ?> M_IO_MAIN_TRANSFORMER_THREAD
            = flowable -> flowable
            .onErrorReturn((Function<Throwable, DataResponse>) ExceptionHandle::handleException)
            .subscribeOn(Schedulers.io());

    @SuppressWarnings("unchecked")
    private <T> FlowableTransformer<T, T> ioMain() {
        return (FlowableTransformer<T, T>) M_IO_MAIN_TRANSFORMER;
    }

    @SuppressWarnings("unchecked")
    private <T> FlowableTransformer<T, T> ioThread() {
        return (FlowableTransformer<T, T>) M_IO_MAIN_TRANSFORMER_THREAD;
    }

    public <T> long getResultInThred(Flowable<T> resultFlowable, Consumer<T> consumer) {
        return createTask(resultFlowable, consumer, ioThread()).startTask();
    }

    public <T> long getResult(Flowable<T> resultFlowable, Consumer<T> consumer) {
        return createTask(resultFlowable, consumer, ioMain()).startTask();
    }

    private <T> NetManager.Task<T> createTask(Flowable<T> resultFlowable, Consumer<T> consumer, FlowableTransformer<T, T> transformer) {
        return new NetManager.Task<>(resultFlowable, consumer, transformer);
    }

    class Task<T> {
        Flowable<T> resultFlowable;
        Consumer<T> consumer;
        FlowableTransformer<T, T> transformer;

        Task(Flowable<T> resultFlowable, Consumer<T> consumer, FlowableTransformer<T, T> transformer) {
            this.resultFlowable = resultFlowable;
            this.consumer = consumer;
            this.transformer = transformer;
        }

        long startTask() {
            long taskId = getTaskId();
            Disposable disposable = resultFlowable.compose(transformer).subscribe(t -> {
                if (consumer != null) {
                    consumer.accept(t);
                    cancelTask(taskId);
                }
            });
            reqTasks.put(taskId, disposable);
            return taskId;
        }
    }

    private NetReqDialog loading;


    public void showLoadingDialog(Context context,boolean isCancel,long taskId) {
        if (loading != null) {
            loading.dismiss();
        }
        loading = new NetReqDialog(context,getTaskById(taskId));
        if(!isCancel) {
            loading.hideCancel();
        }
        loading.show();
    }


    public void disMassLoading() {
        if(loading != null) {
            cancelTask(loading.getTaskId());
            loading.dismiss();
        }
    }




    public void cancelCurrentTask() {
        if (reqTasks.containsKey(currentTaskId)) {
            cancelTask(currentTaskId);
        }
    }

    public void cancelTask(long taskId) {
        if (reqTasks.containsKey(taskId)) {
            Disposable disposable = getTaskById(taskId);
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
                reqTasks.remove(taskId);
            }
            Logger.i("cancelTask-----" + taskId);
        }
    }

    private Disposable getTaskById(long taskId) {
        Disposable disposable = null;
        if (reqTasks.containsKey(taskId)) {
            disposable = reqTasks.get(taskId);
        }
        return disposable;
    }

    public IApi getApi() {
        return mApi;
    }
}
