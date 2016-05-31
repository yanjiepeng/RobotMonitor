package com.zk.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zk.bean.Taskbean;

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

    public List<Taskbean> getData(int id , int last_index) {
        List<Taskbean> data = new ArrayList<Taskbean>();

        //从数据库获取数据
        data = QueryRecentData(id , last_index);


        return data;

    }

    /**
     * 此方法是从数据库查询对应设备的任务情况
     * @param id
     */
    private List<Taskbean> QueryRecentData(int id , int last_index) {
    List<Taskbean> result = new ArrayList<Taskbean>();
        String requestID = id+"";
        Cursor cursor = db.rawQuery("select * from device_task where deviceID=? and _id < ?",new String[]{id+"" , last_index+20+"" });
        while (cursor.moveToNext()) {
            Taskbean tb = new Taskbean();

            tb.setId(cursor.getInt(cursor.getColumnIndex("_id"))+"");
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
