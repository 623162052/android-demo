package cs.plugins.http.volley;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import butterknife.Bind;
import cs.plugins.http.HttpConstant;
import cs.app.R;
import cs.plugins.base.BaseActivity;
import cs.plugins.http.volley.util.RequestQueueUtil;

/**
 * Volley Json
 */
public class VolleyJsonActivity extends BaseActivity {
    private final static String TAG = "VolleyJsonActivity";

    @Bind(R.id.volley_json_textview) TextView mTxtDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_json);

        jsonReq(HttpConstant.REQUEST_WEATHER);
    }

    /**
     * JSON request
     */
    public void jsonReq(String reqUrl) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, reqUrl, null,
        new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mTxtDisplay.setText("Response: " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTxtDisplay.setText("VolleyError response: " + JSON.toJSONString(error));
            }
        });

        // Access the RequestQueue through your singleton class.
        RequestQueueUtil.getInstance(this).addToRequestQueue(jsObjRequest);
    }

}
