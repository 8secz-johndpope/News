package com.heaven.news.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.heaven.news.BuildConfig;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * FileName: com.heaven.flybetter.receiver.WXAppRegisterReceiver.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-09-05 21:34
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class WXAppRegisterReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.unregisterApp();
        // 将该app注册到微信
        msgApi.registerApp(BuildConfig.WX_APP_ID);
    }
}
