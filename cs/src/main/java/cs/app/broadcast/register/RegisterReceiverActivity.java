package cs.app.broadcast.register;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import butterknife.OnClick;
import cs.app.R;
import cs.plugins.base.BaseActivity;


/**
 * 代码注册Receiver
 */
public class RegisterReceiverActivity extends BaseActivity {

    private SMSReceiver smsReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_receiver);
    }

    /**
     * 发送广播
     */
    @OnClick(R.id.btn_register_broadcast_send) void sendBroadcast(){
        Intent intent = new Intent();
        intent.setAction("android.provider.Telephony.SMS_RECEIVED");
        intent.putExtra("DATA_1", "你好");
        this.sendBroadcast(intent);
    }

    /**
     * 注册Receiver
     */
    @OnClick(R.id.btn_register_receiver) void registerReceiver(){
        //生成一个BroiadcastReceiver对象
        smsReceiver = new SMSReceiver();
        //生成一个IntentFilter对象
        IntentFilter filter = new IntentFilter();
        //为IntentFilter添加一个Action
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        //将BroadcastReceiver对象注册到系统当中
        registerReceiver(smsReceiver, filter);
    }

    /**
     * 解除绑定
     * 用于解除BroadcastReceiver的绑定状态。一旦解除完成，响应的BroadcastReceiver就不会再接收系统所广播的Intent
     */
    @OnClick(R.id.btn_unregister_receiver) void unregisterReceiver(){
        //解除BroadcastReceiver对象的注册
        unregisterReceiver(smsReceiver);
    }


}
