package com.heaven.news.ui.base;

import android.databinding.ViewDataBinding;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
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

    LinearLayout title_left_area;
    ImageView home_online_help;
    ImageView home_push_mess;
    ImageView change_language;

    TextView titleBar;

    LinearLayout title_right_area;
    ImageView toolbar_home_image;
    ImageView toolbar_phone_image;
    ImageView mine_message_image;
    ImageView extra_image;
    TextView extra_function;

    @Override
    public int iniTitleBarResId() {
        return R.layout.title_bar;
    }

    @Override
    public void initTitle(View titleView) {
        super.initTitle(titleView);
        title_left_area = titleView.findViewById(R.id.title_left_area);
        home_online_help = titleView.findViewById(R.id.home_online_help);
        home_push_mess = titleView.findViewById(R.id.home_push_mess);
        change_language = titleView.findViewById(R.id.change_language);

        titleBar = titleView.findViewById(R.id.title);

        title_right_area = titleView.findViewById(R.id.title_right_area);
        toolbar_home_image = titleView.findViewById(R.id.toolbar_home_image);
        toolbar_phone_image = titleView.findViewById(R.id.toolbar_phone_image);
        mine_message_image = titleView.findViewById(R.id.mine_message_image);
        extra_image = titleView.findViewById(R.id.extra_image);
        extra_function = titleView.findViewById(R.id.extra_function);
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

    protected void setExtaTitle(int res) {
        if (extra_function != null) {
            extra_function.setText(res);
            extra_function.setVisibility(View.VISIBLE);
        }
    }

    protected void setExtraImage(int res) {
        if (extra_image != null) {
            extra_image.setImageResource(res);
        }
    }

    protected void showOnlyTitle() {
        title_left_area.setVisibility(View.GONE);
        title_right_area.setVisibility(View.GONE);
    }

    protected void showHomeTitle(boolean showVoucher) {
        title_left_area.setVisibility(View.VISIBLE);
        title_right_area.setVisibility(View.VISIBLE);
        home_online_help.setVisibility(View.VISIBLE);
        home_push_mess.setVisibility(View.VISIBLE);
        change_language.setVisibility(View.VISIBLE);

        toolbar_home_image.setVisibility(View.GONE);
        toolbar_phone_image.setVisibility(View.GONE);
        mine_message_image.setVisibility(View.GONE);
        if (showVoucher) {
            extra_image.setVisibility(View.VISIBLE);
            extra_function.setVisibility(View.GONE);
        } else {
            extra_image.setVisibility(View.GONE);
            extra_function.setVisibility(View.VISIBLE);
        }
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

            float areaWidth = (width - textPaintWidth - 30 * density) / 2;

            if (title_left_area != null && title_right_area != null) {
                RelativeLayout.LayoutParams paramsLeft = (RelativeLayout.LayoutParams) title_left_area.getLayoutParams();
                RelativeLayout.LayoutParams paramsRight = (RelativeLayout.LayoutParams) title_right_area.getLayoutParams();
                paramsLeft.width = (int) areaWidth;
                paramsRight.width = (int) areaWidth;
                title_left_area.setLayoutParams(paramsLeft);
                title_right_area.setLayoutParams(paramsRight);
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
