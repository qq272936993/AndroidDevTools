package pers.yangws.androiddevtools.helper.app;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;

import pers.yangws.androiddevtools.application.BasisApplication;

/**
 * Created by 1yyg-安卓 on 2015/12/29.
 * 这里可以使用Application的 Context,是因为只是获取数据,不是UI
 * */
public class ResourceHelper {


    private static Resources getResource(){
        return BasisApplication.getApplication().getResources();
    }


    /**
     * 获取字符串资源
     * @param  resId 资源id
     * @return  字符串
     * */
    public static String getString(int resId){
        return getResource().getString(resId);
    }

    /**
     * 获取字符串资源并格式化
     * @param resId 资源id
     * @param formatArgs 格式化对象
     * @return 字符串
     * */
    public static String getString(int resId,Object... formatArgs){
        return getResource().getString(resId, formatArgs);
    }

    /**
     * 获取整形资料
     * @param  resId 资源id
     * @return  整形
     * */
    public static int getInt(int resId){
        return getResource().getInteger(resId);
    }


    /**
     * 获取动画资源
     * @param resId 资源id
     * @return xml资源解析类
     * */
    public static XmlResourceParser getAnimation(int resId){
        return getResource().getAnimation(resId);
    }


    /**
     * 获取boolean资源
     * @param resId 资源id
     * @return boolean
     * */
    public static boolean getBoolean(int resId){
        return getResource().getBoolean(resId);
    }

    /**
     * 获取颜色资源
     * @param resId 资源id
     * @return 颜色资源
     * */
    public static int getColor(int resId){
         return getResource().getColor(resId);
    }

    /**
     * 获取dimension资源
     * @param resId 资源id
     * @return
     * */
    public static float getDimension(int resId){
        return getResource().getDimension(resId);
    }


    public static int getDimensionPixelOffset(int resId){
         return getResource().getDimensionPixelOffset(resId);
    }


    public static Drawable getDrawable(int resId){
        return getResource().getDrawable(resId);
    }

    public static int[] getIntArray(int resId){
        return getResource().getIntArray(resId);
    }

    public static XmlResourceParser getLayout(int resId){
        return getResource().getLayout(resId);
    }


    public static String[] getStringArray(int resId){
         return getResource().getStringArray(resId);
    }

    public static CharSequence getText(int resId){
        return getResource().getText(resId);
    }

    public static CharSequence getText(int resId , CharSequence def){
        return getResource().getText(resId , def);
    }

    public static CharSequence[] getTextArray(int resId){
        return getResource().getTextArray(resId);
    }




}
