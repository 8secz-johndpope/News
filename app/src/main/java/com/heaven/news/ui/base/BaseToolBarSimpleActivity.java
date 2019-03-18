package com.heaven.news.ui.base;

import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.heaven.base.ui.activity.BaseSimpleBindActivity;
import com.heaven.base.vm.BaseViewModel;
import com.heaven.news.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * FileName: com.heaven.news.ui.base.BaseToolBarActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-14 12:50
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class BaseToolBarSimpleActivity<VM extends BaseViewModel, B extends ViewDataBinding> extends BaseSimpleBindActivity<VM,B> {
    ImageView  home_online_help;
    ImageView  home_push_mess;
    ImageView  change_language;
    TextView titleBar;
    ImageView  toolbar_home_image;
    ImageView  toolbar_phone_image;

    @Override
    public int iniTitleBarResId() {
        return R.layout.title_bar;
    }

    @Override
    public void setTitle(String title) {
        if(titleBar != null) {
            titleBar.setText(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        if(titleBar != null) {
            titleBar.setText(titleId);
        }
    }

    @Override
    public void initView(View rootView) {
        home_online_help = rootView.findViewById(R.id.home_online_help);
        home_push_mess = rootView.findViewById(R.id.home_push_mess);
        change_language = rootView.findViewById(R.id.change_language);
        titleBar = rootView.findViewById(R.id.title);
        toolbar_home_image = rootView.findViewById(R.id.toolbar_home_image);
        toolbar_phone_image = rootView.findViewById(R.id.toolbar_phone_image);
    }

    @Override
    protected void setBarColor(SystemBarTintManager tintManager) {
        tintManager.setStatusBarTintResource(R.mipmap.toolbar_bg);
    }
}
