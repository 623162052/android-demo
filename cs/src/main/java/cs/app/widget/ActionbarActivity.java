package cs.app.widget;

import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import butterknife.Bind;
import cs.app.R;
import cs.plugins.base.BaseCompatActivity;
import cs.plugins.log.Logger;


public class ActionbarActivity extends BaseCompatActivity {
    private static String TAG = "ActionbarActivity";
    private ShareActionProvider mShareActionProvider;

    @Bind(R.id.toolbar_actionbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0L);
    }

    /**
     * 创建ActionBar 菜单项
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }


    /**
     * ActionBar 菜单项背选中
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 处理动作按钮的点击事件
        switch (item.getItemId()) {
            case R.id.action_search:
                //openSearch();
                Logger.d(TAG, "action_search");
                return true;
            case R.id.action_settings:
                //openSettings();
                Logger.d(TAG, "action_settings");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
