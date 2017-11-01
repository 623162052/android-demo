package cs.app.widget.swiperefresh;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import cs.app.R;
import cs.plugins.base.BaseCompatActivity;
import cs.plugins.log.Logger;

/**
 * 下拉刷新
 */
public class SwipeRefreshActivity extends BaseCompatActivity {
    private static final String TAG = "SwipeRefreshActivity";

    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.refresh_listview)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Logger.d(TAG, "onRefresh()");

                String[] mPlanetTitles = getResources().getStringArray(R.array.actionbar_array);
                listView.setAdapter(new ArrayAdapter<String>(SwipeRefreshActivity.this,
                        android.R.layout.simple_expandable_list_item_1, mPlanetTitles));

                refreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_refresh, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // Check if user triggered a refresh:
            case R.id.menu_refresh:
                Log.i(TAG, "Refresh menu item selected");

                // Signal SwipeRefreshLayout to start the progress indicator
                refreshLayout.setRefreshing(true);

                // Start the refresh background task.
                // This method calls setRefreshing(false) when it's finished.
//                myUpdateOperation();
                String[] mPlanetTitles = getResources().getStringArray(R.array.actionbar_array);
                listView.setAdapter(new ArrayAdapter<String>(SwipeRefreshActivity.this,
                        android.R.layout.simple_expandable_list_item_1, mPlanetTitles));

                refreshLayout.setRefreshing(false);

                return true;
        }

        // User didn't trigger a refresh, let the superclass handle this action
        return super.onOptionsItemSelected(item);
    }

}
