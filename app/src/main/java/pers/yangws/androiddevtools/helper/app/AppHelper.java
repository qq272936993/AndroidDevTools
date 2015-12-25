/**
 *
 */
package pers.yangws.androiddevtools.helper.app;

import java.util.List;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;

/**
 * @作者: 杨文松
 * @时间: 2015年12月11日
 * @描述: app相关信息方法
 */
public class AppHelper {


    /**
     * 是否存在需要检查的Service正在运行
     *
     * @param context     上下文
     * @param serviceName 服务名称
     * @return true(存在) / false(不存在)
     */
    public static boolean hasExistServiceRunning(Context context,
                                                 String serviceName) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningServiceInfo> serviceInfos = activityManager
                .getRunningServices(100);
        for (RunningServiceInfo info : serviceInfos) {
            if (info.service.getClassName().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取当前所有在的Activity(活动界面)
     *
     * @param context
     */
    public static Activity getTopActivity(Context context) {
        ActivityManager manager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        ComponentName componentName = info.topActivity;
        String shortClassName = componentName.getShortClassName();

        //获取任务池中的Activity

        return null;
    }

    /**
     * 是否是顶层的Activity
     *
     * @param actNames
     * @param context
     */
    public static boolean isTopActivity(List<String> actNames, Context context) {
        ActivityManager manager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        String shortClassName = info.topActivity.getClassName();

        return actNames.contains(shortClassName);
    }


}
