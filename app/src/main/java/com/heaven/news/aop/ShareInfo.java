package com.heaven.news.aop;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * FileName: com.heaven.flybetter.aop.ShareInfo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-09-05 20:16
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class ShareInfo implements Parcelable{
//    SendMessageToWX.Req.WXSceneSession；分享到微信好友
//    SendMessageToWX.Req.WXSceneTimeline；分享到微信朋友圈
//    SendMessageToWX.Req.WXSceneFavorite；添加到微信收藏夹

    public static final int WX_FRIEND = 0;//分享到微信好友
    public static final int WX_SQUARE = 1;//分享到微信朋友圈
    public static final int WX_COLLECT = 2;//添加到微信收藏夹

    public static final int WX_TXT = 0;//文本
    public static final int WX_IMG = 1;//图片
    public static final int WX_MUSIC = 2;//音乐
    public static final int WX_VIDEO = 3;//视频
    public static final int WX_WEB = 4;//网页
    public static final int WX_APP_INFO = 5;//app信息
    public static final int WX_EMOJI = 6;//表情


    public int wx_type = WX_FRIEND;
    public int wx_send_ype = WX_TXT;

    public String tag;
    public String url;
    public String title;
    public String subTitle;
    public String description;
    public String message;
    public int    rImageId;
    public String imagePath;
    public int rThumbBmpId;
    public String thumbBmpPath;
    public Bitmap bitmap;
    public String musicUrl;
    public String videoUrl;
    public String webpageUrl;
    public String emojiPath;



    @Override
    public String toString() {
        return "ShareInfo{" +
                "wx_type=" + wx_type +
                ", wx_send_ype=" + wx_send_ype +
                ", tag='" + tag + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", description='" + description + '\'' +
                ", message='" + message + '\'' +
                ", rImageId=" + rImageId +
                ", imagePath='" + imagePath + '\'' +
                ", bitmap=" + bitmap +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.wx_type);
        dest.writeInt(this.wx_send_ype);
        dest.writeString(this.tag);
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeString(this.subTitle);
        dest.writeString(this.description);
        dest.writeString(this.message);
        dest.writeInt(this.rImageId);
        dest.writeString(this.imagePath);
        dest.writeInt(this.rThumbBmpId);
        dest.writeString(this.thumbBmpPath);
        dest.writeParcelable(this.bitmap, flags);
        dest.writeString(this.musicUrl);
        dest.writeString(this.videoUrl);
        dest.writeString(this.webpageUrl);
        dest.writeString(this.emojiPath);
    }

    public ShareInfo() {
    }

    protected ShareInfo(Parcel in) {
        this.wx_type = in.readInt();
        this.wx_send_ype = in.readInt();
        this.tag = in.readString();
        this.url = in.readString();
        this.title = in.readString();
        this.subTitle = in.readString();
        this.description = in.readString();
        this.message = in.readString();
        this.rImageId = in.readInt();
        this.imagePath = in.readString();
        this.rThumbBmpId = in.readInt();
        this.thumbBmpPath = in.readString();
        this.bitmap = in.readParcelable(Bitmap.class.getClassLoader());
        this.musicUrl = in.readString();
        this.videoUrl = in.readString();
        this.webpageUrl = in.readString();
        this.emojiPath = in.readString();
    }

    public static final Creator<ShareInfo> CREATOR = new Creator<ShareInfo>() {
        @Override
        public ShareInfo createFromParcel(Parcel source) {
            return new ShareInfo(source);
        }

        @Override
        public ShareInfo[] newArray(int size) {
            return new ShareInfo[size];
        }
    };
}
