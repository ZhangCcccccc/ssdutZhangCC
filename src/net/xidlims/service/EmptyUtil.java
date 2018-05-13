package net.xidlims.service;

import org.springframework.util.StringUtils;

public class EmptyUtil {

	public static boolean isStringEmpty(String str) {

		if (str == null) {
			return true;
		}else {
			return !StringUtils.hasLength(str.trim());
		}

	}
	
	public static boolean isStringArrayEmpty(String[] str) {

		if (str!=null && str.length>0) {
			return false;
		}else {
			return true;
		}
	}
	
	public static boolean isIntegerEmpty(Integer num) {

		if (num == null) {
			return true;
		}else {
			return false;
		}

	}
	
	public static boolean isObjectEmpty(Object obj) {

		if (obj == null) {
			return true;
		}else {
			return false;
		}

	}
	
	public static boolean isIntegerArray(Integer[] arr, Integer targetValue) {
	    for(Integer s: arr){
	        if(s.equals(targetValue))
	            return true;
	    }
	    return false;
	}
}
