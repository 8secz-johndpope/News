package com.heaven.news.engine;

import android.content.Context;

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
        DataSource dataSource = builder.build();
//        addMainSourceHeader(dataSource);
//        addFlightSourceHeader(dataSource);
//        addConfigSourceHeader(dataSource);

//        DataSource.Builder builder = new DataSource.Builder(context);
//        builder.addNetRepo(BuildConfig.ROOT_URL, ConverterFactory.create());
//        builder.addNetRepo(BuildConfig.CONFIG_URL, ConverterFactory.create());
//        builder.addNetRepo(BuildConfig.FLIGHT_URL, ConverterFactory.create());
//        DataRepo.Builder versionBuilder = builder.addNetRepoBuilder(BuildConfig.VERSION_URL,ConverterFactory.create());
//
//        versionBuilder.setTime(5,5,5);
//        DataSource dataSource = builder.build();
//        addMainSourceHeader(dataSource);
//        addFlightSourceHeader(dataSource);
//        addConfigSourceHeader(dataSource);
        return dataSource;
    }


    private void addMainSourceHeader(DataSource dataSource) {
        dataSource.addHeader(NetGlobalConfig.CONTENTTYPE,NetGlobalConfig.CONTENTTYPEXML);
        dataSource.addHeader("Connection", "close");
    }

    private void addFlightSourceHeader(DataSource dataSource) {
        HashMap<String,String> headerMap = new HashMap<>();
        headerMap.put(NetGlobalConfig.CONTENTTYPE,NetGlobalConfig.CONTENTTYPEXML);
        headerMap.put("Connection", "close");
        headerMap.put("DATA-TYPE", "PROTOFUL");
        dataSource.addExtraHeader(BuildConfig.FLIGHT_URL,headerMap);
    }

    private void addConfigSourceHeader(DataSource dataSource) {
        HashMap<String,String> headerMap = new HashMap<>();
        headerMap.put(NetGlobalConfig.CONTENTTYPE,NetGlobalConfig.CONTENTTYPEJSON);
        headerMap.put("Connection", "close");
        dataSource.addExtraHeader(BuildConfig.CONFIG_URL,headerMap);
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

}
