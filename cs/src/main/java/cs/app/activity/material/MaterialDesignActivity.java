package cs.app.activity.material;

import android.os.Bundle;
import android.view.Window;

import cs.app.R;
import cs.plugins.base.BaseActivity;

public class MaterialDesignActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_material_design);
    }



}
