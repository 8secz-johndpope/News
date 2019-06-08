package com.heaven.news.api.convert;

import com.heaven.news.api.convert.fastjson.FastJsonnConverterFactory;
import com.heaven.news.api.convert.protostuff.ProtoBufResBodyConvert;
import com.heaven.news.api.convert.szair.SzAirRequestBodyConvert;
import com.heaven.news.api.convert.szair.SzAirResponseBodyConvert;

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
    private SzAirRequestBodyConvert<Object> requestBodyConvert;


    /** Create an instance using a default {@link } instance for conversion. */
    public static ConverterFactory create() {
        return createInstance();
    }

    /** Create an instance using {@code serializer} for conversion. */
    public static ConverterFactory createInstance() {
        return new ConverterFactory();
    }


    @Nullable
    @Override
    public Converter<ResponseBody, Object> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        ProtoType protoType = null;
        if (annotations != null && annotations.length > 0) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof ProtoType) {
                    protoType = (ProtoType) annotation;
                    break;
                }
            }
        }
        Converter<ResponseBody, Object> resBodyConvert = null;
        if (protoType != null) {
            if(ProtoType.JSON == protoType.resType()) {
                resBodyConvert =  FastJsonnConverterFactory.create().responseBodyConverter(type,annotations,retrofit);
            } else if(ProtoType.XML == protoType.resType()) {
                resBodyConvert =  new SzAirResponseBodyConvert<Object>(requestBodyConvert);
            } else if(ProtoType.PROTO == protoType.resType()) {
                resBodyConvert = new ProtoBufResBodyConvert<Object>(type);
            }
        } else {
            resBodyConvert =  new SzAirResponseBodyConvert<>(requestBodyConvert);
        }

        return resBodyConvert;
    }

    @Nullable
    @Override
    public Converter<Object, RequestBody> requestBodyConverter(Type type, Annotation[] annotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        ProtoType protoType = null;
        if (annotations != null && annotations.length > 0) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof ProtoType) {
                    protoType = (ProtoType) annotation;
                    break;
                }
            }
        }
        Converter<Object, RequestBody> reqBodyConvert = null;
        if (protoType != null) {
            if(ProtoType.JSON == protoType.resType()) {
                reqBodyConvert = FastJsonnConverterFactory.create().requestBodyConverter(type,annotations,methodAnnotations,retrofit);
            } else if(ProtoType.XML == protoType.resType()) {
                if (!(type instanceof Class)) {
                    return null;
                }
                reqBodyConvert = requestBodyConvert =new SzAirRequestBodyConvert<>();
            } else if(ProtoType.PROTO == protoType.resType()) {

            }
        } else {
            if (!(type instanceof Class)) {
                return null;
            }
            reqBodyConvert = requestBodyConvert = new SzAirRequestBodyConvert<>();
        }



        return reqBodyConvert;
    }
}
