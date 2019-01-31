package com.heaven.base.ui.funinterface;



import com.heaven.data.net.DataResponse;

import java.util.List;

import io.reactivex.Flowable;

/**
 * FileName: com.heaven.fly.base.methodinterface.INetRemoteData.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-03-19 20:52
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
@FunctionalInterface
public interface INetRemoteData {
    Flowable<DataResponse<List>> getData(String...param);
}
