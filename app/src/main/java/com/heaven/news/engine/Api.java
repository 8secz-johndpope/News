package com.heaven.news.engine;

import android.support.v4.util.LongSparseArray;

import com.heaven.data.manager.DataSource;
import com.heaven.data.net.DataResponse;
import com.heaven.data.net.ExceptionHandle;
import com.heaven.news.ui.vm.model.ConfigData;
import com.orhanobut.logger.Logger;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * FileName: com.heaven.flybetter.engine.Api.java
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

    private static synchronized long getTaskId() {
        currentTaskId += 10;
        Logger.i("createTaskid----" + currentTaskId);
        return currentTaskId;
    }


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


    private  final FlowableTransformer<?, ?> M_IO_MAIN_TRANSFORMER
            = flowable -> flowable
            .onErrorReturn((Function<Throwable, DataResponse>) ExceptionHandle::handleException)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    private final FlowableTransformer<?, ?> M_IO_MAIN_TRANSFORMER_CONFIG
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

    private  final FlowableTransformer<?, ?> M_IO_MAIN_TRANSFORMER_HOME_CONFIG
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
    public  <T> FlowableTransformer<T, T> ioMain() {
        return (FlowableTransformer<T, T>) M_IO_MAIN_TRANSFORMER;
    }

    @SuppressWarnings("unchecked")
    public  <T> FlowableTransformer<T, T> ioMainConfig() {
        return (FlowableTransformer<T, T>) M_IO_MAIN_TRANSFORMER_CONFIG;
    }

    @SuppressWarnings("unchecked")
    public  <T> FlowableTransformer<T, T> ioHomeConfig() {
        return (FlowableTransformer<T, T>) M_IO_MAIN_TRANSFORMER_HOME_CONFIG;
    }

    public <T> long getResult(Flowable<T> resultFlowable, Consumer<T> consumer) {
        long taskId = getTaskId();
        Disposable disposable = resultFlowable.compose(ioMain()).subscribe(new TaskIdConsumer<T>(taskId,consumer));
        reqTasks.put(taskId,disposable);
        return taskId;
    }

    public <T> long getConfigResult(Flowable<T> resultFlowable,Consumer<T> consumer) {
        long taskId = getTaskId();
        Disposable disposable = resultFlowable.compose(ioMainConfig()).subscribe(new TaskIdConsumer<T>(taskId,consumer));
        reqTasks.put(taskId,disposable);
        return taskId;
    }

    public <T> long getHomeConfigResult(Flowable<T> resultFlowable,Consumer<T> consumer) {
        long taskId = getTaskId();
        Disposable disposable = resultFlowable.compose(ioHomeConfig()).subscribe(new TaskIdConsumer<T>(taskId,consumer));
        reqTasks.put(taskId,disposable);
        return taskId;
    }

    class TaskIdConsumer<T> implements Consumer<T>{
        long taskid;
        Consumer<T> resultConsumer;
        TaskIdConsumer(long taskid, Consumer<T> consumer) {
            this.taskid = taskid;
            this.resultConsumer = consumer;
        }

        @Override
        public void accept(T t) throws Exception {
            if(resultConsumer != null) {
                cancelTask(taskid);
                resultConsumer.accept(t);
            }
        }
    }


    public static void cancelCurrentTask() {
        if(reqTasks.containsKey(currentTaskId)) {
            cancelTask(currentTaskId);
        }
    }

    public static void cancelTask(long taskId) {
        if(reqTasks.containsKey(taskId)) {
            Disposable disposable = reqTasks.get(taskId);
            if(disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
            reqTasks.remove(taskId);
            Logger.i("cancelTask-----" + taskId);
        }
    }
}
