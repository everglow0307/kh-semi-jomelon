package com.jomelon.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {
	
	//static으로
	public static String encryptSHA256(String pw) {
		String sha = ""; // 암호화하는 변수 담을곳
		
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(pw.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i<byteData.length; i++){
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				
			}
			sha = sb.toString();
			
		}catch(NoSuchAlgorithmException e){
			//e.printStackTrace();
			System.out.println("Encrypt Error - NosuchAlgorithmException");
			
			sha = null;
		}
		return sha;
	}
}
