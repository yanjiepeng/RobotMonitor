package com.zk.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zk.bean.taskbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class DataService {

    SQLiteDatabase db;

    public DataService(SQLiteDatabase db) {
        this.db = db;
    }

    public List<taskbean> getData(int id, int last_index) {
        List<taskbean> data = new ArrayList<taskbean>();

        //从数据库获取数据
        data = QueryRecentData(id, last_index);


        return data;

    }

    /**
     * 此方法是从数据库查询对应设备的任务情况
     *
     * @param id
     */
    private List<taskbean> QueryRecentData(int id, int last_index) {
        List<taskbean> result = new ArrayList<taskbean>();
        String requestID = id + "";
        Cursor cursor = db.rawQuery("select * from device_task where deviceID=? and _id < ?", new String[]{id + "", last_index + 20 + ""});
        while (cursor.moveToNext()) {
            taskbean tb = new taskbean();

            tb.setId(cursor.getInt(cursor.getColumnIndex("_id")) + "");
            tb.setName(cursor.getString(cursor.getColumnIndex("name")));
            tb.setWorkzone(cursor.getString(cursor.getColumnIndex("workzone")));
            tb.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            tb.setType(cursor.getString(cursor.getColumnIndex("type")));
            tb.setTime(cursor.getString(cursor.getColumnIndex("time")));
            tb.setPeriod(cursor.getString(cursor.getColumnIndex("period")));

            result.add(tb);

        }


        return result;
    }


    /**
     * 此方法是从数据库查询异常数据
     */
    public List<taskbean> QueryExceptiontData() {
        List<taskbean> result = new ArrayList<taskbean>();
        Cursor cursor = db.rawQuery("select * from device_task where status =? ", new String[]{"异常"});
        while (cursor.moveToNext()) {
            taskbean tb = new taskbean();

            tb.setId(cursor.getInt(cursor.getColumnIndex("_id")) + "");
            tb.setName(cursor.getString(cursor.getColumnIndex("name")));
            tb.setWorkzone(cursor.getString(cursor.getColumnIndex("workzone")));
            tb.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            tb.setType(cursor.getString(cursor.getColumnIndex("type")));
            tb.setTime(cursor.getString(cursor.getColumnIndex("time")));
            tb.setPeriod(cursor.getString(cursor.getColumnIndex("period")));

            result.add(tb);
        }
        return result;
    }

}
