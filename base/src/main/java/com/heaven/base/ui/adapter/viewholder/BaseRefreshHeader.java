package com.heaven.base.ui.adapter.viewholder;

/**
 * 作者:Heaven
 * 时间: on 2017/4/24 10:46
 * 邮箱:heavenisme@aliyun.com
 */

public interface BaseRefreshHeader {
    int STATE_NORMAL = 0;
    int STATE_RELEASE_TO_REFRESH = 1;
    int STATE_REFRESHING = 2;
    int STATE_DONE = 3;

    void onMove(float delta);

    boolean releaseAction();

    void refreshComplete();
}
