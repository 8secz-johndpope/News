package com.heaven.model.ifilm;

/**
 * Created by Leo on 2015/3/9.
 */

/**
 * 无返回数据的操作结果类
 */
public class BaseModel {

    private int status = 0;// 1.成功， 0失败
    private String urlLink;
    private String message;
    private String exceptionMessage;   //异常信息系统内部错误

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
