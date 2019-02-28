package com.heaven.news.di.components;


import com.heaven.data.manager.DataSource;
import com.heaven.news.di.modules.EngineModule;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.ServiceCore;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 作者:Heaven
 * 时间: on 2017/3/31 16:29
 * 邮箱:heavenisme@aliyun.com
 */
@Singleton
@Component(modules = EngineModule.class)
public interface EngineComponent {

    AppEngine appEngine();

    DataSource dataSource();

    ServiceCore serviceCore();
}
