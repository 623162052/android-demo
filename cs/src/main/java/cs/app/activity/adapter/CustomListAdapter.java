package cs.app.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cs.app.R;

/**
 * Created by shiwx on 2016/1/12.
 */
public class CustomListAdapter extends BaseAdapter {
    private LayoutInflater mInflater = null;
    private List<ItemConfig> dataList = null;
    private Context mContext = null;

    public CustomListAdapter(Context context, List<ItemConfig> dataList) {
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
        // 获取数据集中与指定索引对应的数据项
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // 获取在列表中与指定索引对应的行id
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        // 如果缓存convertView为空，则需要创建View
        if (view == null) {
            // 根据自定义的Item布局加载布局
            view = mInflater.inflate(R.layout.adapter_custom_list, parent,false);
            holder = new ViewHolder(view);

            // 将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ItemConfig currentItem = dataList.get(position);
        if(currentItem.getTitleId() != 0){
            int strId = currentItem.getTitleId();
            holder.title.setText(mContext.getResources().getString(strId));
        }else{
            holder.title.setText(currentItem.getTitle());
        }

        return view;
    }


    // ViewHolder静态类
    static class ViewHolder {
        @Bind(R.id.adapter_custom_item_title)
        TextView title;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}


