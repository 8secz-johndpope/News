package com.heaven.model.ifilm;

import java.util.List;

/**
 * Created by leolu on 2017/4/14.
 */

public class CaseModel {


    /**
     * demoCoverpageUrl : http://hk-videosample.oss-cn-hongkong.aliyuncs.com/%E6%A0%B7%E7%89%87%E5%B0%81%E9%9D%A2/2%E6%97%85%E8%A1%8C.jpg
     * serviceType : [{"serviceNmae":"三维","iconName":"http://hk-usermaterial.oss-cn-hongkong.aliyuncs.com/otherReq/3D%401.5.png","serviceId":21},{"serviceNmae":"包装","iconName":"http://hk-usermaterial.oss-cn-hongkong.aliyuncs.com/otherReq/%E5%8C%85%E8%A3%85%401.5.png","serviceId":22},{"serviceNmae":"动画","iconName":"http://hk-usermaterial.oss-cn-hongkong.aliyuncs.com/otherReq/%E5%8A%A8%E7%94%BB%401.5x.png","serviceId":24}]
     * referencePrice : 1000
     * editor_name : Han.W
     * demo_url : http://hk-videosample.oss-cn-hongkong.aliyuncs.com/webSample/%E6%97%85%E8%A1%8C%20%20%E5%8D%97%E9%9D%9E%E7%BE%8E%E5%A6%99%E4%B9%8B%E6%97%85.mp4
     * avator : http://ifilmo.oss-cn-shenzhen.aliyuncs.com/editoravatar/QdDCyAjsKS.jpg
     * dict_value : 旅游
     * demo_id : 2
     * title : 旅游类样片
     */

    private String demoCoverpageUrl;
    private int referencePrice;
    private String editor_name;
    private String demo_url;
    private String avator;
    private String dict_value;
    private int demo_id;
    private String title;
    private List<ServiceType> serviceType;

    public String getDemoCoverpageUrl() {
        return demoCoverpageUrl;
    }

    public void setDemoCoverpageUrl(String demoCoverpageUrl) {
        this.demoCoverpageUrl = demoCoverpageUrl;
    }

    public int getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(int referencePrice) {
        this.referencePrice = referencePrice;
    }

    public String getEditor_name() {
        return editor_name;
    }

    public void setEditor_name(String editor_name) {
        this.editor_name = editor_name;
    }

    public String getDemo_url() {
        return demo_url;
    }

    public void setDemo_url(String demo_url) {
        this.demo_url = demo_url;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getDict_value() {
        return dict_value;
    }

    public void setDict_value(String dict_value) {
        this.dict_value = dict_value;
    }

    public int getDemo_id() {
        return demo_id;
    }

    public void setDemo_id(int demo_id) {
        this.demo_id = demo_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ServiceType> getServiceType() {
        return serviceType;
    }

    public void setServiceType(List<ServiceType> serviceType) {
        this.serviceType = serviceType;
    }

}
