package cs.app.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import cs.app.R;
import cs.plugins.base.BaseFragment;

/**
 * Created by shiwx on 2015/1/13.
 */
public class ContentFragment extends BaseFragment {
    private static final String TAG = "ContentFragment";

    @Bind(R.id.btn_fragment_textView)
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_fragment, container, false);
        return view;
    }

    /**
     * do work in onViewCreated
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 接收抽屉传来的数据
        Bundle bundle = getArguments();
        if(bundle != null){
            String argText = bundle.getString("text");
            textView.setText(argText);
        }

    }
}
