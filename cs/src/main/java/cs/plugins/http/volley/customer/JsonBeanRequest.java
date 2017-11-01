package cs.plugins.http.volley.customer;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

/**
 * 自定义Request
 * Created by shiwx on 2015/1/23.
 */
public class JsonBeanRequest<T> extends Request<T> {

    private static final String TAG = "JsonBeanRequest";
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;

    public JsonBeanRequest(String url, Class<T> clazz, Map<String, String> headers,
                           Response.Listener<T> listener, Response.ErrorListener errListener) {
        super(Method.GET, url, errListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    /**
     * 对解析后的结果进行封装
     */
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            Log.d(TAG, "parseNetworkResponse json: " + json);

            return Response.success(
                    JSON.parseObject(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }

    }

    /**
     * Volley会把parseNetworkResponse()方法返回的数据带到主线程的回调中
     */
    @Override
    protected void deliverResponse(T response) {
        Log.d(TAG, "deliverResponse response: " + JSON.toJSONString(response));
        listener.onResponse(response);
    }

}
