package cs.plugins.storage;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by shiwx on 2016/1/5.
 */
public class InternalStore {
    private static final String TAG = "InternalStore";
    private Context mContext;
    private static InternalStore sInstance;

    private InternalStore(Context context) {
        mContext = context;
    }

    public static synchronized InternalStore getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new InternalStore(context);
        }
        return sInstance;
    }


    /**
     * 写
     */
    public void write(String fileName, String content) {
        FileOutputStream fos = null;
        try {
            //MODE_PRIVATE, MODE_APPEND, MODE_WORLD_READABLE, and MODE_WORLD_WRITEABLE
            fos = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 读
     */
    public void read(String fileName) {
        FileInputStream fis = null;
        BufferedWriter bw = null;
        try {
            fis = mContext.openFileInput(fileName);

            int buffer = 0;
            if ((buffer = fis.read()) != -1) {
                Log.d(TAG, "buffer: " + buffer);        // TODO
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
