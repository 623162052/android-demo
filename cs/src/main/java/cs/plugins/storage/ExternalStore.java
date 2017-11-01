package cs.plugins.storage;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by shiwx on 2016/1/12.
 */
public class ExternalStore {
    private static final String TAG = "InternalStore";
    private Context mContext;
    private static ExternalStore sInstance;

    private ExternalStore(Context context) {
        mContext = context;
    }

    public static synchronized ExternalStore getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ExternalStore(context);
        }
        return sInstance;
    }

    /**
     * 检测external storage是否可读写
     * @return
     */
    public boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ? true : false;
    }

    /**
     * 检测external storage是否可读
     * @return
     */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }


    /**
     * 创建目录
     */
    public void createDir(String dir){
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), dir);
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }else{
            Log.e(TAG, "Directory created");
        }
    }

}
