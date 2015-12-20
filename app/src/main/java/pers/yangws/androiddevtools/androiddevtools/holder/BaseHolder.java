package pers.yangws.androiddevtools.androiddevtools.holder;

import android.content.Context;
import android.view.View;



/***
 * @author 杨文松
 * @version 1.0
 * @描述  面向Holder编程的基类
 * */
public abstract class BaseHolder<T> {

	protected T mData;
	private View rootView;
	protected Context mContext;
		
	public BaseHolder(Context context){
		this.mContext = context;
		rootView = initView();
		rootView.setTag(this);
	}
	
	protected abstract View initView() ;
	
	protected abstract void refreshView();
	
	public View getRootView(){
		return this.rootView;
	}
	
	public void setData(T data){
		this.mData = data;
		refreshView();
	}
	
}
