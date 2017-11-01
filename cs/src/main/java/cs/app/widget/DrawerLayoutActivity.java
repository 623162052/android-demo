package cs.app.widget;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.OnItemClick;
import cs.app.R;
import cs.app.activity.fragment.ContentFragment;
import cs.plugins.base.BaseCompatActivity;
import cs.plugins.log.Logger;

public class DrawerLayoutActivity extends BaseCompatActivity {

    private static final String TAG = "DrawerLayoutActivity";

    private String[] mPlanetTitles;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.left_drawer)
    ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        mPlanetTitles = getResources().getStringArray(R.array.actionbar_array);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, mPlanetTitles));

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener(){

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                Logger.d(TAG, "onDrawerSlide");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Logger.d(TAG, "onDrawerOpened");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Logger.d(TAG, "onDrawerClosed");
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Logger.d(TAG, "onDrawerStateChanged");
            }
        });

    }


    @OnItemClick(R.id.left_drawer) void onItemClick(int position){
        String data = mPlanetTitles[position];

        Fragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("text", data);
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }




}
