package com.heaven.news.engine;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.heaven.annotation.aspect.TraceTime;
import com.heaven.data.manager.DataSource;
import com.heaven.news.BuildConfig;
import com.heaven.news.R;
import com.heaven.news.api.ConfigApi;
import com.heaven.news.api.LoginApi;
import com.heaven.news.consts.Constants;
import com.heaven.news.ui.vm.model.base.EasyGoService;
import com.heaven.news.ui.vm.model.base.HomeService;
import com.heaven.news.ui.vm.model.base.ConfigData;
import com.heaven.news.ui.vm.model.base.HomeImageInfo;
import com.heaven.news.ui.vm.model.base.PhoenixService;
import com.heaven.news.ui.vm.model.base.UserLoginInfo;
import com.heaven.news.ui.vm.model.base.UserSecret;
import com.heaven.news.ui.vm.model.base.Version;
import com.heaven.news.ui.vm.model.base.VersionUpdate;
import com.heaven.news.utils.RxRepUtils;
import com.neusoft.szair.model.easycardmodel.EasyCardWebServiceServiceSoapBinding;
import com.neusoft.szair.model.easycardmodel.WALLET_QUERY;
import com.neusoft.szair.model.easycardmodel.walletInfoQuery;
import com.neusoft.szair.model.member.CRMFrequentFlyerWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.member.addressVo;
import com.neusoft.szair.model.member.credentialVo;
import com.neusoft.szair.model.member.queryMiles;
import com.neusoft.szair.model.member.queryMilesConditionVO;
import com.neusoft.szair.model.memberbase.MemberLoginWebServiceImplServiceSoapBinding;
import com.neusoft.szair.model.memberbase.emailVo;
import com.neusoft.szair.model.memberbase.loginNew;
import com.neusoft.szair.model.memberbase.loginReqVO;
import com.neusoft.szair.model.memberbase.memberInfoVo;
import com.neusoft.szair.model.memberbase.phoneVo;
import com.neusoft.szair.model.memberbase.queryRespVO;
import com.neusoft.szair.model.memberbase.vipDetails;
import com.neusoft.szair.model.memberbase.vipDocument;
import com.neusoft.szair.model.memberbase.wrappedQueryRespVO;
import com.neusoft.szair.model.soap.SOAPConstants;
import com.neusoft.szair.model.usercouponsearch.UserCouponSearchWebServiceServiceSoapBinding;
import com.neusoft.szair.model.usercouponsearch.queryUseCouponCnt;
import com.neusoft.szair.model.usercouponsearch.userCouponSearchConditionVO;
import com.orhanobut.logger.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
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
    String TAG = DataCore.class.getSimpleName();
    public static int VERSION = 0;
    public static int HOME = 1;
    public static int LOGIN = 2;
    public static int MINE = 3;
    public static int MILE = 4;

    private Context context;

    private HomeService homeService;//首页服务
    private EasyGoService easyGoService;//易行服务
    private PhoenixService phoenixService;//凤凰知音服务

    private Map<Observer<CoreDataWrapper>, MutableLiveData<CoreDataWrapper>> observers = new HashMap<>();


    private DataSource dataSource;

    private CoreDataWrapper coreDataWrapper = new CoreDataWrapper();
    private Version version;
    private ConfigData configData;
    private HomeImageInfo homeConfigData;
    private queryRespVO userAllInfo;

    private boolean hasLogin;

    private String userId;                                  //用户id
    private String crmId;                                   //凤凰知音id
    private String userName;
    private String userNameCh;
    private String userNameEn;
    private String userSex;
    private String idNumber;                                //证件号码
    private phoneVo phone;                                  //用户手机号
    private addressVo address;                              //用户地址
    private emailVo mail;                                   //用户邮件
    private ArrayList<String> idNumberList;                 //用户证件数组
    private boolean isIdentify;                              //是否认证
    private String identifyType;                            //认证类型
    private String ffpIdentify;                             //银联认证标识
    private boolean isRegistPhoenix;                        //凤凰知音是否注册
    private String phoenixNumber;                            //凤凰知音卡号
    private String phoenixCardLevel;                        //凤凰知音卡级别
    private ArrayList<String> phoenixIdList;                 //凤凰知音证件列表
    private String userCrmCardNumber;                       //常旅客卡号
    private String groupFlag;                               //大客户标示
    private String groupCode;                               //大客户编码
    private String userMile;                                //用户可用里程
    private String userCouponCount;                         //优惠券数量
    DataCore(DataSource dataSource, Context context) {
        this.dataSource = dataSource;
        dataSource.runWorkThread(this::prepareData);
    }

    private void prepareData() {
        requestVersion();
        autoLogin();
        requestHomeConfig();
        homeConfigData = dataSource.getCacheEntity(DataSource.DISK, Constants.HOMECONFIG);
    }

    private void requestVersion() {
        RxRepUtils.getConfigResult(dataSource.getNetApi(BuildConfig.CONFIG_URL, ConfigApi.class).getConfig(), configData -> {
            CoreDataWrapper dataWrapper = getCoreDataWrapper(true, VERSION);
            dataWrapper.versionUpdate = CheckVersion.checkVersion(configData.androidversionnew,dataSource);
            notifyCoreDataChange(dataWrapper);
        });
    }

    public void initLoginData(queryRespVO userInfo) {
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
            if (userInfo._VIP != null) {
                if (userInfo._VIP._VIPDETAILS != null) {
                    userId = userInfo._VIP._VIPDETAILS._USER_ID;
                    crmId = userInfo._VIP._VIPDETAILS._CLKCRM_ID;
                    ffpIdentify = userInfo._VIP._VIPDETAILS._FFP_IDENTIFY;
                    initUserName(userInfo._VIP._VIPDETAILS);
                    if (userInfo._VIP._VIP_DOCUMENTS != null) {
                        userIdNumber(userInfo._VIP._VIP_DOCUMENTS);
                    }
                }
            }

            phone = userInfo._PHONE;
            address = userInfo._ADDRESS;
            mail = userInfo._EMAIL;

            phoenixInfo(userInfo._MEMBER, userInfo._CREDENTIAL_LIST);
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
            setNameChEn(userVipDetails);
        }
    }

    private void setNameChEn(vipDetails userVipDetails) {
        if (userVipDetails != null) {
            if (!TextUtils.isEmpty(userVipDetails._SURNAME_CN)
                    && !TextUtils.isEmpty(userVipDetails._FIRSTNAME_CN)) {
                userNameCh  = userVipDetails._SURNAME_CN + userVipDetails._FIRSTNAME_CN;

            } else if (!TextUtils.isEmpty(userVipDetails._SURNAME_CN)
                    && TextUtils.isEmpty(userVipDetails._FIRSTNAME_CN)) {
                userNameCh = userVipDetails._SURNAME_CN;

            } else if (TextUtils.isEmpty(userVipDetails._SURNAME_CN)
                    && !TextUtils.isEmpty(userVipDetails._FIRSTNAME_CN)) {
                userNameCh = userVipDetails._FIRSTNAME_CN;
            }

            if (TextUtils.isEmpty(userVipDetails._SURNAME_EN) && !TextUtils.isEmpty(userVipDetails._FIRSTNAME_EN)) {
                userNameEn = userVipDetails._FIRSTNAME_EN;
            } else if (TextUtils.isEmpty(userVipDetails._FIRSTNAME_EN) && !TextUtils.isEmpty(userVipDetails._SURNAME_EN)) {
                userNameEn = userVipDetails._SURNAME_EN;
            } else if (!TextUtils.isEmpty(userVipDetails._FIRSTNAME_EN) && !TextUtils.isEmpty(userVipDetails._SURNAME_EN)) {
                userNameEn = userVipDetails._SURNAME_EN + "/" + userVipDetails._FIRSTNAME_EN;
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
            UserSecret userSecret = dataSource.getCacheEntity(DataSource.DISK, Constants.USERINFO);
            if (userSecret != null && !TextUtils.isEmpty(userSecret.userCount) && !TextUtils.isEmpty(userSecret.pwd)) {
                login(userSecret.userCount, userSecret.pwd);
            }
        }
    }

    public void login(String userCount, String pwd) {
        if (!TextUtils.isEmpty(userCount) && !TextUtils.isEmpty(pwd)) {
//            prepareLoginCache(userCount, pwd);

            loginNew login = new loginNew();
            loginReqVO loginreqvo = new loginReqVO();
            loginreqvo._USER_NAME = userCount;
            loginreqvo._PASSWORD = pwd;

            loginreqvo._APP_ID = SOAPConstants.APP_ID;
            loginreqvo._APP_IP = SOAPConstants.APP_IP;
            loginreqvo._DEVICE_TYPE = SOAPConstants.DEVICE_TYPE;

            loginreqvo._DEVICE_TOKEN = "";
            login._LOGIN_PARAM = loginreqvo;

            Logger.i("RequestLogin---" + loginreqvo.toString());
            MemberLoginWebServiceImplServiceSoapBinding bind = new MemberLoginWebServiceImplServiceSoapBinding("loginNew", login);//非短信验证码登陆，用户新接口

            Long loginTaskId = RxRepUtils.getResult(dataSource.getNetApi(LoginApi.class).login(bind), loginResponse -> {
                if (loginResponse.code == 0 && loginResponse.data != null && loginResponse.data._LOGIN_RESULT != null) {
                    if ("0000".equals(loginResponse.data._LOGIN_RESULT._CODE)) {
                        UserSecret userSecret = new UserSecret(userCount, pwd);
                        UserLoginInfo userLoginInfo = new UserLoginInfo(userCount, pwd);
                        userLoginInfo.userInfo = loginResponse.data._LOGIN_RESULT;
                        initLoginData(loginResponse.data._LOGIN_RESULT);
                        resetCoreDataWrapper();
                        notifyCoreDataChange(getCoreDataWrapper(true, LOGIN));
                        dataSource.cacheData(DataSource.DISK, Constants.USERINFO, userSecret);
                        dataSource.cacheData(DataSource.DISK, userLoginInfo.key, userLoginInfo);
                        requestMileData();
                        requestUserCouponNum();
                        requestWalletInfo();
                    } else {
                        notifyCoreDataChange(getCoreDataWrapper(false, LOGIN));
                    }
                } else {
                    notifyCoreDataChange(getCoreDataWrapper(false, LOGIN));
                }
            });
        }
    }

    private void resetCoreDataWrapper() {
        coreDataWrapper.userName = userName;
        coreDataWrapper.userNameCh = userNameCh;
        coreDataWrapper.userNameEn = userNameEn;
        coreDataWrapper.idNumber = idNumber;
        coreDataWrapper.userId = userId;
        coreDataWrapper.cardLevel = phoenixCardLevel;
        coreDataWrapper.ffpIdentify = ffpIdentify;
        coreDataWrapper.phoenixNumber = phoenixNumber;
        coreDataWrapper.crmId = crmId;
        coreDataWrapper.idNumberList = idNumberList;
        coreDataWrapper.phoenixIdList = phoenixIdList;

        if ("Gold".equalsIgnoreCase(phoenixCardLevel)) {
            coreDataWrapper.cardLevelImgRes = R.mipmap.icon_golden_card;
            coreDataWrapper.cardLevelRes = R.string.card_level_gold;
        } else if ("Lifetime Platinum".equalsIgnoreCase(phoenixCardLevel)) {
            coreDataWrapper.cardLevelImgRes = R.mipmap.icon_lifetime_card;
            coreDataWrapper.cardLevelRes = R.string.card_level_lifetime_platinum;
        } else if ("Normal".equalsIgnoreCase(phoenixCardLevel)) {
            coreDataWrapper.cardLevelImgRes = R.mipmap.icon_blue_card;
            coreDataWrapper.cardLevelRes = R.string.card_level_normal;
        } else if ("Platinum".equalsIgnoreCase(phoenixCardLevel)) {
            coreDataWrapper.cardLevelImgRes = R.mipmap.icon_black_card;
            coreDataWrapper.cardLevelRes = R.string.card_level_platinum;
        } else if ("Silver".equalsIgnoreCase(phoenixCardLevel)) {
            coreDataWrapper.cardLevelImgRes = R.mipmap.icon_silve_card;
            coreDataWrapper.cardLevelRes = R.string.card_level_silver;
        } else {
            coreDataWrapper.cardLevelRes = R.string.card_level_normal;
        }

        if ("F".equals(userSex)) {
            coreDataWrapper.sexHeaderRes = R.mipmap.icon_header_femal;
        } else if ("M".equals(userSex)) {
            coreDataWrapper.sexHeaderRes = R.mipmap.icon_header_man;
        } else {
            coreDataWrapper.sexHeaderRes = R.mipmap.icon_header_null;
        }

        if("1".equals(ffpIdentify)) {
            coreDataWrapper.ffpIdentifyRes = R.mipmap.bank_identify;
        }
    }


    /**
     * 请求用户里程
     */
    public void requestMileData() {
        queryMiles parameters = new queryMiles();
        parameters._QUERY_MILES_CONDITION = new queryMilesConditionVO();
        parameters._QUERY_MILES_CONDITION._USER_ID = userId;
        parameters._QUERY_MILES_CONDITION._CRM_MEMBER_ID = crmId;
        parameters._QUERY_MILES_CONDITION._CRM_LEVEL = phoenixCardLevel;
        CRMFrequentFlyerWebServiceImplServiceSoapBinding bind = new CRMFrequentFlyerWebServiceImplServiceSoapBinding("queryMiles",parameters);
        RxRepUtils.getResult(dataSource.getNetApi(LoginApi.class).queryMile(bind), response -> {
            if(response.code == 0 && response.data != null && response.data._QUERY_MILES_RESULT != null) {
                if(response.data._QUERY_MILES_RESULT._FLIGHT_MILES != null) {
                    userMile = response.data._QUERY_MILES_RESULT._FLIGHT_MILES._SURPLUS_MILES;
                    coreDataWrapper.userMile = userMile;
                    coreDataWrapper.expiredMiles = response.data._QUERY_MILES_RESULT._FLIGHT_MILES._EXPIRED_MILES;
                    coreDataWrapper.nextExpiredMiles = response.data._QUERY_MILES_RESULT._FLIGHT_MILES._NEXT_EXPIRED_MILES;
                    resetCoreDataWrapper();
                    notifyCoreDataChange(getCoreDataWrapper(true,MILE));
                }
            }
        });
    }

    public void requestUserCouponNum() {
        userCouponSearchConditionVO reqvo = new userCouponSearchConditionVO();
        reqvo._USER_ID = userId;

        queryUseCouponCnt queryCoupon = new queryUseCouponCnt();
        queryCoupon._USECOUPON_CNT_CONDITION = reqvo;
        UserCouponSearchWebServiceServiceSoapBinding bind = new UserCouponSearchWebServiceServiceSoapBinding("queryUseCouponCnt",queryCoupon);

        RxRepUtils.getResult(dataSource.getNetApi(LoginApi.class).queryUserCouponCount(bind), response -> {
            if(response.code == 0 && response.data != null && response.data._USECOUPON_CNT_RESULT != null && "0".equals(response.data._USECOUPON_CNT_RESULT._OP_RESULT)) {
                userCouponCount = response.data._USECOUPON_CNT_RESULT._COUNT;
                coreDataWrapper.couponCount = userCouponCount;
                notifyCoreDataChange(getCoreDataWrapper(true,MINE));
            }
        });
    }

    public void requestWalletInfo() {
        WALLET_QUERY walletQuery = new WALLET_QUERY();
        walletQuery._USER_ID = userId;
        walletQuery._BANKCARD_TYPE = "0";

        walletInfoQuery walletInfoQuery = new walletInfoQuery();
        walletInfoQuery._WALLET_QUERY_CONDITION = walletQuery;
        EasyCardWebServiceServiceSoapBinding binding = new EasyCardWebServiceServiceSoapBinding("walletInfoQuery",walletInfoQuery);
        RxRepUtils.getResult(dataSource.getNetApi(LoginApi.class).querywalletInfo(binding), response -> {
            if(response.code == 0 && response.data != null && response.data._WALLET_QUERY_RESULT != null) {
                String ecardNum = response.data._WALLET_QUERY_RESULT._EASYCARD_COUNT;
                coreDataWrapper.ecardNum = "0".equals(ecardNum)? "--" : ecardNum;
                coreDataWrapper.walletLeftMoney = response.data._WALLET_QUERY_RESULT._USE_AMT;
                notifyCoreDataChange(getCoreDataWrapper(true,MINE));
            }
        });

    }

    private void prepareLoginCache(String userCount, String pwd) {
        UserLoginInfo loginInfo = dataSource.getCacheEntity(DataSource.DISK, userCount + pwd);
        if (loginInfo != null && loginInfo.userInfo != null) {
            Logger.i("DataCore--prepareLoginCache" + loginInfo.userInfo.toString());
            initLoginData(loginInfo.userInfo);
            notifyCoreDataChange(getCoreDataWrapper(true, LOGIN));
        }
    }



    private int requestHomeCount = 0;
    private void requestHomeConfig() {
        long taskId = RxRepUtils.getHomeConfigResult(dataSource.getNetApi(BuildConfig.CONFIG_URL, ConfigApi.class).getImageConfig(), homeConfigData -> {
            if (homeConfigData.netCode == 0) {
                this.homeConfigData = homeConfigData;
                dataSource.cacheData(DataSource.DISK, Constants.HOMECONFIG, homeConfigData);
                notifyCoreDataChange(getCoreDataWrapper(true, HOME));
//                cancelHomeTask();
            } else {
                if (requestHomeCount < 3) {
                    requestHomeCount++;
                    requestHomeConfig();
                } else {
                    notifyCoreDataChange(getCoreDataWrapper(false, HOME));
                }
            }
        });
    }


    public Version getVersion() {
        return version;
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


    public void registerDataTypeObaserver(LifecycleOwner lifecycleOwner, Observer<CoreDataWrapper> typeObserver) {
        if (!observers.containsKey(typeObserver)) {
            MutableLiveData<CoreDataWrapper> dataTypeLive = new MutableLiveData<>();
            dataTypeLive.observe(lifecycleOwner, typeObserver);
            observers.put(typeObserver, dataTypeLive);
        }
    }

    public void removeForeverObserve(Observer<CoreDataWrapper> typeObserver) {
        Object object = observers.remove(typeObserver);
        Logger.i("DataCore----removeForeverObserve--" + object);
    }

    private void notifyCoreDataChange(CoreDataWrapper coreDataWrapper) {
        Logger.i("DataCore----notifyCoreDataChange-" + coreDataWrapper.toString());
        if (observers != null && observers.size() > 0) {
            for (MutableLiveData<CoreDataWrapper> dataTypeLive : observers.values()) {
                dataTypeLive.postValue(coreDataWrapper);

            }
        }
    }

    private CoreDataWrapper getCoreDataWrapper(boolean isSuccess, int dataType) {
        coreDataWrapper.isSuccess = isSuccess;
        coreDataWrapper.dataType = dataType;
        if (isSuccess) {
            if (VERSION == dataType) {
                if (configData != null && configData.androidversionnew != null) {
                    coreDataWrapper.version = configData.androidversionnew;
                }
            } else if (HOME == dataType) {
                if (homeConfigData != null) {
                    coreDataWrapper.homeConfigData = homeConfigData;
                }
            } else if (LOGIN == dataType) {
                if (userAllInfo != null) {
                    coreDataWrapper.userAllInfo = userAllInfo;
                }
            } else if (MINE == dataType) {

            }
        }
        return coreDataWrapper;

    }

    public CoreDataWrapper getCoreDataWrapper() {
        return coreDataWrapper;
    }

    public class CoreDataWrapper {
        public boolean isSuccess = true;
        public int dataType = -1;
        public VersionUpdate versionUpdate;
        public Version version;
        public ConfigData configData;
        public HomeImageInfo homeConfigData;
        public queryRespVO userAllInfo;

        public String userName = "--";
        public String userNameCh;
        public String userNameEn;
        public int    sexHeaderRes = R.mipmap.icon_header_null;
        public String idNumber;
        public String userId;
        public String crmId;
        public String phoenixNumber;
        public ArrayList<String> idNumberList;                 //用户证件数组
        public ArrayList<String> phoenixIdList;                 //凤凰知音证件列表
        public String cardLevel;
        public int cardLevelRes;
        public int cardLevelImgRes;
        public String ffpIdentify;
        public int  ffpIdentifyRes;
        public String userMile = "--";
        public String expiredMiles = "--";
        public String nextExpiredMiles = "--";
        public String ecardNum = "--";
        public String walletLeftMoney = "--";
        public String couponCount = "--";

        @Override
        public String toString() {
            return "CoreDataWrapper{" +
                    "isSuccess=" + isSuccess +
                    ", dataType=" + dataType +
                    ", version=" + version +
                    ", configData=" + configData +
                    ", homeConfigData=" + homeConfigData +
                    ", userAllInfo=" + userAllInfo +
                    ", userName='" + userName + '\'' +
                    ", sexHeaderRes=" + sexHeaderRes +
                    ", idNumber='" + idNumber + '\'' +
                    ", userId='" + userId + '\'' +
                    ", cardLevel='" + cardLevel + '\'' +
                    ", cardLevelImgRes=" + cardLevelImgRes +
                    ", userMile='" + userMile + '\'' +
                    ", expiredMiles='" + expiredMiles + '\'' +
                    ", nextExpiredMiles='" + nextExpiredMiles + '\'' +
                    ", ecardNum='" + ecardNum + '\'' +
                    ", walletLeftMoney='" + walletLeftMoney + '\'' +
                    ", couponCount='" + couponCount + '\'' +
                    '}';
        }
    }
}
