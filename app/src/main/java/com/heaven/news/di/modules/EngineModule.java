package com.heaven.news.di.modules;

import android.content.Context;

import com.heaven.data.manager.DataSource;
import com.heaven.data.net.NetGlobalConfig;
import com.heaven.news.BuildConfig;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.ServiceCore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 作者:Heaven
 * 时间: on 2017/3/31 16:30
 * 邮箱:heavenisme@aliyun.com
 */
@Module
public class EngineModule {

    private final Context mContext;
    private DataSource dataSource;
    public EngineModule(Context context) {
        this.mContext = context;
        DataSource.Builder builder = new DataSource.Builder(context);
        builder.addNetRepo(BuildConfig.ROOT_URL, NetGlobalConfig.PROTOTYPE.XML);
        dataSource = builder.build();
        dataSource.addHeader(NetGlobalConfig.CONTENTTYPE,NetGlobalConfig.CONTENTTYPEXML);
        ServiceCore.initServiceCore(mContext);
    }

    @Provides
    @Singleton
    AppEngine providerAppEngine() {
        return AppEngine.getInstance();
    }

    @Provides
    @Singleton
    DataSource providerDataSource() {
        return dataSource;
    }

    @Provides
    @Singleton
    ServiceCore providerServiceCore() {
        return ServiceCore.getInstance(mContext);
    }
}
