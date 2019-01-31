package com.heaven.model.ifilm;

/**
 * Created by LeoLu on 2017/1/4.
 */

public class ADModel {
    private String adUrl;
    private int adImageRes;
    private boolean showButton;

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public int getAdImageRes() {
        return adImageRes;
    }

    public void setAdImageRes(int adImageRes) {
        this.adImageRes = adImageRes;
    }

    public boolean isShowButton() {
        return showButton;
    }

    public void setShowButton(boolean showButton) {
        this.showButton = showButton;
    }
}
