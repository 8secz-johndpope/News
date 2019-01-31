package com.heaven.data.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


/**
 * 作者：Heaven
 * 时间: on 2016/10/18 15:12
 * 邮箱：heavenisme@aliyun.com
 */

public class NetStateWatcher {
    private static NetStateWatcher instance;

    private int netWorkState = NETWORK_NONE;
    /**
     * 没有连接网络
     */
    private static final int NETWORK_NONE = -1;
    /**
     * 移动网络
     */
    private static final int NETWORK_MOBILE = 0;
    /**
     * 无线网络
     */
    private static final int NETWORK_WIFI = 1;

    private static Context mContext;

    NetworkInfo activeNetworkInfo;

    public static NetStateWatcher init(Context context) {
        if (instance == null) {
            mContext = context;
            instance = new NetStateWatcher(context);
        }
        return instance;
    }

    private NetStateWatcher(Context context) {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(netReceiver, filter);
        netWorkState = initNetWorkState();
    }

    BroadcastReceiver netReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 如果相等的话就说明网络状态发生了变化
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                netWorkState = initNetWorkState();
                // 接口回调传过去状态的类型
                if (netWorkState == NETWORK_NONE) {
                    Toast.makeText(mContext, "网络状态不可用", Toast.LENGTH_SHORT).show();
                } else if (netWorkState == NETWORK_MOBILE) {
                    Toast.makeText(mContext, "移动网络状态", Toast.LENGTH_SHORT).show();
                } else if (netWorkState == NETWORK_WIFI) {
                    Toast.makeText(mContext, "wifi网络状态", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    /**
     * 初始化网络状态
     *
     * @return 返回网络状态
     */
    private int initNetWorkState() {
        // 得到连接管理器对象
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                return NETWORK_WIFI;
            } else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                return NETWORK_MOBILE;
            }
        } else {
            return NETWORK_NONE;
        }
        return NETWORK_NONE;
    }

    /**
     * 取得当前的网络状态
     *
     * @return 网络是否可用
     */
    public boolean isNetAvaliable() {
        boolean isNetOk = netWorkState != NETWORK_NONE;
        return isNetOk;
    }

}


