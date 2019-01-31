package com.heaven.model.soap.szair.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CryptUtility {
	// AES128 EBC encrypt with PKCS7Padding mode
	public static byte[] encrypt(byte[] origData, byte[] keyData) {
		byte[] cryptedData = null;
		try{
			SecretKeySpec key = new SecretKeySpec(keyData, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");

			cipher.init(Cipher.ENCRYPT_MODE, key);
			cryptedData = new byte[cipher.getOutputSize(origData.length)];
			int ctLength = cipher.update(origData, 0, origData.length, cryptedData, 0);
			ctLength += cipher.doFinal(cryptedData, ctLength);
		}
		catch(Exception e){
			
		}

		return cryptedData;
	}
	
	public static byte[] decrypt(byte[] origData, byte[] keyData) {
		byte[] cryptedData = null;
		try{
			SecretKeySpec key = new SecretKeySpec(keyData, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");

			cipher.init(Cipher.DECRYPT_MODE, key);
			cryptedData = new byte[cipher.getOutputSize(origData.length)];
			int ctLength = cipher.update(origData, 0, origData.length, cryptedData, 0);
			ctLength += cipher.doFinal(cryptedData, ctLength);
			byte[] result = new byte[ctLength];
			System.arraycopy(cryptedData, 0, result, 0, result.length);
			return result;
		}
		catch(Exception e){
			
		}


		return cryptedData;
	}
	
	public static String encrypt(String key, String text) {
		key = getPasswordKey(key);
		try{
			byte[] passwordByte = CryptUtility.encrypt(text.getBytes(), key.getBytes());
			String encode = CryptUtility.base64Encode(passwordByte);
			return encode;
		}catch(Exception e){
			return "";
		}
		
    }

    public static String decrypt(String key, String text) {
    	key = getPasswordKey(key);
    	try{
    		byte[] decodeByte = CryptUtility.base64Decode(text);
        	byte[] passwordByte = CryptUtility.decrypt(decodeByte, key.getBytes());
        	String decode = new String(passwordByte);
    		return decode;
    	}catch(Exception e){
    		return "";
    	}
    }
    
    private static String getPasswordKey(String key){
    	if(key == null){
    		return "szair-1234567890";
    	}else if(key.length() >= 16){
    		return key.substring(0, 16);
    	}else if(key.length() >= 11){
    		return "szair"+key.substring(0, 11);
    	}else{
    		return "szair-1234567890";
    	}
    }
	
	// base64 encode and decode
	public static String base64Encode(byte[] crypted) {
		return android.util.Base64.encodeToString(crypted,
				android.util.Base64.URL_SAFE);
	}

	public static byte[] base64Decode(String encodeStr) {
		return android.util.Base64.decode(encodeStr,
				android.util.Base64.URL_SAFE);
	}
}
