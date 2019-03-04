package com.heaven.data.convert.szair;

import com.neusoft.szair.model.soap.SOAPBinding;

import org.simpleframework.xml.Serializer;
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
public class SzAirConvertFactory extends Converter.Factory{

    /** Create an instance using a default {@link Persister} instance for conversion. */
    public static SzAirConvertFactory create() {
        return create(new Persister());
    }

    /** Create an instance using {@code serializer} for conversion. */
    public static SzAirConvertFactory create(Serializer serializer) {
        return new SzAirConvertFactory(serializer, true);
    }

    /** Create an instance using a default {@link Persister} instance for non-strict conversion. */
    public static SzAirConvertFactory createNonStrict() {
        return createNonStrict(new Persister());
    }

    /** Create an instance using {@code serializer} for non-strict conversion. */
    @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
    public static SzAirConvertFactory createNonStrict(Serializer serializer) {
        if (serializer == null) {
            throw new NullPointerException("serializer == null");
        }
        return new SzAirConvertFactory(serializer, false);
    }

    private final Serializer serializer;
    private final boolean strict;

    private SOAPBinding binding;
    SzAirRequestBodyConvert requestBodyConvert;
    SzAirResponseBodyConvert responseBodyConvert;

    private SzAirConvertFactory(Serializer serializer, boolean strict) {
        this.serializer = serializer;
        this.strict = strict;
        requestBodyConvert = new SzAirRequestBodyConvert<>(serializer);
    }

    public boolean isStrict() {
        return strict;
    }


    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (!(type instanceof Class)) {
            return null;
        }
        Class<?> cls = (Class<?>) type;
        responseBodyConvert = new SzAirResponseBodyConvert<>(cls, serializer, strict);
        responseBodyConvert.setBinding(requestBodyConvert);
        return responseBodyConvert;
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (!(type instanceof Class)) {
            return null;
        }
        return requestBodyConvert;
    }

}
