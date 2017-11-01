package cs.plugins.http.volley.customer;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import cs.plugins.http.HttpConstant;
import cs.app.R;
import cs.plugins.base.BaseActivity;
import cs.plugins.http.volley.util.RequestQueueUtil;

/**
 *  velloy 自定义请求
 */
public class CustomRequestActivity extends BaseActivity {

    private static final String TAG = "CustomRequestActivity";
    JsonBeanRequest jsonBeanRequest; // Assume this exists.
    RequestQueue mRequestQueue;  // Assume this exists.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_request);

        // [此处不加上http会报错]
        customReq(HttpConstant.REQUEST_WEATHER);
    }

    /**
     * Volley推荐用法
     */
    public void customReq(String weatherUrl) {
        Log.d(TAG, "BEGIN customReq()");

        // 获取请求队列
        // [此处是ApplicationContext]
        mRequestQueue = RequestQueueUtil.getInstance(this.getApplicationContext()).getRequestQueue();

        // Request a string response from the provided URL.
        Map<String, String> parameter = new HashMap<>();
        jsonBeanRequest = new JsonBeanRequest(weatherUrl,WeatherBean.class, parameter, new Response.Listener<WeatherBean>() {
            @Override
            public void onResponse(WeatherBean response) {
                Log.d(TAG, "onResponse WeatherBean: " + JSON.toJSONString(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "onErrorResponse: " + JSON.toJSONString(volleyError));
            }
        });

        // Add a request (in this example, called stringRequest) to your RequestQueue. [此处是ActivityContext]
        RequestQueueUtil.getInstance(this).addToRequestQueue(jsonBeanRequest);
    }

}
