package cs.plugins.storage.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLiteService {

    public SQLiteManager sqlManager = null;

    public SQLiteService(Context context) {
        sqlManager = new SQLiteManager(context);
    }

    // 对sqlite新增操作(一种插入用法)
    public boolean insertOper(Object[] params) {
        boolean flag = false;
        SQLiteDatabase sqliteDb = null;
        try {
            sqliteDb = sqlManager.getWritableDatabase(); // 实现对数据库的写操作

            String sqlStr = "insert into person(name, address) values (?, ?)";
            sqliteDb.execSQL(sqlStr, params);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqliteDb != null) {
                sqliteDb.close();
            }
        }
        return flag;
    }

    // 对sqlite新增操作(另一种插入用法)
    public boolean insertOper(ContentValues conVals) {
        boolean flag = false;
        SQLiteDatabase sqliteDb = null;
        long id = -1;

        try {
            sqliteDb = sqlManager.getWritableDatabase(); // 实现对数据库的写操作
            id = sqliteDb.insert("person", null, conVals);
            flag = id != -1 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqliteDb != null) {
                sqliteDb.close();
            }
        }
        return flag;
    }

    // 对sqlite删除操作(一种插入用法)
    public boolean delOper(Object[] params) {
        boolean flag = false;
        SQLiteDatabase sqliteDb = null;

        try {
            String sqlStr = "delete from person where name = ? ";
            sqliteDb = sqlManager.getWritableDatabase(); // 实现对数据库的写操作
            sqliteDb.execSQL(sqlStr, params);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqliteDb != null) {
                sqliteDb.close();
            }
        }
        return flag;
    }

    // 对sqlite删除操作(另一种插入用法)
    public boolean delOper(String whereClause, String[] whereArgs) {
        boolean flag = false;
        SQLiteDatabase sqliteDb = null;
        int affectRows = 0;
        try {
            sqliteDb = sqlManager.getWritableDatabase(); // 实现对数据库的写操作
            affectRows = sqliteDb.delete("person", whereClause, whereArgs); // whereClause不包含where关键字
            flag = affectRows > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqliteDb != null) {
                sqliteDb.close();
            }
        }
        return flag;
    }

    // 对sqlite修改操作
    public boolean updateOper(Object[] params) {
        boolean flag = false;
        SQLiteDatabase sqliteDb = null;

        try {
            String sqlStr = "update person set name = ?, address = ? where id = ? ";
            sqliteDb = sqlManager.getWritableDatabase(); // 实现对数据库的写操作
            sqliteDb.execSQL(sqlStr, params);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqliteDb != null) {
                sqliteDb.close();
            }
        }

        return flag;
    }

    // 对sqlite修改操作
    public boolean updateAnotherOper(ContentValues values, String whereClause, String[] whereArgs) {
        boolean flag = false;
        SQLiteDatabase sqliteDb = null;
        int affectRows = 0;
        try {
            sqliteDb = sqlManager.getWritableDatabase(); // 实现对数据库的写操作
            affectRows = sqliteDb.update("person", values, whereClause, whereArgs);
            flag = affectRows > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqliteDb != null) {
                sqliteDb.close();
            }
        }

        return flag;
    }

    // 对sqlite查询单条记录操作
    public Map<String, String> retrieveOper(String[] params) {
        Map<String, String> result = new HashMap<String, String>();
        SQLiteDatabase sqliteDb = null;

        try {
            sqliteDb = sqlManager.getReadableDatabase();
            String sqlStr = "select * from person where id = ? ";
            Cursor cursor = sqliteDb.rawQuery(sqlStr, params);
            int columsCount = cursor.getColumnCount();

            while (cursor.moveToNext()) {
                for (int i = 0; i < columsCount; i++) {
                    String colName = cursor.getColumnName(i);
                    String colVal = cursor.getString(cursor.getColumnIndex(colName));
                    if (colVal == null) {
                        colVal = "";
                    }
                    result.put(colName, colVal);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqliteDb != null) {
                sqliteDb.close();
            }
        }

        return result;
    }

    // 对sqlite查询多条记录操作
    public List<Map<String, String>> retrieveMultOper(String[] params) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        SQLiteDatabase sqliteDb = null;

        try {
            String sqlStr = "select * from person ";
            sqliteDb = sqlManager.getReadableDatabase();
            Cursor cursor = sqliteDb.rawQuery(sqlStr, params);
            int columsCount = cursor.getColumnCount();

            while (cursor.moveToNext()) {
                Map<String, String> itemResult = new HashMap<String, String>();
                for (int i = 0; i < columsCount; i++) {
                    String colName = cursor.getColumnName(i);
                    String colVal = cursor.getString(cursor.getColumnIndex(colName));
                    if (colVal == null) {
                        colVal = "";
                    }
                    itemResult.put(colName, colVal);
                }

                result.add(itemResult);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqliteDb != null) {
                sqliteDb.close();
            }
        }

        return result;
    }

    // 对sqlite查询多条记录操作
    public List<Map<String, String>> retrieveAnotherMultOper(String selection, String[] selectionArgs) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        SQLiteDatabase sqliteDb = null;

        try {
            sqliteDb = sqlManager.getReadableDatabase();
            Cursor cursor = sqliteDb.query("person", null, selection, selectionArgs, null, null, null);
            int columsCount = cursor.getColumnCount();

            while (cursor.moveToNext()) {
                Map<String, String> itemResult = new HashMap<String, String>();
                for (int i = 0; i < columsCount; i++) {
                    String colName = cursor.getColumnName(i);
                    String colVal = cursor.getString(cursor.getColumnIndex(colName));
                    if (colVal == null) {
                        colVal = "";
                    }
                    itemResult.put(colName, colVal);
                }

                result.add(itemResult);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqliteDb != null) {
                sqliteDb.close();
            }
        }

        return result;
    }

}
