package com.heaven.news.api;

import com.heaven.data.net.DataResponse;
import com.neusoft.szair.model.easycardmodel.EasyCardWebServiceServiceSoapBinding;
import com.neusoft.szair.model.easycardmodel.walletInfoQueryResponse;
import com.neusoft.szair.model.flightsearch.FlightSearchWebServiceServiceSoapBinding;
import com.neusoft.szair.model.flightsearch.flightSearchDomesticResponse;
import com.neusoft.szair.model.fullchannel.SearchFullchannelWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.fullchannel.searchByTraceResponse;
import com.neusoft.szair.model.member.CRMFrequentFlyerWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.member.queryMilesResponse;
import com.neusoft.szair.model.memberbase.MemberLoginWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.memberbase.loginNewResponse;
import com.neusoft.szair.model.noticelist.NoticeListWebServiceServiceSoapBinding;
import com.neusoft.szair.model.noticelist.queryNoticeListResponse;
import com.neusoft.szair.model.usercouponsearch.UserCouponSearchWebServiceServiceSoapBinding;
import com.neusoft.szair.model.usercouponsearch.queryUseCouponCntResponse;

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
public interface Api {
    @Headers({"Content-Type:text/xml; charset=utf-8"})
    @POST("searchFullchannelWebService?wsdl")
    Flowable<DataResponse<searchByTraceResponse>> searchByTrace(@Body SearchFullchannelWebServiceImplServiceSoapBinding requestEnvelope);


}
