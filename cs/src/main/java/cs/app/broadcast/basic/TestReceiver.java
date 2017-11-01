package cs.app.broadcast.basic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;

/**
 * 广播接收者
 */
public class TestReceiver extends BroadcastReceiver {
private static final String TAG = "TestReceiver";

    /**
     * 构造方法
     */
	public TestReceiver() {
		super();
		Log.d(TAG, "[TestReceiver].TestReceiver()");
	}

    /**
     * 接收广播处理
     */
	@Override
	public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "[TestReceiver].onReceive: " + JSON.toJSONString(intent));
	}

}
