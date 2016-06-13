package com.zk.robotmonitor;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zk.database.MyDbHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_robot_cut, tv_robot_code, tv_robot_weld, tv_robot_carry, tv_robot_check, tv_robot_agv, tv_data_exception, tv_agv_charge;

    public String DB_NAME = "mjrobot.db";
    public int DB_VERSION = 1;
    public static SQLiteDatabase sqldb;
    MyDbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new MyDbHelper(this, DB_NAME, null, DB_VERSION);
        sqldb = helper.getWritableDatabase();  // 通过helper的getWritableDatabase()得到SQLiteOpenHelper所创建的数据库

        initActionBar();
        initWidget();

    }


   /*  *//*
        模拟插入两个异常数据
     *//*
    private void insertData() {
        ContentValues values = new ContentValues();
        for (int i = 0; i < 2; i++) {
            values.put("deviceID", "1");
            values.put("name", "切割机器人");
            values.put("workzone", "工位2");
            values.put("status", "异常");

            values.put("type", "工件a");
            values.put("time", "2016-05-30 16:14:07");
            values.put("period", "2016-05-30 16:14:26");
            sqldb.insert("device_task", "_id", values);
        }
    }*/

     /*
        初始化界面控件
     */
    private void initWidget() {

        tv_robot_cut = (TextView) findViewById(R.id.tv_robot_cut);
        tv_robot_code = (TextView) findViewById(R.id.tv_robot_code);
        tv_robot_weld = (TextView) findViewById(R.id.tv_robot_weld);
        tv_robot_carry = (TextView) findViewById(R.id.tv_robot_carry);
        tv_robot_check = (TextView) findViewById(R.id.tv_camera_check);
        tv_robot_agv = (TextView) findViewById(R.id.tv_agv_car);
        tv_data_exception = (TextView) findViewById(R.id.tv_exception_data);
        tv_agv_charge = (TextView) findViewById(R.id.tv_charge);

        tv_robot_cut.setOnClickListener(this);
        tv_robot_code.setOnClickListener(this);
        tv_robot_weld.setOnClickListener(this);
        tv_robot_carry.setOnClickListener(this);
        tv_robot_check.setOnClickListener(this);
        tv_robot_agv.setOnClickListener(this);
        tv_data_exception.setOnClickListener(this);
        tv_agv_charge.setOnClickListener(this);
    }

    /*
       初始化actionbar
     */
    private void initActionBar() {

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setCustomView(R.layout.titlebar);

        actionBar.setIcon(R.drawable.zklogo);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_exception_data:
                startActivity(new Intent(MainActivity.this, ExceptionActivity.class));
                break;
            case R.id.tv_robot_cut:
                Intent intent1 = new Intent(MainActivity.this, TaskDetailActivity.class);
                intent1.putExtra("deviceID", 1);
                startActivity(intent1);
                break;
            case R.id.tv_robot_code:
                Intent intent2 = new Intent(MainActivity.this, TaskDetailActivity.class);
                intent2.putExtra("deviceID", 2);
                startActivity(intent2);
                break;
            case R.id.tv_camera_check:
                Intent intent3 = new Intent(MainActivity.this, TaskDetailActivity.class);
                intent3.putExtra("deviceID", 3);
                startActivity(intent3);
                break;
            case R.id.tv_agv_car:
                Intent intent4 = new Intent(MainActivity.this, TaskDetailActivity.class);
                intent4.putExtra("deviceID", 4);
                startActivity(intent4);
                break;
            case R.id.tv_robot_weld:
                Intent intent5 = new Intent(MainActivity.this, TaskDetailActivity.class);
                intent5.putExtra("deviceID", 5);
                startActivity(intent5);
                break;
            case R.id.tv_robot_carry:
                Intent intent6 = new Intent(MainActivity.this, TaskDetailActivity.class);
                intent6.putExtra("deviceID", 6);
                startActivity(intent6);
                break;
            case R.id.tv_charge:
                Intent intent7 = new Intent(MainActivity.this, AgvChargeActivity.class);
                startActivity(intent7);
                break;
        }
    }
}
