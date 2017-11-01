package cs.app.widget.charts;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.Bind;
import cs.app.R;
import cs.plugins.base.BaseActivity;

/**
 * Line图表
 */
public class LineChartsActivity extends BaseActivity {

    @Bind(R.id.line_charts_web_view)
    WebView lineChartsWebView;

    private WebSettings webSettings = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_charts);

        // 启用Javascript
        webSettings = lineChartsWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        lineChartsWebView.loadUrl("file:///android_asset/html/line-basic.html");

    }

}
