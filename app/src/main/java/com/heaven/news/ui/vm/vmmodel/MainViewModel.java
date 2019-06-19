package com.heaven.news.ui.vm.vmmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.view.View;

import com.heaven.data.manager.DataSource;
import com.heaven.data.net.DataResponse;
import com.heaven.news.consts.Constants;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.manager.DataCoreManager;
import com.neusoft.szair.model.flightproto.FlightSearchDomesticResultVO;
import com.neusoft.szair.model.flightsearch.FlightSearchWebServiceServiceSoapBinding;
import com.neusoft.szair.model.flightsearch.flightSearchDomestic;
import com.neusoft.szair.model.flightsearch.flightSearchDomesticConditionVO;
import com.neusoft.szair.model.flightsearch.tripInfoVO;
import com.neusoft.szair.model.fullchannel.SearchFullchannelWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.fullchannel.fullchannelVO;
import com.neusoft.szair.model.fullchannel.searchByTrace;
import com.neusoft.szair.model.fullchannel.searchByTraceConditionVO;
import com.neusoft.szair.model.fullchannel.traceParamVO;
import com.neusoft.szair.model.noticelist.NoticeListWebServiceServiceSoapBinding;
import com.neusoft.szair.model.noticelist.noticeInfoListVO;
import com.neusoft.szair.model.noticelist.queryNoticeList;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * FileName: com.heaven.news.ui.vm.viewmodel.MainViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-25 16:45
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class MainViewModel extends AbstractViewModel {
    public List<noticeInfoListVO> noticeList;

    public EasyGoSearch easyGoSearch;
    private final MutableLiveData<List<noticeInfoListVO>> noticeListLive = new MutableLiveData<>();
    private final MutableLiveData<List<fullchannelVO>> routeListLive = new MutableLiveData<>();
    public final MutableLiveData<DataResponse<FlightSearchDomesticResultVO>> flightListLive = new MutableLiveData<>();


    @Override
    public void initModel() {
        noticeList = AppEngine.instance().getDataSource().getCacheEntity(DataSource.DISK,Constants.NOTICE);
        easyGoSearch = new EasyGoSearch();
        searchUserRoute(1);
    }

    /**
     * 请求首页公告
     */
    public void requestNotice() {
        queryNoticeList noticelist = new queryNoticeList();

        noticelist._PAGE_NO = 0;
        noticelist._PAGE_COUNT = 3;
        NoticeListWebServiceServiceSoapBinding binding = new NoticeListWebServiceServiceSoapBinding("queryNoticeList",noticelist);
        mNetManager.getResult(mApi.queryNoticeList(binding), dataResponse -> {
            if(dataResponse.code == 0 && dataResponse.data != null) {
                if(dataResponse.data._NOTICE_INFO_LIST != null && dataResponse.data._NOTICE_INFO_LIST._NOTICE_INFO_LIST != null) {
                    noticeList = dataResponse.data._NOTICE_INFO_LIST._NOTICE_INFO_LIST;
                    noticeListLive.setValue(noticeList);
                    AppEngine.instance().getDataSource().cacheData(DataSource.DISK, Constants.NOTICE,noticeList);
                }
            }
        });
    }


    /**
     * 航班查询
     * @param view 查询view
     */
    public long flightSearch(View view,LifecycleOwner owner,Observer<DataResponse<FlightSearchDomesticResultVO>> observer) {
        Logger.i("flightSearch------");
        flightListLive.observe(owner,observer);
        List<tripInfoVO> flightLIst = new ArrayList<>();
        tripInfoVO flightVo = new tripInfoVO();
        flightVo._ORG_CITY = "SZX";
        flightVo._DST_CITY = "PEK";
        flightVo._DEPARTURE_DATE = "2019-06-25";
        flightVo._INDEX = "1";
        flightLIst.add(flightVo);
        flightSearchDomestic req = new flightSearchDomestic();
        req._FLIGHT_SEARCH_CONDITION = new flightSearchDomesticConditionVO();
        req._FLIGHT_SEARCH_CONDITION._TRIP_INFO_LIST = flightLIst;
        req._FLIGHT_SEARCH_CONDITION._QUERY_FLAG = "DC";
        req._FLIGHT_SEARCH_CONDITION._USER_ID = AppEngine.instance().dataCore().getCoreDataWrapper().userId;
        req._FLIGHT_SEARCH_CONDITION._CRM_MEMBER_ID = AppEngine.instance().dataCore().getCoreDataWrapper().crmId;

        FlightSearchWebServiceServiceSoapBinding binding = new FlightSearchWebServiceServiceSoapBinding("flightSearchDomestic",req);
        long startNanos = System.nanoTime();
        long taskId = mNetManager.getResult(mApi.searchFlight(binding), response -> {
            flightListLive.setValue(response);
            long stopNanos = System.nanoTime();
            Logger.i("mainmodel_time:proto" + TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos));
        });


//        long startNanos1 = System.nanoTime();
//        RxRepUtils.instance().getResult(AppEngine.instance().api().getApi(BuildConfig.ROOT_URL, FlightApi.class).searchFlightXml(binding), response -> {
//            long stopNanos1 = System.nanoTime();
//            Logger.i("mainmodel_time:xml" + TimeUnit.NANOSECONDS.toMillis(stopNanos1 - startNanos1));
//        });
        return taskId;
    }

    public void searchUserRoute(int pageIndex) {
        DataCoreManager dataCore = AppEngine.instance().dataCore();
        if(dataCore.isLogin()) {
            DataCoreManager.CoreDataWrapper coreDataWrapper = dataCore.getCoreDataWrapper();
            searchByTrace req = new searchByTrace();
            req._SEARCH_TRACE_CONDITION = new searchByTraceConditionVO();
            req._SEARCH_TRACE_CONDITION._MEMBER_ID = coreDataWrapper.userId;
            req._SEARCH_TRACE_CONDITION._PAGE_INDEX = String.valueOf(pageIndex);
            req._SEARCH_TRACE_CONDITION._PAGE_COUNT = "4";
            req._SEARCH_TRACE_CONDITION._FLIGHT_TYPE = "UNUSED";
            req._SEARCH_TRACE_CONDITION._PARAM_LIST = new ArrayList<>();
            if(coreDataWrapper.phoenixIdList != null && coreDataWrapper.phoenixIdList.size() > 0) {
                for(String phoenixId : coreDataWrapper.phoenixIdList) {
                    traceParamVO traceVoCh = new traceParamVO();
                    traceVoCh._CERT_NO = phoenixId;
                    traceVoCh._PSGR_NAME = coreDataWrapper.userNameCh;
                    traceParamVO traceVoEn = new traceParamVO();
                    traceVoEn._CERT_NO = phoenixId;
                    traceVoEn._PSGR_NAME = coreDataWrapper.userNameEn;
                    req._SEARCH_TRACE_CONDITION._PARAM_LIST.add(traceVoCh);
                    req._SEARCH_TRACE_CONDITION._PARAM_LIST.add(traceVoEn);
                }
            } else {
                if(coreDataWrapper.idNumberList != null && coreDataWrapper.idNumberList.size() > 0) {
                    for(String idNum : coreDataWrapper.idNumberList) {
                        traceParamVO traceVoCh = new traceParamVO();
                        traceVoCh._CERT_NO = idNum;
                        traceVoCh._PSGR_NAME = coreDataWrapper.userNameCh;
                        traceParamVO traceVoEn = new traceParamVO();
                        traceVoEn._CERT_NO = idNum;
                        traceVoEn._PSGR_NAME = coreDataWrapper.userNameEn;
                        req._SEARCH_TRACE_CONDITION._PARAM_LIST.add(traceVoCh);
                        req._SEARCH_TRACE_CONDITION._PARAM_LIST.add(traceVoEn);
                    }
                }

            }
            SearchFullchannelWebServiceImplServiceSoapBinding binding = new SearchFullchannelWebServiceImplServiceSoapBinding("searchByTrace",req);

            mNetManager.getResult(mApi.searchByTrace(binding), response -> {
                if(response.code == 0 && response.data != null && response.data._SEARCH_TRACE_RESULT != null) {
                    routeListLive.setValue(response.data._SEARCH_TRACE_RESULT._FULL_CHANNEL);
                } else {
                    routeListLive.setValue(null);
                }
            });

        }
    }

    /**
     * 易行查询
     */
    public void easyGoSearch() {
        if(!TextUtils.isEmpty(easyGoSearch.getSearchKey())) {

        }
    }

    public void observeNoticeList(LifecycleOwner owner, Observer<List<noticeInfoListVO>> observer) {
        noticeListLive.observe(owner,observer);
    }

    public void observeRouteList(LifecycleOwner owner, Observer<List<fullchannelVO>> observer) {
        routeListLive.observe(owner,observer);
    }

    public static class EasyGoSearch extends BaseObservable{
        private String searchKey;

        @Bindable
        public String getSearchKey() {
            return searchKey;
        }

        @Bindable
        public void setSearchKey(String searchKey) {
            this.searchKey = searchKey;
        }
    }
}
