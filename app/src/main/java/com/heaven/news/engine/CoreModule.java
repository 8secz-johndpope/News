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

}
