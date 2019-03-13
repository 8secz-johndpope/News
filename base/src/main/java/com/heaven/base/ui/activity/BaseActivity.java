package com.heaven.base.ui.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.heaven.base.R;
import com.heaven.base.ui.SpUtil;
import com.heaven.base.ui.view.widget.SwipeBackLayout;
import com.heaven.base.utils.MPermissionUtils;

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
        View rootView = getLayoutInflater().inflate(this.initLayoutResId(), null, false);
        mViewBinding = DataBindingUtil.bind(rootView);
        initView(rootView);
        this.makeContentView(rootView);
    }

    @Override
    public void initView(View rootView) {

    }

    public void makeContentView(View rootView) {
        super.setContentView(getContainer(rootView));
    }

    private View getContainer(View rootView) {
        rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.alpha_white));
        View container = getLayoutInflater().inflate(R.layout.activity_base, null, false);
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
