package cs.app.broadcast.basic;

import android.content.Intent;
import android.os.Bundle;

import butterknife.OnClick;
import cs.app.R;
import cs.plugins.base.BaseActivity;


/**
 *  1、广播发送者通过Intent发送数据
 *  2、AndroidManifest.xml注册Receiver，定义intent-filter判断Receiver是否接收
 *    【使用这样的方法进行注册之后，即使你的应用程序没有启动，或者已经被关闭，这个BroadcastReceiver依然会继续运行】
 *  3、Receiver在onReceive方法处理逻辑
 */
/**
 * 广播发送者
 */
public class BroadcastActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
    }

    /**
     * 发送广播
     */
    @OnClick(R.id.btn_broadcast_send) void sendBroadcast(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_EDIT);
        intent.putExtra("DATA_1", "你好");
        this.sendBroadcast(intent);
    }

}
