package cs.plugins.util;

import android.os.Build;

/**
 * Created by shiwx on 2016/1/27.
 */
public class VersionUtil {

    public int getSdkVersion(){
        // Build.VERSION_CODES
        return Build.VERSION.SDK_INT;
    }
}
