package com.heaven.news.engine;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.heaven.news.utils.SystemUtil;
//import com.squareup.leakcanary.LeakCanary;


/**
 * 作者：Heaven.
 * 时间: on 2017/2/21 14:55
 * 邮箱：heavenisme@aliyun.com
 */

public class App extends Application{
    private static CoreComponent coreComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        coreComponent = DaggerCoreComponent.builder().application(this).build();
        init();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        long start = System.currentTimeMillis();
        Log.d("ryg", "use time:" + (System.currentTimeMillis() - start));
    }


    /**
     * app初始化
     */
    private void init(){
        initDi();
        AppEngine.instance();
//        this.initializeLeakDetection();
    }

    private void initDi() {

    }


    /**
     * 取得代理
     * @return app代理
     */
    protected static CoreComponent getCoreComponent() {
        return coreComponent;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
