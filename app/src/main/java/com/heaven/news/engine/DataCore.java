package com.heaven.news.engine;

import android.app.Activity;
import android.text.TextUtils;
import android.util.SparseArray;

import com.heaven.annotation.aspect.TraceTime;
import com.heaven.data.manager.DataSource;
import com.heaven.news.BuildConfig;
import com.heaven.news.api.ConfigApi;
import com.heaven.news.api.LoginApi;
import com.heaven.news.consts.Constants;
import com.heaven.news.ui.vm.model.AdInfo;
import com.heaven.news.ui.vm.model.ConfigData;
import com.heaven.news.ui.vm.model.UserLoginInfo;
import com.heaven.news.ui.vm.model.Version;
import com.heaven.news.ui.vm.viewmodel.WelecomModel;
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
import java.util.Set;

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
    public static int LOGIN = 1;
    public static int MILE = 2;

    private DataSource dataSource;
    private Map<Activity,DataReadyObserver> observers = new HashMap<>();

    private ConfigData configData;
    private UpdateInfo updateInfo;
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

                phoenixInfo(userInfo._MEMBER,userInfo._CREDENTIAL_LIST);
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
            if(!TextUtils.isEmpty(userVipDetails._IDENTIFY_TYPE)) {
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

    private void phoenixInfo(memberInfoVo phoenixInfo,List<credentialVo> phonenixIdList) {
        if(phoenixInfo != null) {
            phoenixCardLevel = phoenixInfo._PRIMARY_TIER_NAME;
            phoenixNumber = phoenixInfo._MEMBER_NUMBER;
        }

        if(phonenixIdList != null && phonenixIdList.size() > 0) {
            phoenixIdList = new ArrayList<>();
            for(credentialVo idInfo : phonenixIdList) {
                phoenixIdList.add(idInfo._CREDENTIAL_NUM);
            }
        }
    }

    //自动登录
    @TraceTime
    public void autoLogin() {
        boolean isAutoLogin = dataSource.getSharePreBoolean(Constants.ISAUTOLOGIN);
        if(isAutoLogin) {
            UserLoginInfo userInfo = dataSource.getCacheEntity(DataSource.DISK,Constants.USERINFO);
            if(userInfo != null && !TextUtils.isEmpty(userInfo.userCount) && !TextUtils.isEmpty(userInfo.userPwd)) {
                loginNew login = new loginNew();
                loginReqVO loginreqvo = new loginReqVO();
                loginreqvo._USER_NAME = userInfo.userCount;
                loginreqvo._PASSWORD = Constants.getPassword(userInfo.userPwd);

                loginreqvo._APP_ID = SOAPConstants.APP_ID;
                loginreqvo._APP_IP = SOAPConstants.APP_IP;
                loginreqvo._DEVICE_TYPE = SOAPConstants.DEVICE_TYPE;

                loginreqvo._DEVICE_TOKEN = "";
                login._LOGIN_PARAM = loginreqvo;


                MemberLoginWebServiceImplServiceSoapBinding bind = new MemberLoginWebServiceImplServiceSoapBinding("loginNew",login);//非短信验证码登陆，用户新接口

                RxRepUtils.getResult(dataSource.getNetApi(LoginApi.class).login(bind), loginNewResponseDataResponse -> {
                    if (loginNewResponseDataResponse.code == 0) {
                        UserLoginInfo userLoginInfo = new UserLoginInfo();
                        userLoginInfo.userCount = userInfo.userCount;
                        userLoginInfo.userPwd = userInfo.userPwd;
                        initLoginData(loginNewResponseDataResponse.data);
                        dataSource.cacheData(DataSource.DISK, Constants.USERINFO, userLoginInfo);
                    }
                    Logger.i("autoLogin------" + loginNewResponseDataResponse);
                    notifyDataUpdate(LOGIN);
                });
            }
        }

    }

    private void notifyDataUpdate(int dataType) {
        if(observers != null && observers.size() > 0) {
            Collection<DataReadyObserver> observerSet = observers.values();
            for(DataReadyObserver observer : observerSet) {
                if(observer != null) {
                    observer.dataReady(dataType);
                }
            }
        }
    }

    @TraceTime
    private void requestVersion() {
        long startTime = System.currentTimeMillis();
        RxRepUtils.getConfigResult(dataSource.getNetApi(BuildConfig.CONFIG_URL,ConfigApi.class).getConfig(), configData -> {
            long endTime = System.currentTimeMillis();
            long requestTime = endTime - startTime;
            if (configData.netCode == 0 && configData.androidversionnew != null) {
                this.configData = configData;
                checkVersion(configData.androidversionnew);
            } else {
                updateInfo = new UpdateInfo();
                updateInfo.requestTime = requestTime;
                updateInfo.isNetError = true;
                updateInfo.reason = configData.message;
                processNextStep(updateInfo);
            }
            notifyDataUpdate(VERSION);
        });
    }

    private void checkVersion(Version version) {
        AppInfo appInfo = AppEngine.getInstance().getAppConfig();
        updateInfo = new UpdateInfo();
        updateInfo.updateUrl = version.url;
        updateInfo.updateMessage = version.txt;
        if (version.cversion > 65534) {
            updateInfo.isServiceMainta = true;
        } else {
            if (appInfo.verCode < version.cversion) {
                updateInfo.needUpdate = true;
                if (appInfo.verCode < version.fversion) {
                    updateInfo.isForceUpdate = true;
                }
            }
        }
        checkAdInfo(updateInfo);
        processNextStep(updateInfo);
    }

    private void checkAdInfo(UpdateInfo updateInfo) {
        updateInfo.adInfo = getTestAdInfoData();
//        updateInfo.isShowAd = true;
        if(updateInfo.isShowAd && updateInfo.adInfo != null) {
            AppEngine.getInstance().cacheData(DataSource.DB, Constants.ADINFO, updateInfo.adInfo );
        } else {
            updateInfo.isShowAd = false;
        }
    }


    private void processNextStep(UpdateInfo updateInfo) {
        boolean isOldUser = AppEngine.getInstance().getDataSource().getSharePreBoolean(Constants.ISOLDUSER);
        if (isOldUser) {
            updateInfo.nextGuidePage = false;
        } else {
            updateInfo.nextGuidePage = true;
        }
        AppEngine.getInstance().getDataSource().setSharePreBoolean(Constants.ISOLDUSER, true);
    }

    private AdInfo getTestAdInfoData() {
        AdInfo adInfo = new AdInfo();
        adInfo.isVideo = false;
        adInfo.urlImage = "http://img0.imgtn.bdimg.com/it/u=1344159241,3681424911&fm=26&gp=0.jpg";
        adInfo.urlVideo = "";
        adInfo.content = "百思不得姐减肥肯定是怕几点睡激动是怕";

        return adInfo;
    }

    //获取广告信息
    @TraceTime
    private void getAdInfo() {
        try {
            RxRepUtils.getConfigResult(ApiManager.getApi(BuildConfig.CONFIG_URL, ConfigApi.class).getAdInfo(), configData -> {
                if(configData.netCode == 0) {
                    dataSource.cacheData(DataSource.DISK, Constants.ADINFO, configData);
                }
            });
        } catch (Exception e) {
            Logger.i("getAdInfo:" + e.getMessage());
        }
    }

    public boolean isLogin() {
        return hasLogin;
    }

    public String getUserName() {
        return userName;
    }

    public UpdateInfo getUpdateInfo() {
        return updateInfo;
    }

    public void addDataObserver(Activity activity,DataReadyObserver observer) {
        observers.put(activity,observer);
        Logger.i("addDataObserver----" + activity);
    }

    public void removeDataObserver(Activity activity) {
        observers.remove(activity);
        Logger.i("removeDataObserver----" + activity);
    }

    public interface DataReadyObserver{
        void dataReady(int dataType);
    }


    public static class UpdateInfo {
        public boolean isNetError;
        public String reason;
        public long requestTime;
        public boolean isServiceMainta;
        public boolean needUpdate;
        public boolean isForceUpdate;
        public String updateUrl;
        public String updateMessage;
        public boolean nextGuidePage = false;
        public boolean isShowAd = false;
        public AdInfo adInfo;

        @Override
        public String toString() {
            return "UpdateInfo{" +
                    "isNetError=" + isNetError +
                    ", reason='" + reason + '\'' +
                    ", requestTime=" + requestTime +
                    ", isServiceMainta=" + isServiceMainta +
                    ", needUpdate=" + needUpdate +
                    ", isForceUpdate=" + isForceUpdate +
                    ", updateUrl='" + updateUrl + '\'' +
                    ", updateMessage='" + updateMessage + '\'' +
                    ", nextGuidePage=" + nextGuidePage +
                    ", isShowAd=" + isShowAd +
                    ", adInfo=" + adInfo +
                    '}';
        }
    }

}
