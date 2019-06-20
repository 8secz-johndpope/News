package com.heaven.news.ui.model.bean.base;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * FileName: com.heaven.news.ui.vm.model.base.ServiceInfo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-29 15:11
 *
 * @author heaven
 * @version V1.0 服务信息
 */
public class ServiceInfo implements Serializable {
    private static final long serialVersionUID = 49941190139005470L;
    public int serviceType;
    public String serviceName;
    public ArrayList<ServiceItem> serviceItems;
}
