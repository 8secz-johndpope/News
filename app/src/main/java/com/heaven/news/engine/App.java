package com.heaven.news.engine;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.heaven.news.utils.SystemUtil;
//import com.squareup.leakcanary.LeakCanary;


/**
 * 作者：Heaven.
 * 时间: on 2017/2/21 14:55
 * 邮箱：heavenisme@aliyun.com
 */

public class App extends Application{
    /**
     * app代理.
     */
    private static AppComponent appDelegate;

    @Override
    public void onCreate() {
        super.onCreate();
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
        initializeInjector(this);
        // ... your codes
        if (inMainProcess()) {
            // 注意：以下操作必须在主进程中进行
            // 1、UI相关初始化操作
            // 2、相关Service调用
            AppEngine.initEngine();
        } else {
//            AppEngine.instance().initNim();
        }
//        this.initializeLeakDetection();
    }

    public boolean inMainProcess() {
        String packageName = getPackageName();
        String processName = SystemUtil.getProcessName(this);
        return packageName.equals(processName);
    }

    /**
     * 生成代理注入器.
     * @param app app
     */
    private void initializeInjector(final App app) {
        appDelegate = DaggerAppComponent.builder().appModule(new AppModule(app)).build();
    }



    /**
     * 取得代理
     * @return app代理
     */
    public static AppComponent getAppDelegate() {
        return  appDelegate;
    }

}
