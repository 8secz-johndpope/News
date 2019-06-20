package com.heaven.news.ui.model.bean.base;

/**
 * FileName: com.heaven.news.ui.model.bean.base.SettingItem.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-06-20 14:18
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class SettingItem {
    public static final int SWITCH_LANGUAGE = 0;
    public static final int SWITCH_BAR_NOTIFY = 1;
    public static final int SWITCH_SOUND = 2;
    public static final int SWITCH_PUSH_MESSAGE = 3;
    public static final int VERSION = 4;

    public int type;
    public boolean isOpen;
    public String name;

}
