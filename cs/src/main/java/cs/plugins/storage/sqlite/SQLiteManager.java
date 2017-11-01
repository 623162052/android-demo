package cs.plugins.storage.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * SQLite
 * 保存目录在：data/data/com.example.android_db
 * @author shiwx
 *
 * TODO
 */
public class SQLiteManager extends SQLiteOpenHelper {

    private Context context;
    private static String name = "mybd.db";	// 数据据名称
    private static int version = 1;	// 数据库版本


    public SQLiteManager(Context context){
        super(context, name, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // sqlite支持数据类型：整形、字符串、日期、二进制

        // 创建数据库表
        String sql = "create table person(id integer primary key autoincrement, "
                + "name varchar(64),address varchar(64))";
        db.execSQL(sql);	// 执行SQL
        Log.d("SQLiteManager", "onCreate end");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
