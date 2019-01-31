package com.heaven.base.ui.funinterface;

import com.heaven.service.aidl.MediaFolder;

import java.util.List;

import io.reactivex.Flowable;

/**
 * FileName: com.heaven.flybetter.base.methodinterface.IServiceMediaData.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-04-09 16:13
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
@FunctionalInterface
public interface IServiceMediaData {
    Flowable<List<MediaFolder>> getFolders();
}
