package com.heaven.news.ui.vm.viewmodel;

import android.Manifest;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.databinding.ObservableField;

import com.heaven.base.vm.BaseViewModel;
import com.heaven.news.api.LoginApi;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.vm.present.MainPt;
import com.heaven.news.utils.RxRepUtils;
import com.neusoft.szair.model.noticelist.NoticeListWebServiceServiceSoapBinding;
import com.neusoft.szair.model.noticelist.noticeInfoListVO;
import com.neusoft.szair.model.noticelist.queryNoticeList;
import java.util.List;

/**
 * FileName: com.heaven.news.ui.vm.viewmodel.MainViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-25 16:45
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class MainViewModel extends BaseViewModel<MainPt> {
    public List<noticeInfoListVO> noticeList;
    public final MutableLiveData<List<noticeInfoListVO>> noticeListLive = new MutableLiveData<>();

    @Override
    public void initModel() {
        requestNotice();
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
                }
            }
        });
    }

    public void observeNoticeList(LifecycleOwner owner, Observer<List<noticeInfoListVO>> observer) {
        noticeListLive.observe(owner,observer);
    }
}
