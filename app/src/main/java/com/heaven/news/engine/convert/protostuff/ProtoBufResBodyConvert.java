package com.heaven.news.engine.convert.protostuff;

import android.support.annotation.NonNull;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * FileName: com.heaven.news.engine.convert.protostuff.ProtoBufResBodyConvert.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-19 12:11
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class ProtoBufResBodyConvert<T> implements Converter<ResponseBody, T> {
    @SuppressWarnings("unchecked")
    private Class<? super T> targetClass;

    @SuppressWarnings("unchecked")
    ProtoBufResBodyConvert(Type type) {
        this.targetClass = (Class<? super T>) $Gson$Types.getRawType(type);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public T convert(@NonNull ResponseBody responseBody) throws IOException {
        return null;
    }
}
