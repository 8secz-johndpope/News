package com.heaven.news.ui.vm.model.base;

import java.io.Serializable;

/**
 * FileName: com.heaven.news.ui.vm.model.base.UserSecret.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-25 20:32
 *
 * @author heaven
 * @version V1.0 TODO <描述当前版本功能>
 */
public class UserSecret implements Serializable {
    private static final long serialVersionUID = 6985762909949879832L;
    public String userCount;
    public String pwd;

    public UserSecret(String userCount, String pwd) {
        this.userCount = userCount;
        this.pwd = pwd;
    }
}
