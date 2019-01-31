package com.heaven.data.net;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者:Heaven
 * 时间: on 2017/8/15 16:46
 * 邮箱:heavenisme@aliyun.com
 */

public class CacheInterceptor implements Interceptor {
    private NetStateWatcher netStateWatcher;

    public CacheInterceptor(Context context) {
        netStateWatcher = NetStateWatcher.init(context);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request =  chain.request();
        Request.Builder builder = request.newBuilder();
        if (!netStateWatcher.isNetAvaliable() && NetGlobalConfig.CACHE_ABLE) {
            final CacheControl cacheControl = new CacheControl.Builder()
                    .onlyIfCached()
                    .maxStale(NetGlobalConfig.MAX_STALE,TimeUnit.SECONDS)
                    .build();

            request = builder
                    .cacheControl(cacheControl)
                    .build();
        } else {
            final CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.noCache();
            builder.cacheControl(cacheBuilder.build());
        }

        return chain.proceed(request);
    }

    /**
     * 完全安装http的缓存规范来操作缓存,只在CacheStrategy.DEFAULT时起作用,只对get请求起作用.
     * @param builder
     * @param info
     * @param <E>
     */
//    private <E> void cacheControl(Request.Builder builder) {
//
//        if(info.cacheMode == CacheStrategy.DEFAULT){
//            final CacheControl.Builder cacheBuilder = new CacheControl.Builder();
//            cacheBuilder.maxAge((int) info.cacheMaxAge, TimeUnit.MILLISECONDS);
//            CacheControl cache = cacheBuilder.build();
//            builder.cacheControl(cache);
//            return;
//        }else {
//            final CacheControl.Builder cacheBuilder = new CacheControl.Builder();
//            cacheBuilder.noCache();
//            builder.cacheControl(cacheBuilder.build());
//        }
//
//
//    }
}
