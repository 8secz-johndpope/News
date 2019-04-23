package com.heaven.news.api;

import com.heaven.data.net.DataResponse;
import com.neusoft.szair.model.flightproto.FlightSearchDomesticResultVO;
import com.neusoft.szair.model.flightsearch.FlightSearchWebServiceServiceSoapBinding;
import com.neusoft.szair.model.flightsearch.flightSearchDomesticResponse;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * FileName: com.heaven.news.api.LoginApi.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-28 13:11
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public interface FlightApi {
    @Headers({"Content-Type:text/xml; charset=utf-8"})
    @POST("flightSearchWebService?wsdl")
    Flowable<DataResponse<flightSearchDomesticResponse>> searchFlightXml(@Body FlightSearchWebServiceServiceSoapBinding requestEnvelope);
}
