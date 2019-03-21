package com.heaven.news.ui.base;

import android.databinding.ViewDataBinding;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
public abstract class BaseToolBarSimpleActivity<VM extends BaseViewModel, B extends ViewDataBinding> extends BaseSimpleBindActivity<VM, B> {

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
}
