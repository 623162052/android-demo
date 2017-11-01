package cs.plugins.base;


import android.app.Application;
import android.content.Context;
import android.content.Intent;

import cs.plugins.log.Logger;
import cs.plugins.map.amap.demo.TrackService;

/**
 * 程序的入口
 * 定义和应用生命周期相关的东西
 * 1、基本组件间的数据传递
 * 2、缓存数据
 * <p/>
 * 参考：http://my.oschina.net/ljhUncle/blog/90900
 * <p/>
 * Created by shiwx on 2015/1/9.
 */
public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d(TAG, "onCreate");

        mContext = getApplicationContext();

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext，或者放在Activity中的setContentView之前
        //SDKInitializer.initialize(this);
    }

    public static Context getContext() {
        return mContext;
    }


    @Override
    public void onTerminate() {
        Logger.d(TAG, "onTerminate");

        Intent intent = new Intent(BaseApplication.getContext(), TrackService.class);
        intent.setPackage("cs.plugins.map.amap.demo");
        stopService(intent);

        super.onTerminate();
    }


    @Override
    public void onLowMemory() {
        Logger.e(TAG, "onLowMemory");
        super.onLowMemory();
        System.gc();
    }
}
