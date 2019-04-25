package com.heaven.news.ui.base;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heaven.base.ui.activity.BaseBindActivity;
import com.heaven.base.ui.view.SystemBarTintManager;
import com.heaven.base.vm.BaseViewModel;
import com.heaven.news.R;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.activity.Welcome;

import java.util.ArrayList;

/**
 * FileName: com.heaven.news.ui.base.BaseToolBarActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-14 12:50
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public abstract class BaseToolBarBindActivity<VM extends BaseViewModel, B extends ViewDataBinding> extends BaseBindActivity<VM, B> {

    LinearLayout titleLeftArea;
    ImageView goBack;
    ImageView homeOnlineHelp;
    ImageView homePushMess;
    ImageView changeLanguage;

    TextView titleBar;

    LinearLayout titleRightArea;
    ImageView toolbarHomeImage;
    ImageView toolbarPhoneImage;
    ImageView mineMessageImage;
    ImageView toolbarSetting;
    ImageView extraImage;
    TextView extraFunction;

    ArrayList<View.OnClickListener> toobarListenerList = new ArrayList<>();

    View.OnClickListener toobarRootListener = v -> {
        if(R.id.go_back == v.getId()) {
            onBackPress();
        } else if(R.id.home_online_help == v.getId()){
            showOnlineHelp();
        } else if(R.id.home_push_mess == v.getId() || R.id.mine_message_image == v.getId()){
            gotoMessage();
        } else if(R.id.change_language == v.getId()){
            changeLanguage();
        } else if(R.id.toolbar_setting == v.getId()){
            gotoSetting();
        } else if(R.id.toolbar_home_image == v.getId()) {
            returnHome();
        }else {
            if(toobarListenerList != null && toobarListenerList.size() > 0) {
                for(View.OnClickListener listener : toobarListenerList) {
                    listener.onClick(v);
                }
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppEngine.APP_STATUS != AppEngine.STATUS_NORMAL) { // 非正常启动流程，直接重新初始化应用界面
            AppEngine.instance().reInitApp();
            finish();
        }
    }

    private void showOnlineHelp() {

    }

    private void onBackPress() {
        finish();
    }

    private void changeLanguage() {

    }


    private void returnHome() {

    }

    private void gotoSetting() {

    }

    private void gotoMessage() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            onBackPress();
        }
        return false;
    }

    @Override
    public int iniTitleBarResId() {
        return R.layout.title_bar;
    }

    @Override
    public void initTitle(View titleView) {
        super.initTitle(titleView);
        titleLeftArea = titleView.findViewById(R.id.title_left_area);
        goBack = titleView.findViewById(R.id.go_back);
        homeOnlineHelp = titleView.findViewById(R.id.home_online_help);
        homePushMess = titleView.findViewById(R.id.home_push_mess);
        changeLanguage = titleView.findViewById(R.id.change_language);

        titleBar = titleView.findViewById(R.id.title);

        titleRightArea = titleView.findViewById(R.id.title_right_area);
        toolbarHomeImage = titleView.findViewById(R.id.toolbar_home_image);
        toolbarPhoneImage = titleView.findViewById(R.id.toolbar_phone_image);
        mineMessageImage = titleView.findViewById(R.id.mine_message_image);
        toolbarSetting = titleView.findViewById(R.id.toolbar_setting);
        extraImage = titleView.findViewById(R.id.extra_image);
        extraFunction = titleView.findViewById(R.id.extra_function);

        goBack.setOnClickListener(toobarRootListener);
        homeOnlineHelp.setOnClickListener(toobarRootListener);
        homePushMess.setOnClickListener(toobarRootListener);
        changeLanguage.setOnClickListener(toobarRootListener);
        toolbarHomeImage.setOnClickListener(toobarRootListener);
        toolbarPhoneImage.setOnClickListener(toobarRootListener);
        mineMessageImage.setOnClickListener(toobarRootListener);
        toolbarSetting.setOnClickListener(toobarRootListener);
        extraImage.setOnClickListener(toobarRootListener);
        extraFunction.setOnClickListener(toobarRootListener);
    }

    @Override
    public void setTitle(String title) {
        if (titleBar != null) {
            titleBar.setText(title);
            adjustTitlePosition(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        if (titleBar != null) {
            titleBar.setText(titleId);
            adjustTitlePosition(getString(titleId));
        }
    }

    public void showTitleBarOnlyTitle(int titleId) {
        titleLeftArea.setVisibility(View.GONE);
        titleRightArea.setVisibility(View.GONE);
        setTitle(titleId);
    }

    public void showMineTitleBarTitle(int titleId) {
        setTitle(titleId);
        hideTitleBarAll();
        titleLeftArea.setVisibility(View.GONE);
        titleRightArea.setVisibility(View.VISIBLE);
        toolbarSetting.setVisibility(View.VISIBLE);
        mineMessageImage.setVisibility(View.VISIBLE);

    }

    public void showMineTitleBarTitle(String title) {
        setTitle(title);
        hideTitleBarAll();
        titleLeftArea.setVisibility(View.GONE);
        titleRightArea.setVisibility(View.VISIBLE);
        toolbarSetting.setVisibility(View.VISIBLE);
        mineMessageImage.setVisibility(View.VISIBLE);

    }

    public void showHomeTitleBarTitle(String title,boolean hasLogin) {
        setTitle(title);
        hideTitleBarAll();
        homeOnlineHelp.setVisibility(View.VISIBLE);
        homePushMess.setVisibility(View.VISIBLE);
        changeLanguage.setVisibility(View.VISIBLE);
        if(!hasLogin) {
            extraFunction.setVisibility(View.VISIBLE);
        }

    }

    public void showHomeTitleBarTitle(int title,boolean hasLogin) {
        setTitle(title);
        hideTitleBarAll();
        titleLeftArea.setVisibility(View.VISIBLE);
        titleRightArea.setVisibility(View.VISIBLE);
        homeOnlineHelp.setVisibility(View.VISIBLE);
        homePushMess.setVisibility(View.VISIBLE);
        changeLanguage.setVisibility(View.VISIBLE);
        if(!hasLogin) {
            extraFunction.setVisibility(View.VISIBLE);
        }

    }

    public void showBackTitleBarTitle(int title) {
        setTitle(title);
        hideTitleBarAll();
        titleLeftArea.setVisibility(View.VISIBLE);
        titleRightArea.setVisibility(View.VISIBLE);
        goBack.setVisibility(View.VISIBLE);
        toolbarHomeImage.setVisibility(View.VISIBLE);
        toolbarPhoneImage.setVisibility(View.VISIBLE);
    }

    public void showBackTitleBarTitle(String title) {
        setTitle(title);
        hideTitleBarAll();
        titleLeftArea.setVisibility(View.VISIBLE);
        titleRightArea.setVisibility(View.VISIBLE);
        goBack.setVisibility(View.VISIBLE);
        toolbarHomeImage.setVisibility(View.VISIBLE);
        toolbarPhoneImage.setVisibility(View.VISIBLE);
    }

    protected void setExtaTitle(int res) {
        if (extraFunction != null) {
            extraFunction.setText(res);
        }
    }

    protected void setExtaTitle(String res) {
        if (extraFunction != null) {
            extraFunction.setText(res);
        }
    }

    protected void setExtraImage(int res) {
        if (extraImage != null) {
            extraImage.setImageResource(res);
        }
    }


    protected void showHomeTitle(boolean showVoucher) {
        hideTitleBarAll();
        titleLeftArea.setVisibility(View.VISIBLE);
        titleRightArea.setVisibility(View.VISIBLE);
        homeOnlineHelp.setVisibility(View.VISIBLE);
        homePushMess.setVisibility(View.VISIBLE);
        changeLanguage.setVisibility(View.VISIBLE);
        if (showVoucher) {
            extraImage.setVisibility(View.VISIBLE);
            extraFunction.setVisibility(View.GONE);
        } else {
            extraImage.setVisibility(View.GONE);
            extraFunction.setVisibility(View.VISIBLE);
        }
    }

    private void hideTitleBarAll() {
        titleLeftArea.setVisibility(View.GONE);
        titleRightArea.setVisibility(View.GONE);
        goBack.setVisibility(View.GONE);
        homeOnlineHelp.setVisibility(View.GONE);
        homePushMess.setVisibility(View.GONE);
        changeLanguage.setVisibility(View.GONE);

        toolbarHomeImage.setVisibility(View.GONE);
        toolbarPhoneImage.setVisibility(View.GONE);
        toolbarSetting.setVisibility(View.GONE);
        mineMessageImage.setVisibility(View.GONE);
        extraImage.setVisibility(View.GONE);
        extraFunction.setVisibility(View.GONE);
    }

    private void adjustTitlePosition(String title) {
        if (titleBar != null) {
            TextPaint textPaint = titleBar.getPaint();

            textPaint.setTextSize(titleBar.getTextSize());
            float textPaintWidth = textPaint.measureText(title);

            DisplayMetrics metric = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metric);
            int width = metric.widthPixels;     // 屏幕宽度（像素）
            int height = metric.heightPixels;   // 屏幕高度（像素）
            float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
            int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）

            float halfTitle = (width - 30 * density) / 2;

            if (textPaintWidth > halfTitle) {
                textPaintWidth = halfTitle;
            }

            float areaWidth = (width - textPaintWidth - 30 * density) / 2;


            if (titleLeftArea != null && titleRightArea != null) {
                RelativeLayout.LayoutParams paramsLeft = (RelativeLayout.LayoutParams) titleLeftArea.getLayoutParams();
                RelativeLayout.LayoutParams paramsRight = (RelativeLayout.LayoutParams) titleRightArea.getLayoutParams();
                paramsLeft.width = (int) areaWidth;
                paramsRight.width = (int) areaWidth;
                titleLeftArea.setLayoutParams(paramsLeft);
                titleRightArea.setLayoutParams(paramsRight);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initView(View rootView) {

    }

    @Override
    protected void setBarColor(SystemBarTintManager tintManager) {
        tintManager.setStatusBarTintResource(R.mipmap.toolbar_bg);
    }

    public void addToolBarListener(View.OnClickListener toolBarListener) {
        toobarListenerList.add(toolBarListener);
    }
}
