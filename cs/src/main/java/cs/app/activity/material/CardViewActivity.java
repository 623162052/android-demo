package cs.app.activity.material;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import butterknife.Bind;
import cs.app.R;
import cs.plugins.base.BaseActivity;

/**
 * add 'com.android.support:cardview-v7:21.0.+'
 */
public class CardViewActivity extends BaseActivity {

    @Bind(R.id.card_view_material)
    CardView mCardView;

    @Bind(R.id.card_view_info_text)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        mTextView.setText("CardView");
    }

}
