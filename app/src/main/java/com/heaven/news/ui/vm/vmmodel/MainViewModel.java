package com.heaven.news.ui.vm.vmmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.view.View;

import com.heaven.data.manager.DataSource;
import com.heaven.news.BuildConfig;
import com.heaven.news.api.FlightApi;
import com.heaven.news.api.LoginApi;
import com.heaven.news.consts.Constants;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.utils.RxRepUtils;
import com.neusoft.szair.model.flightsearch.FlightSearchWebServiceServiceSoapBinding;
import com.neusoft.szair.model.flightsearch.flightSearchDomestic;
import com.neusoft.szair.model.flightsearch.flightSearchDomesticConditionVO;
import com.neusoft.szair.model.flightsearch.flightSearchDomesticResponse;
import com.neusoft.szair.model.flightsearch.tripInfoVO;
import com.neusoft.szair.model.noticelist.NoticeListWebServiceServiceSoapBinding;
import com.neusoft.szair.model.noticelist.noticeInfoListVO;
import com.neusoft.szair.model.noticelist.queryNoticeList;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

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
    public final MutableLiveData<List<noticeInfoListVO>> noticeListLive = new MutableLiveData<>();

    @Override
    public void initModel() {
        noticeList = AppEngine.instance().getDataSource().getCacheEntity(DataSource.DISK,Constants.NOTICE);
        easyGoSearch = new EasyGoSearch();
    }

    public void requestNotice() {
        queryNoticeList noticelist = new queryNoticeList();

        noticelist._PAGE_NO = 0;
        noticelist._PAGE_COUNT = 3;
        NoticeListWebServiceServiceSoapBinding binding = new NoticeListWebServiceServiceSoapBinding("queryNoticeList",noticelist);
        RxRepUtils.instance().getResult(AppEngine.instance().api().getApi(LoginApi.class).queryNoticeList(binding), dataResponse -> {
            if(dataResponse.code == 0 && dataResponse.data != null) {
                if(dataResponse.data._NOTICE_INFO_LIST != null && dataResponse.data._NOTICE_INFO_LIST._NOTICE_INFO_LIST != null) {
                    noticeList = dataResponse.data._NOTICE_INFO_LIST._NOTICE_INFO_LIST;
                    noticeListLive.setValue(noticeList);
                    AppEngine.instance().getDataSource().cacheData(DataSource.DISK, Constants.NOTICE,noticeList);
                }
            }
        });
    }


    public void flightSearch(View view) {
        Logger.i("flightSearch------");

        List<tripInfoVO> flightLIst = new ArrayList<>();
        tripInfoVO flightVo = new tripInfoVO();
        flightVo.ORG_CITY = "SZX";
        flightVo.DST_CITY = "PEK";
        flightVo.DEPARTURE_DATE = "2019-04-20";
        flightVo.INDEX = "1";
        flightLIst.add(flightVo);
        flightSearchDomestic req = new flightSearchDomestic();
        req.FLIGHT_SEARCH_CONDITION = new flightSearchDomesticConditionVO();
        req.FLIGHT_SEARCH_CONDITION.TRIP_INFO_LIST = flightLIst;
        req.FLIGHT_SEARCH_CONDITION.QUERY_FLAG = "DC";
        req.FLIGHT_SEARCH_CONDITION.USER_ID = AppEngine.instance().dataCore().getCoreDataWrapper().userId;
        req.FLIGHT_SEARCH_CONDITION.CRM_MEMBER_ID = AppEngine.instance().dataCore().getCoreDataWrapper().crmId;

        FlightSearchWebServiceServiceSoapBinding binding = new FlightSearchWebServiceServiceSoapBinding("flightSearchDomestic",req);

        RxRepUtils.instance().getResult(AppEngine.instance().api().getApi(BuildConfig.FLIGHT_URL, FlightApi.class).searchFlight(binding), response -> {
            Logger.i(response.toString());
        });
    }

    public void easyGoSearch() {
        if(!TextUtils.isEmpty(easyGoSearch.getSearchKey())) {

        }
    }

    public void observeNoticeList(LifecycleOwner owner, Observer<List<noticeInfoListVO>> observer) {
        noticeListLive.observe(owner,observer);
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
