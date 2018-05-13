package net.xidlims.service;


/**********************************************
 * Description: 通用模块，用于各种格式转换
 * 
 * @author 贺子龙
 * @date 2016-08-31
 ***********************************************/


public class ConvertUtil {
	
	/**********************************************
	 * Description: 通用模块，string[]转int[]
	 * 
	 * @author 贺子龙
	 * @date 2016-08-31
	 ***********************************************/
	public static int[] stringArrayToIntArray(String[] stringArray) {
		// 定义新数组int类型
		int[] intString = new int[stringArray.length];
		// 转换
		for (int i = 0; i < stringArray.length; i++) {
			intString[i] = Integer.parseInt(stringArray[i]);
		}
		
	   return intString;
	}
	
	/**********************************************
	 * Description: 通用模块，string转int[]
	 * 
	 * @author 贺子龙
	 * @date 2016-08-31
	 ***********************************************/
	public static int[] stringToIntArray(String string) {
		// 将字符转成数组
		String[] stringArray = string.split(",");
		// 定义新数组int类型
		int[] intString = new int[stringArray.length];
		// 转换
		for (int i = 0; i < stringArray.length; i++) {
			intString[i] = Integer.parseInt(stringArray[i]);
		}
		
	   return intString;
	}
	
}
