package com.zk.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/5/28.
 */
public class MyDbHelper extends SQLiteOpenHelper {

    Context mContext;
    String sql = "CREATE  TABLE device_task(_id INTEGER PRIMARY KEY AUTO_INCREMENT , deviceID INTEGER , name VARCHAR, workzone VARCHAR , status VARCHAR , type VARCHAR , time TEXT , period TEXT)"; //创建sql语句

    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        this.mContext = context;
    }

    /**
     * 当数据库首次创建时执行该方法，一般将创建表等初始化操作放在该方法中执行.
     * 重写onCreate方法，调用execSQL方法创建表
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

//        db.execSQL(sql);
    }

    //数据库升级
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
