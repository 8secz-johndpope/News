package com.heaven.news.engine;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.text.TextUtils;

import com.heaven.annotation.aspect.TraceTime;
import com.heaven.base.ui.activity.BaseActivity;
import com.heaven.data.manager.DataSource;
import com.heaven.news.BuildConfig;
import com.heaven.news.api.ConfigApi;
import com.heaven.news.api.LoginApi;
import com.heaven.news.consts.Constants;
import com.heaven.news.ui.vm.model.AdInfo;
import com.heaven.news.ui.vm.model.ConfigData;
import com.heaven.news.ui.vm.model.HomeImageInfo;
import com.heaven.news.ui.vm.model.UserLoginInfo;
import com.heaven.news.ui.vm.model.Version;
import com.heaven.news.utils.RxRepUtils;
import com.neusoft.szair.model.member.addressVo;
import com.neusoft.szair.model.member.credentialVo;
import com.neusoft.szair.model.memberbase.MemberLoginWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.memberbase.emailVo;
import com.neusoft.szair.model.memberbase.loginNew;
import com.neusoft.szair.model.memberbase.loginNewResponse;
import com.neusoft.szair.model.memberbase.loginReqVO;
import com.neusoft.szair.model.memberbase.memberInfoVo;
import com.neusoft.szair.model.memberbase.phoneVo;
import com.neusoft.szair.model.memberbase.queryRespVO;
import com.neusoft.szair.model.memberbase.vipDetails;
import com.neusoft.szair.model.memberbase.vipDocument;
import com.neusoft.szair.model.soap.SOAPConstants;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: com.heaven.news.engine.DataCore.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-21 15:41
 *
 * @version V1.0 核心数据管理类
 */
public class DataCore {

    public static int VERSION = 0;
    public static int HOME = 1;
    public static int LOGIN = 2;
    public static int MILE = 3;

    public final MutableLiveData<String> userNameLive = new MutableLiveData<>();
    public final MutableLiveData<ConfigData> configLive = new MutableLiveData<>();
    public final MutableLiveData<HomeImageInfo> homeConfigLive = new MutableLiveData<>();

    private DataSource dataSource;

    private ConfigData configData;
    private HomeImageInfo homeConfigData;
    private queryRespVO userAllInfo;

    private boolean hasLogin;

    private String userName;
    private String userSex;
    private String idNumber;                                //证件号码
    private phoneVo phone;                                  //用户手机号
    private addressVo address;                              //用户地址
    private emailVo mail;                                   //用户邮件
    private ArrayList<String> idNumberList;                 //用户证件数组
    private boolean isIdentify;                              //是否认证
    private String identifyType;                            //认证类型
    private boolean isRegistPhoenix;                        //凤凰知音是否注册
    private String phoenixNumber;                            //凤凰知音卡号
    private String phoenixCardLevel;                        //凤凰知音卡级别
    private ArrayList<String> phoenixIdList;                 //凤凰知音证件列表
    private String userCrmCardNumber;                       //常旅客卡号
    private String groupFlag;                               //大客户标示
    private String groupCode;                               //大客户编码


    DataCore(DataSource dataSource) {
        this.dataSource = dataSource;
        this.dataSource.runWorkThread(this::prepareData);
    }


    private void prepareData() {
        autoLogin();
        requestVersion();
        requestHomeConfig();
//        getAdInfo();
    }

    public void initLoginData(loginNewResponse loginData) {
        if (loginData != null) {
            queryRespVO userInfo = loginData._LOGIN_RESULT;
            if (userInfo != null) {
                hasLogin = true;
                userAllInfo = userInfo;
                userCrmCardNumber = userInfo._CRM_CARD_NUMBER;

                if ("1".equals(userInfo._CRM_FREQUENT_FLYER_FLAG)) {
                    isRegistPhoenix = true;
                }
                groupFlag = userInfo._GROUP_FLAG;
                if (userInfo._GROUP_INFO != null) {
                    groupCode = userInfo._GROUP_INFO._CUS_BIG_CODE;
                }
                if (loginData._LOGIN_RESULT._VIP != null) {
                    if (loginData._LOGIN_RESULT._VIP._VIPDETAILS != null) {
                        initUserName(loginData._LOGIN_RESULT._VIP._VIPDETAILS);
                        if (loginData._LOGIN_RESULT._VIP._VIP_DOCUMENTS != null) {
                            userIdNumber(loginData._LOGIN_RESULT._VIP._VIP_DOCUMENTS);
                        }
                    }
                }

                phone = userInfo._PHONE;
                address = userInfo._ADDRESS;
                mail = userInfo._EMAIL;

                phoenixInfo(userInfo._MEMBER, userInfo._CREDENTIAL_LIST);
            }
        }
    }

    private void initUserName(vipDetails userVipDetails) {
        if (userVipDetails != null) {
            if (TextUtils.isEmpty(userVipDetails._SURNAME_CN)
                    && TextUtils.isEmpty(userVipDetails._FIRSTNAME_CN)) {
                if (TextUtils.isEmpty(userVipDetails._SURNAME_EN) && !TextUtils.isEmpty(userVipDetails._FIRSTNAME_EN)) {
                    userName = userVipDetails._FIRSTNAME_EN;
                } else if (TextUtils.isEmpty(userVipDetails._FIRSTNAME_EN) && !TextUtils.isEmpty(userVipDetails._SURNAME_EN)) {
                    userName = userVipDetails._SURNAME_EN;
                } else if (!TextUtils.isEmpty(userVipDetails._FIRSTNAME_EN) && !TextUtils.isEmpty(userVipDetails._SURNAME_EN)) {
                    userName = userVipDetails._SURNAME_EN + "/" + userVipDetails._FIRSTNAME_EN;
                } else {
                    userName = userVipDetails._LOGIN_MOBILE;
                }
            } else {
                if (TextUtils.isEmpty(userVipDetails._SURNAME_CN)) {
                    userName = userVipDetails._FIRSTNAME_CN;
                } else if (TextUtils.isEmpty(userVipDetails._FIRSTNAME_CN)) {
                    userName = userVipDetails._SURNAME_CN;
                } else {
                    userName = userVipDetails._SURNAME_CN + userVipDetails._FIRSTNAME_CN;
                }
            }
            userSex = userVipDetails._SEX;
            if (!TextUtils.isEmpty(userVipDetails._IDENTIFY_TYPE)) {
                identifyType = userVipDetails._IDENTIFY_TYPE;
            }
        }
    }

    private void userIdNumber(List<vipDocument> userIdInfoList) {
        if (userIdInfoList != null && userIdInfoList.size() > 0) {
            idNumber = userIdInfoList.get(0)._DOCUMENTNO;
            idNumberList = new ArrayList<>();
            for (vipDocument idInfo : userIdInfoList) {
                if ("NI".equals(idInfo._DOCUMENTTYPE)) {
                    idNumber = idInfo._DOCUMENTNO;
                }
                idNumberList.add(idInfo._DOCUMENTNO);
            }
        }
    }

    private void phoenixInfo(memberInfoVo phoenixInfo, List<credentialVo> phonenixIdList) {
        if (phoenixInfo != null) {
            phoenixCardLevel = phoenixInfo._PRIMARY_TIER_NAME;
            phoenixNumber = phoenixInfo._MEMBER_NUMBER;
        }

        if (phonenixIdList != null && phonenixIdList.size() > 0) {
            phoenixIdList = new ArrayList<>();
            for (credentialVo idInfo : phonenixIdList) {
                phoenixIdList.add(idInfo._CREDENTIAL_NUM);
            }
        }
    }

    //自动登录
    @TraceTime
    public void autoLogin() {
        boolean isAutoLogin = dataSource.getSharePreBoolean(Constants.ISAUTOLOGIN);
        if (isAutoLogin) {
            UserLoginInfo userInfo = dataSource.getCacheEntity(DataSource.DISK, Constants.USERINFO);
            if (userInfo != null && !TextUtils.isEmpty(userInfo.userCount) && !TextUtils.isEmpty(userInfo.userPwd)) {
                loginNew login = new loginNew();
                loginReqVO loginreqvo = new loginReqVO();
                loginreqvo._USER_NAME = userInfo.userCount;
                loginreqvo._PASSWORD = Constants.getPassword(userInfo.userPwd);

                loginreqvo._APP_ID = SOAPConstants.APP_ID;
                loginreqvo._APP_IP = SOAPConstants.APP_IP;
                loginreqvo._DEVICE_TYPE = SOAPConstants.DEVICE_TYPE;

                loginreqvo._DEVICE_TOKEN = "";
                login._LOGIN_PARAM = loginreqvo;


                MemberLoginWebServiceImplServiceSoapBinding bind = new MemberLoginWebServiceImplServiceSoapBinding("loginNew", login);//非短信验证码登陆，用户新接口

                RxRepUtils.getResult(dataSource.getNetApi(LoginApi.class).login(bind), loginNewResponseDataResponse -> {
                    if (loginNewResponseDataResponse.code == 0) {
                        UserLoginInfo userLoginInfo = new UserLoginInfo();
                        userLoginInfo.userCount = userInfo.userCount;
                        userLoginInfo.userPwd = userInfo.userPwd;
                        initLoginData(loginNewResponseDataResponse.data);
                        userNameLive.setValue(userName);
                        dataSource.cacheData(DataSource.DISK, Constants.USERINFO, userLoginInfo);
                    }
                    Logger.i("autoLogin------" + loginNewResponseDataResponse);
                });
            }
        }

    }


    @TraceTime
    private void requestVersion() {
        RxRepUtils.getConfigResult(dataSource.getNetApi(BuildConfig.CONFIG_URL, ConfigApi.class).getConfig(), configData -> {
            this.configData = configData;
            configLive.setValue(configData);
            if (configData.netCode == 0 && configData.androidversionnew != null) {
                this.configData = configData;
                configLive.setValue(configData);
            } else {
                configLive.setValue(configData);
            }
        });
    }


    private void requestHomeConfig() {
        RxRepUtils.getHomeConfigResult(dataSource.getNetApi(BuildConfig.CONFIG_URL, ConfigApi.class).getImageConfig(), configData -> {
            if (configData.netCode == 0) {
                this.homeConfigData = configData;
                homeConfigLive.setValue(homeConfigData);
            }
        });
    }


    public boolean isLogin() {
        return hasLogin;
    }

    public String getUserName() {
        return userName;
    }

    public ConfigData getConfigData() {
        return configData;
    }

    public HomeImageInfo getHomeConfigData() {
        return homeConfigData;
    }


    public void registLoginObserver(BaseActivity activity, Observer<String> userName) {
        userNameLive.observe(activity,userName);
    }

    public void registConfigObserver(BaseActivity activity, Observer<ConfigData> configObserver) {
        configLive.observe(activity,configObserver);
    }

    public void registHomeConfigObserver(BaseActivity activity, Observer<HomeImageInfo> homeImageInfoObserver) {
        homeConfigLive.observe(activity,homeImageInfoObserver);
    }

}
