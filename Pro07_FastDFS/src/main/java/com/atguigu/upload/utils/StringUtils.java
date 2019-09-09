package com.atguigu.upload.utils;

public class StringUtils {
	
	public static String getExtName(String originalFilename) {
		
		if(originalFilename == null || originalFilename.length() == 0) {
			return null;
		}
		//aaa.aaa.jpg
		return originalFilename.substring(originalFilename.lastIndexOf(".")+1);
	}

}
