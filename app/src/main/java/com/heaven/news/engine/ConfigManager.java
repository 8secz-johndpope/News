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
import com.heaven.news.ui.vm.model.base.City;
import com.heaven.news.ui.vm.model.base.ConfigData;
import com.heaven.news.ui.vm.model.base.EasyGoService;
import com.heaven.news.ui.vm.model.base.HomeService;
import com.heaven.news.ui.vm.model.base.PhoenixService;
import com.heaven.news.ui.vm.model.base.TimeStamp;
import com.heaven.news.ui.vm.model.base.VersionUpdate;
import com.heaven.news.utils.RxRepUtils;
import com.neusoft.szair.model.city.CityListWebServiceServiceSoapBinding;
import com.neusoft.szair.model.city.queryCityList;
import com.orhanobut.logger.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
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
    private ConfigWrapper configWrapper = new ConfigWrapper();
    private ConfigData configData;
    private Map<Observer<ConfigWrapper>, MutableLiveData<ConfigWrapper>> observers = new HashMap<>();
    private Context context;
    private DataSource dataSource;

    private HomeService homeService;//首页服务
    private EasyGoService easyGoService;//易行服务
    private PhoenixService phoenixService;//凤凰知音服务
    private ArrayList<City> citys;
    private ArrayList<City> citysEn;
    private ArrayList<City> citysOften;

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
        loadCitys(context);
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
            if(configData != null && configData.netCode == 0) {
                configSuccess(configData);
            }
            ConfigWrapper dataWrapper = getConfigDataWrapper(true, VERSION);
            notifyConfigDataChange(dataWrapper);
        });
    }

    private int requestConfigCount = 0;
    private void requestConfig() {
        RxRepUtils.getConfigResult(dataSource.getNetApi(BuildConfig.CONFIG_URL, ConfigApi.class).getConfig(), configData -> {
            if(configData != null && configData.netCode == 0) {
                ConfigWrapper dataWrapper = getConfigDataWrapper(true, VERSION);
                notifyConfigDataChange(dataWrapper);
                configSuccess(configData);
            } else {
                if (requestConfigCount < 3) {
                    requestConfigCount++;
                    requestConfig();
                }
            }
        });
    }

    private void configSuccess(ConfigData configData) {

    }

    public void refreshConfigByTimeStamp(TimeStamp newStamp) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        InputStream allServiceIn = context.getResources().openRawResource(R.raw.timestamp);
        Reader readerAll = new InputStreamReader(allServiceIn);
        TimeStamp oldStamp = gson.fromJson(readerAll, TimeStamp.class);
        compareTimeStamp(newStamp,oldStamp);
    }

    private void compareTimeStamp(TimeStamp newStamp,TimeStamp oldStamp) {
        if(newStamp != null && oldStamp != null) {
            if(newStamp.CITY > oldStamp.CITY) {
                loadNewCity();
            }

        }
    }


    private void loadNewCity() {
        queryCityList queryCityList = new queryCityList();
        CityListWebServiceServiceSoapBinding binding = new CityListWebServiceServiceSoapBinding("queryCityList",queryCityList);
        RxRepUtils.getResult(RxRepUtils.getCommonApi().searchNewCity(binding), response -> {

        });
    }

    private void loadNewInSurance() {

    }

    private void loadNewSplMeal() {

    }

    private void loadNewBank() {

    }



    public ArrayList<City> loadCitys(Context context) {
        if (citys == null) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            InputStream allServiceIn = context.getResources().openRawResource(R.raw.city);
            Reader readerAll = new InputStreamReader(allServiceIn);
            citys = gson.fromJson(readerAll, new TypeToken<List<City>>() {
            }.getType());
            sortCity(citys);
            String result = gson.toJson(citys);
            Logger.json(gson.toJson(citys));
        }
        return citys;
    }

    private void sortCity(ArrayList<City> citys) {
        Collections.sort(citys, (o1, o2) -> {
            char cityO1 = TextUtils.isEmpty(o1._PY_NAME)? ' ' : o1._PY_NAME.charAt(0);
            char cityO2 = TextUtils.isEmpty(o2._PY_NAME)? ' ' : o2._PY_NAME.charAt(0);;

            int compare = 0;
            if(cityO1 > cityO2) {
                compare = 1;
            } else if(cityO1 < cityO2){
                compare = -1;
            }

            return compare;
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
                    configWrapper.versionUpdate = CheckVersion.checkVersion(configData.androidversionnew,dataSource);
                }
            }
        }
        return configWrapper;

    }

    public class ConfigWrapper{
        public boolean isSuccess = true;
        public int dataType = -1;
        public VersionUpdate versionUpdate;
    }
}
