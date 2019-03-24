package com.heaven.news.ui.vm.viewmodel;

import android.Manifest;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;

import com.heaven.annotation.aspect.Permission;
import com.heaven.base.utils.RxSchedulers;
import com.heaven.base.vm.BaseViewModel;
import com.heaven.news.api.LoginApi;
import com.heaven.news.engine.Api;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.vm.present.MainPt;
import com.heaven.news.utils.CryptUtility;
import com.neusoft.szair.model.memberbase.MemberLoginWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.memberbase.loginNew;
import com.neusoft.szair.model.memberbase.loginReqVO;
import com.neusoft.szair.model.soap.SOAPConstants;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * FileName: com.heaven.news.ui.vm.viewmodel.MainViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-25 16:45
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class MainViewModel extends BaseViewModel<MainPt> {
    public final MutableLiveData<String> userName = new MutableLiveData<>();

    public final ObservableField<String> passwordObserve = new ObservableField<>();


    @Override
    public void initModel() {

    }
}
