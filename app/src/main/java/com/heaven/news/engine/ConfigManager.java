package com.heaven.news.engine;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.heaven.base.ui.view.calendar.Calendar;
import com.heaven.base.ui.view.calendar.CalendarUtil;
import com.heaven.base.ui.view.calendar.LunarCalendar;
import com.heaven.base.ui.view.calendar.Month;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import io.reactivex.Flowable;
import io.reactivex.Observable;

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
    public static String CITY = "city";
    public static String CITY_EN = "city_en";
    public static String CITY_OFTEN = "city_often";
    public static String CITY_HOT = "city_hot";
    public static String CITY_INDEX = "city_index";
    SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy年MM月");


    private final Object lock = new Object();

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
    private List<cityListVO> citys = new ArrayList<>();
    private List<cityListVO> citysEn = new ArrayList<>();
    private List<cityListVO> citysOften = new Vector<>();
    private List<cityListVO> citysHot = new ArrayList<>();
    private List<cityListVO> citysIndex = new ArrayList<>();
    private HashMap<String, Integer> indexMap = new HashMap<>();
    private HashMap<String, Integer> indexMapEn = new HashMap<>();
    private ArrayList<Month> calendars = new ArrayList<>();
    private ArrayList calendarsAll = new ArrayList();

    private int cityGroupIndexOffset = 0;

    ConfigManager(DataSource dataSource, Context context) {
        this.context = context;
        this.dataSource = dataSource;
        requestVersion();
        requestConfig();
        dataSource.runWorkThread(this::initLocalCity);
        dataSource.runWorkThread(() -> initCalendar(context));
        dataSource.runWorkThread(() -> loadLocalService(context));
    }


    private void loadLocalService(Context context) {
        loadHomeService(context);
        loadEasyGoService(context);
        loadPhoenixService(context);
    }


    public ArrayList loadCalendar() {
        if (calendarsAll.size() == 0) {
            initCalendar(context);
        }

        return calendarsAll;
    }


    private void initCalendar(Context context) {
        calendars.clear();
        calendarsAll.clear();
        LunarCalendar.init(context);
        Date d = new Date();
        Calendar mCurrentDate = new Calendar();
        mCurrentDate.setYear(CalendarUtil.getDate("yyyy", d));
        mCurrentDate.setMonth(CalendarUtil.getDate("MM", d));
        mCurrentDate.setDay(CalendarUtil.getDate("dd", d));
        LunarCalendar.setupLunarCalendar(mCurrentDate);


        java.util.Calendar startCal = java.util.Calendar.getInstance();     //开始年月
        //开始日期设为月的第一天，结束日期设为最后一天
        startCal.set(java.util.Calendar.DAY_OF_MONTH, 1);

        java.util.Calendar month = java.util.Calendar.getInstance();
        for (int position = 0; position < 12; position++) {
            month.setTime(startCal.getTime());
            month.add(java.util.Calendar.MONTH, position);
            createMonthData(month,mCurrentDate);
        }
    }

    private void createMonthData(java.util.Calendar month,Calendar currentDate) {
        Date date = month.getTime();
        Month calendarTitle = new Month();
        calendarTitle.title = monthFormat.format(date);
        calendarTitle.year = (CalendarUtil.getDate("yyyy", date));
        calendarTitle.month = (CalendarUtil.getDate("MM", date));
        calendarTitle.addDayInMonth(CalendarUtil.initCalendarForMonthView(calendarTitle.year, calendarTitle.month, currentDate, 1));
        calendars.add(calendarTitle);
        calendarsAll.add(calendarTitle);
        calendarsAll.addAll(calendarTitle.days);
        Logger.i("initCalendar--" + calendarTitle.title );
    }

    public Pair<List<cityListVO>, List<String>> getAllCitys() {
        ArrayList<cityListVO> citysAll = new ArrayList<>();
        cityGroupIndexOffset = 0;
        if (currentCity != null) {
            currentCity.groupTitle = "当前城市";
            currentCity.groupFlag = 1;
            currentCity.isFirstInGroup = true;
            currentCity.isLastInGroup = true;
            currentCity.specialCitys = new ArrayList<>();
            currentCity.specialCitys.add(currentCity);
            citysAll.add(currentCity);
            indexMap.put("当前", 0);
            cityGroupIndexOffset++;
        }

        if (citysOften != null && citysOften.size() > 0) {
            cityListVO oftenCitys = new cityListVO();
            oftenCitys.groupFlag = 2;
            oftenCitys.isTitle = true;
            oftenCitys.specialCitys = citysOften;
            oftenCitys.isFirstInGroup = true;
            oftenCitys.isLastInGroup = true;
            oftenCitys.groupTitle = "常用城市";
            citysAll.add(oftenCitys);
            indexMap.put("常用", 1);
            cityGroupIndexOffset++;

        }

        if (citysHot != null && citysHot.size() > 0) {
            cityListVO hotCitys = new cityListVO();
            hotCitys.groupFlag = 3;
            hotCitys.isTitle = true;
            hotCitys.specialCitys = citysHot;
            hotCitys.isFirstInGroup = true;
            hotCitys.isLastInGroup = true;
            hotCitys.groupTitle = "热门城市";
            citysAll.add(hotCitys);
            indexMap.put("热门", 2);
            cityGroupIndexOffset++;
        }

        if (citys != null && citys.size() > 0) {
            citysAll.addAll(citys);
        }

        ArrayList<String> indexNameLlist = new ArrayList<>(indexMap.keySet());
        Collections.sort(indexNameLlist, (o1, o2) -> {
            if (o1.equals("当前")) {
                return -1;
            } else if (o2.equals("当前")) {
                return 1;
            } else if (o1.equals("常用")) {
                return -1;
            } else if (o2.equals("常用")) {
                return 1;
            } else if (o1.equals("热门")) {
                return -1;
            } else if (o2.equals("热门")) {
                return 1;
            } else {
                return o1.compareTo(o2);
            }

        });

        return new Pair<>(citysAll, indexNameLlist);
    }

    public int getCityGroupIndex(String indexName) {
        int index = 0;
        if (indexMap != null && indexMap.containsKey(indexName)) {
            if ("当前".equals(indexName)) {
                return 0;
            } else if ("常用".equals(indexName)) {
                return 1;
            } else if ("热门".equals(indexName)) {
                return 2;
            } else {
                index = indexMap.get(indexName);
                index += cityGroupIndexOffset;
            }
        }
        return index;
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
            if (configData != null) {
                this.configData = configData;
                ConfigWrapper dataWrapper = getConfigDataWrapper(true, VERSION);
                notifyConfigDataChange(dataWrapper);
                if (configData.netCode == 0) {
                    if (configData.timestamp != null && configData.androidversionnew != null) {
                        RxRepUtils.cancelTask(reqverTaskId);
                    }
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
        if (configData.timestamp != null) {
            refreshConfigByTimeStamp(configData.timestamp);
        }

        if (configData.city != null && configData.city.size() > 0) {
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
        synchronized (lock) {
            currentCity = dataSource.getCacheEntity(DataSource.DISK, CITY_CURRENT);
            if (currentCity == null) {
                currentCity = getCityBySanCode("SZX");
            }
            citys = loadLocalCityCh();
            citysEn = loadLocalCityEn();
            citysIndex = loadLocalCityIndex();
            List<cityListVO> hotsCitys = dataSource.getCacheEntity(DataSource.DISK, CITY_HOT);
            if (hotsCitys != null && hotsCitys.size() > 0) {
                citysHot = hotsCitys;
            }

            List<cityListVO> oftenCitys = dataSource.getCacheEntity(DataSource.DISK, CITY_OFTEN);
            if (oftenCitys != null && oftenCitys.size() > 0) {
                citysOften = oftenCitys;
            }
            groupCity();
            cacheData();
        }
    }

    private void cacheData() {
        if (citys != null && citys.size() > 0) {
            dataSource.cacheData(DataSource.DISK, CITY, citys);
        }

        if (citysEn != null && citysEn.size() > 0) {
            dataSource.cacheData(DataSource.DISK, CITY_EN, citysEn);
        }

        if (citysIndex != null && citysIndex.size() > 0) {
            dataSource.cacheData(DataSource.DISK, CITY_INDEX, citysIndex);
        }
    }

    private void initHotCity(List<String> hotCitys) {
        synchronized (lock) {
            citysHot.clear();
            for (String sanCode : hotCitys) {
                cityListVO hotCity = getCityBySanCode(sanCode);
                if (hotCity != null) {
                    citysHot.add(hotCity);
                }
            }
            if (citysHot != null && citysHot.size() > 0) {
                dataSource.cacheData(DataSource.DISK, CITY_HOT, citysHot);
            }
        }
    }

    private List<cityListVO> loadLocalCityIndex() {
        List<cityListVO> citysIndex = dataSource.getCacheEntity(DataSource.DISK, CITY_INDEX);
        if (citysIndex == null) {
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
        List<cityListVO> citys = dataSource.getCacheEntity(DataSource.DISK, CITY);
        if (citys == null) {
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
        List<cityListVO> citysEn = dataSource.getCacheEntity(DataSource.DISK, CITY_EN);
        if (citysEn == null) {
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
        if (0 == cityType) {
            if (citys != null && citys.size() > 0) {
                return citys;
            } else {
                return loadLocalCityCh();
            }
        } else {
            if (citysEn != null && citysEn.size() > 0) {
                return citysEn;
            } else {
                return loadLocalCityEn();
            }
        }
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
                dataSource.cacheData(DataSource.DISK, CITY_OFTEN, citysOften);
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
        });
    }

    private void testWriteCity(List<cityListVO> citys) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        IoUtil.save("heaven", gson.toJson(citys));
    }

    private void loadNewCitys(List<cityListVO> newCitys) {
        synchronized (lock) {
            if (newCitys != null && newCitys.size() > 0) {
                List<cityListVO> newCitysCh = IoUtil.deepCopyList(newCitys);
                List<cityListVO> newCitysEn = IoUtil.deepCopyList(newCitys);
                List<cityListVO> newCitysIndex = IoUtil.deepCopyList(newCitys);
                sortCity(newCitysCh);
                sortCityEn(newCitysEn);
                sortCityIndex(newCitysIndex);
                this.citys = newCitysCh;
                this.citysEn = newCitysEn;
                this.citysIndex = newCitysIndex;
                dataSource.cacheData(DataSource.DISK, CITY, newCitysCh);
                dataSource.cacheData(DataSource.DISK, CITY_EN, newCitysEn);
                dataSource.cacheData(DataSource.DISK, CITY_INDEX, newCitysIndex);
                groupCity();
                testWriteCity(newCitysCh);
            }
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

    @SuppressLint("CheckResult")
    private void groupCity() {
        synchronized (lock) {
            indexMap.clear();
            indexMapEn.clear();
            if (citys != null && citys.size() > 0) {
                Observable.fromIterable(citys).subscribe(cityListVO -> {
                    if (!TextUtils.isEmpty(cityListVO._PY_NAME)) {
                        String groupTitle = String.valueOf(Character.toUpperCase(cityListVO._PY_NAME.charAt(0)));
                        if (!indexMap.containsKey(groupTitle)) {
                            cityListVO.isTitle = true;
                            cityListVO.groupTitle = groupTitle;
                            cityListVO.isFirstInGroup = true;
                            int index = citys.indexOf(cityListVO);
                            if (index > 0) {
                                cityListVO preLast = citys.get(index - 1);
                                preLast.isLastInGroup = true;
                            }
                            indexMap.put(groupTitle, index);
                        } else {
                            cityListVO.groupTitle = groupTitle;
                        }
                    }
                });
                Logger.i("groupCity---");
            }

            if (citysEn != null && citysEn.size() > 0) {
                Observable.fromIterable(citysEn).subscribe(cityListVO -> {
                    if (!TextUtils.isEmpty(cityListVO._FULLNAME_EN)) {
                        String groupTitle = String.valueOf(Character.toUpperCase(cityListVO._FULLNAME_EN.charAt(0)));
                        if (!indexMapEn.containsKey(groupTitle)) {
                            cityListVO.isTitle = true;
                            cityListVO.groupTitle = groupTitle;
                            cityListVO.isFirstInGroup = true;
                            int index = citys.indexOf(cityListVO);
                            if (index > 0) {
                                cityListVO preLast = citys.get(index - 1);
                                preLast.isLastInGroup = true;
                            }
                            indexMapEn.put(groupTitle, citysEn.indexOf(cityListVO));
                        } else {
                            cityListVO.groupTitle = groupTitle;
                        }
                        Logger.i("groupCity1---" + groupTitle);
                    }
                });
                Logger.i("groupCity---");
            }
        }
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
}
