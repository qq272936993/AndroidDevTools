package pers.yangws.androiddevtools.application;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pers.yangws.androiddevtools.Constants;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

public class BasisApplication extends Application{
	
	/**
	 * 需要说明下,很多数据中这个集合都是定义成List的集合.这里定义成Map形式的集合,
	 * 		用于方便管理,因为分模块管理.有可能需求是退出某个模块,而并不是退出整个应用.
	 * */
	private static Map<String,List<Activity>> mOpenActivitys;	//所有打开的Activity,方便用于安全退出
	
	//获取到主线程
	private static Thread mMainThread;
	//获取到主线程的handler
	private static Handler mMainThreadHandler;
	//获取到主线程的looper
	private static Looper mMainThreadLooper;
	//获取到主线程的上下文
	private static BasisApplication mContext;
	//获取到主线程的id
    private static int mMainTheadId;
	
    private static SharedPreferences mSp;
    
    
	@Override
	public void onCreate() {
		super.onCreate();
		
		mContext = this;
		mOpenActivitys = new HashMap<String, List<Activity>>();
		mMainThreadHandler = new Handler();				//Handler
		mMainThread = Thread.currentThread();	//当前线程就是主线程
		mMainThreadLooper = getMainLooper();			//主线程Looper对象
		
		//android.os.Process.myUid()   获取到用户id
	    //android.os.Process.myPid()获取到进程id
	    //android.os.Process.myTid()获取到调用线程的id
		mMainTheadId = android.os.Process.myTid();
		
		mSp = getSharedPreferences(Constants.sp.SP_NAME, Context.MODE_PRIVATE);
		
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionFilterHandler());
	}
	
	
	
	/**
	 * 主线程
	 * */
	public static Thread getMainThread(){
		return mMainThread;
	}
	
	/**
	 * Handler对象
	 * */
	public static Handler getMainThreadHandler(){
		return mMainThreadHandler;
	}
	
	/**
	 * 主线程Looper对象
	 * 由于已经有getMainLooper方法,所以写成了UiLooper
	 * */
	public static Looper getUiLooper(){
		return mMainThreadLooper;
	}
	
	public static int getMainThreadId(){
		return mMainTheadId;
	}
	
	/**
	 * 获取SharePreferences
	 * */
	public static SharedPreferences getSharePaPreferences(){
		return mSp;
	}
	
	
	/**
	 * 获取APP的上下文
	 * */
	public static BasisApplication getApplication(){
		return mContext;
	}
	
	
	
	/**
	 * 添加打开的Activity进入集合管理中
	 * @param module 模块
	 * @param activity 界面
	 * */
	public void addActivity(String module,Activity activity){
		if( mOpenActivitys.containsKey(module) ){
			mOpenActivitys.get(module).add(activity);
		}else{
			List<Activity> activitys = new ArrayList<Activity>();
			activitys.add(activity);
			mOpenActivitys.put(module, activitys);
		}
	}
	
	/**
	 * 退出某个模块
	 * @param module 模块
	 * */
	public void quitModule(String module){
		if(mOpenActivitys.containsKey(module)){
			List<Activity> activities = mOpenActivitys.get(module);
			for(Activity activity : activities){
				activity.finish();
			}
			activities.clear();
			activities = null;		//释放缓存
			mOpenActivitys.remove(module);
		}
		
	}
	
	
	/**
	 * 退出整个App,遍历所有模块
	 * */
	public void quitApp(){
		for(Entry<String, List<Activity>> entry : mOpenActivitys.entrySet()){
			for(Activity activity : entry.getValue()){
				activity.finish();
			}
		}
		mOpenActivitys.clear();
	}
	
	
	
	
	
	
	
	
	/**
	 * 自定义的一个UncaughtExceptionHandler
	 * */
	public class ExceptionFilterHandler implements UncaughtExceptionHandler{

		/**
		 * 这里可以错任何针对异常的处理,比如记录日志等等
		 * */
		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			
			
			
		}
	}
	
	
}
