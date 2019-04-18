package com.heaven.news.engine;

import com.heaven.news.ui.vm.vmmodel.AbstractViewModel;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * FileName: com.heaven.news.engine.CoreComponent.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-16 11:40
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
@Singleton
@Component(modules = {CoreModule.class})
public interface CoreComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(App application);
        CoreComponent build();
    }

    void inject(AbstractViewModel viewModel);

    void inject(AppEngine appEngine);
}
