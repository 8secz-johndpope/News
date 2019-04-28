package com.heaven.news.engine;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.heaven.data.manager.DataSource;
import com.heaven.data.net.DataResponse;
import com.heaven.news.R;
import com.heaven.news.ui.vm.model.base.City;
import com.heaven.news.ui.vm.model.base.EasyGoService;
import com.heaven.news.ui.vm.model.base.HomeService;
import com.heaven.news.ui.vm.model.base.PhoenixService;
import com.heaven.news.ui.vm.model.base.TimeStamp;
import com.heaven.news.utils.RxRepUtils;
import com.neusoft.szair.model.city.CityListWebServiceServiceSoapBinding;
import com.neusoft.szair.model.city.queryCityList;
import com.neusoft.szair.model.city.queryCityListResponse;
import com.orhanobut.logger.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * FileName: com.heaven.news.engine.ConfigManager.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-27 18:51
 *
 * @version V1.0 配置数据管理
 */
public class ConfigManager {
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
}
