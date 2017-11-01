package cs.plugins.map.amap.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import cs.plugins.base.BaseApplication;
import cs.plugins.log.Logger;

/**
 * 获取定位并记录
 */
public class TrackService extends Service implements AMapLocationListener {
    private static final String TAG = "TrackService";
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    public static final long UPDATE_MIN_TIME = 10 * 1000;

    public TrackService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate()");

        // TODO: 绑定定位事件
        locationClient = new AMapLocationClient(BaseApplication.getContext().getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置是否需要显示地址信息
        locationOption.setNeedAddress(true);
        locationOption.setGpsFirst(false);
        locationOption.setInterval(UPDATE_MIN_TIME);
        // 单词定位
        locationOption.setOnceLocation(false);
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationClient.setLocationOption(locationOption);

        // 设置定位监听
        locationClient.setLocationListener(this);
        // 启动定位
        locationClient.startLocation();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * 定位位置改变事件
     * @param aMapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (null != aMapLocation) {
            Logger.d(TAG, aMapLocation.getLongitude() + "-" + aMapLocation.getLatitude());
            // TODO: 记录定位到文件
        }
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();

        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

}
