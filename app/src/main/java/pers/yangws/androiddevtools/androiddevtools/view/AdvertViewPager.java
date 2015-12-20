package pers.yangws.androiddevtools.androiddevtools.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * 广告轮询播放ViewPager
 * */
public class AdvertViewPager extends ViewPager {

	private final int START_LOOP = 1;
	private final int LOOPING = 2;
	private final int STOP_LOOP = 0;
	private long mSpanTime = 3000;
	
	
	public AdvertViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AdvertViewPager(Context context) {
		super(context);
	}


	@Override
	public void setAdapter(PagerAdapter arg0) {
		super.setAdapter(arg0);
	}
	
	public void setSpanTime(long spanTime){
		this.mSpanTime = spanTime;
	}

	
	public void setStartItem(int size){
		 int position = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2 % size);
		 setCurrentItem(position);
	}
	

	/**
	 * 开始轮询
	 * */
	public void startRotation(){
		mHandler.sendEmptyMessage(START_LOOP);
	}
	
	
	/**
	 * 停止轮询
	 * */
	public void stopRetation(){
		mHandler.sendEmptyMessage(STOP_LOOP);
	}
	
	private boolean isLooping = false;		//是否循环
	
	private Handler  mHandler = new Handler(){
		
		
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case START_LOOP:		//开始轮询
				{
					isLooping = true;
					mHandler.sendEmptyMessageDelayed(LOOPING , mSpanTime);	//开启轮询
				}
					break;
				case LOOPING:
				{
					if(isLooping){
						int position = getCurrentItem();
						setCurrentItem(position+1);
						mHandler.sendEmptyMessageDelayed(LOOPING, mSpanTime);
					}else{
						mHandler.sendEmptyMessageDelayed(LOOPING, mSpanTime);
					}
				}
					break;
				case STOP_LOOP:			//停止轮询
				{
					isLooping = false;
				}
					break;
			default:
				break;
			}
		}
	};
	
	
	/**
	 * 这里这么做是为了在点击广告栏后停止滚动
	 * */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
			{
				isLooping = false;
			}
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
			{
				isLooping = true;
			}
				break;
		default:
			break;
		}
		
		return super.onTouchEvent(event);
	}
	
	
	
	
}
