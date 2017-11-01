package cs.app.widget.update;

import android.os.Bundle;

import butterknife.OnClick;
import cs.app.R;
import cs.plugins.base.BaseActivity;

/**
 * 自动更新
 */
public class UpdateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }

    /**
     * 更新按钮被点击
     */
    @OnClick(R.id.btn_update_activity) void clickUpdate(){
        UpdateManager manager = new UpdateManager(this);
        manager.checkUpdate();
    }

}
