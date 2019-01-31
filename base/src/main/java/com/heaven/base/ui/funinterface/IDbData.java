package com.heaven.base.ui.funinterface;

import java.util.HashMap;

import io.reactivex.Flowable;
import io.realm.RealmResults;

/**
 * FileName: com.heaven.fly.base.methodinterface.IDbData.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-03-19 21:06
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
@FunctionalInterface
public interface IDbData {
    Flowable<RealmResults> getData(HashMap<String, Object> param);
}
