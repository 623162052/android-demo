package cs.app.widget.gridview;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnItemClick;
import cs.app.R;
import cs.app.activity.adapter.CategoryEntity;
import cs.app.activity.adapter.CustomGridAdapter;
import cs.plugins.base.BaseActivity;
import cs.plugins.log.Logger;

/**
 * 自定义GridView
 */
public class GridViewActivity extends BaseActivity {
    private static final String TAG = "GridViewActivity";
    private List<CategoryEntity> listConfig = null;

    @Bind(R.id.custom_grid_view)
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        listConfig = new ArrayList<>();
        listConfig.add(new CategoryEntity("交通罚款", R.drawable.jtfk));
        listConfig.add(new CategoryEntity("商旅预订", R.drawable.chuxingyi0));
        listConfig.add(new CategoryEntity("火车票", R.drawable.train_ticket));
        listConfig.add(new CategoryEntity("大麦网", R.drawable.damaiwang));
        listConfig.add(new CategoryEntity("挂号就医", R.drawable.guahao));
        listConfig.add(new CategoryEntity("健康管理", R.drawable.zhgy));

        CustomGridAdapter shortcutAdapter = new CustomGridAdapter(GridViewActivity.this, listConfig);
        gridView.setNumColumns(3);
        gridView.setDrawSelectorOnTop(true);
        gridView.setAdapter(shortcutAdapter);
    }

    /**
     * GridView Item点击事件
     */
    @OnItemClick(R.id.custom_grid_view)
    void onGridViewItemClick(int position){
        CategoryEntity category = listConfig.get(position);
        Logger.d(TAG, "category:" + category.getCategoryName());
    }

}
