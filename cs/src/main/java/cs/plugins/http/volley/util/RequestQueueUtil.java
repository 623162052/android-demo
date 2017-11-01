package cs.plugins.http.volley.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import cs.plugins.base.BaseApplication;

public class RequestQueueUtil {

    private static RequestQueueUtil mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;
    private ImageLoader mImageLoader;

    /**
     * 构造方法
     */
    private RequestQueueUtil(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new LruBitmapCache(LruBitmapCache.getCacheSize(BaseApplication.getContext())));

    }

    /**
     * 获取请求队列
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * 添加请求到队列
     */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    /**
     * 获取单实例
     */
    public static synchronized RequestQueueUtil getInstance(Context context) {
        return mInstance == null ? mInstance = new RequestQueueUtil(context) : mInstance;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
