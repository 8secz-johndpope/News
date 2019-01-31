package com.heaven.base.ui.funinterface;

import java.util.ArrayList;

import io.realm.RealmObject;

/**
 * FileName: com.heaven.fly.base.entity.DataList.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-03-19 20:57
 *
 * @version V1.0
 */
public class DataList<T extends RealmObject> {
    public ArrayList<T> results;
}
