package cs.plugins.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import butterknife.ButterKnife;

/**
 * Base Activity
 */
public abstract class BaseActivity extends Activity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        // Add ButterKnife Library
        ButterKnife.bind(this);




        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            // Remove shadow below actionbar
//            getActionBar().setElevation(0);
        }else{

        }

    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ApplicationUtil.removeActivity(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
