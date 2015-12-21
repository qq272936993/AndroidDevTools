/**
 * 
 */
package pers.yangws.androiddevtools.view;

import java.io.File;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.widget.Toast;

/**
 * @作者: 杨文松
 * @时间: 2015年12月8日
 * @描述: 
 * 		SurfaceView视频播放基类
 */
public class MMSurfaceView extends SurfaceView
	implements Callback, OnPreparedListener,OnErrorListener,
	OnInfoListener{

	private final String TAG = "MMSurfaceView";		//日志标签
	private SurfaceHolder mHolder;		
	protected MediaPlayer mMediaPlayer;				//媒体播放类
	protected String mVideoPath;					//视频播放的路径
	protected boolean isRepair = false;				//是否正在尝试修复
	protected boolean isMute   = false;				//是否静音
	protected boolean isLoop   = false;				//是否循环播放
	protected OnCompletionListener mOnCompletionListener;	//完成监听
	private Handler mHandler = new Handler();
	private boolean isCreated;
	

	public MMSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public MMSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MMSurfaceView(Context context) {
		super(context);
		init();
	}

	
	@SuppressWarnings("deprecation")
	public void init(){
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	
	/**
	 * 是否静音
	 * @param isMute
	 * */
	public void setIsMute(boolean isMute){
		this.isMute = isMute;
	}
	
	/**
	 * 是否循环播放
	 * */
	public void setIsLoop(boolean isLoop){
		this.isLoop = isLoop;
	}
	
	/**
	 * 设置完成监听
	 * */
	public void addOnCompletionListener(OnCompletionListener onCompletionListener){
		this.mOnCompletionListener = onCompletionListener;
	}
	
	public void setVideoPath(String videoPath){
		try{
			this.mVideoPath = videoPath;
			isRepair = false;
			
			prepare();
			requestLayout();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 准备播放
	 * */
	protected void prepare(){
		releaseMediaPlay();
		if(TextUtils.isEmpty(mVideoPath) || 
				!new File(mVideoPath).exists() ||
				new File(mVideoPath).length() <= 0){
			//不是有效路径
			return;
		}
		
		try{
			mMediaPlayer = new MediaPlayer();
			if(isMute) mMediaPlayer.setVolume(0f, 0f);				//设置声音
			
			mMediaPlayer.setOnPreparedListener(this);
			if(mOnCompletionListener != null)
				mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
			
			mMediaPlayer.setLooping(isLoop);
			mMediaPlayer.setOnInfoListener(this);
			mMediaPlayer.setOnErrorListener(this);
			mMediaPlayer.setDataSource(mVideoPath);
			mMediaPlayer.setAudioStreamType(android.media.AudioManager.STREAM_MUSIC);
			canPlayVideo();
		}catch(Exception exception){
			exception.printStackTrace();
			Log.i(TAG, "prepare error");
		}
	}
	
	/**
	 * 释放视频播放器
	 * */
	public void releaseMediaPlay(){
			if(mMediaPlayer != null){
				try {
					if(mMediaPlayer.isPlaying()) mMediaPlayer.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					mMediaPlayer.release();
					mMediaPlayer = null;
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(mMediaPlayer != null)	mMediaPlayer.release();
					mMediaPlayer = null;
				}
			}
		
	}
	
	
	/**
	 * 是否可以播放视频
	 * */
	public void canPlayVideo(){
		try {
			if(mMediaPlayer != null && isCreated){
				mMediaPlayer.setDisplay(getHolder());
				mMediaPlayer.prepareAsync();
			}
		} catch (IllegalStateException e) {
			//非法转态异常
			onError(mMediaPlayer, 0, 0);
		}
	}
	
	
	/***
	 * 停止播放
	 * */     
	public final void stop(){
		if(mMediaPlayer != null){
			mMediaPlayer.stop();
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}
	
	/**
	 * 设置是否循环播放
	 * */
	public void setLoop(boolean isLoop){
		if(mMediaPlayer != null){
			mMediaPlayer.setLooping(isLoop);
		}
	}
	
	/**
	 * 是否正在播放
	 * */
	public final boolean isPlaying(){
		if(mMediaPlayer != null ){
			try {
				return mMediaPlayer.isPlaying();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 获取文件时长
	 * */
	public int getDuration(){
		try {
			if(mMediaPlayer != null){
				return mMediaPlayer.getDuration();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	

	@Override
	public void onPrepared(MediaPlayer mp) {
		mp.start();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mHolder = holder;
		isCreated = true;
		canPlayVideo();	
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		isCreated = false;
		releaseMediaPlay();
	}

	@Override
	public boolean onError(MediaPlayer mp, int whatError, int extra) {
		try {
			switch (whatError) {
				case MediaPlayer.MEDIA_ERROR_IO:
				{//文件或网络相关的错误
					Toast.makeText(getContext(), "文件不完整!", Toast.LENGTH_SHORT).show();
				}
					break;
				case MediaPlayer.MEDIA_ERROR_MALFORMED:
				{//比特流是不符合先关编码标准的文件
					Toast.makeText(getContext(), "文件编码不正确!", Toast.LENGTH_SHORT).show();
				}			
					break;
				case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
				{//视频流是其容器不是有效的渐进式播放即视频的索引（例如，MOOV原子）不是在文件的开头。
					Toast.makeText(getContext(), "非有效的视频文件!", Toast.LENGTH_SHORT).show();
				}
					break;
				case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
				{//媒体播放器死亡,通常这种情况需要释放MediaPlay和重新实例化一个对象
					restar();
				}
					break;
				case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
				{//有些操作需要太长时间才能完成,一般超过3-5秒
					restar();
				}
					break;
				case MediaPlayer.MEDIA_ERROR_UNKNOWN:
				{//未指定媒体播放器的错误,不进行重连
					//重新释放
					if(getVisibility() == View.VISIBLE){
						restar();
					}
				}
					break;
				case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:
				{//比特流是符合相关的编码标准或文件规范,但媒体框架不支持该功能
					Toast.makeText(getContext(), "媒体框架不支持该功能!", Toast.LENGTH_SHORT).show();;
				}
					break;
				default:
				{
					restar();
				}
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 重新开启
	 * */
	private void restar(){
		if(!isPlaying() && !isRepair){
			isRepair = true;		//设置正在错误重启操作
			
			mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					if(getVisibility() == View.VISIBLE){
						System.out.println("onError 向重启!");
						setVideoPath(mVideoPath);
					}
				}
			}, 1000);
		}
	}

	@Override
	public boolean onInfo(MediaPlayer mp, int whatInfo, int extra) {
		switch (whatInfo) {
			case MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING:
			{
				//当文件中的音频和视频数据不正确地交错是将触发如下操作.在一个正确交错的媒体文件中,
					//音频和视频样本将依序排列,从而使得播放能够有效和平稳定进行.
				
			}
				break;
			case MediaPlayer.MEDIA_INFO_NOT_SEEKABLE:
			{
				//当媒体不能正确定位时,(这意味着它可能是一个在线流)将触发如下操作.
				
			}
				break;
			case MediaPlayer.MEDIA_INFO_UNKNOWN:
			{
				//信息尚未指定或未知
				
			}
				break;
			case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
			{
				//当设备无法播放视频时将触发如下操作.这可能是将要播放视频,但是该视频太
				//复杂或者码率过高
				
			}
				break;
			case MediaPlayer.MEDIA_INFO_METADATA_UPDATE:
			{
				//当心的元数据可用时将触发它
				
			}
			default:
				break;
		}
	
		return true;
	}
	
	
	
}
