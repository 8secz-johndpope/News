package com.heaven.base.ui.activity;

import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.heaven.base.R;
import com.heaven.base.ui.SpUtil;
import com.heaven.base.ui.view.widget.SwipeBackLayout;
import com.heaven.base.utils.MPermissionUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * FileName: com.heaven.base.ui.activity.BaseActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-13 11:58
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity implements IBaseActivity{
    public B mViewBinding;
    public View titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(initLayoutResId() > 0) {
            LinearLayout rootView = (LinearLayout) getLayoutInflater().inflate(R.layout.base, null);
            if(iniTitleBarResId() > 0) {
                titleBar = getLayoutInflater().inflate(iniTitleBarResId(),null);
                rootView.addView(titleBar);
                initTitle(titleBar);
            }
            View mainView = getLayoutInflater().inflate(this.initLayoutResId(), null);
            mViewBinding = DataBindingUtil.bind(mainView);
            rootView.addView(mainView);
            this.makeContentView(rootView);
            getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
            initMmersionTitleBar();
            initView(rootView);
        }
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
    private void setTranslucentStatus(boolean on) {
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

    private void makeContentView(View rootView) {
        super.setContentView(getContainer(rootView));
    }

    private View getContainer(View rootView) {
        rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.alpha_white));
        View container = getLayoutInflater().inflate(R.layout.swipback_base, null, false);
        SwipeBackLayout swipeBackLayout = container.findViewById(R.id.swipeBackLayout);
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        View ivShadow = container.findViewById(R.id.iv_shadow);
        swipeBackLayout.addView(rootView);
        swipeBackLayout.setOnSwipeBackListener((fa, fs) -> ivShadow.setAlpha(1 - fs));
        return container;
    }


    public void reload() {
        AppCompatDelegate.setDefaultNightMode(SpUtil.isNight() ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
        recreate();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
