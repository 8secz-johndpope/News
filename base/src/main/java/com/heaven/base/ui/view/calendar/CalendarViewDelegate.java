/*
 * Copyright (C) 2016 huanghaibin_dev <huanghaibin_dev@163.com>
 * WebSite https://github.com/MiracleTimes-Dev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.heaven.base.ui.view.calendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Google规范化的属性委托,
 * 代码量多，但是不影响阅读性
 */
final class CalendarViewDelegate {

    /**
     * 周起始：周日
     */
    static final int WEEK_START_WITH_SUN = 1;

    /**
     * 周起始：周一
     */
    static final int WEEK_START_WITH_MON = 2;

    /**
     * 周起始：周六
     */
    static final int WEEK_START_WITH_SAT = 7;


    /**
     * 周起始
     */
    private int mWeekStart;

    /**
     * 全部显示
     */
    static final int MODE_ALL_MONTH = 0;
    /**
     * 仅显示当前月份
     */
    static final int MODE_ONLY_CURRENT_MONTH = 1;

    /**
     * 自适应显示，不会多出一行，但是会自动填充
     */
    static final int MODE_FIT_MONTH = 2;

    /**
     * 月份显示模式
     */
    private int mMonthViewShowMode;


    /**
     * 默认选择模式
     */
    static final int SELECT_MODE_DEFAULT = 0;

    /**
     * 单选模式
     */
    static final int SELECT_MODE_SINGLE = 1;

    /**
     * 范围选择模式
     */
    static final int SELECT_MODE_RANGE = 2;

    /**
     * 多选模式
     */
    static final int SELECT_MODE_MULTI = 3;

    /**
     * 选择模式
     */
    private int mSelectMode;


    /**
     * 支持转换的最小农历年份
     */
    static final int MIN_YEAR = 1900;
    /**
     * 支持转换的最大农历年份
     */
    private static final int MAX_YEAR = 2099;


    /**
     * 标记文本
     */
    private String mSchemeText;

    /**
     * 最小年份和最大年份
     */
    private int mMinYear, mMaxYear;

    /**
     * 最小年份和最大年份对应最小月份和最大月份
     * when you want set 2015-07 to 2017-08
     */
    private int mMinYearMonth, mMaxYearMonth;

    /**
     * 最小年份和最大年份对应最小天和最大天数
     * when you want set like 2015-07-08 to 2017-08-30
     */
    private int mMinYearDay, mMaxYearDay;

    /**
     * 日期和农历文本大小
     */
    private int mDayTextSize, mLunarTextSize;

    /**
     * 日历卡的项高度
     */
    private int mCalendarItemHeight;

    /**
     * 是否是全屏日历
     */
    private boolean isFullScreenCalendar;

    /**
     * 星期栏的高度
     */
    private int mWeekBarHeight;

    /**
     * 今天的日子
     */
    private Calendar mCurrentDate;


    private boolean mMonthViewScrollable,
            mWeekViewScrollable,
            mYearViewScrollable;

    /**
     * 当前月份和周视图的item位置
     */
    int mCurrentMonthViewItem;

    /**
     * 标记的日期,数量巨大，请使用这个
     */
    Map<String, Calendar> mSchemeDatesMap;


    /**
     * 保存选中的日期
     */
    Calendar mSelectedCalendar;

    /**
     * 保存标记位置
     */
    Calendar mIndexCalendar;

    /**
     * 多选日历
     */
    Map<String, Calendar> mSelectedCalendars = new HashMap<>();

    private int mMaxMultiSelectSize;

    /**
     * 选择范围日历
     */
    Calendar mSelectedStartRangeCalendar, mSelectedEndRangeCalendar;

    private int mMinSelectRange, mMaxSelectRange;

    CalendarViewDelegate(Context context, @Nullable AttributeSet attrs) {

        LunarCalendar.init(context);

        setSelectRange(mMinSelectRange, mMaxSelectRange);


        if (mMinYear <= MIN_YEAR) {
            mMinYear = MIN_YEAR;
        }
        if (mMaxYear >= MAX_YEAR) {
            mMaxYear = MAX_YEAR;
        }
        init();
    }

    private void init() {
        mCurrentDate = new Calendar();
        Date d = new Date();
        mCurrentDate.setYear(CalendarUtil.getDate("yyyy", d));
        mCurrentDate.setMonth(CalendarUtil.getDate("MM", d));
        mCurrentDate.setDay(CalendarUtil.getDate("dd", d));
        mCurrentDate.setCurrentDay(true);
        LunarCalendar.setupLunarCalendar(mCurrentDate);
        setRange(mMinYear, mMinYearMonth, mMaxYear, mMaxYearMonth);

    }


    private void setRange(int minYear, int minYearMonth,
                          int maxYear, int maxYearMonth) {
        this.mMinYear = minYear;
        this.mMinYearMonth = minYearMonth;
        this.mMaxYear = maxYear;
        this.mMaxYearMonth = maxYearMonth;
        if (this.mMaxYear < mCurrentDate.getYear()) {
            this.mMaxYear = mCurrentDate.getYear();
        }
        if (this.mMaxYearDay == -1) {
            this.mMaxYearDay = CalendarUtil.getMonthDaysCount(this.mMaxYear, mMaxYearMonth);
        }
        int y = mCurrentDate.getYear() - this.mMinYear;
        mCurrentMonthViewItem = 12 * y + mCurrentDate.getMonth() - this.mMinYearMonth;
    }

    void setRange(int minYear, int minYearMonth, int minYearDay,
                  int maxYear, int maxYearMonth, int maxYearDay) {
        this.mMinYear = minYear;
        this.mMinYearMonth = minYearMonth;
        this.mMinYearDay = minYearDay;
        this.mMaxYear = maxYear;
        this.mMaxYearMonth = maxYearMonth;
        this.mMaxYearDay = maxYearDay;
//        if (this.mMaxYear < mCurrentDate.getYear()) {
//            this.mMaxYear = mCurrentDate.getYear();
//        }
        if (this.mMaxYearDay == -1) {
            this.mMaxYearDay = CalendarUtil.getMonthDaysCount(this.mMaxYear, mMaxYearMonth);
        }
        int y = mCurrentDate.getYear() - this.mMinYear;
        mCurrentMonthViewItem = 12 * y + mCurrentDate.getMonth() - this.mMinYearMonth;
    }

    String getSchemeText() {
        return mSchemeText;
    }

    int getWeekBarHeight() {
        return mWeekBarHeight;
    }

    int getMinYear() {
        return mMinYear;
    }

    int getMaxYear() {
        return mMaxYear;
    }

    int getDayTextSize() {
        return mDayTextSize;
    }

    int getLunarTextSize() {
        return mLunarTextSize;
    }

    int getCalendarItemHeight() {
        return mCalendarItemHeight;
    }

    void setCalendarItemHeight(int height) {
        mCalendarItemHeight = height;
    }

    int getMinYearMonth() {
        return mMinYearMonth;
    }

    int getMaxYearMonth() {
        return mMaxYearMonth;
    }


    boolean isMonthViewScrollable() {
        return mMonthViewScrollable;
    }

    boolean isWeekViewScrollable() {
        return mWeekViewScrollable;
    }

    boolean isYearViewScrollable() {
        return mYearViewScrollable;
    }

    void setMonthViewScrollable(boolean monthViewScrollable) {
        this.mMonthViewScrollable = monthViewScrollable;
    }

    void setWeekViewScrollable(boolean weekViewScrollable) {
        this.mWeekViewScrollable = weekViewScrollable;
    }

    void setYearViewScrollable(boolean yearViewScrollable) {
        this.mYearViewScrollable = yearViewScrollable;
    }

    int getWeekStart() {
        return mWeekStart;
    }

    void setWeekStart(int mWeekStart) {
        this.mWeekStart = mWeekStart;
    }

    /**
     * 选择模式
     *
     * @return 选择模式
     */
    int getSelectMode() {
        return mSelectMode;
    }

    /**
     * 设置选择模式
     *
     * @param mSelectMode mSelectMode
     */
    void setSelectMode(int mSelectMode) {
        this.mSelectMode = mSelectMode;
    }

    int getMinSelectRange() {
        return mMinSelectRange;
    }

    int getMaxSelectRange() {
        return mMaxSelectRange;
    }

    int getMaxMultiSelectSize() {
        return mMaxMultiSelectSize;
    }

    void setMaxMultiSelectSize(int maxMultiSelectSize) {
        this.mMaxMultiSelectSize = maxMultiSelectSize;
    }

    final void setSelectRange(int minRange, int maxRange) {
        if (minRange > maxRange && maxRange > 0) {
            mMaxSelectRange = minRange;
            mMinSelectRange = minRange;
            return;
        }
        if (minRange <= 0) {
            mMinSelectRange = -1;
        } else {
            mMinSelectRange = minRange;
        }
        if (maxRange <= 0) {
            mMaxSelectRange = -1;
        } else {
            mMaxSelectRange = maxRange;
        }
    }

    Calendar getCurrentDay() {
        return mCurrentDate;
    }

    void updateCurrentDay() {
        Date d = new Date();
        mCurrentDate.setYear(CalendarUtil.getDate("yyyy", d));
        mCurrentDate.setMonth(CalendarUtil.getDate("MM", d));
        mCurrentDate.setDay(CalendarUtil.getDate("dd", d));
        LunarCalendar.setupLunarCalendar(mCurrentDate);
    }

    void clearSelectedScheme() {
        mSelectedCalendar.clearScheme();
    }

    int getMinYearDay() {
        return mMinYearDay;
    }

    int getMaxYearDay() {
        return mMaxYearDay;
    }

    boolean isFullScreenCalendar() {
        return isFullScreenCalendar;
    }

    final void updateSelectCalendarScheme() {
        if (mSchemeDatesMap != null && mSchemeDatesMap.size() > 0) {
            String key = mSelectedCalendar.toString();
            if (mSchemeDatesMap.containsKey(key)) {
                Calendar d = mSchemeDatesMap.get(key);
                mSelectedCalendar.mergeScheme(d, getSchemeText());
            }
        } else {
            clearSelectedScheme();
        }
    }

    final void updateCalendarScheme(Calendar targetCalendar) {
        if (targetCalendar == null) {
            return;
        }
        if (mSchemeDatesMap == null || mSchemeDatesMap.size() == 0) {
            return;
        }
        String key = targetCalendar.toString();
        if (mSchemeDatesMap.containsKey(key)) {
            Calendar d = mSchemeDatesMap.get(key);
            targetCalendar.mergeScheme(d, getSchemeText());
        }
    }

    Calendar createCurrentDate() {
        Calendar calendar = new Calendar();
        calendar.setYear(mCurrentDate.getYear());
        calendar.setWeek(mCurrentDate.getWeek());
        calendar.setMonth(mCurrentDate.getMonth());
        calendar.setDay(mCurrentDate.getDay());
        calendar.setCurrentDay(true);
        LunarCalendar.setupLunarCalendar(calendar);
        return calendar;
    }

    final Calendar getMinRangeCalendar() {
        Calendar calendar = new Calendar();
        calendar.setYear(mMinYear);
        calendar.setMonth(mMinYearMonth);
        calendar.setDay(mMinYearDay);
        calendar.setCurrentDay(calendar.equals(mCurrentDate));
        LunarCalendar.setupLunarCalendar(calendar);
        return calendar;
    }

    @SuppressWarnings("unused")
    final Calendar getMaxRangeCalendar() {
        Calendar calendar = new Calendar();
        calendar.setYear(mMaxYear);
        calendar.setMonth(mMaxYearMonth);
        calendar.setDay(mMaxYearDay);
        calendar.setCurrentDay(calendar.equals(mCurrentDate));
        LunarCalendar.setupLunarCalendar(calendar);
        return calendar;
    }

    /**
     * 添加事件标记，来自Map
     */
    final void addSchemesFromMap(List<Calendar> mItems) {
        if (mSchemeDatesMap == null || mSchemeDatesMap.size() == 0) {
            return;
        }
        for (Calendar a : mItems) {
            if (mSchemeDatesMap.containsKey(a.toString())) {
                Calendar d = mSchemeDatesMap.get(a.toString());
                a.setScheme(TextUtils.isEmpty(d.getScheme()) ? getSchemeText() : d.getScheme());
                a.setSchemeColor(d.getSchemeColor());
                a.setSchemes(d.getSchemes());
            } else {
                a.setScheme("");
                a.setSchemeColor(0);
                a.setSchemes(null);
            }
        }
    }

    /**
     * 清楚选择
     */
    final void clearSelectRange() {
        mSelectedStartRangeCalendar = null;
        mSelectedEndRangeCalendar = null;
    }

    /**
     * 获得选中范围
     *
     * @return 选中范围
     */
    final List<Calendar> getSelectCalendarRange() {
        if (mSelectMode != SELECT_MODE_RANGE) {
            return null;
        }
        List<Calendar> calendars = new ArrayList<>();
        if (mSelectedStartRangeCalendar == null ||
                mSelectedEndRangeCalendar == null) {
            return calendars;
        }
        final long ONE_DAY = 1000 * 3600 * 24;
        java.util.Calendar date = java.util.Calendar.getInstance();

        date.set(mSelectedStartRangeCalendar.getYear(),
                mSelectedStartRangeCalendar.getMonth() - 1,
                mSelectedStartRangeCalendar.getDay());//

        long startTimeMills = date.getTimeInMillis();//获得起始时间戳


        date.set(mSelectedEndRangeCalendar.getYear(),
                mSelectedEndRangeCalendar.getMonth() - 1,
                mSelectedEndRangeCalendar.getDay());//
        long endTimeMills = date.getTimeInMillis();
        for (long start = startTimeMills; start <= endTimeMills; start += ONE_DAY) {
            date.setTimeInMillis(start);
            Calendar calendar = new Calendar();
            calendar.setYear(date.get(java.util.Calendar.YEAR));
            calendar.setMonth(date.get(java.util.Calendar.MONTH) + 1);
            calendar.setDay(date.get(java.util.Calendar.DAY_OF_MONTH));
//            if (mCalendarInterceptListener != null &&
//                    mCalendarInterceptListener.onCalendarIntercept(calendar)) {
//                continue;
//            }
            LunarCalendar.setupLunarCalendar(calendar);
            calendars.add(calendar);
        }
        addSchemesFromMap(calendars);
        return calendars;
    }
}
