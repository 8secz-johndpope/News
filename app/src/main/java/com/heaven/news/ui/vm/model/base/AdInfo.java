package com.heaven.news.ui.vm.model.base;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * FileName: com.heaven.news.ui.vm.model.base.AdInfo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-13 12:24
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
public class AdInfo implements Serializable {
    private static final long serialVersionUID = 3240490919279217334L;
    public boolean isVideo;
    public String urlVideo;
    public String urlImage;
    public String content;

    @Override
    public String toString() {
        return "AdInfo{" +
                "isVideo=" + isVideo +
                ", urlVideo='" + urlVideo + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
