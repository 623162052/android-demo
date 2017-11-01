package cs.plugins.base;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by shiwx on 2016/1/17.
 */
public abstract class BaseCompatActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        // Add ButterKnife Library
        ButterKnife.bind(this);

        // Remove shadow below actionbar

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setElevation(0);
        }

    }

}
