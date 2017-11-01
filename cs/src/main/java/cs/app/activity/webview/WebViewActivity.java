package cs.app.activity.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.Bind;
import cs.app.R;
import cs.plugins.base.BaseActivity;

public class WebViewActivity extends BaseActivity {

    private static final String TAG = "WebViewActivity";

    @Bind(R.id.webview_1)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // 设置支持Javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 远程资源
        webView.loadUrl("http://www.baidu.com");

        // 启用JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //setUserAgentString()

        /**
         * WebView中打开的Link都是通过WebView展示
         * 默认WebViewClient，此处通过自定义WebViewClient添加更多控制
         */
        webView.setWebViewClient(new MyWebViewClient());

        // Binding JavaScript code to Android code
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "keyCode: " + keyCode);

        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_FORWARD && webView.canGoForward()){
            webView.goForward();
            return true;
        }

        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }


    /**
     * WebViewClient:   帮助WebView处理各种通知、请求事件
     * WebChromeClient: 主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
     */
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Log.d(TAG, "url: " + url);

            // 内部网页，不通过浏览器应用打开
            if (Uri.parse(url).getHost().indexOf(".baidu.com") != -1) {
                // This is my web site, so do not override; let my WebView load the page
                return false;
            }

            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
}
