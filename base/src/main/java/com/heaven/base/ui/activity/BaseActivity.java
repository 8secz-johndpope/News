package com.heaven.base.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.heaven.base.R;
import com.heaven.base.ui.SpUtil;

/**
 * FileName: com.heaven.base.ui.activity.BaseActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-13 11:58
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class BaseActivity extends AppCompatActivity {

    public void reload() {
        AppCompatDelegate.setDefaultNightMode(SpUtil.isNight() ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
        recreate();
    }
}
