package com.heaven.news.engine;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.heaven.data.net.DataRepo;
import com.heaven.news.api.convert.ConverterFactory;
import com.heaven.news.api.convert.fastjson.FastJsonnConverterFactory;
import com.heaven.news.api.convert.protostuff.SzProtobufConvertFactory;
import com.heaven.news.api.convert.szair.SzAirConvertFactory;
import com.heaven.data.manager.DataSource;
import com.heaven.data.net.NetGlobalConfig;
import com.heaven.news.BuildConfig;
import com.heaven.news.net.DynamicIntercepter;

import java.util.HashMap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import okhttp3.Headers;
import okhttp3.Interceptor;

/**
 * FileName: com.heaven.news.engine.CoreModule.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-16 11:35
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
@Module
public class CoreModule {

    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    DataSource provideDataSource(Context context) {
        DataSource.Builder builder = new DataSource.Builder(context);
        DataRepo.Builder versionBuilder = builder.addNetRepoBuilder(BuildConfig.ROOT_URL,ConverterFactory.create());
        versionBuilder.addDynamicInterceptor(new DynamicIntercepter());
        return builder.build();
    }


    @Provides
    @Singleton
    ServiceCore providerServiceCore() {
        return null;//ServiceCore.instance(mContext);
    }

    @Provides
    @Singleton
    DataCore providerDataCore(DataSource dataSource,Context app) {
        return new DataCore(dataSource,app);
    }


    @Provides
    @Singleton
    Api providerApi(DataSource dataSource) {
        return new Api(dataSource);
    }

    @Provides
    @Singleton
    ConfigManager configManage(DataSource dataSource,Context app) {
        return new ConfigManager(dataSource,app);
    }


//    /**
//     * 添加请求头
//     *
//     * @param extraHeaders
//     *         请求头
//     */
//    public void addExtraHeader(HashMap<String, String> extraHeaders) {
//        Headers.Builder builder = headers.newBuilder();
//        Disposable subscribe = Flowable.fromIterable(extraHeaders.entrySet()).subscribe(stringStringEntry -> {
//            String key = stringStringEntry.getKey();
//            String value = stringStringEntry.getValue();
//            if (!TextUtils.isEmpty(headers.get(key))) {
//                builder.set(key, value);
//            } else {
//                builder.add(key, value);
//            }
//
//        });
//        headers = builder.build();
//        updateHeaders(headers);
//    }
//
//    /**
//     * 删除请求头
//     *
//     * @param key
//     *         key
//     * @param value
//     *         value
//     */
//    public void removeExtraHeader(String key, String value) {
//        Headers.Builder builder = headers.newBuilder();
//        builder.removeAll(key);
//        headers = builder.build();
//        updateHeaders(headers);
//    }
//
//    /**
//     * 删除请求头
//     *
//     * @param extraHeaders
//     *         请求头
//     */
//    public void removeExtraHeader(HashMap<String, String> extraHeaders) {
//        Headers.Builder builder = headers.newBuilder();
//        Disposable subscribe = Flowable.fromIterable(extraHeaders.entrySet()).subscribe(stringStringEntry -> {
//            String key = stringStringEntry.getKey();
//            builder.removeAll(key);
//        });
//        headers = builder.build();
//        updateHeaders(headers);
//    }
//
//    /**
//     * 更新请求头信息
//     *
//     * @param headers
//     *         请求头信息
//     */
//    private void updateHeaders(Headers headers) {
//        headerInterceptor.setHeaders(headers);
//    }

}
