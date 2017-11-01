package cs.app.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.OnClick;
import cs.app.R;
import cs.plugins.base.BaseActivity;


/**
 * 处理消息在UI主线程，发送消息在主线程(Handler默认关联主线程)
 */
public class HandlerActivity1 extends BaseActivity {
    private static final String TAG = "HandlerActivity";

    static int i = 0;
    @Bind(R.id.progressBar_handler)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
    }

    /**
     * 开始
     */
    @OnClick(R.id.btn_handler_start) void btn_handler_start(){
        progressBar.setVisibility(View.VISIBLE);

        Log.d(TAG, "btn_start--->" + Thread.currentThread().getId());
        handler.post(updateThread);         // Handler把线程加到消息队列等待执行
    }

    /**
     * 结束
     */
    @OnClick(R.id.btn_handler_end) void btn_handler_end(){
        handler.removeCallbacks(updateThread);  // Handler把线程移除消息队列
        progressBar.setVisibility(View.INVISIBLE);
    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            progressBar.setProgress(msg.arg1);
            if(msg.arg1 >= 100){
                handler.removeCallbacks(updateThread);
                progressBar.setVisibility(View.INVISIBLE);
            }else{
                handler.post(updateThread);
            }

        }
    };
    Runnable updateThread = new Runnable() {
        @Override
        public void run() {
            i = i + 30;
            Message message = handler.obtainMessage();
            message.arg1 = i;
            Log.d(TAG, "updateThread.run --> i: " + i);
            Log.d(TAG, "updateThread.run--->" + Thread.currentThread().getId());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.sendMessage(message);       // 发送消息给Handler处理
        }
    };

}
