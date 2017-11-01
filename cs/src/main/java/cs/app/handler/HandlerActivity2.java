package cs.app.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSON;

import butterknife.Bind;
import butterknife.OnClick;
import cs.app.R;
import cs.plugins.base.BaseActivity;

/**
 * 处理消息在其他线程
 * 发送消息在UI线程
 *
 * 通过Bundle传递参数
 */
public class HandlerActivity2 extends BaseActivity {

    static int i = 0;
    private static final String TAG = "HandlerActivity";
    @Bind(R.id.btn_handler_start)
    Button btn_start;
    @Bind(R.id.btn_handler_end)
    Button btn_end;
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
    @OnClick(R.id.btn_handler_start) void btnStartOnClick(){
        progressBar.setVisibility(View.VISIBLE);

        Log.d(TAG, "btn_start--->" + Thread.currentThread().getId());
//                handler.post(updateThread);         // Handler把线程加到消息队列等待执行

        /**
         * HandlerThread:
         * 生成一个HandlerThread对象，实现了使用Looper来处理消息队列的功能
         */
        HandlerThread handlerThread = new HandlerThread("handler_thread");
        handlerThread.start();

        MyHandler myHandler = new MyHandler(handlerThread.getLooper());
        Message msg = myHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("name", "shiwx");
        msg.setData(bundle);
        msg.sendToTarget();
    }


    /**
     * 结束
     */
    @OnClick(R.id.btn_handler_end) void btnEndOnClick(){
        // handler.removeCallbacks(updateThread);  // Handler把线程移除消息队列
        progressBar.setVisibility(View.INVISIBLE);
    }

    class MyHandler extends Handler {
        public MyHandler(){}
        public MyHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            Log.d(TAG, "bundle: " + JSON.toJSONString(bundle));
            Log.d(TAG, "Handler--->" + Thread.currentThread().getId());
        }
    }
}
