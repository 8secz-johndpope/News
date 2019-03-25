package com.heaven.data.convert.szair;

import android.support.annotation.NonNull;

import com.google.common.io.ByteStreams;
import com.heaven.data.net.DataResponse;
import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPEnvelope;
import com.neusoft.szair.model.soap.SOAPFault;
import com.neusoft.szair.model.soap.SOAPObject;

import org.simpleframework.xml.Serializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import javax.annotation.Nullable;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Converter;

/**
 * FileName: com.heaven.data.convert.szair.SzAirResponseBodyConvert.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-04 12:21
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class SzAirResponseBodyConvert<T> implements Converter<ResponseBody, T> {
    SzAirRequestBodyConvert requestBodyConvert;


    SzAirResponseBodyConvert() {
    }

    void setBinding(SzAirRequestBodyConvert requestBodyConvert) {
        this.requestBodyConvert = requestBodyConvert;
    }

    @Override
    public T convert(@NonNull ResponseBody responseBody) throws IOException {
        DataResponse response = new DataResponse();
        try {
            SOAPBinding binding = requestBodyConvert.getBinding();
            if (binding != null) {
//                BufferedSource source = responseBody.source();
//                Buffer buffer = source.buffer();
//                source.request(Long.MAX_VALUE); // Buffer the entire body.
//                String  repBody = new String(ByteStreams.toByteArray(new GZIPInputStream(buffer.inputStream())));
//                SOAPEnvelope soapEnvelope = binding.makeResponse(new ByteArrayInputStream(repBody.getBytes()));
                MediaType mediaType = responseBody.contentType();
//                SOAPEnvelope soapEnvelope = binding.makeResponse(responseBody.byteStream());
                SOAPEnvelope soapEnvelope = binding.makeResponse(new GZIPInputStream(responseBody.byteStream()));
                response.code = 0;
                if (soapEnvelope.bodyElements.size() == 1) {
                    Object result = soapEnvelope.bodyElements.get(0);
                    if (result instanceof SOAPFault) {
                        SOAPFault fault = (SOAPFault) result;
                        response.code = 9;
                        response.reason = fault.getfaultstring();
                    } else {
                        response.data = result;
                    }
                }
                return (T) response;
            }

        } catch (RuntimeException  e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            responseBody.close();
        }
        return (T) response;
    }

}
