package com.heaven.data.convert.szair;

import com.google.common.io.ByteStreams;
import com.heaven.data.net.DataResponse;
import com.neusoft.szair.model.soap.SOAPBinding;
import com.neusoft.szair.model.soap.SOAPEnvelope;
import com.neusoft.szair.model.soap.SOAPFault;
import com.neusoft.szair.model.soap.SOAPObject;

import org.simpleframework.xml.Serializer;

import java.io.IOException;
import java.util.zip.GZIPInputStream;

import javax.annotation.Nullable;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * FileName: com.heaven.data.convert.szair.SzAirResponseBodyConvert.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-04 12:21
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class SzAirResponseBodyConvert <T> implements Converter<ResponseBody, T> {
    SzAirRequestBodyConvert requestBodyConvert;


     SzAirResponseBodyConvert() {
    }

    public void setBinding(SzAirRequestBodyConvert requestBodyConvert) {
        this.requestBodyConvert = requestBodyConvert;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        try {

//           String repBody = new String(ByteStreams.toByteArray(new GZIPInputStream(value.byteStream())));
            SOAPEnvelope soapEnvelope = requestBodyConvert.getBinding().makeResponse(new GZIPInputStream(value.byteStream()));
            DataResponse response = new DataResponse();
            response.code = 0;
            if(soapEnvelope.bodyElements.size() == 1) {
             Object  result = soapEnvelope.bodyElements.get(0);
                if(result instanceof SOAPFault) {
                    SOAPFault fault = (SOAPFault)result;
                    response.code = 9;
                    response.reason = fault.getfaultstring();
                } else {
                    response.data = result;
                }
            }
            return (T) response;
        } catch (RuntimeException | IOException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            value.close();
        }
    }

}
