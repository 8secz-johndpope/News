package com.heaven.data.convert.protostuff;

import org.simpleframework.xml.core.Persister;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * FileName: com.heaven.data.convert.szair.SzAirConvertFactory.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-04 12:14
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class SzAirConvertFactory extends Converter.Factory {

    /** Create an instance using a default {@link Persister} instance for conversion. */
    public static SzAirConvertFactory create() {
        return createInstance();
    }

    /** Create an instance using {@code serializer} for conversion. */
    public static SzAirConvertFactory createInstance() {
        return new SzAirConvertFactory();
    }


    private SzAirRequestBodyConvert requestBodyConvert;

    private SzAirConvertFactory() {
    }


    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        SzAirResponseBodyConvert responseBodyConvert = new SzAirResponseBodyConvert<>();
        responseBodyConvert.setBinding(requestBodyConvert);
        return responseBodyConvert;
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
