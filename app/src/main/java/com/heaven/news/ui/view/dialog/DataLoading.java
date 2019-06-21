package com.heaven.news.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.heaven.news.R;
import com.heaven.news.engine.manager.NetManager;

import java.util.Objects;

import io.reactivex.disposables.Disposable;

/**
 * FileName: com.heaven.news.ui.view.dialog.DataLoading.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-17 16:27
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class DataLoading extends Dialog {
    private ImageView waitingIcon;
    public ImageView cancel;
    private TextView noticeText;



    public DataLoading(Context context, long taskId, NetManager.OnCancleTaskListener cancleTaskListener ) {
        super(context, R.style.customerDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.net_req_dialog, null);
        waitingIcon = view.findViewById(R.id.waiting_icon);
        cancel = view.findViewById(R.id.cancel);
        noticeText = view.findViewById(R.id.notice_text);
        setContentView(view);
        // 加载动画
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.waiting_anim);
        waitingIcon.startAnimation(anim);
        cancel.setOnClickListener(v -> {
            if(cancleTaskListener != null) {
                cancleTaskListener.onCancel(taskId);
            }
            dismiss();
        });
        hideSystemUI(view);
    }

    //盖在状态栏上
    public void hideSystemUI(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }

    public void setNoticeText(String noticeText) {
        this.noticeText.setText(noticeText);
    }

    public void hideCancel() {
        cancel.setVisibility(View.GONE);
    }

}
