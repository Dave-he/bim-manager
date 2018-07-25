package com.mjwise.demo.system.utils;

import java.util.UUID;

public class UUIDUtil {
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		str = str.replaceAll("-","");
		return str.toUpperCase();
	}
}
