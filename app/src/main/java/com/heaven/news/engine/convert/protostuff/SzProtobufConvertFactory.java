package com.heaven.news.engine.convert.protostuff;

import com.heaven.news.engine.convert.szair.SzAirRequestBodyConvert;

import org.simpleframework.xml.core.Persister;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * FileName: com.heaven.news.engine.convert.protostuff.SzProtobufConvertFactory.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-19 12:08
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class SzProtobufConvertFactory extends Converter.Factory{

    /** Create an instance using a default {@link Persister} instance for conversion. */
    public static SzProtobufConvertFactory create() {
        return createInstance();
    }

    /** Create an instance using {@code serializer} for conversion. */
    public static SzProtobufConvertFactory createInstance() {
        return new SzProtobufConvertFactory();
    }


    private SzAirRequestBodyConvert requestBodyConvert;

    private SzProtobufConvertFactory() {
    }


    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new ProtoBufResBodyConvert<>(type);
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (!(type instanceof Class)) {
            return null;
        }
        requestBodyConvert = new SzAirRequestBodyConvert<>();
        return requestBodyConvert;
    }

}
