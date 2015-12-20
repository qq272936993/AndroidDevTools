package pers.yangws.androiddevtools.androiddevtools.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BinderPoolService extends Service {
	
	public static final String TAG = "BinderPoolService";
	
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
