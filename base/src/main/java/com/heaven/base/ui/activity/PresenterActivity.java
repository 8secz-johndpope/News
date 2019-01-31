package com.heaven.base.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.heaven.base.R;
import com.heaven.base.presenter.BasePresenter;
import com.heaven.base.ui.SpUtil;
import com.heaven.base.ui.view.widget.SwipeBackLayout;

/**
 * 作者：Heaven
 * 时间: on 2017/2/20 16:43
 * 邮箱：heavenisme@aliyun.com
 */

public abstract class PresenterActivity extends AppCompatActivity  implements IBaseActivity{

    abstract void parseArgumentsFromIntent(Intent argIntent);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(this.initLayoutResId(), null, false);
        this.makeContentView(rootView);
        init();
        initView();
    }

    private void init() {
        onInitPresenters();
        if (getIntent() != null) {
            parseArgumentsFromIntent(getIntent());
        }
    }

    public void makeContentView(View rootView) {
        super.setContentView(getContainer(rootView));
    }

    private View getContainer(View rootView) {
        rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.alpha_white));
        View container = getLayoutInflater().inflate(R.layout.activity_base, null, false);
        SwipeBackLayout swipeBackLayout = (SwipeBackLayout) container.findViewById(R.id.swipeBackLayout);
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
}
