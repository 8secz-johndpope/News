package com.heaven.news.ui.vm.vmmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.neusoft.szair.model.city.cityListVO;
import com.neusoft.szair.model.fullchannel.fullchannelVO;

import java.util.List;

/**
 * FileName: com.heaven.news.ui.vm.vmmodel.SelectCityViewModel.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-29 16:05
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class SelectCityViewModel extends AbstractViewModel {
    private final MutableLiveData<List<cityListVO>> cityListLive = new MutableLiveData<>();

    @Override
    public void initModel() {

    }


    public void observeRouteList(LifecycleOwner owner, Observer<List<cityListVO>> observer) {
        cityListLive.observe(owner,observer);
    }
}
