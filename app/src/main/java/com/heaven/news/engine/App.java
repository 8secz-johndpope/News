package com.heaven.news.engine;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.heaven.base.ui.view.swipebacktest.BGASwipeBackHelper;
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
    }


    /**
     * app初始化
     */
    private void init(){
        AppEngine.instance();
        /**
         * 必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回
         * 第一个参数：应用程序上下文
         * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，请把该界面里比较特殊的 View 的 class 添加到该集合中，目前在库中已经添加了 WebView 和 SurfaceView
         */
        BGASwipeBackHelper.init(this, null);
//        this.initializeLeakDetection();
    }


    /**
     * 取得代理
     * @return app代理
     */
    public static CoreComponent getCoreComponent() {
        return coreComponent;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
