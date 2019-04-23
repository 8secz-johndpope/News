package com.heaven.news.api;

import com.heaven.data.net.DataResponse;
import com.neusoft.szair.model.easycardmodel.EasyCardWebServiceServiceSoapBinding;
import com.neusoft.szair.model.easycardmodel.queryBankCardResponse;
import com.neusoft.szair.model.easycardmodel.walletInfoQueryResponse;
import com.neusoft.szair.model.flightproto.FlightSearchDomesticResultVO;
import com.neusoft.szair.model.flightsearch.FlightSearchWebServiceServiceSoapBinding;
import com.neusoft.szair.model.flightsearch.flightSearchDomesticResponse;
import com.neusoft.szair.model.member.CRMFrequentFlyerWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.member.queryMilesResponse;
import com.neusoft.szair.model.memberbase.MemberLoginWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.memberbase.loginNewResponse;
import com.neusoft.szair.model.noticelist.NoticeListWebServiceServiceSoapBinding;
import com.neusoft.szair.model.noticelist.queryNoticeListResponse;
import com.neusoft.szair.model.usercouponsearch.UserCouponSearchWebServiceServiceSoapBinding;
import com.neusoft.szair.model.usercouponsearch.queryUseCouponCntResponse;

import io.reactivex.Flowable;
import retrofit2.Call;
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
public interface LoginApi {
    @Headers({"Content-Type:text/xml; charset=utf-8"})
    @POST("memberLoginWebService?wsdl")
    Flowable<DataResponse<loginNewResponse>> login(@Body MemberLoginWebServiceImplServiceSoapBinding requestEnvelope);


    @Headers({"Content-Type:text/xml; charset=utf-8"})
    @POST("noticeListWebService?wsdl")
    Flowable<DataResponse<queryNoticeListResponse>> queryNoticeList(@Body NoticeListWebServiceServiceSoapBinding requestEnvelope);

    @Headers({"Content-Type:text/xml; charset=utf-8"})
    @POST("CRMFrequentFlyerWebService?wsdl")
    Flowable<DataResponse<queryMilesResponse>> queryMile(@Body CRMFrequentFlyerWebServiceImplServiceSoapBinding requestEnvelope);

    @Headers({"Content-Type:text/xml; charset=utf-8"})
    @POST("userCouponSearchWebService?wsdl")
    Flowable<DataResponse<queryUseCouponCntResponse>> queryUserCouponCount(@Body UserCouponSearchWebServiceServiceSoapBinding requestEnvelope);

    @Headers({"Content-Type:text/xml; charset=utf-8"})
    @POST("EasyCardWebService?wsdl")
    Flowable<DataResponse<walletInfoQueryResponse>> querywalletInfo(@Body EasyCardWebServiceServiceSoapBinding requestEnvelope);


    @Headers({"Content-Type:text/xml; charset=utf-8"})
    @POST("flightSearchWebService?wsdl")
    Flowable<DataResponse<flightSearchDomesticResponse>> searchFlight(@Body FlightSearchWebServiceServiceSoapBinding requestEnvelope);
}
