package cs.app.widget.tab;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;

import butterknife.Bind;
import butterknife.OnPageChange;
import cs.app.R;
import cs.plugins.base.BaseCompatActivity;

/**
 * Tabs
 * TODO
 */
public class TabsActivity extends BaseCompatActivity {
    private static final String TAG = "TabsActivity";
    public static int tabSize = 3;

    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    CollectionPagerAdapter mPagerAdapter;
    @Bind(R.id.pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        // ViewPager and its adapters use support library fragments, so use getSupportFragmentManager.
        mPagerAdapter = new CollectionPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        // 指定在action bar中显示tab.
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // 添加3个tab, 并指定tab的文字和TabListener
        final ActionBar actionBar = getSupportActionBar();
        for (int i = 0; i < tabSize; i++) {
            ActionBar.Tab tabItem = actionBar.newTab();
            tabItem.setText("Tab " + (i + 1));
            tabItem.setTabListener(tabListener);
            actionBar.addTab(tabItem);
        }
    }

    // 创建一个tab listener，在用户切换tab时调用.
    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            Log.d(TAG, "onTabSelected: " + tab.getPosition());
            // 在ViewPager中切换页面
            // 当tab被选中时, 切换到ViewPager中相应的页面.
            mViewPager.setCurrentItem(tab.getPosition());
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            // 隐藏指定的tab
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            // 可以忽略这个事件
        }
    };

    /**
     * 切换页面时, 设置当前被选择Tab页
     */
    @OnPageChange(R.id.pager) void onPageChange(int position){
        // 当划屏切换页面时，选择相应的tab.
        getSupportActionBar().setSelectedNavigationItem(position);
    }

}