package pers.yangws.androiddevtools.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public abstract class BasisPagerAdapter<T> extends PagerAdapter{
	
	protected List<T> mData;
	protected Context mContext;
	
	public BasisPagerAdapter(Context context ,List<T> data){
		this.mContext = context;
		this.mData = data;
	}
	
	public void setData(List<T> data){
		this.mData = data;
		notifyDataSetChanged();
	}
	

	@Override
	public int getCount() {
		return mData != null ? mData.size() : 0;
	}

	
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
	
	
	/**
	 * 初始化对象
	 * */
	@Override
	public Object instantiateItem(View container, int position) {
		View view = getView(position);
		((ViewPager)container).addView(view);	//这个这个View应该何处而来
		return view;
	}
	
	
	//销毁 TODO需要测试object 和 container的类型 
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager)container).removeView((View)object);
	}
	

	/**
	 * 获取视图
	 * @param position 坐标
	 * */
	public abstract View getView(int position);
		
	
	
	
}
