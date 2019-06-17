package com.heaven.news.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.heaven.news.R;

/**
 * FileName: com.heaven.news.ui.view.dialog.NetReqDialog.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-17 16:27
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class NetReqDialog extends Dialog {
    public NetReqDialog(Context context) {
        super(context, R.style.Theme_Dialog);
    }

    public NetReqDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected NetReqDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
