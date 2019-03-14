package com.heaven.base.ui.activity;

import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(initLayoutResId() > 0) {
            LinearLayout contentContainer = null;
            View rootView = null;
            if(initToolBarResId() > 0) {
                rootView = getLayoutInflater().inflate(R.layout.base, null);
                contentContainer = rootView.findViewById(R.id.content_container);
                View tooBarView = getLayoutInflater().inflate(initToolBarResId(), null);
                contentContainer.addView(tooBarView);
            }

            View mainView = getLayoutInflater().inflate(this.initLayoutResId(), null);
            mViewBinding = DataBindingUtil.bind(mainView);

            if(contentContainer != null) {
                contentContainer.addView(mainView);
                initView(rootView);
                this.makeContentView(rootView);
            } else {
                initView(mainView);
                this.makeContentView(mainView);
            }
            initTitleBar();
        }

    }

    private void initTitleBar() {
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
        // 自定义颜色
//        tintManager.setTintColor(Color.parseColor("#24b7a4"));
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
    public int initToolBarResId() {
        return 0;
    }

    @Override
    public void initView(View rootView) {

    }

    public void makeContentView(View rootView) {
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
