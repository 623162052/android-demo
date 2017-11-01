package cs.app.widget.notification;

import android.os.Bundle;

import cs.app.R;
import cs.plugins.base.BaseActivity;

/**
 * 通知
 */
public class NotificationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        new NotificationUtil(NotificationActivity.this).notification("标题", "正文");
    }

}
