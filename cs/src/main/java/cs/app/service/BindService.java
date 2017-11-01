package cs.app.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import cs.app.MainActivity;
import cs.app.R;

/**
 * Created by shiwx on 2015/2/12.
 */
public class BindService extends Service {

    private static final String TAG = "BindService";
    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate [BindService thread id is" + Thread.currentThread().getId() + " ]");
        super.onCreate();

        // 前置Service
        foregroundService();
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

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return myBinder;
    }


    class MyBinder extends Binder {
        public void startDownload() {
            Log.d("TAG", "startDownload() executed");
            // 执行具体的下载任务

        }
    }

    /**
     * 前台Service
     */
    private void foregroundService(){
        // TODO: 此处API过时
//        Notification notification = new Notification(R.drawable.ic_launcher, "有通知到来", System.currentTimeMillis());
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//        notification.setLatestEventInfo(this, "这是通知的标题", "这是通知的内容", pendingIntent);
//        startForeground(1, notification);

        // Create a Notification Builder
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setContentTitle("通知的标题");
        mBuilder.setContentText("通知的内容");

        // Define the Notification 's Action, Set the Notification 's Click Behavior
        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        // Issue the Notification
        int mNotificationId = 001;
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());

    }
}
