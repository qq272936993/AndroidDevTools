package pers.yangws.androiddevtools.helper;

import android.content.Context;
import android.widget.Toast;

import pers.yangws.androiddevtools.application.BasisApplication;

/**
 * Created by 1yyg-安卓 on 2015/12/23.
 *
 * 提醒工具类
 * Android Toast
 */
public class PromptHelper {



    /**
     * 在主线程中显示Toast提示
     * @param context 上下文,使用Activity生命周期的Context
     * @param text 显示文本
     * */
    public static void showToast(final Context context ,final String text){
        //1.判断是否为主线程
        if(BasisApplication.getMainThread().equals(Thread.currentThread())){
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }else{
            BasisApplication.getMainThreadHandler().post(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 在主线程中显示Toast提示
     * */
    public static void showToast(final Context context,final int resId){
        //1.判断是否在主线程
        if(BasisApplication.getMainThread().equals(Thread.currentThread())){
            Toast.makeText(context, context.getResources().getText(resId), Toast.LENGTH_SHORT).show();
        }else{
            BasisApplication.getMainThreadHandler().post(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, context.getText(resId), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



}
