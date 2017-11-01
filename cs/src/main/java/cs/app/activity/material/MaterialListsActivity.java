package cs.app.activity.material;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import cs.app.R;
import cs.plugins.base.BaseActivity;

/**
 * RecyclerView
 *
 * add 'com.android.support:recyclerview-v7:21.0.+'
 */
public class MaterialListsActivity extends BaseActivity {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Bind(R.id.material_recycler_view)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_lists);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        String[] myDataSet = new String[]{"abc", "edc", "rfv", "tgb"};

        // specify an adapter (see also next example)
        mAdapter = new RecyclerViewAdapter(myDataSet);
        mRecyclerView.setAdapter(mAdapter);

    }

}
