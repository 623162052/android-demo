package cs.app.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cs.app.R;

/**
 * Created by shiwx on 2015/4/23.
 */
public class CustomGridAdapter extends BaseAdapter {
    private static final String TAG = "CustomGridAdapter";
    private Context mContext;
    private List<CategoryEntity> dataList;

    public CustomGridAdapter(Context context, List<CategoryEntity> dataList) {
        this.mContext = context;
        this.dataList = dataList;

    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);
            holder = new ViewHolder(convertView);

            CategoryEntity config = dataList.get(position);
            holder.gridImage.setImageDrawable(mContext.getDrawable(config.getImageId()));
            holder.categoryName.setText(config.getCategoryName());

            convertView.setTag(holder);
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.grid_item_txt)
        TextView categoryName;

        @Bind(R.id.grid_item_img)
        ImageView gridImage;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
