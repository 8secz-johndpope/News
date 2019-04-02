package com.heaven.news.ui.vm.model.base;

/**
 * FileName: com.heaven.news.ui.vm.model.base.UpdateInfo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-24 09:43
 *
 * @version V1.0 升级信息
 */
public class UpdateInfo {
    public boolean isNetError;
    public String reason;
    public long requestTime;
    public boolean isServiceMainta;
    public boolean needUpdate;
    public boolean isForceUpdate;
    public String updateUrl;
    public String updateMessage;
    public boolean nextGuidePage = false;
    public boolean isShowAd = false;
    public AdInfo adInfo;

    {
        adInfo = new AdInfo();
        adInfo.isVideo = false;
        adInfo.urlImage = "http://img0.imgtn.bdimg.com/it/u=1344159241,3681424911&fm=26&gp=0.jpg";
        adInfo.urlVideo = "";
        adInfo.content = "百思不得姐减肥肯定是怕几点睡激动是怕";
    }

    @Override
    public String toString() {
        return "UpdateInfo{" +
                "isSuccess=" + isNetError +
                ", reason='" + reason + '\'' +
                ", requestTime=" + requestTime +
                ", isServiceMainta=" + isServiceMainta +
                ", needUpdate=" + needUpdate +
                ", isForceUpdate=" + isForceUpdate +
                ", updateUrl='" + updateUrl + '\'' +
                ", updateMessage='" + updateMessage + '\'' +
                ", nextGuidePage=" + nextGuidePage +
                ", isShowAd=" + isShowAd +
                ", adInfo=" + adInfo +
                '}';
    }
}
