package cs.plugins.http.volley;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import cs.plugins.http.HttpConstant;
import cs.app.R;
import cs.plugins.base.BaseActivity;
import cs.plugins.http.volley.util.RequestQueueUtil;


/**
 * Volley:
 *      StringRequest [OK]
 *      ImageRequest  [OK]
 *          ImageRequest [OK]
 *          ImageLoader:for large numbers of ImageRequests   [OK]
 *          NetworkImageView [OK]
 *      JsonObjectRequest  [OK]
 *      JsonArrayRequest
 *
 * 问题:
 *  超时、重试请求
 *  下载图片
 */
public class HttpVolleyActivity extends BaseActivity {
    private static final String TAG = "HttpVolleyActivity";
    StringRequest stringRequest; // Assume this exists.
    RequestQueue mRequestQueue;  // Assume this exists.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_volley);

        recommendReq(HttpConstant.BAIDU_URL);
    }

    /**
     * Volley推荐用法
     */
    public void recommendReq(String reqUrl){
        Log.d(TAG, "begin recommendReq()");

        final TextView mTextView = (TextView) findViewById(R.id.activity_volley_textview);

        // 获取请求队列
        // [此处是ApplicationContext]
        mRequestQueue = RequestQueueUtil.getInstance(this.getApplicationContext()).getRequestQueue();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, reqUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                mTextView.setText("Response is: " + response.substring(0, 500));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "volleyError: " + JSON.toJSONString(volleyError));

                mTextView.setText("That didn't work!");
            }
        });

        // Add a request (in this example, called stringRequest) to your RequestQueue.
        // [此处是ActivityContext]
        RequestQueueUtil.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();

        /**
         * 取消所有当前Activity的Http请求
         */
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }

    }
}
