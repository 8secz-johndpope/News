package com.heaven.news.engine.manager;

import android.content.Context;
import android.support.v4.util.LongSparseArray;

import com.heaven.data.manager.DataSource;
import com.heaven.data.net.DataResponse;
import com.heaven.data.net.ExceptionHandle;
import com.heaven.news.BuildConfig;
import com.heaven.news.api.IApi;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.utils.RxRepUtils;
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
    private IApi api;
    NetManager(DataSource dataSource, Context context) {
        api = dataSource.getNetApi(IApi.class);
    }

    private static long currentTaskId = 1000000000000000L;
    private static LongSparseArray<Disposable> reqTasks = new LongSparseArray<>();

    private synchronized long getTaskId() {
        currentTaskId += 10;
        Logger.i("createTaskid----" + currentTaskId);
        return currentTaskId;
    }

    private static final FlowableTransformer<?, ?> M_IO_MAIN_TRANSFORMER
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

    public  <T> long getConfigResult(Flowable<T> resultFlowable, Consumer<T> consumer) {
        long taskId = getTaskId();
        Disposable disposable = resultFlowable.onErrorReturn(throwable -> (T) "").subscribeOn(Schedulers.io()).subscribe(getTaskConsumer(taskId, consumer));
        reqTasks.put(taskId, disposable);
        return taskId;
    }

    public  <T> long getResultInThred(Flowable<T> resultFlowable, Consumer<T> consumer) {
        long taskId = getTaskId();
        Disposable disposable = resultFlowable.compose(ioThread()).subscribe(getTaskConsumer(taskId, consumer));
        reqTasks.put(taskId, disposable);
        return taskId;
    }

    public  <T> long getResult(Flowable<T> resultFlowable, Consumer<T> consumer) {
        long taskId = getTaskId();
        Disposable disposable = resultFlowable.compose(ioMain()).subscribe(getTaskConsumer(taskId, consumer));
        reqTasks.put(taskId, disposable);
        return taskId;
    }

    public  IApi getCommonApi() {
        return AppEngine.instance().api().getApi(BuildConfig.ROOT_URL, IApi.class);
    }

    private <T> NetManager.TaskIdConsumer<T> getTaskConsumer(long taskId, Consumer<T> consumer) {
        return new NetManager.TaskIdConsumer<T>(taskId, consumer);
    }

    class TaskIdConsumer<T> implements Consumer<T> {
        long taskid;
        Consumer<T> resultConsumer;

        TaskIdConsumer(long taskid, Consumer<T> consumer) {
            this.taskid = taskid;
            this.resultConsumer = consumer;
        }

        @Override
        public void accept(T t) throws Exception {
            if (resultConsumer != null) {
                resultConsumer.accept(t);
                cancelTask(taskid);
            }
        }
    }


    public void cancelCurrentTask() {
        if (reqTasks.containsKey(currentTaskId)) {
            cancelTask(currentTaskId);
        }
    }

    public void cancelTask(long taskId) {
        if (reqTasks.containsKey(taskId)) {
            Disposable disposable = reqTasks.get(taskId);
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
            reqTasks.remove(taskId);
            Logger.i("cancelTask-----" + taskId);
        }
    }

    public IApi getApi() {
        return api;
    }
}
