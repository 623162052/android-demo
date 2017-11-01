package cs.app.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.OnClick;
import cs.app.R;
import cs.plugins.base.BaseActivity;

public class HandlerActivity3 extends BaseActivity {
    @Bind(R.id.bar)
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_activity3);
    }

    @OnClick(R.id.startButton) void onStartClick(){
        bar.setVisibility(View.VISIBLE);
        updateBarHandler.post(updateThread);
    }

    //使用匿名内部类来复写Handler当中的handleMessage方法
    Handler updateBarHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            bar.setProgress(msg.arg1);
            Bundle bundle = msg.getData();
            updateBarHandler.post(updateThread);
            System.out.println("test---->" + bundle.getString("test"));
        }

    };
    //线程类，该类使用匿名内部类的方式进行声明
    Runnable updateThread = new Runnable(){
        int i = 0 ;
        @Override
        public void run() {
            System.out.println("Begin Thread" + i);
            i = i + 10 ;
            //得到一个消息对象，Message类是有Android操作系统提供
            Message msg = updateBarHandler.obtainMessage();

            //将msg对象的arg1参数的值设置为i,用arg1和arg2这两个成员变量传递消息，优点是系统性能消耗较少
            msg.arg1 = i ;
            Bundle bundle = new Bundle();
            bundle.putString("test", "test bundle");
            msg.setData(bundle);
            try {
                //设置当前显示睡眠1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //将msg对象加入到消息队列当中
            if( i > 100){
                //如果当i的值为100时，就将线程对象从handler当中移除
                updateBarHandler.removeCallbacks(updateThread);
                System.out.println("removeCallbacks");
            }else{
                updateBarHandler.sendMessage(msg);
                System.out.println("sendMessage");
            }
        }
    };
}
