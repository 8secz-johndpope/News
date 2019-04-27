package com.heaven.news.engine;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.heaven.data.manager.DataSource;
import com.heaven.news.R;
import com.heaven.news.ui.vm.model.base.City;
import com.heaven.news.ui.vm.model.base.EasyGoService;
import com.heaven.news.ui.vm.model.base.HomeService;
import com.heaven.news.ui.vm.model.base.PhoenixService;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.heaven.news.engine.ConfigManager.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-27 18:51
 *
 * @version V1.0 配置数据管理
 */
public class ConfigManager {
    private DataSource dataSource;

    private HomeService homeService;//首页服务
    private EasyGoService easyGoService;//易行服务
    private PhoenixService phoenixService;//凤凰知音服务
    private ArrayList<City> citys;
    private ArrayList<City> citysCh;
    private ArrayList<City> citysEn;
    private ArrayList<City> citysOften;

    ConfigManager(DataSource dataSource, Context context) {
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

    public ArrayList<City> loadCitys(Context context) {
        if (citys == null) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            InputStream allServiceIn = context.getResources().openRawResource(R.raw.city);
            Reader readerAll = new InputStreamReader(allServiceIn);
            citys = gson.fromJson(readerAll, new TypeToken<List<City>>() {
            }.getType());
        }
        return citys;
    }
}
