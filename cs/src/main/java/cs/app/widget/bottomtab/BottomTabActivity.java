package cs.app.widget.bottomtab;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import cs.app.R;
import cs.plugins.base.BaseActivity;

/**
 * Bottom Tab
 */
public class BottomTabActivity extends BaseActivity {

    @Bind(R.id.rrg_tab)
    RadioGroup radioGroup;
    @Bind(R.id.radio_homePage)
    RadioButton radioHomePage;
    @Bind(R.id.radio_assistant)
    RadioButton radioAssistant;
    @Bind(R.id.radio_tribe)
    RadioButton radioTribe;
    @Bind(R.id.radio_my)
    RadioButton radioMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);
    }


    @OnCheckedChanged(R.id.radio_homePage)
    void onCheckedChanged(boolean checked) {
        //

        // 设置默认选中
//        radioHomePage.setChecked(true);
    }

}
