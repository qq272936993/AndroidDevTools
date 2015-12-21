/**
 * 
 */
package pers.yangws.androiddevtools.helper;

import android.text.TextUtils;

/**
 * @作者: 杨文松
 * @时间: 2015年12月11日
 * @描述: 
 * 		校验工具类
 */
public class CheckHelper {

	
	/**
	 * 校验文本长度
	 * @param text 校验的文本 
	 * @param min 最少文本
	 * @param max 最多文本
	 * */
	public static boolean checkLength(String text,int min, int max){
		if(TextUtils.isEmpty(text) && min <= 0) return true;
		else{
			int length = text.length();
			return min <= length && length <= max;  
		}
	}
	
	/**
	 * 判断是否超过最小长度
	 * @param text 校验文本
	 * @param min 最小文本
	 * */
	public static boolean checkLengthMin(String text,int min){
		if(TextUtils.isEmpty(text) && min <= 0) return true;
		else{
			int length = text.length();
			return min <= length;
		}
	}
	
	
	/**
	 * 判断是否超过最大长度
	 * @param text 校验文本
	 * @param max 最大长度
	 * */
	public static boolean checkLengthMax(String text, int max){
		if(TextUtils.isEmpty(text) && max == 0) return true;
		else{
			int length = text.length();
			return length <= max;  
		}
	}
	
	
	
}
