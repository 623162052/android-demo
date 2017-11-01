package cs.app.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import cs.app.R;
import cs.plugins.base.BaseCompatActivity;

/**
 * support.v4.Fragment
 */
public class FragmentActivity extends BaseCompatActivity {
    private static final String TAG = "FragmentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // open target fragment
        Intent intent = getIntent();
        String targetFragment = intent.getStringExtra("TARGET");

        Fragment target = Fragment.instantiate(FragmentActivity.this, targetFragment);
        addFragment(target);
    }

    /**
     * add
     */
    public void addFragment(Fragment target){
        // 动态插入Fragment
        // ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", "Hello I am Fragment");
        target.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, target, TAG);
        fragmentTransaction.commit();
    }

    /**
     * replace
     */
    public void replaceFragment(){
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", "Hello, I am another Fragment");
        contentFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, contentFragment, TAG);
        fragmentTransaction.commit();
    }

}
