package com.heaven.news.ui.model.vm;

import android.databinding.Observable;
import android.text.TextUtils;

import com.heaven.data.manager.DataSource;
import com.heaven.data.net.DataResponse;
import com.heaven.news.BR;
import com.heaven.news.consts.Constants;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.net.BaseResCallBack;
import com.heaven.news.ui.model.bean.base.UserInfo;
import com.neusoft.szair.model.memberbase.MemberBaseWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.phonecaptcha.MemberPhoneCaptchaWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.phonecaptcha.sendPhoneCaptcha;
import com.neusoft.szair.model.phonecaptcha.sendPhoneCaptchaReqVO;
import com.neusoft.szair.model.phonecaptcha.sendPhoneCaptchaResponse;
import com.neusoft.szair.model.soap.SOAPConstants;
import com.orhanobut.logger.Logger;

import io.reactivex.functions.Consumer;

/**
 * FileName: com.heaven.news.ui.vm.viewmodel.LoginVm.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-08 15:45
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class LoginVm extends AbstractVm {

    public UserInfo userInfo;

    @Override
    public void initModel() {
        UserInfo userSecret = AppEngine.instance().getDataSource().getCacheEntity(DataSource.DISK, Constants.USERINFO);
        userInfo = userSecret != null ? userSecret : new UserInfo();
        userInfo.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (BR.count == propertyId) {
                    Logger.i("sonPropertyChanged count--");
                } else if (BR.password == propertyId) {
                    Logger.i("sonPropertyChanged password--");
                }
            }
        });
    }

    public long requestVerifyCode(String phoneNum, BaseResCallBack<Boolean> callBack) {
        sendPhoneCaptchaReqVO arg0 = new sendPhoneCaptchaReqVO();
        arg0._PHONE_NUMBER = phoneNum;
        arg0._CAPTCHA_TYPE = "5";

        sendPhoneCaptcha sendPhoneCaptcha = new sendPhoneCaptcha();
        sendPhoneCaptcha._SEND_PHONE_CAPTCHA_PARAM = arg0;
        sendPhoneCaptcha._SEND_PHONE_CAPTCHA_PARAM._APP_ID = SOAPConstants.APP_ID;
        sendPhoneCaptcha._SEND_PHONE_CAPTCHA_PARAM._APP_IP = SOAPConstants.APP_IP;
        MemberPhoneCaptchaWebServiceImplServiceSoapBinding binding = new MemberPhoneCaptchaWebServiceImplServiceSoapBinding("sendPhoneCaptcha", sendPhoneCaptcha);
        long taskId = netManager.getResultInThred(api.requestVeryfiCode(binding), response -> {
            if (response.code == 0 && response.data != null && response.data._SEND_PHONE_CAPTCHA_RESULT != null) {
                if ("0000".equals(response.data._SEND_PHONE_CAPTCHA_RESULT._CODE)) {
                    callBack.updateSuccessPostValue(true);
                } else {
                    callBack.updateErrorPostValue(TextUtils.isEmpty(response.data._SEND_PHONE_CAPTCHA_RESULT._CODE) ? -1 : Integer.parseInt(response.data._SEND_PHONE_CAPTCHA_RESULT._CODE), response.data._SEND_PHONE_CAPTCHA_RESULT._MSG);
                }
            } else {
                callBack.updateErrorPostValue(-1, "");
            }
        });

        return taskId;
    }
}
