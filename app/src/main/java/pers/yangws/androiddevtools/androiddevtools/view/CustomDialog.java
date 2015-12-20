package pers.yangws.androiddevtools.androiddevtools.view;


import pers.yangws.androiddevtools.application.BasisApplication;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


/**
 * 自定义Dialog
 * */
public class CustomDialog {

	
	public static WindowManager.LayoutParams getDefaultLayoutParams(){
		 WindowManager.LayoutParams sysParams= new WindowManager.LayoutParams();
		 WindowManager.LayoutParams params = sysParams;
         params.height = WindowManager.LayoutParams.WRAP_CONTENT;
         params.width = WindowManager.LayoutParams.WRAP_CONTENT;
         params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                 | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
         params.format = PixelFormat.TRANSLUCENT;
         //params.windowAnimations = com.android.internal.R.style.Animation_Toast;
         params.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;	//优先于手机UI
         params.setTitle("Toast");
         
         return params;
	}
	
	private CustomDialog(){
		throw new RuntimeException("don't create this Class");
	}
	
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
	 * 开启自定义Toast
	 * @param context 上下文对象
	 * @param view 自定义布局
	 * @param 显示参数
	 * */
	public static void automaticToastShow(Context context, View view, WindowManager.LayoutParams mParams){
		WindowManager.LayoutParams sysParams = mParams != null ? mParams : getDefaultLayoutParams();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.addView(view, sysParams);
	}
	
	/**
	 * 隐藏关闭自定义Toast
	 * @param context 上下文
	 * @param mView 显示的布局View
	 * */
	public static void automaticToastHide(Context context , View mView){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		if (mView != null) {
            // note: checking parent() just to make sure the view has
            // been added...  i have seen cases where we get here when
            // the view isn't yet added, so let's try not to crash.
            if (mView.getParent() != null) {
            	wm.removeView(mView);
            }

            mView = null;
        }
	}
	
	
	
	
}
