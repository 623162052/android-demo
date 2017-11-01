package cs.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnItemClick;
import cs.app.activity.adapter.CategoryEntity;
import cs.app.activity.adapter.CategoryListAdapter;
import cs.app.activity.adapter.CustomListAdapter;
import cs.app.activity.adapter.ItemConfig;
import cs.app.activity.fragment.ContentFragment;
import cs.app.activity.fragment.FragmentActivity;
import cs.app.activity.material.CardViewActivity;
import cs.app.activity.material.MaterialDesignActivity;
import cs.app.activity.material.MaterialListsActivity;
import cs.app.activity.webview.WebViewActivity;
import cs.app.broadcast.basic.BroadcastActivity;
import cs.app.broadcast.register.RegisterReceiverActivity;
import cs.app.handler.HandlerActivity1;
import cs.app.handler.HandlerActivity2;
import cs.app.handler.HandlerActivity3;
import cs.app.handler.HandlerLooperActivity;
import cs.app.service.ServiceActivity;
import cs.app.task.AsyncTaskActivity;
import cs.app.widget.ActionbarActivity;
import cs.app.widget.DrawerLayoutActivity;
import cs.app.widget.IconActivity;
import cs.app.widget.WidgetActivity;
import cs.app.widget.bottomtab.BottomTabActivity;
import cs.app.widget.charts.LineChartsActivity;
import cs.app.widget.gridview.GridViewActivity;
import cs.app.widget.notification.NotificationActivity;
import cs.app.widget.search.SearchActivity;
import cs.app.widget.search.SearchResultsActivity;
import cs.app.widget.swiperefresh.SwipeRefreshActivity;
import cs.app.widget.tab.TabsActivity;
import cs.app.widget.tablayout.TabLayoutActivity;
import cs.app.widget.update.UpdateActivity;
import cs.plugins.base.BaseActivity;
import cs.plugins.http.volley.HttpVolleyActivity;
import cs.plugins.http.volley.ImageRequestActivity;
import cs.plugins.http.volley.VolleyJsonActivity;
import cs.plugins.http.volley.customer.CustomRequestActivity;
import cs.plugins.log.Logger;
import cs.plugins.map.amap.demo.LocationAccuracyActivity;
import cs.plugins.map.amap.demo.TrackActivity;
import cs.plugins.map.amap.location.StartActivity;
import cs.plugins.map.amap.map2d.AMapMainActivity;
import cs.plugins.storage.realm.json.RealmActivity;
import cs.plugins.storage.sqlite.SQLiteActivity;

/**
 * HomePage
 */
public class MainActivity extends BaseActivity {
    private static String TAG = "MainActivity";

    private static Map<String, List<ItemConfig>> mapCategories;
    private static String currentCategory;
    List<CategoryEntity> categoryList;

    @Bind(R.id.category_list_view)
    ListView categoryListView;

    @Bind(R.id.item_list_view)
    ListView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapCategories = new HashMap<>();
        categoryList = new ArrayList<>();
        categoryList.add(new CategoryEntity("前端"));
        categoryList.add(new CategoryEntity("控件"));
        categoryList.add(new CategoryEntity("网页"));
        categoryList.add(new CategoryEntity("地图"));
        categoryList.add(new CategoryEntity("设计"));
        categoryList.add(new CategoryEntity("网络请求"));

        categoryList.add(new CategoryEntity("存储"));
        categoryList.add(new CategoryEntity("任务"));
        categoryList.add(new CategoryEntity("服务"));
        categoryList.add(new CategoryEntity("广播"));
        categoryList.add(new CategoryEntity("处理"));

        /**
         * Frontend
         */
        // TODO
        List<ItemConfig> frontendList = new ArrayList<>();
        frontendList.add(new ItemConfig(R.string.title_activity_drawer_layout, DrawerLayoutActivity.class.getName()));
        frontendList.add(new ItemConfig("ContentFragment", ContentFragment.class.getName()));
        mapCategories.put("前端", frontendList);

        // Widget
        List<ItemConfig> widgetList = new ArrayList<>();
        widgetList.add(new ItemConfig(R.string.title_activity_widget, WidgetActivity.class.getName()));
        widgetList.add(new ItemConfig(R.string.title_activity_icon, IconActivity.class.getName()));
        widgetList.add(new ItemConfig(R.string.title_activity_update, UpdateActivity.class.getName()));
        widgetList.add(new ItemConfig(R.string.title_activity_action_bar, ActionbarActivity.class.getName()));
        widgetList.add(new ItemConfig(R.string.title_activity_tabs, TabsActivity.class.getName()));
        widgetList.add(new ItemConfig(R.string.title_activity_tab_layout, TabLayoutActivity.class.getName()));
        widgetList.add(new ItemConfig(R.string.title_activity_notification, NotificationActivity.class.getName()));
        widgetList.add(new ItemConfig(R.string.title_activity_swipe_refresh, SwipeRefreshActivity.class.getName()));
        widgetList.add(new ItemConfig(R.string.title_activity_search, SearchActivity.class.getName()));
        widgetList.add(new ItemConfig(R.string.title_activity_search_results, SearchResultsActivity.class.getName()));
        widgetList.add(new ItemConfig(R.string.title_activity_grid_view, GridViewActivity.class.getName()));
        widgetList.add(new ItemConfig(R.string.title_activity_bottom_tab, BottomTabActivity.class.getName()));

        mapCategories.put("控件", widgetList);

        // WebView
        List<ItemConfig> webViewList = new ArrayList<>();
        webViewList.add(new ItemConfig(R.string.title_activity_web_view, WebViewActivity.class.getName()));
        webViewList.add(new ItemConfig(R.string.title_activity_line_charts, LineChartsActivity.class.getName()));
        mapCategories.put("网页", webViewList);

        // MapAMap
        List<ItemConfig> aMapList = new ArrayList<>();
        aMapList.add(new ItemConfig(R.string.title_activity_location_amap, StartActivity.class.getName()));
        aMapList.add(new ItemConfig(R.string.title_activity_map2d, AMapMainActivity.class.getName()));
        aMapList.add(new ItemConfig(R.string.title_activity_track, TrackActivity.class.getName()));
        aMapList.add(new ItemConfig(R.string.title_activity_location_accuracy, LocationAccuracyActivity.class.getName()));

        mapCategories.put("地图", aMapList);

        // Material Design
        List<ItemConfig> materialDesignList = new ArrayList<>();
        materialDesignList.add(new ItemConfig(R.string.title_activity_material_design, MaterialDesignActivity.class.getName()));
        materialDesignList.add(new ItemConfig(R.string.title_activity_material_lists, MaterialListsActivity.class.getName()));
        materialDesignList.add(new ItemConfig(R.string.title_activity_card_view, CardViewActivity.class.getName()));
        mapCategories.put("设计", materialDesignList);

        /**
         * Background
         */
        // HTTP
        List<ItemConfig> httpList = new ArrayList<>();
        httpList.add(new ItemConfig(R.string.title_activity_http_volley, HttpVolleyActivity.class.getName()));
        httpList.add(new ItemConfig(R.string.title_activity_image_request, ImageRequestActivity.class.getName()));
        httpList.add(new ItemConfig(R.string.title_activity_volley_json, VolleyJsonActivity.class.getName()));
        httpList.add(new ItemConfig(R.string.title_activity_custom_request, CustomRequestActivity.class.getName()));
        mapCategories.put("网络请求", httpList);

        // Storage
        List<ItemConfig> storageList = new ArrayList<>();
        storageList.add(new ItemConfig(R.string.title_activity_sqlite, SQLiteActivity.class.getName()));
        storageList.add(new ItemConfig(R.string.title_activity_realm_json, RealmActivity.class.getName()));
        mapCategories.put("存储", storageList);

        // Task
        List<ItemConfig> taskList = new ArrayList<>();
        taskList.add(new ItemConfig(R.string.title_activity_async_task, AsyncTaskActivity.class.getName()));
        mapCategories.put("任务", taskList);

        // Service
        List<ItemConfig> serviceList = new ArrayList<>();
        serviceList.add(new ItemConfig(R.string.title_activity_service, ServiceActivity.class.getName()));
        mapCategories.put("服务", serviceList);

        // Broadcast
        List<ItemConfig> broadcastList = new ArrayList<>();
        broadcastList.add(new ItemConfig(R.string.title_activity_broadcast, BroadcastActivity.class.getName()));
        broadcastList.add(new ItemConfig(R.string.title_activity_register_receiver, RegisterReceiverActivity.class.getName()));
        mapCategories.put("广播", broadcastList);

        // Handler TODO: 验证
        List<ItemConfig> handlerList = new ArrayList<>();
        handlerList.add(new ItemConfig(R.string.title_activity_handler, HandlerActivity1.class.getName()));
        handlerList.add(new ItemConfig(R.string.title_activity_handler_activity2, HandlerActivity2.class.getName()));
        handlerList.add(new ItemConfig(R.string.title_activity_handler_activity3, HandlerActivity3.class.getName()));
        handlerList.add(new ItemConfig(R.string.title_activity_handler_looper, HandlerLooperActivity.class.getName()));
        mapCategories.put("处理", handlerList);

        CategoryListAdapter categoryListAdapter = new CategoryListAdapter(MainActivity.this, categoryList);
        categoryListView.setAdapter(categoryListAdapter);
    }

    /**
     * 类别点击事件
     */
    @OnItemClick(R.id.category_list_view)
    void onCategoryClick(int position) {
        Logger.d(TAG, "onCategoryClick: " + position);
        currentCategory = categoryList.get(position).getCategoryName();
        CustomListAdapter adapter = new CustomListAdapter(MainActivity.this, mapCategories.get(currentCategory));
        itemList.setAdapter(adapter);
    }

    /**
     * 列表点击事件
     */
    @OnItemClick(R.id.item_list_view) void onListItemClick(int position) {
        try {
            List<ItemConfig> items = mapCategories.get(currentCategory);
            ItemConfig item = items.get(position);
            Class clazz = Class.forName(item.getTargetClass());

            boolean isActivity = Activity.class.isAssignableFrom(clazz);
            Logger.d(TAG, "clazz: " + clazz.getName());
            Logger.d(TAG, "isActivity: " + isActivity);

            if(isActivity){
                // Show Activity
                startActivity(new Intent(MainActivity.this, clazz));
            }else{
                // Show Fragment
                Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                intent.putExtra("TARGET", clazz.getName());
                startActivity(intent);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
