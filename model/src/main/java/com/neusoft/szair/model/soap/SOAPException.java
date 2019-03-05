package com.neusoft.szair.model.soap;

import android.text.TextUtils;

public class SOAPException extends Exception {
	static final long serialVersionUID = 7818375828146090155L;
	
	private String errorCode = null;
	
	private String errorMsg = null;
    
    public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		if(TextUtils.isEmpty(errorMsg)){
			//return SzAirApplication.getInstance().getString(R.string.ex_web_service_exception);
			return "";
		}
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public SOAPException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
    }
	
	public SOAPException(String errorCode, String errorMsg, Throwable throwable) {
		super(throwable);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
    }

}
