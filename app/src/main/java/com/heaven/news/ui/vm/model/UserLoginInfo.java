package com.heaven.news.ui.vm.model;

import com.neusoft.szair.model.memberbase.queryRespVO;

import java.io.Serializable;

/**
 * FileName: com.heaven.news.ui.vm.model.UserLoginInfo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-09 01:05
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class UserLoginInfo implements Serializable {
    public String key;
    public String userCount;
    public String userPwd;
    public String userId;                       //用户
    public queryRespVO userInfo;

    public UserLoginInfo(String userCount, String userPwd) {
        this.userCount = userCount;
        this.userPwd = userPwd;
        this.key = userCount + userPwd;
    }

    @Override
    public String toString() {
        return "UserLoginInfo{" +
                "userCount='" + userCount + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userId='" + userId + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
