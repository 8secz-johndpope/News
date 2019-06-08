package com.heaven.news.engine;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.heaven.base.ui.view.calendar.Calendar;
import com.heaven.base.ui.view.calendar.CalendarUtil;
import com.heaven.base.ui.view.calendar.FestivalDay;
import com.heaven.base.ui.view.calendar.FestivalDayGroup;
import com.heaven.base.ui.view.calendar.LunarCalendar;
import com.heaven.base.ui.view.calendar.Month;
import com.heaven.data.manager.DataSource;
import com.heaven.news.BuildConfig;
import com.heaven.news.R;
import com.heaven.news.api.ConfigApi;
import com.heaven.news.api.IApi;
import com.heaven.news.api.VersionApi;
import com.heaven.news.ui.vm.model.base.CityGroup;
import com.heaven.news.ui.vm.model.base.CityInfo;
import com.heaven.news.ui.vm.model.base.ConfigData;
import com.heaven.news.ui.vm.model.base.EasyGoService;
import com.heaven.news.ui.vm.model.base.HomeService;
import com.heaven.news.ui.vm.model.base.PhoenixService;
import com.heaven.news.ui.vm.model.base.TimeStamp;
import com.heaven.news.ui.vm.model.base.VersionUpdate;
import com.heaven.news.ui.vm.vmmodel.SelectDateViewModel;
import com.heaven.news.utils.DateUtil;
import com.heaven.news.utils.IoUtil;
import com.heaven.news.utils.RxRepUtils;
import com.neusoft.szair.model.city.CityListWebServiceServiceSoapBinding;
import com.neusoft.szair.model.city.cityListVO;
import com.neusoft.szair.model.city.queryCityList;
import com.orhanobut.logger.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
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
    public static int VERSION = 0;
    public static String CITY_CURRENT = "city_current";
    public static String CALENDAR_FESTIVAL = "calendar_festival";
    public static String CITY_INFO = "city_info";
    public static String CITY_LAST = "city_last";
    public static String CITY_OFTEN = "city_often";
    public static String CITY_HOT = "city_hot";
    SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy年MM月");
    private Lock lock = new ReentrantLock();


    public boolean isRequestVersionFinish = false;
    private ConfigWrapper configWrapper = new ConfigWrapper();
    private ConfigData configData;
    private Map<Observer<ConfigWrapper>, MutableLiveData<ConfigWrapper>> observers = new HashMap<>();
    private Context context;
    private DataSource dataSource;

    private HomeService homeService;//首页服务
    private EasyGoService easyGoService;//易行服务
    private PhoenixService phoenixService;//凤凰知音服务
    private cityListVO currentCity;
    private CityInfo cityInfo = new CityInfo();
    private HashMap<String, CityGroup> cityGroupChMap = new HashMap<>();
    private HashMap<String, CityGroup> cityGroupEnMap = new HashMap<>();
    private List<cityListVO> citysOften = new ArrayList<>();
    private List<cityListVO> citysHot = new ArrayList<>();
    private List<cityListVO> citysIndex = new ArrayList<>();
    private List<Month> calendars = new ArrayList<>();
    private List<FestivalDay> calendarFestivals = new ArrayList<>();
    private HashMap<String, FestivalDayGroup> festivalGroupMap = new HashMap<>();

    ConfigManager(DataSource dataSource, Context context) {
        this.context = context;
        this.dataSource = dataSource;
        requestVersion();
        requestConfig();
        requestCalendarFestival();
        dataSource.runWorkThread(this::initLocalCity);
        dataSource.runWorkThread(() -> initCalendar(context));
        dataSource.runWorkThread(() -> loadLocalService(context));
    }


    private void loadLocalService(Context context) {
        loadHomeService(context);
        loadEasyGoService(context);
        loadPhoenixService(context);
    }

    public List<Month> loadMonth() {
        if (calendars.size() == 0) {
            initCalendar(context);
        } else {
            Calendar currentDate = DateUtil.getCurrentDate();
            Month localCurrentDate = calendars.get(0);
            if (currentDate.getYear() != localCurrentDate.year || currentDate.getMonth() != localCurrentDate.month) {
                initCalendar(context);
            }
        }
        return IoUtil.deepCopyList(calendars);
    }

    public CityInfo loadAllCity() {
        if (cityInfo.chIndexs.size() == 0 || cityInfo.chCityGroups.size() == 0) {
            List<cityListVO> lastCitys = dataSource.getCacheEntity(DataSource.DISK, CITY_LAST);
            if (lastCitys == null || lastCitys.size() == 0) {
                lastCitys = loadLocalRawCity();
                try {
                    lock.lock();
                    groupCityBy(lastCitys);
                } finally {
                    lock.unlock();
                }
            }
            return IoUtil.deepCopyList(cityInfo);

        } else {
            return IoUtil.deepCopyList(cityInfo);
        }
    }


    public ConfigData loadConfigData() {
        return configData;
    }


    private void initCalendar(Context context) {
        try{
            lock.lock();
            calendarFestivals = dataSource.getCacheEntity(DataSource.DISK, CALENDAR_FESTIVAL);
            calendars.clear();
            LunarCalendar.init(context);
            Calendar mCurrentDate = DateUtil.getCurrentDate();

            java.util.Calendar startCal = java.util.Calendar.getInstance();     //开始年月
            //开始日期设为月的第一天，结束日期设为最后一天
            startCal.set(java.util.Calendar.DAY_OF_MONTH, 1);

            java.util.Calendar month = java.util.Calendar.getInstance();
            for (int position = 0; position < 12; position++) {
                month.setTime(startCal.getTime());
                month.add(java.util.Calendar.MONTH, position);
                createMonthData(month, mCurrentDate);
            }

            mergeFestival(calendars,calendarFestivals);
        } catch (Exception e) {
            lock.unlock();
        }
    }

    private void mergeFestival(List<Month> calendars,List<FestivalDay> festivals) {
        if(calendars.size() > 0 && festivals != null && festivals.size() > 0) {
            festivalGroupMap.clear();
            final Disposable subscribe = Flowable.fromIterable(festivals).groupBy(festivalDay -> festivalDay.date).subscribe(festivalDayGroupedFlowable -> {
                final Disposable subscribe1 = festivalDayGroupedFlowable.subscribe(new ConfigManager.FestivalGroupConsume(festivalDayGroupedFlowable.getKey()));
            });
            for(Month month : calendars) {
                String monthKey = month.year + "-" + (month.month >= 10? month.month : "0" + month.month) ;
                if(festivalGroupMap.containsKey(monthKey)) {
                    FestivalDayGroup festivalDayGroup = festivalGroupMap.get(monthKey);
                    if(festivalDayGroup != null) {
                        festivalDayGroup.updateCalendarFestival(month.days);
                        Logger.i("mergeFestival--" + monthKey);
                    }
                }
            }
            Logger.i("mergeFestival--" + "finish");
        }

    }


    private void createMonthData(java.util.Calendar month, Calendar currentDate) {
        Date date = month.getTime();
        Month monthWrapper = new Month();
        monthWrapper.title = monthFormat.format(date);
        monthWrapper.year = (CalendarUtil.getDate("yyyy", date));
        monthWrapper.month = (CalendarUtil.getDate("MM", date));
        monthWrapper.addDayInMonth(CalendarUtil.initCalendarForMonthView(monthWrapper.year, monthWrapper.month, currentDate, 1));
        calendars.add(monthWrapper);
        Logger.i("initCalendar--" + monthWrapper.title);
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
        RxRepUtils.getConfigResult(dataSource.getNetApi(IApi.class).getVersion(BuildConfig.CONFIG_URL + "config.json"), versionData -> {
            isRequestVersionFinish = true;
            if (!TextUtils.isEmpty(versionData)) {
                this.configData = JSON.parseObject(versionData, ConfigData.class);
                ConfigWrapper dataWrapper = getConfigDataWrapper(true, VERSION);
                notifyConfigDataChange(dataWrapper);
                if (configData != null && configData.timestamp != null) {
                    RxRepUtils.cancelTask(reqverTaskId);
                    configSuccess(configData);
                }
            } else {
                ConfigWrapper dataWrapper = getConfigDataWrapper(true, VERSION);
                notifyConfigDataChange(dataWrapper);
            }
            Logger.i("requestVersion" + configData.toString());
        });
    }

    private int requestConfigCount = 0;
    private long reqverTaskId;

    private void requestConfig() {
        reqverTaskId = RxRepUtils.getConfigResult(dataSource.getNetApi(IApi.class).getConfig(BuildConfig.CONFIG_URL+"config.json"), configData -> {
            if (!TextUtils.isEmpty(configData)) {
                this.configData = JSON.parseObject(configData, ConfigData.class);
                ConfigWrapper dataWrapper = getConfigDataWrapper(true, VERSION);
                notifyConfigDataChange(dataWrapper);
                configSuccess(this.configData);
            } else {
                if (requestConfigCount < 3) {
                    requestConfigCount++;
                    requestConfig();
                }
            }
            Logger.i("requestVersion" + configData.toString());
        });
    }

    private void requestCalendarFestival() {
        reqverTaskId = RxRepUtils.getConfigResult(dataSource.getNetApi(IApi.class).getCalendarFestivalConfig(BuildConfig.CONFIG_URL + "calendar.json"), configData -> {
            if (!TextUtils.isEmpty(configData)) {
                Type type = new TypeReference<List<FestivalDay>>() {
                }.getType();
                calendarFestivals = JSON.parseObject(configData, type);
                if (calendarFestivals != null && calendarFestivals.size() > 0) {
                    cacheData(CALENDAR_FESTIVAL, calendarFestivals);
                    initCalendar(context);
                }
            }
        });
    }

    public List<FestivalDay> getFestival() {
        return calendarFestivals;
    }

    private void configSuccess(ConfigData configData) {
        if (configData != null) {
            if (configData.timestamp != null) {
                refreshConfigByTimeStamp(configData.timestamp);
            }

            if (configData.city != null && configData.city.size() > 0) {
                initHotCity(configData.city);
            }


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
        currentCity = dataSource.getCacheEntity(DataSource.DISK, CITY_CURRENT);
        if (currentCity == null) {
            currentCity = getCityBySanCode("SZX");
        }
        List<cityListVO> hotsCitys = dataSource.getCacheEntity(DataSource.DISK, CITY_HOT);
        if (hotsCitys != null && hotsCitys.size() > 0) {
            citysHot = hotsCitys;
        }

        List<cityListVO> oftenCitys = dataSource.getCacheEntity(DataSource.DISK, CITY_OFTEN);
        if (oftenCitys != null && oftenCitys.size() > 0) {
            citysOften = oftenCitys;
        }

        List<cityListVO> lastCitys = dataSource.getCacheEntity(DataSource.DISK, CITY_LAST);
        if (lastCitys == null || lastCitys.size() == 0) {
            lastCitys = loadLocalRawCity();
        }
        groupCityBy(lastCitys);

    }

    private void groupCityBy(List<cityListVO> citys) {
        cityGroupChMap.clear();
        cityGroupEnMap.clear();
        final Disposable subscribe = Flowable.fromIterable(citys).groupBy(cityListVO -> String.valueOf(Character.toUpperCase(cityListVO._PY_NAME.charAt(0)))).subscribe(groupedFlowable -> {
            final Disposable subscribe1 = groupedFlowable.subscribe(new GroupCityConsume(groupedFlowable.getKey(), 0));
        });

        final Disposable subscribe1 = Flowable.fromIterable(citys).groupBy(cityListVO -> String.valueOf(Character.toUpperCase(cityListVO._FULLNAME_EN.charAt(0)))).subscribe(groupedFlowable -> {
            final Disposable subscribe2 = groupedFlowable.subscribe(new GroupCityConsume(groupedFlowable.getKey(), 1));
        });

        CityGroup currentCityGroup = new CityGroup();
        currentCityGroup.groupTitle = context.getString(R.string.current_city_title);
        currentCityGroup.groupFlag = 1;
        currentCityGroup.addCity(currentCity);

        CityGroup offtenCityGroup = new CityGroup();
        offtenCityGroup.groupTitle = context.getString(R.string.offten_city_title);
        ;
        offtenCityGroup.groupFlag = 2;
        offtenCityGroup.addCityAll(citysOften);

        CityGroup hotCityGroup = new CityGroup();
        hotCityGroup.groupTitle = context.getString(R.string.hot_city_title);
        ;
        hotCityGroup.groupFlag = 3;
        hotCityGroup.addCityAll(citysHot);

        cityGroupChMap.put(currentCityGroup.groupTitle, currentCityGroup);
        cityGroupChMap.put(offtenCityGroup.groupTitle, offtenCityGroup);
        cityGroupChMap.put(hotCityGroup.groupTitle, hotCityGroup);

        cityGroupEnMap.put(currentCityGroup.groupTitle, currentCityGroup);
        cityGroupEnMap.put(offtenCityGroup.groupTitle, offtenCityGroup);
        cityGroupEnMap.put(hotCityGroup.groupTitle, hotCityGroup);

        List<Map.Entry<String, CityGroup>> cityListCh = new ArrayList<>(cityGroupChMap.entrySet());
        Collections.sort(cityListCh, (o1, o2) -> {
            if (context.getString(R.string.current_city_title).equals(o1.getKey())) {
                return -1;
            } else if (context.getString(R.string.current_city_title).equals(o2.getKey())) {
                return 1;
            } else if (context.getString(R.string.offten_city_title).equals(o1.getKey())) {
                return -1;
            } else if (context.getString(R.string.offten_city_title).equals(o2.getKey())) {
                return 1;
            } else if (context.getString(R.string.hot_city_title).equals(o1.getKey())) {
                return -1;
            } else if (context.getString(R.string.hot_city_title).equals(o2.getKey())) {
                return 1;
            } else {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        List<Map.Entry<String, CityGroup>> cityListEn = new ArrayList<>(cityGroupEnMap.entrySet());
        Collections.sort(cityListEn, (o1, o2) -> {
            if (context.getString(R.string.current_city_title).equals(o1.getKey())) {
                return -1;
            } else if (context.getString(R.string.current_city_title).equals(o2.getKey())) {
                return 1;
            } else if (context.getString(R.string.offten_city_title).equals(o1.getKey())) {
                return -1;
            } else if (context.getString(R.string.offten_city_title).equals(o2.getKey())) {
                return 1;
            } else if (context.getString(R.string.hot_city_title).equals(o1.getKey())) {
                return -1;
            } else if (context.getString(R.string.hot_city_title).equals(o2.getKey())) {
                return 1;
            } else {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        cityInfo.clearData();

        final Disposable subscribe2 = Flowable.fromIterable(cityListCh).subscribe(groupEntry -> {
            cityInfo.chIndexs.add(groupEntry.getKey());
            cityInfo.chCityGroups.add(groupEntry.getValue());
        });

        final Disposable subscribe3 = Flowable.fromIterable(cityListEn).subscribe(groupEntry -> {
            cityInfo.enIndexs.add(groupEntry.getKey());
            cityInfo.enCityGroups.add(groupEntry.getValue());
        });

        sortCityIndex(citys);
        citysIndex = citys;
        cacheData(CITY_INFO, null);
        cacheData(CITY_LAST, citys);
        Logger.i("groupCityBy---" + cityInfo.toString());
    }

    private void cacheData(String type, Object entity) {
        if (CITY_INFO.equals(type)) {
            if (cityInfo != null && cityInfo.chCityGroups.size() > 0) {
                dataSource.cacheData(DataSource.DISK, CITY_INFO, cityInfo);
            }
        } else if (CITY_LAST.equals(type)) {
            dataSource.cacheData(DataSource.DISK, CITY_LAST, entity);
        } else if (CITY_HOT.equals(type)) {
            dataSource.cacheData(DataSource.DISK, CITY_HOT, entity);
        } else if (CITY_OFTEN.equals(type)) {
            dataSource.cacheData(DataSource.DISK, CITY_OFTEN, entity);
        } else if (CALENDAR_FESTIVAL.equals(type)) {
            dataSource.cacheData(DataSource.DISK, CALENDAR_FESTIVAL, entity);
        }
    }


    private void initHotCity(List<String> hotCitys) {
        citysHot.clear();
        for (String sanCode : hotCitys) {
            cityListVO hotCity = getCityBySanCode(sanCode);
            if (hotCity != null) {
                citysHot.add(hotCity);
            }
        }
        if (citysHot != null && citysHot.size() > 0) {
            cacheData(CITY_HOT, citysHot);
        }
    }

    private List<cityListVO> loadLocalCityIndex() {
        List<cityListVO> citysIndex = dataSource.getCacheEntity(DataSource.DISK, CITY_LAST);
        if (citysIndex == null) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            InputStream allServiceIn = context.getResources().openRawResource(R.raw.city);
            Reader readerAll = new InputStreamReader(allServiceIn);
            citysIndex = gson.fromJson(readerAll, new TypeToken<List<cityListVO>>() {
            }.getType());
            sortCityIndex(citysIndex);
            this.citysIndex = citysIndex;
        }
        return citysIndex;
    }

    private List<cityListVO> loadLocalRawCity() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        InputStream allServiceIn = context.getResources().openRawResource(R.raw.city);
        Reader readerAll = new InputStreamReader(allServiceIn);
        List<cityListVO> localRawCitys = gson.fromJson(readerAll, new TypeToken<List<cityListVO>>() {
        }.getType());
        return localRawCitys;
    }

    @SuppressLint("CheckResult")
    public void saveCityOften(cityListVO cityOften) {
        if (citysOften != null && cityOften != null) {
            if (citysOften.size() == 0) {
                citysOften.add(0, cityOften);
            } else {
                ArrayList<cityListVO> existList = new ArrayList<>();
                Flowable.fromIterable(citysOften).filter(cityListVO -> !TextUtils.isEmpty(cityOften._SHORT_NAME) && cityOften._SHORT_NAME.equals(cityListVO._SHORT_NAME)).subscribe(existList::add);

                if (existList.size() > 0) {
                    citysOften.removeAll(existList);
                }

                if (citysOften.size() < 9) {
                    citysOften.add(0, cityOften);
                } else {
                    citysOften.remove(citysOften.size() - 1);
                    citysOften.add(0, cityOften);
                }
            }
            if (citysOften != null && citysOften.size() > 0) {
                cacheData(CITY_OFTEN, citysOften);
            }
        }
    }

    private void reqNewCity() {
        queryCityList queryCityList = new queryCityList();
        CityListWebServiceServiceSoapBinding binding = new CityListWebServiceServiceSoapBinding("queryCityList", queryCityList);
        RxRepUtils.getResultInThred(RxRepUtils.getCommonApi().searchNewCity(binding), response -> {
            if (response.code == 0 && response.data != null && response.data._CITY_LIST_VO != null && response.data._CITY_LIST_VO._CITY_LIST_VO != null) {
                List<cityListVO> newCitys = response.data._CITY_LIST_VO._CITY_LIST_VO;
                if (newCitys.size() > 0) {
                    try {
                        lock.lock();
                        groupCityBy(newCitys);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });
    }


    private void loadNewInSurance() {

    }

    private void loadNewSplMeal() {

    }

    private void loadNewBank() {

    }

    private cityListVO getCityBySanCode(String sanCode) {
        cityListVO target = null;
        List<cityListVO> cityIndexs = citysIndex.size() > 0 ? citysIndex : loadLocalCityIndex();
        if (cityIndexs != null && cityIndexs.size() > 0) {
            cityListVO condition = new cityListVO();
            condition._SHORT_NAME = sanCode;
            int index = Collections.binarySearch(cityIndexs, condition);
            if (index >= 0) {
                target = cityIndexs.get(index);
            }
        }
        return target;
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

    void removeForeverObserve(Observer<ConfigWrapper> typeObserver) {
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


    public class GroupCityConsume implements Consumer<cityListVO> {
        private String key;
        private int type = 0;//0中文 1英文

        public GroupCityConsume(String key, int type) {
            this.key = key;
            this.type = type;
        }

        @Override
        public void accept(cityListVO cityListVO) throws Exception {
            if (type == 0) {
                if (cityGroupChMap.containsKey(key)) {
                    CityGroup cityGroup = cityGroupChMap.get(key);
                    if (cityGroup != null) {
                        cityGroup.addCity(cityListVO);
                    }
                } else {
                    CityGroup cityGroup = new CityGroup();
                    cityGroup.groupTitle = key;
                    cityGroup.addCity(cityListVO);
                    cityGroupChMap.put(key, cityGroup);
                }

            } else {
                if (cityGroupEnMap.containsKey(key)) {
                    CityGroup cityGroup = cityGroupEnMap.get(key);
                    if (cityGroup != null) {
                        cityGroup.addCity(cityListVO);
                    }
                } else {
                    CityGroup cityGroup = new CityGroup();
                    cityGroup.groupTitle = key;
                    cityGroup.addCity(cityListVO);
                    cityGroupEnMap.put(key, cityGroup);
                }
            }
        }
    }


    private class FestivalGroupConsume implements Consumer<FestivalDay> {
        private String yearMonth;
        private int year;
        private int month;

        FestivalGroupConsume(String date) {
            if (!TextUtils.isEmpty(date) && date.contains("-")) {
                yearMonth = date.substring(0,date.lastIndexOf("-"));
                String[] dates = date.split("-");
                try {
                    year = Integer.parseInt(dates[0]);
                    month = Integer.parseInt(dates[1]);
                } catch (Exception e) {
                    Logger.i(e.getMessage());
                }
            }
        }

        @Override
        public void accept(FestivalDay festivalDay) throws Exception {
            festivalDay.year = year;
            festivalDay.month = month;
            if (festivalGroupMap.containsKey(yearMonth)) {
                FestivalDayGroup festivalDayGroup = festivalGroupMap.get(yearMonth);
                if (festivalDayGroup != null) {
                    festivalDayGroup.addFestivalDay(festivalDay);
                }
            } else {
                FestivalDayGroup festivalDayGroup = new FestivalDayGroup();
                festivalDayGroup.date = yearMonth;
                festivalDayGroup.addFestivalDay(festivalDay);
                festivalGroupMap.put(yearMonth, festivalDayGroup);
            }
        }
    }
}
