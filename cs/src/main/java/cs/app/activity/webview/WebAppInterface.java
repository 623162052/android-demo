package cs.app.activity.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by shiwx on 2015/1/29.
 */
public class WebAppInterface {
    private Context mContext;

    /**
     * Instantiate the interface and set the context
     */
    public WebAppInterface(Context context){
        mContext = context;
    }

    /**
     * Show a toast from the web page
     */
    @JavascriptInterface
    public void showToast(String toastContent) {
        Toast.makeText(mContext, toastContent, Toast.LENGTH_LONG).show();
    }


}
