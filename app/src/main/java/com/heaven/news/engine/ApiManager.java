package com.heaven.news.engine;

/**
 * FileName: com.heaven.flybetter.engine.ApiManager.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-06-23 21:51
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class ApiManager {
    private static ApiManager instance;

    private ApiManager(){

    }

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }

    public static <T> T getApi(Class<T> apiClass){
        return AppEngine.getInstance().getNetApi(apiClass);
    }
}
