package cs.app.provider;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by shiwx on 2015/1/13.
 */
public class SettingProvider extends ActionProvider {

    private Context mContext;

    /**
     * Creates a new instance. ActionProvider classes should always implement a
     * constructor that takes a single Context parameter for inflating from menu XML.
     *
     * @param context Context for accessing resources.
     */
    public SettingProvider(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View onCreateActionView(MenuItem forItem) {
        return super.onCreateActionView(forItem);

//        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
//        View view = layoutInflater.inflate(R.layout.activity_sqlite, null);
//        ImageButton button = (ImageButton) view.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Do something...
//            }
//        });

//        return view;
    }

    @Override
    public View onCreateActionView() {
        return null;
    }
}
