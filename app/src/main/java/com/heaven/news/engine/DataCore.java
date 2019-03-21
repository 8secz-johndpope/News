package com.heaven.news.engine;

/**
 * FileName: com.heaven.news.engine.DataCore.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-21 15:41
 *
 * @version V1.0 核心数据管理类
 */
public class DataCore {
    private static DataCore instance;

    private DataCore() {

    }

    public static DataCore getInstance() {
        if (instance == null) {
            synchronized (DataCore.class) {
                if (instance == null) {
                    instance = new DataCore();
                }
            }

        }
        return instance;
    }

    public void initLoginData() {

    }

}
