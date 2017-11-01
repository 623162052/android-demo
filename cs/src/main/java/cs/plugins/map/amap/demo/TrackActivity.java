package cs.plugins.map.amap.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cs.app.R;
import cs.plugins.base.BaseApplication;
import cs.plugins.storage.SharedPreferencesUtil;

/**
 * 行走轨迹
 */
public class TrackActivity extends Activity {
    private static final String TAG = "TrackActivity";
    private MapView mapView;
    private AMap aMap;

    /**
     * 启动Service定位
     */
    @OnClick(R.id.btn_startLocation) void startLocation(){
        Intent intent = new Intent(TrackActivity.this, TrackService.class);
        intent.setPackage("cs.plugins.map.amap.demo");
        startService(intent);
    }

    /**
     * 清楚定位信息
     */
    @OnClick(R.id.btn_clearLocation) void clearLocation(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        // TODO
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        ButterKnife.bind(this);


        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写

        if (aMap == null) {
            aMap = mapView.getMap();
            LatLng marker1 = new LatLng(31.972114, 118.753349);
            //设置中心点和缩放比例
            aMap.moveCamera(CameraUpdateFactory.changeLatLng(marker1));
            aMap.moveCamera(CameraUpdateFactory.zoomTo(12));
        }

        // 从SP中加载数据
        loadLocation();

    }


    public void loadLocation() {
        SharedPreferencesUtil spUtil = SharedPreferencesUtil.getInstance(BaseApplication.getContext());
        String locations = spUtil.getString("location");
        if (locations != null && !locations.equals("")) {
            String[] locationArr = locations.split(":");
            for (String item : locationArr) {
                String longitude = item.split("-")[0];
                String latitude = item.split("-")[1];

//                List<LatLng> points = new ArrayList<LatLng>();
//                LatLng latLng = new LatLng(Long.valueOf(longitude), Long.valueOf(latitude));
//                points.add(latLng);
//
//                addPath(aMap, new ArrayList<Polyline>(), points, Color.RED, false);
            }
        }

        Random random = new Random(10);
        LatLng start = new LatLng(31.972114, 118.753349);
        List<LatLng> points = new ArrayList<LatLng>();
        int i = 0;
        while (i++ < 10) {
            double lat = random.nextDouble() * 0.1;
            double lng = random.nextDouble() * 0.1;
            LatLng latLng = new LatLng(start.latitude + lat, start.longitude + lng);
            points.add(latLng);
        }
        addPath(aMap, new ArrayList<Polyline>(), points, Color.RED, false);
    }


    public static void addPath(AMap map, List<Polyline> paths, List<LatLng> points, int color, boolean append) {
        if (points.size() == 0) {
            return;
        }
        if (append && paths.size() != 0) {
            Polyline lastPolyline = paths.get(paths.size() - 1);
            ArrayList<LatLng> pathPoints = new ArrayList<LatLng>();
            pathPoints.addAll(lastPolyline.getPoints());
            pathPoints.addAll(points);
            lastPolyline.setPoints(pathPoints);
        } else {
            PolylineOptions polylineOptions = new PolylineOptions().addAll(points).width(5).color(color);
            Polyline polyline = map.addPolyline(polylineOptions);
            paths.add(polyline);
        }
        points.clear();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


}
