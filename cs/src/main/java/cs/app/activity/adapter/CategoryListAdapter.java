package cs.app.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cs.app.R;

/**
 * Created by shiwx on 2016/3/18.
 */
public class CategoryListAdapter extends BaseAdapter {
    private LayoutInflater mInflater = null;
    private List<CategoryEntity> dataList = null;
    private Context mContext = null;

    public CategoryListAdapter(Context context, List<CategoryEntity> dataList) {
        // 根据context上下文加载布局
        this.mInflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        // 如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            // 根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.category_custom_list, parent,false);
            holder = new ViewHolder(convertView);

            // 将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.categoryTitle.setText(dataList.get(position).getCategoryName());
        //holder.categoryImage.setImageDrawable(mContext.getDrawable(R.drawable.common_person_item_btn));
        //holder.categoryImage.setBackground(mContext.getDrawable(R.drawable.common_person_item_btn));
        return convertView;
    }

    // ViewHolder静态类
    static class ViewHolder {
        @Bind(R.id.category_custom_item_title)
        TextView categoryTitle;
        @Bind(R.id.category_custom_item_image)
        NetworkImageView categoryImage;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
