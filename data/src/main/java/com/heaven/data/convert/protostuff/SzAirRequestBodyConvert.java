package com.heaven.data.convert.protostuff;



import com.neusoft.szair.model.soap.SOAPBinding;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.ByteString;
import retrofit2.Converter;

/**
 * FileName: com.heaven.data.convert.szair.SzAirRequestBodyConvert.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-04 12:20
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class SzAirRequestBodyConvert<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.get("text/xml; charset=UTF-8");
    private static final String CHARSET = "UTF-8";


    private SOAPBinding binding;

    public SzAirRequestBodyConvert() {
    }

    @Override public RequestBody convert(T value) throws IOException {
        String requestStr = "";
        if(value instanceof SOAPBinding) {
            binding = (SOAPBinding) value;
            requestStr = binding.makeRequest();
        }
        return RequestBody.create(MEDIA_TYPE, ByteString.encodeUtf8(requestStr));
    }

    public SOAPBinding getBinding() {
        return binding;
    }
}
