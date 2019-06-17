package com.heaven.news.net.convert.protostuff;

import android.support.annotation.NonNull;

import com.google.gson.internal.$Gson$Types;
import com.heaven.data.net.DataResponse;
import com.heaven.data.util.ProtoStuffUtil;
import com.neusoft.szair.model.flightproto.FlightSearchDomesticResultVO;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * FileName: com.heaven.news.net.convert.protostuff.ProtoBufResBodyConvert.java
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
   public ProtoBufResBodyConvert(Type type) {
        this.targetClass = (Class<? super T>) $Gson$Types.getRawType(type);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public T convert(@NonNull ResponseBody responseBody) throws IOException {
        DataResponse response = new DataResponse();
        response.data = ProtoStuffUtil.deserializer(responseBody.source().inputStream(), FlightSearchDomesticResultVO.class);
        return (T) response;
    }
}
