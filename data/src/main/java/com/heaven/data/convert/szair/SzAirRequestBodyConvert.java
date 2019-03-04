package com.heaven.data.convert.szair;



import com.neusoft.szair.model.soap.SOAPBinding;

import org.simpleframework.xml.Serializer;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.annotation.Nullable;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
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

    private final Serializer serializer;

    private SOAPBinding binding;

    SzAirRequestBodyConvert(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        String requestStr = "";
        if(value instanceof SOAPBinding) {
            binding = (SOAPBinding) value;
            requestStr = binding.makeRequest();
        }
        return RequestBody.create(MEDIA_TYPE, ByteString.encodeUtf8(requestStr));
//        try {
//            OutputStreamWriter osw = new OutputStreamWriter(buffer.outputStream(), CHARSET);
//            serializer.write(value, osw);
//            osw.flush();
//        } catch (RuntimeException | IOException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }

    public SOAPBinding getBinding() {
        return binding;
    }

    //    @Nullable
//    @Override
//    public RequestBody convert(T value) throws IOException {
//        String requestStr = "";
//        if(value instanceof SOAPBinding) {
//            requestStr = ((SOAPBinding) value).makeRequest();
//        }
//        return RequestBody.create(MEDIA_TYPE, ByteString.encodeUtf8(requestStr));
//    }
}
