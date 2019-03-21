package com.heaven.news.engine;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;

import com.heaven.base.ui.SpUtil;
import com.heaven.data.dbentity.DownEntity;
import com.heaven.data.fileworker.DownLoadWorker;
import com.heaven.data.manager.DataSource;
import com.heaven.news.BuildConfig;
import com.heaven.news.api.ConfigApi;
import com.heaven.news.api.LoginApi;
import com.heaven.news.consts.Constants;
import com.heaven.news.ui.activity.MainActivity;
import com.heaven.news.utils.CrashHandler;
import com.heaven.news.utils.RxRepUtils;
import com.heaven.news.utils.SystemUtil;
import com.heaven.news.ui.vm.model.UserLoginInfo;
import com.neusoft.szair.model.memberbase.MemberLoginWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.memberbase.loginNew;
import com.neusoft.szair.model.memberbase.loginReqVO;
import com.neusoft.szair.model.soap.SOAPConstants;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.tauth.Tencent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Stack;

//import com.netease.nimlib.sdk.NIMClient;
//import com.netease.nimlib.sdk.SDKOptions;
//import com.netease.nimlib.sdk.StatusBarNotificationConfig;
//import com.netease.nimlib.sdk.auth.LoginInfo;
//import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
//import com.netease.nimlib.sdk.uinfo.UserInfoProvider;

/**
 * 作者:Heaven.
 * 时间: on 2017/3/31 14:05
 * 邮箱:heavenisme@aliyun.com
 */

public final class AppEngine {
    /**
     * 应用程序引擎.
     */
    private static AppEngine instance;

    /**
     * 数据源网络和本地
     */
    private DataSource mDataSource;

    private DataCore mDataCore;

    /**
     * 后台服务中心
     */
//    private ServiceCore mServiceCore;

    /**
     * 当前activity存储栈.
     */
    private static Stack<Activity> store;
    /**
     * application代理.
     */
    private AppComponent appDelegate;

    /**
     * 依赖注入.
     */
    private EngineComponent engineComponent;

    private UserLoginInfo userInfo;

    private AppInfo appConfig;

    public static IWXAPI iWXApi;
    public static Tencent tencent;

    /**
     * 私有构造方法.
     */
    private AppEngine() {
        init();
//        initNim();
        getDataSource().runWorkThread(this::initAppInfo);
    }

    /**
     * 引擎初始化.
     */
    private void init() {
        store = new Stack<>();
        //上下文代理
        appDelegate = App.getAppDelegate();
        //启动后台服务
//        ServiceCore.getInstance(appDelegate.context());
        //白天黑夜切换
        SpUtil.init(appDelegate.app());
        int type = SpUtil.isNight() ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
        AppCompatDelegate.setDefaultNightMode(type);
        //数据源和后台服务依赖注入
        initializeInjector(appDelegate.context());
        //activity声明周期检测
        SwitchBackgroundCallbacks callbacks = new SwitchBackgroundCallbacks();
        appDelegate.app().registerActivityLifecycleCallbacks(callbacks);
        //耗时初始化在线程中
        getDataSource().runWorkThread(this::initInThread);

    }

    private void initInThread() {
        //微信相关分享
        initShare(appDelegate.context());
        //初始化异常捕获类 CrashHandler
        CrashHandler.getInstance().init(appDelegate.context());
        //日志组件初始化
        initLogger();
        //qq X5浏览器内核初始化
        initX5Core(appDelegate.context());
        //应用优化组件初始化
        initAppOptimizeTool(appDelegate.context());
    }

    //自动登录
    public void autoLogin() {
        boolean isAutoLogin = AppEngine.getInstance().getDataSource().getSharePreBoolean(Constants.ISAUTOLOGIN);
        if(isAutoLogin) {
            UserLoginInfo userInfo = AppEngine.getInstance().getDataSource().getCacheEntity(DataSource.DISK,Constants.USERINFO);
            if(userInfo != null && !TextUtils.isEmpty(userInfo.userCount) && !TextUtils.isEmpty(userInfo.userPwd)) {
                loginNew login = new loginNew();
                loginReqVO loginreqvo = new loginReqVO();
                loginreqvo._USER_NAME = userInfo.userCount;
                loginreqvo._PASSWORD = Constants.getPassword(userInfo.userPwd);

                loginreqvo._APP_ID = SOAPConstants.APP_ID;
                loginreqvo._APP_IP = SOAPConstants.APP_IP;
                loginreqvo._DEVICE_TYPE = SOAPConstants.DEVICE_TYPE;

                loginreqvo._DEVICE_TOKEN = "";
                login._LOGIN_PARAM = loginreqvo;


                MemberLoginWebServiceImplServiceSoapBinding bind = new MemberLoginWebServiceImplServiceSoapBinding("loginNew",login);//非短信验证码登陆，用户新接口

                RxRepUtils.getResult(ApiManager.getApi(LoginApi.class).login(bind), loginNewResponseDataResponse -> {
                    if (loginNewResponseDataResponse.code == 0) {
                        UserLoginInfo userLoginInfo = new UserLoginInfo();
                        userLoginInfo.userCount = userInfo.userCount;
                        userLoginInfo.userPwd = userInfo.userPwd;
                        mDataCore.initLoginData(loginNewResponseDataResponse.data);
                        AppEngine.getInstance().cacheData(DataSource.DISK, Constants.USERINFO, userLoginInfo);
                    }
                });
            }
        }

    }

    //获取广告信息
    private void getAdInfo() {
        try {
            RxRepUtils.getConfigResult(ApiManager.getApi(BuildConfig.CONFIG_URL, ConfigApi.class).getAdInfo(), configData -> {
                if(configData.netCode == 0) {
                    getDataSource().cacheData(DataSource.DISK, Constants.ADINFO, configData);
                }
            });
        } catch (Exception e) {
            Logger.i("getAdInfo:" + e.getMessage());
        }
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
        PackageManager pkManager = App.getAppDelegate().app().getPackageManager();
        appConfig.packageName = App.getAppDelegate().app().getPackageName();
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
        initCommonHeader();
    }

    private void initShare(Context context) {
//        tencent = Tencent.createInstance(BuildConfig.QQ_APP_ID, context);
        //注册 微信
        iWXApi = WXAPIFactory.createWXAPI(context, BuildConfig.WX_APP_ID, false);
        iWXApi.unregisterApp();
        iWXApi.registerApp(BuildConfig.WX_APP_ID);
    }

//    void initNim() {
//        // SDK初始化（启动后台服务，若已经存在用户登录信息， SDK 将完成自动登录）
//        NIMClient.init(appDelegate.context(), loginInfo(), options());
//
//        // ... your codes
//        if (inMainProcess()) {
//            // 注意：以下操作必须在主进程中进行
//            // 1、UI相关初始化操作
//            // 2、相关Service调用
//            // 初始化消息提醒
//            NIMClient.toggleNotification(true);
//        }
//    }

    public boolean inMainProcess() {
        String packageName = appDelegate.context().getPackageName();
        String processName = SystemUtil.getProcessName(appDelegate.context());
        return packageName.equals(processName);
    }


    /**
     * 初始化依赖注入器.
     *
     * @param context
     *         上下文
     */
    private void initializeInjector(final Context context) {
        engineComponent = DaggerEngineComponent.builder().engineModule(new EngineModule(context)).build();
        mDataSource = engineComponent.dataSource();
        mDataCore = engineComponent.dataCore();
//        mServiceCore = engineComponent.serviceCore();
    }

    /**
     * 引擎单类.
     *
     * @return 返回引擎
     */
    public static AppEngine getInstance() {
        if (instance == null) {
            synchronized (AppEngine.class) {
                if (instance == null) {
                    instance = new AppEngine();
                }
            }

        }
        return instance;
    }

    /**
     * 初始化.
     */
    public static void initEngine() {
        if (instance == null) {
            instance = new AppEngine();
        }
    }

    /**
     * 取得数据源
     *
     * @return 数据源
     */
    public DataSource getDataSource() {
        mDataSource = engineComponent.dataSource();
        return mDataSource;
    }


    public DataCore dataCore() {
        mDataCore = engineComponent.dataCore();
        return mDataCore;
    }
    /**
     * 缓存数据
     *
     * @param type
     *         缓存类型
     * @param key
     *         键值
     * @param entity
     *         数据
     */
    public void cacheData(int type, String key, Object entity) {
        getDataSource().cacheData(type, key, entity);
    }

    /**
     * 删除头数据
     *
     * @param hashMap
     *         头数据
     */
    public void removeHeaderData(HashMap<String, String> hashMap) {
        getDataSource().removeExtraHeader(hashMap);
    }


    /**
     * 取得缓存数据
     *
     * @param key
     *         键值
     * @param <E>
     *         泛型
     *
     * @return 缓存数据
     */
    public <E> E getCacheData(String key) {
        return getDataSource().getCacheEntity(key);
    }

    /**
     * 取得缓存数据
     *
     * @param type
     *         缓存类型（MEMORY:1 DB:2 DISK:3）
     * @param key
     *         键值
     * @param <E>
     *         泛型
     *
     * @return 缓存数据
     */
    public <E> E getCacheData(int type, String key) {
        return getDataSource().getCacheEntity(type, key);
    }

    /**
     * 取得登录的用户信息
     *
     * @return 用户信息
     */
    public UserLoginInfo getUserInfo() {
        if (userInfo == null) {
            userInfo = getCacheData(DataSource.DB, "userinfo");
        }
        return userInfo;
    }

    /**
     * 保存用户信息
     *
     * @param userInfo
     *         用户信息
     */
    public void setUserInfo(UserLoginInfo userInfo) {
        if (userInfo != null) {
            cacheData(DataSource.DB, "userinfo", userInfo);
            this.userInfo = userInfo;
            HashMap<String, String> headerMap = new HashMap<>();
            headerMap.put("userId", userInfo.userId);
            headerMap.put("User-Agent", "android(shenzhenair)" + appConfig.verName);
            headerMap.put("X-SZAIR-META", getSzairMeta(userInfo.userId));
            headerMap.put("X-SZAIR-LANGUAG", "zh_CN");
            getDataSource().addExtraHeader(headerMap);
        }
    }

    /**
     * 初始化请求头信息
     */
    public void initCommonHeader() {
        HashMap<String, String> headerMap = new HashMap<>();
        if (userInfo != null) {
            headerMap.put("userId", userInfo.userId);
        }
        headerMap.put("User-Agent", "android(shenzhenair)" + "5.1.0");
        headerMap.put("X-SZAIR-META", getSzairMeta(userInfo == null ? "" : userInfo.userId));
        headerMap.put("X-SZAIR-LANGUAG", "zh_CN");
        headerMap.put("Accept-Encoding", "gzip,deflate");
        getDataSource().addExtraHeader(headerMap);
    }

    /**
     * 退出登录
     */
    public void quite() {
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("userId", "0");
        headerMap.put("User-Agent", "android(heaven)" + appConfig.verName);
        headerMap.put("X-SZAIR-META", getSzairMeta("0"));
        getDataSource().addExtraHeader(headerMap);
    }

    /**
     * 生成请求定制信息
     *
     * @param userId
     *         用户id
     *
     * @return 信息
     */
    private String getSzairMeta(String userId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_version", "5.1.0"/*appConfig.verName*/);
            jsonObject.put("system_name", appConfig.DEVICE_TYPE);
            jsonObject.put("MOBILE_MODEL", appConfig.MOBILE_MODEL);
            jsonObject.put("MOBILE_SDK", appConfig.MOBILE_SDK);
            jsonObject.put("MOBILE_RELEASE", appConfig.MOBILE_RELEASE);
            if (!TextUtils.isEmpty(userId)) {
                jsonObject.put("member_id", userId);
            }

            jsonObject.put("system_version", android.os.Build.VERSION.SDK_INT);
            String token = "";

            if (TextUtils.isEmpty(token)) {
                token = "none";
            }
            jsonObject.put("device_token", token);
            jsonObject.put("device_type", appConfig.DEVICE_TYPE);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    /**
     * 取得用户id
     *
     * @return 用户id
     */
    public String getUserId() {
        String userId = "0";
        if (userInfo != null) {
            userId = String.valueOf(userInfo.userId);
        }
        return userId;
    }

    /**
     * 取得网络数据源的api
     *
     * @param apiClass
     *         api接口
     * @param <T>
     *         泛型
     *
     * @return api
     */
    <T> T getNetApi(Class<T> apiClass) {
        return engineComponent.dataSource().getNetApi(apiClass);
    }

    /**
     * 取得网络数据源的api
     *
     * @param apiClass
     *         api接口
     * @param <T>
     *         泛型
     *
     * @return api
     */
    <T> T getNetApi(String url, Class<T> apiClass) throws Exception {
        return engineComponent.dataSource().getNetApi(url, apiClass);
    }

    public ServiceCore getServiceCore() {
        return engineComponent.serviceCore();
    }

    /**
     * 取得注入器.
     *
     * @return 注入器
     */
    public EngineComponent getEngineComponent() {
        return engineComponent;
    }

    /**
     * 取得存储路径.
     *
     * @return 存储路径
     */
    public String getFilesDir() {
        return appDelegate.context().getFilesDir().getPath();
    }

    /**
     * 获取当前的Activity.
     *
     * @return 当前活动
     */
    public Activity getCurActivity() {
        return store.lastElement();
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
        }

        @Override
        public void onActivityStarted(final Activity activity) {

        }

        @Override
        public void onActivityResumed(final Activity activity) {

        }

        @Override
        public void onActivityPaused(final Activity activity) {

        }

        @Override
        public void onActivityStopped(final Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(final Activity activity) {
            store.remove(activity);
            mDataCore.removeDataObserver(activity);
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
            mDataSource.downLoadFile(entity);
        }
    }

    public boolean useExtensionRenderers() {
        return true;//BuildConfig.FLAVOR.equals("withExtensions");
    }

    public AppInfo getAppConfig() {
        return appConfig;
    }
}
