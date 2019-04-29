package com.heaven.news.engine;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.heaven.data.manager.DataSource;
import com.heaven.news.BuildConfig;
import com.heaven.news.R;
import com.heaven.news.api.ConfigApi;
import com.heaven.news.api.VersionApi;
import com.heaven.news.ui.vm.model.base.ConfigData;
import com.heaven.news.ui.vm.model.base.EasyGoService;
import com.heaven.news.ui.vm.model.base.HomeService;
import com.heaven.news.ui.vm.model.base.PhoenixService;
import com.heaven.news.ui.vm.model.base.TimeStamp;
import com.heaven.news.ui.vm.model.base.VersionUpdate;
import com.heaven.news.utils.IoUtil;
import com.heaven.news.utils.RxRepUtils;
import com.neusoft.szair.model.city.CityListWebServiceServiceSoapBinding;
import com.neusoft.szair.model.city.cityListVO;
import com.neusoft.szair.model.city.queryCityList;
import com.orhanobut.logger.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: com.heaven.news.engine.ConfigManager.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-27 18:51
 *
 * @version V1.0 配置数据管理
 */
public class ConfigManager {
    public static int VERSION = 0;
    public static String CITY = "city";
    public static String CITY_EN = "city_en";
    public static String CITY_OFTEN = "city_often";
    public static String CITY_HOT = "city_hot";
    public static String CITY_INDEX = "city_index";
    public boolean isRequestVersionFinish = false;
    private ConfigWrapper configWrapper = new ConfigWrapper();
    private ConfigData configData;
    private Map<Observer<ConfigWrapper>, MutableLiveData<ConfigWrapper>> observers = new HashMap<>();
    private Context context;
    private DataSource dataSource;

    private HomeService homeService;//首页服务
    private EasyGoService easyGoService;//易行服务
    private PhoenixService phoenixService;//凤凰知音服务
    private List<cityListVO> citys = new ArrayList<>();
    private List<cityListVO> citysEn = new ArrayList<>();
    private List<cityListVO> citysOften = new ArrayList<>();
    private List<cityListVO> citysHot = new ArrayList<>();
    private List<cityListVO> citysIndex = new ArrayList<>();

    ConfigManager(DataSource dataSource, Context context) {
        this.context = context;
        this.dataSource = dataSource;
        dataSource.runWorkThread(() -> loadAllService(context));
    }

    private void loadAllService(Context context) {
        requestVersion();
        requestConfig();
        loadHomeService(context);
        loadEasyGoService(context);
        loadPhoenixService(context);
        initLocalCity();
    }

    public HomeService loadHomeService(Context context) {
        if (homeService == null) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            InputStream allServiceIn = context.getResources().openRawResource(R.raw.home);
            Reader readerAll = new InputStreamReader(allServiceIn);
            homeService = gson.fromJson(readerAll, HomeService.class);
        }
        return homeService;
    }

    public EasyGoService loadEasyGoService(Context context) {
        if (easyGoService == null) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            InputStream allServiceIn = context.getResources().openRawResource(R.raw.easygo);
            Reader readerAll = new InputStreamReader(allServiceIn);
            easyGoService = gson.fromJson(readerAll, EasyGoService.class);
        }
        return easyGoService;
    }

    public PhoenixService loadPhoenixService(Context context) {
        if (phoenixService == null) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            InputStream allServiceIn = context.getResources().openRawResource(R.raw.phoenix);
            Reader readerAll = new InputStreamReader(allServiceIn);
            phoenixService = gson.fromJson(readerAll, PhoenixService.class);
        }
        return phoenixService;
    }

    private void requestVersion() {
        RxRepUtils.getConfigResult(dataSource.getNetApi(BuildConfig.VERSION_URL, VersionApi.class).getVersion(), configData -> {
            isRequestVersionFinish = true;
            if (configData != null && configData.netCode == 0) {
                if (configData.timestamp != null && configData.androidversionnew != null) {
                    RxRepUtils.cancelTask(reqverTaskId);
                }
                configSuccess(configData);
            }
            ConfigWrapper dataWrapper = getConfigDataWrapper(true, VERSION);
            notifyConfigDataChange(dataWrapper);
            Logger.i("requestVersion" + configData.toString());
        });
    }

    private int requestConfigCount = 0;
    private long reqverTaskId;

    private void requestConfig() {
        reqverTaskId = RxRepUtils.getConfigResult(dataSource.getNetApi(BuildConfig.CONFIG_URL, ConfigApi.class).getConfig(), configData -> {
            if (configData != null && configData.netCode == 0) {
                ConfigWrapper dataWrapper = getConfigDataWrapper(true, VERSION);
                notifyConfigDataChange(dataWrapper);
                configSuccess(configData);
            } else {
                if (requestConfigCount < 3) {
                    requestConfigCount++;
                    requestConfig();
                }
            }
            Logger.i("requestVersion" + configData.toString());
        });
    }

    private void configSuccess(ConfigData configData) {
        this.configData = configData;
        if (configData.timestamp != null) {
            refreshConfigByTimeStamp(configData.timestamp);
        }

        if(configData.city != null && configData.city.size() > 0) {
            initHotCity(configData.city);
        }
    }

    private void refreshConfigByTimeStamp(TimeStamp newStamp) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        InputStream allServiceIn = context.getResources().openRawResource(R.raw.timestamp);
        Reader readerAll = new InputStreamReader(allServiceIn);
        TimeStamp oldStamp = gson.fromJson(readerAll, TimeStamp.class);
        compareTimeStamp(newStamp, oldStamp);
    }

    private void compareTimeStamp(TimeStamp newStamp, TimeStamp oldStamp) {
        if (newStamp != null && oldStamp != null) {
            if (newStamp.CITY > oldStamp.CITY) {
                reqNewCity();
            }

        }
    }


    private void initLocalCity() {
        citys = loadLocalCityCh();
        citysEn = loadLocalCityEn();
        citysIndex = loadLocalCityIndex();
        citysHot = dataSource.getCacheEntity(DataSource.DISK, CITY_HOT);
    }

    private void initHotCity(List<String> hotCitys) {
        citysHot.clear();
        for(String sanCode : hotCitys) {
            cityListVO hotCity = getCityBySanCode(sanCode);
            if(hotCity != null) {
                citysHot.add(hotCity);
            }
        }
        if(citysHot != null && citysHot.size() > 0) {
            dataSource.cacheData(DataSource.DISK, CITY_HOT, citysHot);
        }
        testWriteCity(citysIndex);
        Logger.i(citysHot.toString());
    }

    public List<cityListVO> getHotCity() {
        return citysHot;
    }

    private List<cityListVO> loadLocalCityIndex() {
        ArrayList<cityListVO>  citysIndex = dataSource.getCacheEntity(DataSource.DISK, CITY_INDEX);
        if(citysIndex == null) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            InputStream allServiceIn = context.getResources().openRawResource(R.raw.city);
            Reader readerAll = new InputStreamReader(allServiceIn);
            citysIndex = gson.fromJson(readerAll, new TypeToken<List<cityListVO>>() {
            }.getType());
            sortCityIndex(citysIndex);
        }
        return citysIndex;
    }

    private List<cityListVO> loadLocalCityCh() {
      ArrayList<cityListVO>  citys = dataSource.getCacheEntity(DataSource.DISK, CITY);
        if(citys == null) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            InputStream allServiceIn = context.getResources().openRawResource(R.raw.city);
            Reader readerAll = new InputStreamReader(allServiceIn);
            citys = gson.fromJson(readerAll, new TypeToken<List<cityListVO>>() {
            }.getType());
            sortCity(citys);
        }
        return citys;
    }

    private List<cityListVO> loadLocalCityEn() {
       ArrayList<cityListVO> citysEn = dataSource.getCacheEntity(DataSource.DISK, CITY_EN);
        if(citysEn == null) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            InputStream allServiceIn = context.getResources().openRawResource(R.raw.city);
            Reader readerAll = new InputStreamReader(allServiceIn);
            citysEn = gson.fromJson(readerAll, new TypeToken<List<cityListVO>>() {
            }.getType());
            sortCityEn(citysEn);
        }
        return citysEn;
    }

    public List<cityListVO> loadCitys(int cityType) {
        if(0 == cityType) {
            if(citys != null && citys.size() > 0) {
                return citys;
            } else {
                return loadLocalCityCh();
            }
        } else {
            if(citysEn != null && citysEn.size() > 0) {
                return citysEn;
            } else {
                return loadLocalCityEn();
            }
        }
    }

    private void reqNewCity() {
        queryCityList queryCityList = new queryCityList();
        CityListWebServiceServiceSoapBinding binding = new CityListWebServiceServiceSoapBinding("queryCityList", queryCityList);
        RxRepUtils.getResult(RxRepUtils.getCommonApi().searchNewCity(binding), response -> {
            if (response.code == 0 && response.data != null && response.data._CITY_LIST_VO != null && response.data._CITY_LIST_VO._CITY_LIST_VO != null) {
                loadNewCitys(response.data._CITY_LIST_VO._CITY_LIST_VO);
            }
            Logger.i("reqNewCity" + response.toString());
        });
    }

    private void testWriteCity(List<cityListVO> citys) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        IoUtil.save("heaven",gson.toJson(citys));
    }

    private void loadNewCitys(List<cityListVO> newCitys) {
        if (newCitys != null && newCitys.size() > 0) {
            ArrayList<cityListVO> newCitysCh = new ArrayList<>(newCitys);
            ArrayList<cityListVO> newCitysEn = new ArrayList<>(newCitys);
            ArrayList<cityListVO> newCitysIndex = new ArrayList<>(newCitys);
            sortCity(newCitysCh);
            sortCityEn(newCitysEn);
            sortCityIndex(newCitysIndex);
            this.citys = newCitysCh;
            this.citysEn = newCitysEn;
            this.citysIndex = newCitysIndex;
            dataSource.cacheData(DataSource.DISK, CITY, newCitysCh);
            dataSource.cacheData(DataSource.DISK, CITY_EN, newCitysEn);
            dataSource.cacheData(DataSource.DISK, CITY_INDEX, newCitysIndex);
        }
    }

    private void loadNewInSurance() {

    }

    private void loadNewSplMeal() {

    }

    private void loadNewBank() {

    }

    private cityListVO getCityBySanCode(String sanCode) {
        cityListVO target = null;
        List<cityListVO> cityIndexs = citysIndex.size() > 0? citysIndex : loadLocalCityIndex();
        if(cityIndexs != null && cityIndexs.size() > 0) {
            cityListVO condition = new cityListVO();
            condition._SHORT_NAME = sanCode;
            int index = Collections.binarySearch(cityIndexs, condition);
            if(index >= 0) {
                target = cityIndexs.get(index);
            }
        }
        return target;
    }


    private void sortCity(List<cityListVO> citys) {
        Collections.sort(citys, (o1, o2) -> {
            char cityO1 = TextUtils.isEmpty(o1._PY_NAME) ? ' ' : Character.toUpperCase(o1._PY_NAME.charAt(0));
            char cityO2 = TextUtils.isEmpty(o2._PY_NAME) ? ' ' : Character.toUpperCase(o2._PY_NAME.charAt(0));

            int compare = 0;
            if (cityO1 > cityO2) {
                compare = 1;
            } else if (cityO1 < cityO2) {
                compare = -1;
            }

            return compare;
        });
    }

    private void sortCityEn(List<cityListVO> citys) {
        Collections.sort(citys, (o1, o2) -> {
            char cityO1 = TextUtils.isEmpty(o1._FULLNAME_EN) ? ' ' : Character.toUpperCase(o1._FULLNAME_EN.charAt(0));
            char cityO2 = TextUtils.isEmpty(o2._FULLNAME_EN) ? ' ' : Character.toUpperCase(o2._FULLNAME_EN.charAt(0));

            int compare = 0;
            if (cityO1 > cityO2) {
                compare = 1;
            } else if (cityO1 < cityO2) {
                compare = -1;
            }

            return compare;
        });
    }

    private void sortCityIndex(List<cityListVO> citys) {
        Collections.sort(citys, (o1, o2) -> {
            String cityO1 = TextUtils.isEmpty(o1._SHORT_NAME) ? "" : o1._SHORT_NAME.toUpperCase();
            String cityO2 = TextUtils.isEmpty(o2._SHORT_NAME) ? "" : o2._SHORT_NAME.toUpperCase();
            return cityO1.compareTo(cityO2);
        });
    }

    public void registerDataTypeObaserver(LifecycleOwner lifecycleOwner, Observer<ConfigWrapper> typeObserver) {
        if (!observers.containsKey(typeObserver)) {
            MutableLiveData<ConfigWrapper> dataTypeLive = new MutableLiveData<>();
            dataTypeLive.observe(lifecycleOwner, typeObserver);
            observers.put(typeObserver, dataTypeLive);
        }
    }

    public void removeForeverObserve(Observer<ConfigWrapper> typeObserver) {
        Object object = observers.remove(typeObserver);
        Logger.i("ConfigManager----removeForeverObserve--" + object);
    }

    private void notifyConfigDataChange(ConfigWrapper configWrapper) {
        Logger.i("ConfigManager----notifyCoreDataChange-" + configWrapper.toString());
        if (observers != null && observers.size() > 0) {
            for (MutableLiveData<ConfigWrapper> dataTypeLive : observers.values()) {
                dataTypeLive.postValue(configWrapper);

            }
        }
    }

    private ConfigWrapper getConfigDataWrapper(boolean isSuccess, int dataType) {
        configWrapper.isSuccess = isSuccess;
        configWrapper.dataType = dataType;
        if (isSuccess) {
            if (VERSION == dataType) {
                if (configData != null && configData.androidversionnew != null) {
                    configWrapper.versionUpdate = CheckVersion.checkVersion(configData.androidversionnew, dataSource);
                }
            }
        }
        return configWrapper;

    }

    public class ConfigWrapper {
        public boolean isSuccess = true;
        public int dataType = -1;
        public VersionUpdate versionUpdate;
    }

    public ConfigWrapper getConfigWrapper() {
        return configWrapper;
    }
}
