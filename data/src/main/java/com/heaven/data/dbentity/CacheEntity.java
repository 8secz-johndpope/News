package com.heaven.data.dbentity;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 作者:Heaven
 * 时间: on 2017/4/7 10:49
 * 邮箱:heavenisme@aliyun.com
 */
public class CacheEntity extends RealmObject {

    public long createTime;//时间戳
    @PrimaryKey
    @Index
    public String key;
    @Required
    public byte[] cacheObject;
}
