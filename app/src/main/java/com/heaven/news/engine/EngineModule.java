package com.heaven.news.engine;

import android.content.Context;

import com.heaven.data.convert.szair.SzAirConvertFactory;
import com.heaven.data.manager.DataSource;
import com.heaven.data.net.NetGlobalConfig;
import com.heaven.news.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者:Heaven
 * 时间: on 2017/3/31 16:30
 * 邮箱:heavenisme@aliyun.com
 */
@Module
public class EngineModule {

    private final Context mContext;
    private DataSource dataSource;
    private DataCore dataCore;
    private Api api;
    public EngineModule(Context context) {
        this.mContext = context;
        DataSource.Builder builder = new DataSource.Builder(context);
        builder.addNetRepo(BuildConfig.ROOT_URL, SzAirConvertFactory.create());
        builder.addNetRepo(BuildConfig.CONFIG_URL, GsonConverterFactory.create());
        dataSource = builder.build();
        dataSource.addHeader(NetGlobalConfig.CONTENTTYPE,NetGlobalConfig.CONTENTTYPEXML);
        dataSource.addHeader("Connection", "close");
        dataCore = new DataCore(dataSource,context);
        api = new Api(dataSource);
//        ServiceCore.initServiceCore(mContext);
    }

    @Provides
    @Singleton
    AppEngine providerAppEngine() {
        return AppEngine.instance();
    }

    @Provides
    @Singleton
    DataSource providerDataSource() {
        return dataSource;
    }

    @Provides
    @Singleton
    ServiceCore providerServiceCore() {
        return null;//ServiceCore.instance(mContext);
    }

    @Provides
    @Singleton
    DataCore providerDataCore() {
        return dataCore;
    }


    @Provides
    @Singleton
    Api providerApi() {
        return api;
    }
}
