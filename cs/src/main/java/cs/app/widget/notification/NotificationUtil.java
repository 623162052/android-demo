package cs.app.widget.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import cs.app.MainActivity;
import cs.app.R;

/**
 * Created by shiwx on 2016/1/25.
 */
public class NotificationUtil {

    private Context mContext;

    public NotificationUtil(Context context){
        this.mContext = context;
    }


    /**
     * Notification
     */
    public void notification(String title, String content){
        // Create a Notification Builder
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(content);

        // Define the Notification 's Action, Set the Notification 's Click Behavior
        Intent resultIntent = new Intent(mContext, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        // Issue the Notification
        int mNotificationId = 001;
        NotificationManager mNotifyMgr = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

}
