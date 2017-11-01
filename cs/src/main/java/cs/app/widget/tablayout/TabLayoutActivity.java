package cs.app.widget.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cs.app.R;
import cs.app.activity.fragment.ContentFragment;
import cs.plugins.base.BaseCompatActivity;
import cs.plugins.log.Logger;

public class TabLayoutActivity extends BaseCompatActivity {
    private static final String TAG = "TabLayoutActivity";


    @Bind(R.id.tab_FindFragment_title)
    TabLayout mTabLayout;
    @Bind(R.id.vp_FindFragment_pager)
    ViewPager mViewPager;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initControls();
    }

    /**
     * 初始化各控件
     */
    private void initControls() {
        //设置TabLayout的模式: TabLayout.MODE_FIXED / TabLayout.MODE_SCROLLABLE
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        for(int i=0; i<4; i++){
            ContentFragment contentFragment = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("text", "栏目" + i);
            contentFragment.setArguments(bundle);
            fragmentList.add(contentFragment);
            titleList.add("栏目" + i);
            mTabLayout.addTab(mTabLayout.newTab().setText("栏目" + i));
        }

        CustomPagerAdapter fragmentAdapter = new CustomPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        mViewPager.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(fragmentAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                Logger.d(TAG, "android.R.id.home");
                return true;
            default: break;
        }


        return super.onOptionsItemSelected(item);
    }
}
