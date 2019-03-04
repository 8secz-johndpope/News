package com.heaven.data.convert.szair;

import com.google.common.io.ByteStreams;
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
    private final Class<T> cls;
    private final Serializer serializer;
    private final boolean strict;
    SzAirRequestBodyConvert requestBodyConvert;

    SzAirResponseBodyConvert(Class<T> cls, Serializer serializer, boolean strict) {
        this.cls = cls;
        this.serializer = serializer;
        this.strict = strict;
    }

    public void setBinding(SzAirRequestBodyConvert requestBodyConvert) {
        this.requestBodyConvert = requestBodyConvert;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        try {

//           String repBody = new String(ByteStreams.toByteArray(new GZIPInputStream(value.byteStream())));
            SOAPEnvelope soapEnvelope = requestBodyConvert.getBinding().makeResponse(new GZIPInputStream(value.byteStream()));

            T realResult;

            if(soapEnvelope.bodyElements.size() == 1) {
             Object  result = soapEnvelope.bodyElements.get(0);
                if(result instanceof SOAPFault) {
                   SOAPObject faltResult = (SOAPObject) cls.newInstance();
                    SOAPFault fault = (SOAPFault)result;
                    java.lang.Exception exception = new java.lang.Exception(fault.getfaultcode(),new Throwable(fault.getfaultstring()));
                    faltResult.setexception(exception);
                    realResult = (T) faltResult;
                } else {
                    realResult = (T) result;
                }
            } else {
                realResult = (T) cls.newInstance();
            }


            return realResult;
        } catch (RuntimeException | IOException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            value.close();
        }
    }

}
