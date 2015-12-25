package pers.yangws.androiddevtools.helper.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import pers.yangws.androiddevtools.Constants;
import pers.yangws.androiddevtools.application.BasisApplication;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.text.TextUtils;


/**
 * SharedPerference工具类
 */
public class SharedPerferenceHelper {


    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    /**
     * 兼容低版本putStringSet
     * @param sp SharedPerference
     * @param key 键值
     * @param defValues 默认值
     * */
    public static void putStringSet( String key, Set<String> defValues) {
        SharedPreferences sp = BasisApplication.getSharePaPreferences();
        if (sp != null && !TextUtils.isEmpty(key) && defValues != null && !defValues.isEmpty()) {
            int sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK);
            if (sdkVersion >= 11) {
                sp.edit().putStringSet(key, defValues).commit();
            } else {
                StringBuffer buffer = new StringBuffer();
                for (String val : defValues) {
                    buffer.append(val).append(Constants.SpecialChar.CHAT_TEXT_LEFT_BRACKETS_TAG);
                }
                sp.edit().putString(key, buffer.toString()).commit();
            }
        }
    }


    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    /**
     * 兼容低版本getStringSet
     * @param sp SharedPerference
     * @param key 键值
     * */
    public static Set<String> getStringSet(String key) {
        SharedPreferences sp = BasisApplication.getSharePaPreferences();
        if (sp != null && !TextUtils.isEmpty(key)) {
            int sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK);
            if (sdkVersion >= 11) {
                return sp.getStringSet(key, null);
            } else {
                String val = sp.getString(key, null);
                if (val == null) return null;
                else {
                    String[] vals = val.split(Constants.SpecialChar.CHAT_TEXT_LEFT_BRACKETS_TAG);
                    return new HashSet<String>(Arrays.asList(vals));
                }
            }
        }
        return null;
    }


}
