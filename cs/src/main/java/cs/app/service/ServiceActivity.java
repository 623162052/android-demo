package cs.app.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import butterknife.OnClick;
import cs.plugins.http.HttpConstant;
import cs.app.R;
import cs.plugins.base.BaseActivity;

/**
 *
 * // TODO: TestService not found
 * Service Test
 * <p/>
 * 主要用于在后台处理一些耗时的逻辑，或者去执行某些需要长期运行的任务。
 * 必要的时候我们甚至可以在程序退出的情况下，让Service在后台继续保持运行状态。
 *
 * 【Service运行在主线程里】
 *  Android的后台就是指，它的运行是完全不依赖UI的。即使Activity被销毁，或者程序被关闭，只要进程还在，Service就可以继续运行。比如说一些应用程序，始终需要与服务器之间始终保持着心跳连接，就可以使用Service来实现。你可能又会问，前面不是刚刚验证过Service是运行在主线程里的么？在这里一直执行着心跳连接，难道就不会阻塞主线程的运行吗？当然会，但是我们可以在Service中再创建一个子线程，然后在这里去处理耗时逻辑就没问题了。
 *  既然在Service里也要创建一个子线程，那为什么不直接在Activity里创建呢？这是因为Activity很难对Thread进行控制，当Activity被销毁之后，就没有任何其它的办法可以再重新获取到之前创建的子线程的实例。而且在一个Activity中创建的子线程，另一个Activity无法对其进行操作。但是Service就不同了，所有的Activity都可以与Service进行关联，然后可以很方便地操作其中的方法，即使Activity被销毁了，之后只要重新与Service建立关联，就又能够获取到原有的Service中Binder的实例。因此，使用Service来处理后台任务，Activity就可以放心地finish，完全不需要担心无法对后台任务进行控制的情况。
 *
 * Service在整个应用程序范围内都是通用的，一个Service可以和多个Activity关联，多个Activity都可以获得Service的Bind实例
 */
public class ServiceActivity extends BaseActivity {
    private static final String TAG = "ServiceActivity";

    private BindService.MyBinder myBinder;
    private ServiceConnection connection = new ServiceConnection() {
        /**
         * 在Activity与Service建立关联时调用
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (BindService.MyBinder) service;
            myBinder.startDownload();
        }

        /**
         * 在Activity与Service解除关联时调用
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Log.d(TAG, "ServiceActivity thread id is " + Thread.currentThread().getId());
    }

    /**
     * 启动Service
     */
    @OnClick(R.id.btn_service_start)
    void startService() {
        Intent intent = new Intent(this, TestService.class);
        intent.setData(Uri.parse(HttpConstant.BAIDU_URL));
        startService(intent);
    }

    /**
     * 停止Service
     */
    @OnClick(R.id.btn_service_stop)
    void stopService() {
        Intent intent = new Intent(this, TestService.class);
        stopService(intent);
    }

    /**
     * 绑定 BindService
     */
    @OnClick(R.id.btn_service_start)
    void startBindService() {
        Intent bindIntent = new Intent(this, BindService.class);
        /**
         * 这里传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service，这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行。
         */
        bindService(bindIntent, connection, BIND_AUTO_CREATE);
    }

    /**
     * 解除绑定 Service
     */
    @OnClick(R.id.btn_service_stop)
    void stopBindService() {
        unbindService(connection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
