package com.heaven.news.engine;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;

import com.alibaba.android.arouter.launcher.ARouter;
import com.heaven.base.ui.SpUtil;
import com.heaven.data.dbentity.DownEntity;
import com.heaven.data.fileworker.DownLoadWorker;
import com.heaven.data.manager.DataSource;
import com.heaven.news.BuildConfig;
import com.heaven.news.engine.manager.ConfigManager;
import com.heaven.news.engine.manager.CoreComponent;
import com.heaven.news.engine.manager.UserManager;
import com.heaven.news.engine.manager.NetManager;
import com.heaven.news.ui.activity.base.MainActivity;
import com.heaven.news.ui.activity.base.Welcome;
import com.heaven.news.utils.CrashHandler;
import com.heaven.news.ui.model.bean.base.UserLoginInfo;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.tauth.Tencent;

import java.lang.ref.WeakReference;
import java.util.Stack;

import javax.inject.Inject;

import dagger.Lazy;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * 作者:Heaven.
 * 时间: on 2017/3/31 14:05
 * 邮箱:heavenisme@aliyun.com
 */

public final class AppEngine {
    public static final int STATUS_FORCE_KILLED = -1;//应用在后台被强杀了
    public static final int STATUS_NORMAL = 2; //APP正常态
    public static int APP_STATUS = STATUS_FORCE_KILLED; //默认为被后台回收了

    /**
     * 应用程序引擎.
     */
    private static WeakReference<AppEngine> engineRef;

    @Inject
    Context app;

    @Inject
    NetManager mNetManager;

    @Inject
    ConfigManager mConfigManager;
    /**
     * 数据源网络和本地
     */
    @Inject
    Lazy<DataSource> mDataSource;
    @Inject
    UserManager mDataCore;
    /**
     * 后台服务中心
     */
//    private ServiceCore mServiceCore;

    /**
     * 当前activity存储栈.
     */
    private static Stack<Activity> store;

    private UserLoginInfo userInfo;

    private AppInfo appConfig;

    public static IWXAPI iWXApi;
    public static Tencent tencent;

    /**
     * 私有构造方法.
     */
    private AppEngine() {
        init();
    }

    /**
     * 引擎初始化.
     */
    private void init() {
        store = new Stack<>();
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                //异常处理
                Logger.i(throwable.getMessage() + " ");
            }
        });
        initDi();
        initDayNightTheme();
//        //启动后台服务
////        ServiceCore.instance(appDelegate.context());
//        //activity声明周期检测
        SwitchBackgroundCallbacks callbacks = new SwitchBackgroundCallbacks();
        ((App)app).registerActivityLifecycleCallbacks(callbacks);
        initArouter();
        getDataSource().runWorkThread(this::initAppInfo);
//        //耗时初始化在线程中
        getDataSource().runWorkThread(this::initInThread);

    }

    private void initDi() {
        //上下文代理
        CoreComponent coreComponent = App.getCoreComponent();
        coreComponent.inject(this);
    }


    private void initDayNightTheme() {
        //白天黑夜切换
        SpUtil.init(app);
        int type = SpUtil.isNight() ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
        AppCompatDelegate.setDefaultNightMode(type);
    }

    private void initInThread() {
        //微信相关分享
        initShare(app);
        //初始化异常捕获类 CrashHandler
        CrashHandler.getInstance().init(app);
        //日志组件初始化
        initLogger();
        //qq X5浏览器内核初始化
        initX5Core(app);
        //应用优化组件初始化
        initAppOptimizeTool(app);
    }

    private void initArouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();//打开日志
            ARouter.openDebug();//打开调式模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init((App)app);
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("heaven")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    private void initAppOptimizeTool(Context context) {
        AppOptimizeTool.toolInit(context);
    }

    private void initX5Core(Context context) {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Logger.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(context, cb);
    }

    /* 初始化应用信息 */
    private void initAppInfo() {
        appConfig = new AppInfo();
        PackageManager pkManager = app.getPackageManager();
        appConfig.packageName = app.getPackageName();
        PackageInfo info = null;
        if (pkManager != null) {
            try {
                info = pkManager.getPackageInfo(appConfig.packageName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        }

        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            appConfig.name = appInfo.loadLabel(pkManager).toString();// 软件名称在这里赋值
            appConfig.verCode = info.versionCode;
            appConfig.verName = info.versionName;
            appConfig.sourceDir = info.applicationInfo.sourceDir;
        }
        // app.APP_IP = Util.getLocalHostIp();
    }

    private void initShare(Context context) {
//        tencent = Tencent.createInstance(BuildConfig.QQ_APP_ID, context);
        //注册 微信
        iWXApi = WXAPIFactory.createWXAPI(context, BuildConfig.WX_APP_ID, false);
        iWXApi.unregisterApp();
        iWXApi.registerApp(BuildConfig.WX_APP_ID);
    }



    /**
     * 引擎单类.
     *
     * @return 返回引擎
     */
    public static AppEngine instance() {
        if (engineRef == null) {
            synchronized (AppEngine.class) {
                if (engineRef == null) {
                    engineRef = new WeakReference<>(new AppEngine());
                }
            }
        } else {
            if(engineRef.get() == null) {
                synchronized (AppEngine.class) {
                    if(engineRef.get() == null) {
                        engineRef = new WeakReference<>(new AppEngine());
                    }
                }
            }
        }
        return engineRef.get();
    }


    /**
     * 取得数据源
     *
     * @return 数据源
     */
    public DataSource getDataSource() {
        return mDataSource.get();
    }


    public UserManager dataCore() {
        return mDataCore;
    }

    public ConfigManager confManager() {
        return mConfigManager;
    }

    public NetManager getNetManager() {
        return mNetManager;
    }

    //    public ServiceCore getServiceCore() {
//        return engineComponent.serviceCore();
//    }


    /**
     * 取得存储路径.
     *
     * @return 存储路径
     */
    public String getFilesDir() {
        return app.getFilesDir().getPath();
    }

    /**
     * 获取当前的Activity.
     *
     * @return 当前活动
     */
    public Activity getCurActivity() {
        if(store.size() == 0) {
            return mainActivity;
        } else {
            return store.lastElement();
        }
    }


    private int activityCount;//activity的数量

    private MainActivity mainActivity;

    /**
     * activity生命周期监控管理
     */
    private class SwitchBackgroundCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(final Activity activity, final Bundle bundle) {
            if(!(activity instanceof MainActivity)) {
                store.add(activity);
            } else {
                mainActivity = (MainActivity) activity;
            }
            Logger.i("onActivityCreated--" + activity);
        }

        @Override
        public void onActivityStarted(final Activity activity) {
            Logger.i("onActivityStarted--" + activity);
        }

        @Override
        public void onActivityResumed(final Activity activity) {
            Logger.i("onActivityResumed--" + activity);
        }

        @Override
        public void onActivityPaused(final Activity activity) {
            Logger.i("onActivityPaused--" + activity);
        }

        @Override
        public void onActivityStopped(final Activity activity) {
            Logger.i("onActivityStopped--" + activity);
        }

        @Override
        public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
            Logger.i("onActivitySaveInstanceState--" + activity);
        }

        @Override
        public void onActivityDestroyed(final Activity activity) {
            if(activity instanceof Observer) {
                mDataCore.removeForeverObserve((Observer) activity);
                mConfigManager.removeForeverObserve((Observer) activity);
            }
            store.remove(activity);
            Logger.i("onActivityDestroyed--" + activity);
        }
    }

    public static void Router(@NonNull Class clazz) {
        if (store.lastElement() != null) {
            Intent intent = new Intent(store.lastElement(), clazz);
            store.lastElement().startActivity(intent);
        }
    }

    public static void Router(@NonNull Intent intent) {
        if (store.lastElement() != null) {
            store.lastElement().startActivity(intent);
        }
    }

    public static void RouterExtra(@NonNull Class clazz, @NonNull Intent intent) {
        if (store.lastElement() != null) {
            intent.setClass(store.lastElement(), clazz);
            store.lastElement().startActivity(intent);
        }
    }

    public static void RouterForResult(@NonNull Class clazz, @NonNull Intent intent, int requestCode) {
        if (store.lastElement() != null) {
            intent.setClass(store.lastElement(), clazz);
            store.lastElement().startActivityForResult(intent, requestCode);
        }
    }

    /* 网易IM相关*/
    // 如果返回值为 null，则全部使用默认参数。
//    private SDKOptions options() {
//        SDKOptions options = new SDKOptions();
//
//        // 如果将新消息通知提醒托管给 SDK 完成，需要添加以下配置。否则无需设置。
//        StatusBarNotificationConfig config = new StatusBarNotificationConfig();
//        config.notificationEntrance = FlashActivity.class; // 点击通知栏跳转到该Activity
//        config.notificationSmallIconId = R.drawable.ic_launcher;
//        // 呼吸灯配置
//        config.ledARGB = Color.GREEN;
//        config.ledOnMs = 1000;
//        config.ledOffMs = 1500;
//        // 通知铃声的uri字符串
//        config.notificationSound = "android.resource://com.netease.nim.demo/raw/msg";
//        options.statusBarNotificationConfig = config;
//
//        // 配置保存图片，文件，log 等数据的目录
//        // 如果 options 中没有设置这个值，SDK 会使用下面代码示例中的位置作为 SDK 的数据目录。
//        // 该目录目前包含 log, file, image, audio, video, thumb 这6个目录。
//        // 如果第三方 APP 需要缓存清理功能， 清理这个目录下面个子目录的内容即可。
//        String sdkPath = Environment.getExternalStorageDirectory() + "/" + appDelegate.context().getPackageName() + "/nim";
//        options.sdkStorageRootPath = sdkPath;
//
//        // 配置是否需要预下载附件缩略图，默认为 true
//        options.preloadAttach = true;
//
//        // 配置附件缩略图的尺寸大小。表示向服务器请求缩略图文件的大小
//        // 该值一般应根据屏幕尺寸来确定， 默认值为 Screen.width / 2
////       options.thumbnailSize = ${Screen.width} / 2;
//
//        // 用户资料提供者, 目前主要用于提供用户资料，用于新消息通知栏中显示消息来源的头像和昵称
//        options.userInfoProvider = new UserInfoProvider() {
//            @Override
//            public UserInfo getUserInfo(String account) {
//                return null;
//            }
//
//            @Override
//            public int getDefaultIconResId() {
//                return R.drawable.default_avatar;
//            }
//
//            @Override
//            public Bitmap getTeamIcon(String tid) {
//                return null;
//            }
//
//            @Override
//            public Bitmap getAvatarForMessageNotifier(String account) {
//                return null;
//            }
//
//            @Override
//            public String getDisplayNameForMessageNotifier(String account, String sessionId,
//                                                           SessionTypeEnum sessionType) {
//                return null;
//            }
//        };
//        return options;
//    }

    // 如果已经存在用户登录信息，返回LoginInfo，否则返回null即可
//    private LoginInfo loginInfo() {
//        return null;
//    }

    /**
     * 下载文件
     *
     * @param url
     *         地址
     * @param folderPath
     *         保存文件夹
     * @param listener
     *         监听
     */
    public void downLoadFile(String url, String folderPath, DownLoadWorker.DownLoadListener listener) {
        if (mDataSource != null) {
            DownEntity entity = new DownEntity(true, -1, url, folderPath, 0, 0);
            entity.listener = listener;
            mDataSource.get().downLoadFile(entity);
        }
    }

    public boolean useExtensionRenderers() {
        return true;//BuildConfig.FLAVOR.equals("withExtensions");
    }

    public AppInfo getAppConfig() {
        return appConfig;
    }

    /**
     * 重新初始化应用界面，清空当前Activity棧，并启动欢迎页面
     */
    public void reInitApp() {
        Intent intent = new Intent(app, Welcome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        app.startActivity(intent);
    }
}
