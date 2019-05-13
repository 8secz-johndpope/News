package com.heaven.base.ui.activity;

import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.heaven.base.R;
import com.heaven.base.ui.SpUtil;
import com.heaven.base.ui.view.SystemBarTintManager;
import com.heaven.base.ui.view.swipeback.SwipeBackHelper;
import com.heaven.base.utils.MPermissionUtils;

/**
 * FileName: com.heaven.base.ui.activity.BaseActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-13 11:58
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity implements IBaseActivity, SwipeBackHelper.Delegate {
    protected SwipeBackHelper mSwipeBackHelper;
    public B mViewBinding;
    public View titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 「必须在 Application 的 onCreate 方法中执行 SwipeBackHelper.init 来初始化滑动返回」
        // 在 super.onCreate(savedInstanceState) 之前调用该方法
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        initMmersionTitleBar();
        setContentView(R.layout.base);
        LinearLayout rootView = findViewById(R.id.base_container);
        if (initLayoutResId() > 0) {
            if (iniTitleBarResId() > 0) {
                View title = getLayoutInflater().inflate(iniTitleBarResId(), rootView, false);
                rootView.addView(title);
                initTitle(title);
            }
            View mainView = getLayoutInflater().inflate(this.initLayoutResId(), rootView, false);
            mViewBinding = DataBindingUtil.bind(mainView);
            rootView.addView(mainView);
            makeContentView(rootView);
            initView(rootView);
        }
        ARouter.getInstance().inject(this);
    }

    public void makeContentView(View rootView) {
        super.setContentView(rootView);
    }

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new SwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 SwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }

    protected void initMmersionTitleBar() {
        // 4.4及以上版本开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        setBarColor(tintManager);
    }

    @Override
    public void initTitle(View titleView) {
    }

    @Override
    public void setTitle(String title) {
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
    }

    // 自定义颜色
    protected void setBarColor(SystemBarTintManager tintManager) {

    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public int iniTitleBarResId() {
        return 0;
    }

    @Override
    public void initView(View rootView) {

    }


    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset
     *         从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }


    public void reload() {
        AppCompatDelegate.setDefaultNightMode(SpUtil.isNight() ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        recreate();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
