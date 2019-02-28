package com.heaven.news.di.components;

import android.content.Context;


import com.heaven.news.di.modules.AppModule;
import com.heaven.news.engine.App;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 作者:Heaven
 * 时间: on 2017/3/31 16:29
 * 邮箱:heavenisme@aliyun.com
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context context();

    App app();
}
