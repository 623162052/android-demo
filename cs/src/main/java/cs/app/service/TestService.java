package cs.app.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {
    private static final String TAG = "TestService";

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind");
		return null;
	}

    /**
     * onCreate()方法只会在Service第一次被创建的时候调用，如果当前Service已经被创建过了，不管怎样调用startService()方法，onCreate()方法都不会再执行
     */
	@Override
	public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
        Log.d(TAG, "onDestroy");
		super.onDestroy();
	}

    /**
     * 闹钟任务
     */
    private void alarmManager(){
		Intent intent = new Intent("android.intent.action.EDIT");
		intent.putExtra("heartType", "fix");
		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        /**
         * 手机需要关闭节电程序
         */
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 20 * 1000, sender);
    }
}
