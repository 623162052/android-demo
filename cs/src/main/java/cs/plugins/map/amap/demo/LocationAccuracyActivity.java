package cs.plugins.map.amap.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;

import butterknife.Bind;
import butterknife.OnClick;
import cs.app.R;
import cs.plugins.base.BaseActivity;
import cs.plugins.log.Logger;
import cs.plugins.map.amap.location.Utils;

/**
 * 高精度定位
 */
public class LocationAccuracyActivity extends BaseActivity implements AMapLocationListener {
    private static String TAG = "LocationAccuracyActivity";
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private static int locationButton = 0;

    @Bind(R.id.tv_result)
    TextView tvResult;
    @Bind(R.id.tv_result2)
    TextView tvResult2;
    @Bind(R.id.bt_location_result)
    TextView locationResult;

    @Bind(R.id.bt_location)
    Button btLocation;
    @Bind(R.id.bt_location2)
    Button btLocation2;
    @Bind(R.id.bt_location_calculate)
    Button calculate;
    @Bind(R.id.cb_gpsFirst)
    CheckBox cb_gpsFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_accuracy);
        setTitle(R.string.title_hight_accuracy);

        btLocation.setEnabled(true);
        btLocation2.setEnabled(false);
        calculate.setEnabled(false);
    }

    /**
     * 第一个按钮定位按钮事件
     */
    @OnClick(R.id.bt_location)
    void location1Click(View v) {
        if (v.getId() == R.id.bt_location) {
            if (btLocation.getText().equals(getResources().getString(R.string.startLocation))) {
                locationButton = 1;
                btLocation.setEnabled(false);
                initOption();
                // 设置定位参数
                locationClient.setLocationOption(locationOption);
                // 启动定位
                locationClient.startLocation();
                mHandler.sendEmptyMessage(Utils.MSG_LOCATION_START);
            }
        }
    }

    /**
     * 第二个按钮定位按钮事件
     */
    @OnClick(R.id.bt_location2)
    void location2Click(View v) {
        if (v.getId() == R.id.bt_location2) {
            if (btLocation.getText().equals(getResources().getString(R.string.startLocation))) {
                locationButton = 2;
                btLocation2.setEnabled(false);
                initOption();

                // 设置定位参数
                locationClient.setLocationOption(locationOption);
                // 启动定位
                locationClient.startLocation();
                mHandler.sendEmptyMessage(Utils.MSG_LOCATION_START);
            }
        }
    }

    /**
     * 计算事件
     */
    @OnClick(R.id.bt_location_calculate)
    void calculate() {
        calculate.setEnabled(false);
        String tvResultText = tvResult.getText().toString();
        double startLatitude = Double.parseDouble(tvResultText.split("-")[0]);
        double startLongitude = Double.parseDouble(tvResultText.split("-")[1]);

        String tvResult2Text = tvResult2.getText().toString();
        double endLatitude = Double.parseDouble(tvResult2Text.split("-")[0]);
        double endLongitude = Double.parseDouble(tvResult2Text.split("-")[1]);

        LatLng startLatlng = new LatLng(startLatitude, startLongitude);
        LatLng endLatlng = new LatLng(endLatitude, endLongitude);
        float result = AMapUtils.calculateLineDistance(startLatlng, endLatlng);
        locationResult.setText(result + " 米");

    }

    // 根据控件的选择，重新设置定位参数
    private void initOption() {
        locationClient = new AMapLocationClient(this.getApplicationContext());

        locationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置定位监听
        locationClient.setLocationListener(this);
        // 设置是否需要显示地址信息
        locationOption.setNeedAddress(true);

        //只有持续定位设置定位间隔才有效，单次定位无效
        locationOption.setOnceLocation(true);
        locationOption.setGpsFirst(cb_gpsFirst.isChecked());
        // 设置发送定位请求的时间间隔,最小值为2000，如果小于2000，按照2000算
//        locationOption.setInterval(2000L);
    }

    Handler mHandler = new Handler() {
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                //开始定位
                case Utils.MSG_LOCATION_START:
                    if (locationButton == 1) {
                        tvResult.setText("正在定位...");
                    } else if (locationButton == 2) {
                        tvResult2.setText("正在定位...");
                    }
                    break;
                // 定位完成
                case Utils.MSG_LOCATION_FINISH:
                    AMapLocation loc = (AMapLocation) msg.obj;
                    double latitude = loc.getLatitude();    //获取纬度
                    double longitude = loc.getLongitude();  //获取经度
                    String result = Utils.getLocationStr(loc);
                    Logger.d(TAG, "result: " + result);

                    if (locationButton == 1) {
                        btLocation2.setEnabled(true);
                        locationButton = 0;
                        tvResult.setText(latitude + "-" + longitude);
                    } else if (locationButton == 2) {
                        locationButton = 0;
                        tvResult2.setText(latitude + "-" + longitude);
                        calculate.setEnabled(true);
                    }

                    if (null != locationClient) {
                        /**
                         * 如果AMapLocationClient是在当前Activity实例化的，
                         * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
                         */
                        locationClient.onDestroy();
                        locationClient = null;
                        locationOption = null;
                    }

                    break;
                //停止定位
                case Utils.MSG_LOCATION_STOP:
                    if (locationButton == 1) {
                        tvResult.setText("定位停止");
                    } else if (locationButton == 2) {
                        tvResult2.setText("定位停止");
                    }
                    break;
                default:
                    break;
            }
        }
    };

    // 定位监听
    @Override
    public void onLocationChanged(AMapLocation loc) {
        if (null != loc) {
            Message msg = mHandler.obtainMessage();
            msg.obj = loc;
            msg.what = Utils.MSG_LOCATION_FINISH;
            mHandler.sendMessage(msg);
        }
    }

    /**
     * Destroy
     */
    @Override
    protected void onDestroy() {
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
