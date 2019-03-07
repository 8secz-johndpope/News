package com.heaven.news.engine;

import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * FileName: com.heaven.flybetter.engine.AppOptimizeTool.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-26 12:03
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class AppOptimizeTool {

    public static void toolInit(Context context) {
        Stetho.initializeWithDefaults(context);

//        Stetho.initialize(Stetho
//                .newInitializerBuilder(context)
//                .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
//                .enableWebKitInspector(
//                        Stetho.defaultInspectorModulesProvider(context)).build());
    }

}
