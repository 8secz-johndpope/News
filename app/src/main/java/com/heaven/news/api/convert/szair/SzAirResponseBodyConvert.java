package com.heaven.news.api.convert.szair;

import android.support.annotation.NonNull;

import com.heaven.data.net.DataResponse;
import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPEnvelope;
import com.neusoft.szair.model.soap.SOAPFault;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * FileName: com.heaven.news.api.convert.szair.SzAirResponseBodyConvert.java
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
                SOAPEnvelope soapEnvelope = binding.makeResponse(responseBody.byteStream());
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
