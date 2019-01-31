package com.heaven.model.ifilm;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by LeoLu on 2016/12/11.
 * 其实返回的数据和 OrderItem 一样 就是简化了一些
 *
 * @see OrderItem
 */
public class ChatModel implements Parcelable {

    private String orderNo;
    private int userId;
    private String filmTitle;
    private String filmTheme;
    private String filmThemeTitle;
    private int editorId;
    private String editorName;
    private String editorAvatar;
    private String coverUrl;
    private int unreadMessageCount;
    private String lastContent;
    private long lastTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getFilmTheme() {
        return filmTheme;
    }

    public void setFilmTheme(String filmTheme) {
        this.filmTheme = filmTheme;
    }

    public String getFilmThemeTitle() {
        return filmThemeTitle;
    }

    public void setFilmThemeTitle(String filmThemeTitle) {
        this.filmThemeTitle = filmThemeTitle;
    }

    public int getEditorId() {
        return editorId;
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public String getEditorAvatar() {
        return editorAvatar;
    }

    public void setEditorAvatar(String editorAvatar) {
        this.editorAvatar = editorAvatar;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public int getUnreadMessageCount() {
        return unreadMessageCount;
    }

    public void setUnreadMessageCount(int unreadMessageCount) {
        this.unreadMessageCount = unreadMessageCount;
    }

    public String getLastContent() {
        return lastContent;
    }

    public void setLastContent(String lastContent) {
        this.lastContent = lastContent;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderNo);
        dest.writeInt(this.userId);
        dest.writeString(this.filmTitle);
        dest.writeString(this.filmTheme);
        dest.writeString(this.filmThemeTitle);
        dest.writeInt(this.editorId);
        dest.writeString(this.editorName);
        dest.writeString(this.editorAvatar);
        dest.writeString(this.coverUrl);
        dest.writeInt(this.unreadMessageCount);
        dest.writeString(this.lastContent);
        dest.writeLong(this.lastTime);
    }

    public ChatModel() {
    }

    protected ChatModel(Parcel in) {
        this.orderNo = in.readString();
        this.userId = in.readInt();
        this.filmTitle = in.readString();
        this.filmTheme = in.readString();
        this.filmThemeTitle = in.readString();
        this.editorId = in.readInt();
        this.editorName = in.readString();
        this.editorAvatar = in.readString();
        this.coverUrl = in.readString();
        this.unreadMessageCount = in.readInt();
        this.lastContent = in.readString();
        this.lastTime = in.readLong();
    }

    public static final Creator<ChatModel> CREATOR = new Creator<ChatModel>() {
        @Override
        public ChatModel createFromParcel(Parcel source) {
            return new ChatModel(source);
        }

        @Override
        public ChatModel[] newArray(int size) {
            return new ChatModel[size];
        }
    };
}
