package com.heaven.model.soap.szair.encrypt;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class KeyGenerator {
	private static final String MD5_KEY = "szair_mobile_app";
	private static final String SECURITY_KEY = "szair01234567890";
	private static final SimpleDateFormat YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

	public String getNewKey() {
		String result = MD5Util.MD5Encode(MD5_KEY);
		return result;
	}
	
	public String getKey() {
		//String timestampt = YMDHMS.format(Calendar.getInstance().getTime());
		double sec = System.currentTimeMillis()/1000.0d;
        String timestampt = String.valueOf(sec);
        //String timestampt = "2014-08-11 22:15:45";
        String data = new KeyGenerator().getNewKey() + timestampt;
		byte[] encrypt = CryptUtility.encrypt(data.getBytes(), SECURITY_KEY.getBytes());
		String key = CryptUtility.base64Encode(encrypt);
		return key.replaceAll("\n", "");
	}
	
}
