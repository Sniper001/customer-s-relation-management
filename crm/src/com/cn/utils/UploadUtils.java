package com.cn.utils;

import java.util.UUID;

public class UploadUtils {
	
	
	/**
	 * �����ļ������ƣ�����Ψһ������
	 * @param fileName
	 * @return
	 */
	public static String getUUIDName(String fileName){
		//�Ȳ���
		int index = fileName.lastIndexOf(".");
		//��ȡ
		String lastName = fileName.substring(index,fileName.length());
		//Ψһ�ַ���
		String uuid = UUID.randomUUID().toString().replace("-", "");
		
		return uuid+lastName;
	}

}
