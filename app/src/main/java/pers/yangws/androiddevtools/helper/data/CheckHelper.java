/**
 *
 */
package pers.yangws.androiddevtools.helper.data;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @作者: 杨文松
 * @时间: 2015年12月11日
 * @描述: 校验工具类
 */
public class CheckHelper {


    /**
     * 校验文本长度
     *
     * @param text 校验的文本
     * @param min  最少文本
     * @param max  最多文本
     */
    public static boolean checkLength(String text, int min, int max) {
        if (TextUtils.isEmpty(text) && min <= 0) return true;
        else {
            int length = text.length();
            return min <= length && length <= max;
        }
    }

    /**
     * 判断是否超过最小长度
     *
     * @param text 校验文本
     * @param min  最小文本
     */
    public static boolean checkLengthMin(String text, int min) {
        if (TextUtils.isEmpty(text) && min <= 0) return true;
        else {
            int length = text.length();
            return min <= length;
        }
    }


    /**
     * 判断是否超过最大长度
     *
     * @param text 校验文本
     * @param max  最大长度
     */
    public static boolean checkLengthMax(String text, int max) {
        if (TextUtils.isEmpty(text) && max == 0) return true;
        else {
            int length = text.length();
            return length <= max;
        }
    }
    
    
    /**
     * 验证IP地址
     * 
     * */
    public static boolean checkIp(String str){
        String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
        StringBuffer buf = new StringBuffer();
        buf.append("^").append(num).append("\\.")
                .append(num).append("\\.").append(num)
                .append("\\.").append(num).append("$");
        return match(buf.toString() , str);   
    }
    
    
    /**
     * @param regex 正则表达式字符串
     * @param str 要匹配的字符串
     * 
     * @return 如果str符合 regex的正则表达式格式,返回true,否则返回false
     * */
    public static boolean match(String regex, String str){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    
    /**
     * 验证网址Url
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     * */
    public static boolean checkUrl(String str){
        String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        return match(regex, str);
    }
    
    
    /**
     * 验证数字
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     * */
    public static boolean checkNumber(String str){
        String regex = "^[0-9]*$";
        return match(regex, str);
    }

}
