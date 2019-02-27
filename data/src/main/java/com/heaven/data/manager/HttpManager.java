package com.heaven.data.manager;

import android.content.Context;
import android.util.ArrayMap;
import android.util.SparseArray;

import com.heaven.data.net.DataRepo;
import com.heaven.data.net.NetGlobalConfig;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 作者：Heaven
 * 时间: on 2016/10/8 13:47
 * 邮箱：heavenisme@aliyun.com
 */
 public class HttpManager {
    private Retrofit retrofit;
    private SparseArray<Call> reqCancelQueue = new SparseArray<>();
    private HashMap<String,Object> apiMap = new HashMap<>();

    HttpManager(Context context) {
        init(context);
    }

    private void init(Context context) {
        initNetClient(context);
    }

    private void initNetClient(Context context) {
        DataRepo.Builder repoBuilder = new DataRepo.Builder();

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        Retrofit.Builder     retrofitBuilder = new Retrofit.Builder();

        NetGlobalConfig.initHttp(context,okHttpBuilder,retrofitBuilder);

        OkHttpClient client = okHttpBuilder.build();

        retrofit = retrofitBuilder.callFactory(client).build();
    }

    @SuppressWarnings("unchecked")
    <T> T getApi(Class<T> apiClass) {
        T api = null;
        try{
            if(apiMap.containsKey(apiClass.getName())) {
                api = (T) apiMap.get(apiClass.getName());
            } else {
                api = retrofit.create(apiClass);
                apiMap.put(apiClass.getName(),api);
            }
        } catch (ClassCastException e) {
            Logger.i("ClassCastException:" + e.getMessage());
        }
        return api;
    }

    /****************************Retrofit***************************/

    /****************************Okhttp3***************************/

    /****************************RX***************************/


    /****************************RX***************************/

}
