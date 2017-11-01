package cs.plugins.map.amap.map2d;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.amap.api.maps2d.MapsInitializer;

import cs.app.R;
import cs.plugins.map.amap.map2d.basic.BaseMapFragmentActivity;
import cs.plugins.map.amap.map2d.basic.BasicMapActivity;
import cs.plugins.map.amap.map2d.basic.CameraActivity;
import cs.plugins.map.amap.map2d.basic.EventsActivity;
import cs.plugins.map.amap.map2d.basic.LayersActivity;
import cs.plugins.map.amap.map2d.basic.MapOptionActivity;
import cs.plugins.map.amap.map2d.basic.OsmMapActivity;
import cs.plugins.map.amap.map2d.basic.ScreenShotActivity;
import cs.plugins.map.amap.map2d.basic.UiSettingsActivity;
import cs.plugins.map.amap.map2d.busline.BuslineActivity;
import cs.plugins.map.amap.map2d.cloud.CloudActivity;
import cs.plugins.map.amap.map2d.district.DistrictActivity;
import cs.plugins.map.amap.map2d.district.DistrictWithBoundaryActivity;
import cs.plugins.map.amap.map2d.geocoder.GeocoderActivity;
import cs.plugins.map.amap.map2d.location.LocationSourceActivity;
import cs.plugins.map.amap.map2d.overlay.CircleActivity;
import cs.plugins.map.amap.map2d.overlay.GroundOverlayActivity;
import cs.plugins.map.amap.map2d.overlay.MarkerActivity;
import cs.plugins.map.amap.map2d.overlay.PolygonActivity;
import cs.plugins.map.amap.map2d.overlay.PolylineActivity;
import cs.plugins.map.amap.map2d.poisearch.PoiAroundSearchActivity;
import cs.plugins.map.amap.map2d.poisearch.PoiKeywordSearchActivity;
import cs.plugins.map.amap.map2d.route.RouteActivity;
import cs.plugins.map.amap.map2d.share.ShareActivity;
import cs.plugins.map.amap.map2d.view.FeatureView;
import cs.plugins.map.amap.map2d.weather.WeatherSearchActivity;

/**
 * AMapV1地图demo总汇
 */
public final class AMapMainActivity extends ListActivity {
	private static class DemoDetails {
		private final int titleId;
		private final int descriptionId;
		private final Class<? extends android.app.Activity> activityClass;

		public DemoDetails(int titleId, int descriptionId,
				Class<? extends android.app.Activity> activityClass) {
			super();
			this.titleId = titleId;
			this.descriptionId = descriptionId;
			this.activityClass = activityClass;
		}
	}

	private static class CustomArrayAdapter extends ArrayAdapter<DemoDetails> {
		public CustomArrayAdapter(Context context, DemoDetails[] demos) {
			super(context, R.layout.feature, R.id.title, demos);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			FeatureView featureView;
			if (convertView instanceof FeatureView) {
				featureView = (FeatureView) convertView;
			} else {
				featureView = new FeatureView(getContext());
			}
			DemoDetails demo = getItem(position);
			featureView.setTitleId(demo.titleId);
			featureView.setDescriptionId(demo.descriptionId);
			return featureView;
		}
	}

	/**
	 * 基本地图
	 * Events功能
	 * Layers图层功能
	 * AMapOptions实现地图：控制是否允许手势移动、缩放等
	 * Markers功能
	 * 地理编码功能
	 * Location小蓝点功能
	 * POI关键词搜索
	 * Busline公交查询
	 * Route路径规划：验证在小城市是否起作用
	 * 行政区划边界查询：验证是否支持镇级单位
	 */
	private static final DemoDetails[] demos = {
			new DemoDetails(R.string.basic_map, R.string.basic_description,
					BasicMapActivity.class),
			new DemoDetails(R.string.osm_map, R.string.osm_description,
					OsmMapActivity.class),
			new DemoDetails(R.string.base_fragment_map,
					R.string.base_fragment_description,
					BaseMapFragmentActivity.class),
			new DemoDetails(R.string.camera_demo, R.string.camera_description,
					CameraActivity.class),
			new DemoDetails(R.string.events_demo, R.string.events_description,
					EventsActivity.class),
			new DemoDetails(R.string.layers_demo, R.string.layers_description,
					LayersActivity.class),
			new DemoDetails(R.string.mapOption_demo,
					R.string.mapOption_description, MapOptionActivity.class),
			new DemoDetails(R.string.screenshot_demo,
					R.string.screenshot_description, ScreenShotActivity.class),
			new DemoDetails(R.string.uisettings_demo,
					R.string.uisettings_description, UiSettingsActivity.class),
			new DemoDetails(R.string.polyline_demo,
					R.string.polyline_description, PolylineActivity.class),
			new DemoDetails(R.string.polygon_demo,
					R.string.polygon_description, PolygonActivity.class),
			new DemoDetails(R.string.circle_demo, R.string.circle_description,
					CircleActivity.class),
			new DemoDetails(R.string.marker_demo, R.string.marker_description,
					MarkerActivity.class),
			new DemoDetails(R.string.groundoverlay_demo,
					R.string.groundoverlay_description,
					GroundOverlayActivity.class),
			new DemoDetails(R.string.geocoder_demo,
					R.string.geocoder_description, GeocoderActivity.class),
			new DemoDetails(R.string.locationsource_demo,
					R.string.locationsource_description,
					LocationSourceActivity.class),
			new DemoDetails(R.string.poikeywordsearch_demo,
					R.string.poikeywordsearch_description,
					PoiKeywordSearchActivity.class),
			new DemoDetails(R.string.poiaroundsearch_demo,
					R.string.poiaroundsearch_description,
					PoiAroundSearchActivity.class),
			new DemoDetails(R.string.cloud_demo, R.string.cloud_description,
					CloudActivity.class),	//TODO：BUG
			new DemoDetails(R.string.busline_demo,
					R.string.busline_description, BuslineActivity.class),
			new DemoDetails(R.string.route_demo, R.string.route_description,
					RouteActivity.class),
			new DemoDetails(R.string.district_demo,
					R.string.district_description, DistrictActivity.class),
			new DemoDetails(R.string.district_boundary_demo,
					R.string.district_boundary_description,
					DistrictWithBoundaryActivity.class),
			new DemoDetails(R.string.share_demo, R.string.share_description,
					ShareActivity.class),
			new DemoDetails(R.string.weather_demo, 
				    R.string.weather_description, WeatherSearchActivity.class)};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.amap_main_activity);
		setTitle("2D地图Demo" + MapsInitializer.getVersion());
		ListAdapter adapter = new CustomArrayAdapter(
				this.getApplicationContext(), demos);
		setListAdapter(adapter);

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		System.exit(0);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		DemoDetails demo = (DemoDetails) getListAdapter().getItem(position);
		startActivity(new Intent(this.getApplicationContext(),
				demo.activityClass));
	}
}
