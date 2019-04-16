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
        builder.addNetRepo(BuildConfig.ROOT_URL, SzAirConvertFactory.create());
        builder.addNetRepo(BuildConfig.CONFIG_URL, GsonConverterFactory.create());
        DataSource dataSource = builder.build();
        dataSource.addHeader(NetGlobalConfig.CONTENTTYPE,NetGlobalConfig.CONTENTTYPEXML);
        dataSource.addHeader("Connection", "close");
        return dataSource;
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

}
