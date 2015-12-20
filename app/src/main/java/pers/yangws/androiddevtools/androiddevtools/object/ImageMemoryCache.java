package pers.yangws.androiddevtools.androiddevtools.object;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;



/**
 * 功能描述:图片内存缓存
 * 		尽量不要自己创建,请使用ImageManager管理
 * */
public class ImageMemoryCache {

	private final String TAG = "ImageMemoryCache";
	
	private LruCache<String , Bitmap> mLruCache;	//强引用缓存
	
	private LinkedHashMap<String, SoftReference<Bitmap>> mSoftCache;
	
	private static final int LRU_CACHE_SIZE = 4 * 1024 * 1024;
	
	private static final int SOFT_CACHE_NUM = 20;	//软引用个数
	
	
	/**
	 * 每次创建这个对象都会开闭一个新的内存区域,建议使用单例模式
	 * 
	 * */
	public ImageMemoryCache(){
		mLruCache = new LruCache<String, Bitmap>(LRU_CACHE_SIZE){
			
			@Override
			protected int sizeOf(String key, Bitmap value) {
				if(value != null){
					return value.getRowBytes() * value.getHeight();
				}else
					return 0;
			}
			
			@Override
			protected void entryRemoved(boolean evicted, String key,
					Bitmap oldValue, Bitmap newValue) {
				if(oldValue != null){
					//强引用缓存容量满的时候,会根据LRU算法把最近没有使用的的图片转入此软引用
					mSoftCache.put(key, new SoftReference<Bitmap>(oldValue));
				}
			}
		};
		
		
		mSoftCache = new LinkedHashMap<String, SoftReference<Bitmap>>(
				 SOFT_CACHE_NUM, 0.75f, true){
			
			/**
			 * 当软应用数量大于20的时候,最旧的软应用将会从链式哈希表中移出
			 * */
			@Override
			protected boolean removeEldestEntry(
					Entry<String, SoftReference<Bitmap>> eldest) {
				if(size() > SOFT_CACHE_NUM){
					return true;
				}
				return false;
			}
		};
		
	}
	
	
	/**
	 * 从缓存中获取图片
	 * */
	public Bitmap getBitmapFromMemory(String url){
		Bitmap bitmap;
		
		//先从强引用缓存中获取
		synchronized (mLruCache) {
			bitmap = mLruCache.get(url);
			if(bitmap != null){
				//如果找到的话,将元素移到LinkedHashMap的最前面,从而保证LRU算法中的最后被删除
				mLruCache.remove(url);
				mLruCache.put(url, bitmap);
				Log.d(TAG, "get bmp from LruCache, url="+url);
				
				return bitmap;
			}
		}
		
		//如果强引用缓存中找不到
		synchronized (mSoftCache) {
			SoftReference<Bitmap> bitmapReference = mSoftCache.get(url);
			if(bitmapReference != null){
				 bitmap = bitmapReference.get(); 
				 if (bitmap != null) {
					// 将图片移回LruCache  
					mLruCache.put(url, bitmap);
					mSoftCache.remove(url);
					Log.d(TAG, "get bmp from SoftReferenceCache, url=" + url);  
                    return bitmap;  
				 }else{
					 mSoftCache.remove(url);
				 }
			}
		}
		return null;
	}
	
	
	/**
	 * 添加图片缓存
	 * */
	public void addBitmapToMemory(String url ,Bitmap bitmap){
		if(bitmap != null){
			synchronized (mLruCache) {
				mLruCache.put(url, bitmap);
			}
		}
	}
	
	public void clearCache(){
		mSoftCache.clear();
	}
	
	
	
	
}
