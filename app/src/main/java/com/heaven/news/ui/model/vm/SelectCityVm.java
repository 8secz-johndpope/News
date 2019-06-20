package com.heaven.news.ui.model.vm;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.neusoft.szair.model.city.cityListVO;

import java.util.List;

/**
 * FileName: com.heaven.news.ui.vm.vmmodel.SelectCityVm.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-29 16:05
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class SelectCityVm extends AbstractVm {
    private final MutableLiveData<List<cityListVO>> cityListLive = new MutableLiveData<>();

    @Override
    public void initModel() {

    }


    public void observeRouteList(LifecycleOwner owner, Observer<List<cityListVO>> observer) {
        cityListLive.observe(owner,observer);
    }
}
