package com.heaven.news.ui.vm.model;

import java.io.Serializable;

/**
 * FileName: com.heaven.news.ui.vm.model.UserSecret.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-25 20:32
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class UserSecret implements Serializable {
    public String userCount;
    public String pwd;

    public UserSecret(String userCount, String pwd) {
        this.userCount = userCount;
        this.pwd = pwd;
    }
}
