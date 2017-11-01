package cs.app.widget.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cs.app.activity.fragment.ContentFragment;

public class CollectionPagerAdapter extends FragmentStatePagerAdapter {
    public CollectionPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("text", String.valueOf(i));
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Tab页数量
     */
    @Override
    public int getCount() {
        return TabsActivity.tabSize;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}
