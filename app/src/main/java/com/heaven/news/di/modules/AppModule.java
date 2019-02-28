package com.heaven.news.di.modules;

import android.content.Context;


import com.heaven.news.engine.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 作者:Heaven
 * 时间: on 2017/3/31 16:30
 * 邮箱:heavenisme@aliyun.com
 */
@Module
public class AppModule {
    private final App mApp;

    public AppModule(App mApp) {
        this.mApp = mApp;
    }

    @Provides
    App providerApp() {
        return mApp;
    }

    @Provides
    @Singleton
    Context providerContext() {
        return mApp.getApplicationContext();
    }
}
