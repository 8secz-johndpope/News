package com.heaven.news.ui.model.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.model.bean.base.SettingItem;
import com.neusoft.szair.model.setting.AppSettingWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.setting.appSettingReqVO;
import com.neusoft.szair.model.setting.operateSetting;
import com.neusoft.szair.model.soap.SOAPConstants;

/**
 * FileName: com.heaven.news.ui.model.vm.SettingVm.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-20 13:43
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class SettingVm extends AbstractVm {
    @Override
    public void initModel() {

    }


    public void reqSetingState(int type, String state, Observer<Boolean> observer) {
        MutableLiveData<Boolean> resultLive = new MutableLiveData<>();
        resultLive.observe(owner,observer);
        operateSetting operatesetting = new operateSetting();
        appSettingReqVO reqParm = new appSettingReqVO();
        operatesetting._APP_SETTING_PARAM = reqParm;
        reqParm._OPERATE = "1";
        reqParm._APP_ID = SOAPConstants.APP_ID;
        reqParm._APP_IP = SOAPConstants.APP_IP;
        if(SettingItem.SWITCH_BAR_NOTIFY == type) {
            reqParm._NOTICE_BAR = state;
        } else if(SettingItem.SWITCH_SOUND == type) {
            reqParm._SOUND = state;
        } else if(SettingItem.SWITCH_PUSH_MESSAGE == type) {
            reqParm._MSG_RECORD = state;
        }
        if(AppEngine.instance().dataCore().isLogin()) {
            reqParm._UUID = AppEngine.instance().dataCore().getCoreDataWrapper().userId;
        }
        AppSettingWebServiceImplServiceSoapBinding binding = new AppSettingWebServiceImplServiceSoapBinding("operateSetting",operatesetting);
        netManager.getResultInThred(api.querySettingSwitchState(binding), response ->{
            if (response.code == 0 && response.data != null && response.data._APP_SETTING_RESULT != null) {
                boolean msgState = "1".equals(response.data._APP_SETTING_RESULT._MSG_RECORD);
                boolean barState = "1".equals(response.data._APP_SETTING_RESULT._NOTICE_BAR);
                boolean soundState = "1".equals(response.data._APP_SETTING_RESULT._SOUND);
                boolean stateResult = false;
                if(SettingItem.SWITCH_BAR_NOTIFY == type) {
                    stateResult = barState;
                } else if(SettingItem.SWITCH_SOUND == type) {
                    stateResult = soundState;
                } else if(SettingItem.SWITCH_PUSH_MESSAGE == type) {
                    stateResult = msgState;
                }
                resultLive.postValue(stateResult);
            }else {
                resultLive.postValue(!"1".equals(state));
            }
        });
    }


    public void logOut() {
        AppEngine.instance().dataCore().logOut();
        ARouter.getInstance().build(RouterUrl.ROUTER_URL_LOGIN).navigation();
    }

}
