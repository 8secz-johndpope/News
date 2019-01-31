package com.heaven.model.ifilm;

/**
 * Created by LeoLu on 2016/12/26.
 */

public class VerificationCode {
    
    private int userId;
    private String verifyCode;
    private long timestamp;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
