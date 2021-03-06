package com.imodel.utils;

public class NumberCheck {
	 public static boolean isInteger(String value) {
		 try {
			 Integer.parseInt(value);
		     return true;
		 } catch (NumberFormatException e) {
			 return false;
		 }
	 }

		 /**
		  * 判断字符串是否是浮点数
		  */
	 public static boolean isDouble(String value) {
		 try {
			 Double.parseDouble(value);
			 if (value.contains("."))
				 return true;
			 return false;
		 } catch (NumberFormatException e) {
			 return false;
		 }
	 }

		 /**
		  * 判断字符串是否是数字
		  */
	 public static boolean isNumber(String value) {
		 return isInteger(value) || isDouble(value);
	 }

}
