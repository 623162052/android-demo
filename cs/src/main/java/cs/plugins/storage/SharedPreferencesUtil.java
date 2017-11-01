package cs.plugins.storage;

import android.content.Context;
import android.content.SharedPreferences;

import cs.plugins.log.Logger;

/**
 * Created by shiwx on 2015/12/30.
 */
public class SharedPreferencesUtil {
    private static final String TAG = "SharedPreferencesUtil";
    private Context mContext;
    private static SharedPreferencesUtil sInstance;

    private static final String LOCATION_LIST = "LOCATION_LIST";

    private SharedPreferencesUtil(Context context) {
        mContext = context;
    }

    public static synchronized SharedPreferencesUtil getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SharedPreferencesUtil(context);
        }
        return sInstance;
    }

    public void putString(String key, String value) {
        Logger.d(TAG, key + " - " + value);
        SharedPreferences settings = mContext.getSharedPreferences(LOCATION_LIST, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        Logger.d(TAG, key);
        SharedPreferences settings = mContext.getSharedPreferences(LOCATION_LIST, 0);
        return settings.getString(key, "");
    }

}
