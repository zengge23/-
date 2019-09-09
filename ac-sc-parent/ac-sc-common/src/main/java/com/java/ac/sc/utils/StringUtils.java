package com.java.ac.sc.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class StringUtils {
	
	public static String checkCode() {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0;i < 4;i++) {
			int random = (int) (Math.random() * 10);
			stringBuilder.append(random);
		}
		return stringBuilder.toString();
	}
	
	public static boolean stringCheck(String source) {
		return source != null && !"".equals(source);
	}
	
	public static String md5(String source) {
		if(!stringCheck(source)) {
			throw new RuntimeException(ACConst.PWD_INVALID);
		}
		byte[] inputBytes = source.getBytes();
		
		String algorithm = "md5";
		
		byte[] outputBytes = null;
		
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			outputBytes = digest.digest(inputBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		char[] characters = new char[] {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		
		for (int i = 0;i < outputBytes.length;i++) {
			byte b = outputBytes[i];
			
			int highValue = (b >> 4) & 15;
			int lowValue = b & 15;
			
			char highChar = characters[highValue];
			char lowChar = characters[lowValue];
			
			stringBuilder.append(highChar).append(lowChar);
		}
		return stringBuilder.toString();
	}

}
