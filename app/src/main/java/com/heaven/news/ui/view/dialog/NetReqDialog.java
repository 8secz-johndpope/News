package com.heaven.news.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.heaven.news.R;

import io.reactivex.disposables.Disposable;

/**
 * FileName: com.heaven.news.ui.view.dialog.NetReqDialog.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-17 16:27
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class NetReqDialog extends Dialog {
    private long taskId;
    private ImageView waitingIcon;
    public ImageView cancel;
    private TextView noticeText;

    public NetReqDialog(Context context, Disposable disposable) {
        super(context, R.style.Theme_Dialog);
        this.taskId = taskId;
        View view = LayoutInflater.from(context).inflate(R.layout.net_req_dialog, null);
        waitingIcon = view.findViewById(R.id.waiting_icon);
        cancel = view.findViewById(R.id.cancel);
        noticeText = view.findViewById(R.id.notice_text);
        setContentView(view);
        // 加载动画
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.waiting_anim);
        waitingIcon.startAnimation(anim);
        cancel.setOnClickListener(v -> {
            if(disposable != null) {
                disposable.dispose();
            }
        });
    }

    public void setNoticeText(String noticeText) {
        this.noticeText.setText(noticeText);
    }

    public void hideCancel() {
        cancel.setVisibility(View.GONE);
    }

    public long getTaskId() {
        return taskId;
    }
}
