package cs.plugins.storage.sqlite;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import cs.app.R;
import cs.plugins.base.BaseActivity;


/**
 * 存储
 * @author shiwx
 *
 */
public class SQLiteActivity extends BaseActivity {
    private String TAG = "StoreActivity";

    @Bind(R.id.store_btn_write)
    Button btn_write;
    @Bind(R.id.sqlite_btn_oper)
    Button sqlite_btn_oper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_store);


        // SDCard
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileService fs = new FileService(SQLiteActivity.this);
                fs.saveToSdc("log", "hello");
                fs.saveToSdc("log", "hello1");
                fs.saveToSdc("log", "hello2");
                fs.saveToSdc("log", "hello3");

                String content = fs.getContentSdc("log");
                Log.i(TAG, content);
            }
        });

        //SQLite
        sqlite_btn_oper = (Button) this.findViewById(R.id.sqlite_btn_oper);
        sqlite_btn_oper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteService dbService = new SQLiteService(SQLiteActivity.this);

//				boolean addFlag = dbService.insertOper(new String[]{"hechen","liyang"});
//				Log.d(TAG, "addFlag: " + addFlag);
//				
//				Map<String, String> person = dbService.retrieveOper(new String[]{"1"});
//				Log.d(TAG, "person: " + person.toString());
//				
//				boolean addFlag2 = dbService.insertOper(new String[]{"xiaoxiao","xinjiang"});
//				Log.d(TAG, "addFlag2: " + addFlag2);
//				
//				List<Map<String, String>> persons = dbService.retrieveMultOper(null);
//				Log.d(TAG, "persons: " + persons.toString());
//				
//				boolean delFlag = dbService.delOper(new String[]{"xiaoxiao"});
//				Log.d(TAG, "delFlag: " + delFlag);
//				
//				List<Map<String, String>> persons2 = dbService.retrieveMultOper(null);
//				Log.d(TAG, "persons2: " + persons2.toString());

//				boolean addFlag = dbService.insertOper(new String[]{"hechen","liyang"});
//				Log.d(TAG, "addFlag: " + addFlag);


                ContentValues conVals = new ContentValues();
                conVals.put("name", "shiwx");
                conVals.put("address", "hainan");
                boolean addFlag = dbService.insertOper(conVals);
                Log.d(TAG + "2", "addFlag: " + addFlag);

                List<Map<String, String>> persons = dbService.retrieveAnotherMultOper(null, null);
                Log.d(TAG + "2", "persons: " + persons.toString());

                boolean delFlag = dbService.delOper(" id = ? ", new String[]{"11"});
                Log.d(TAG + "2", "delFlag: " + delFlag);

                List<Map<String, String>> persons2 = dbService.retrieveAnotherMultOper(null, null);
                Log.d(TAG + "2", "persons2: " + persons2.toString());
            }
        });


        // Content Provider

    }
}