package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	/**
	 * 判断字符串是正数：包含正整数和正小数
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		Pattern pattern=Pattern.compile("^\\d+(\\.\\d+)?$");
        Matcher match=pattern.matcher(str); 
        if(match.matches()==false){ 
           return false; 
        }else{ 
           return true; 
        } 
	}
	
	/**
	 * 判断字符串是正整数
	 * @param str
	 * @return
	 */
	public static boolean isInter(String str){
		Pattern pattern=Pattern.compile("^\\d+$");
        Matcher match=pattern.matcher(str); 
        if(match.matches()==false){ 
           return false; 
        }else{ 
           return true; 
        } 
	}
	
	/**
	 * 判断字符穿是否为空
	 * 当字符串为空或空字符串时返回true 否则返回false
	 * @param str
	 * @return
	 */
	public static final boolean isEmpty(String str)
	{
		if (str == null || str.trim().length() == 0)
		{
			return true;
		}
		return false;

	}
	
	/**
	 * 判断字符数组是否为空
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}
}
