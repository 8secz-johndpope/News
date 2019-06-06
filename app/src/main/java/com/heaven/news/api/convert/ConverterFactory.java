package com.heaven.news.api.convert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * FileName: com.heaven.news.api.convert.ConverterFactory.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-06 13:33
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class ConverterFactory extends Converter.Factory {

    /** Create an instance using a default {@link Persister} instance for conversion. */
    public static ConverterFactory create() {
        return createInstance();
    }

    /** Create an instance using {@code serializer} for conversion. */
    public static ConverterFactory createInstance() {
        return new ConverterFactory();
    }


    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        return super.responseBodyConverter(type, annotations, retrofit);
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }
}
