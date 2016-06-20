package com.zk.robotmonitor;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.zk.database.MyDbHelper;
import com.zk.eventBus.EventP;
import com.zk.service.UpdateProceedService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_robot_cut, tv_robot_code, tv_robot_weld, tv_robot_carry, tv_robot_check, tv_robot_agv, tv_data_exception, tv_agv_charge;
    private TextView tv_proceed_stack_get, tv_proceed_stack_put, tv_proceed_agv_car, tv_proceed_carry_get, tv_proceed_carry_put, tv_proceed_cut, tv_proceed_code,tv_proceed_scan, tv_proceed_version, tv_proceed_point_weld, tv_proceed_line_weld;
    public String DB_NAME = "mjrobot.db";
    public int DB_VERSION = 1;
    public static SQLiteDatabase sqldb;
    private List<TextView> proceeds;
    MyDbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new MyDbHelper(this, DB_NAME, null, DB_VERSION);
        sqldb = helper.getWritableDatabase();  // 通过helper的getWritableDatabase()得到SQLiteOpenHelper所创建的数据库
        proceeds = new ArrayList<TextView>();
        EventBus.getDefault().register(this);
        initActionBar();
        initWidget();

        startService(new Intent(MainActivity.this, UpdateProceedService.class));

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


        tv_proceed_stack_get = (TextView) findViewById(R.id.proceed_stack_get);
        tv_proceed_stack_put = (TextView) findViewById(R.id.proceed_stack_put);
        tv_proceed_agv_car = (TextView) findViewById(R.id.proceed_agv_car);
        tv_proceed_carry_get = (TextView) findViewById(R.id.proceed_carry_get);
        tv_proceed_carry_put = (TextView) findViewById(R.id.proceed_carry_put);
        tv_proceed_cut = (TextView) findViewById(R.id.proceed_cut);
        tv_proceed_version = (TextView) findViewById(R.id.proceed_vesion);
        tv_proceed_code = (TextView) findViewById(R.id.proceed_code);
        tv_proceed_scan = (TextView) findViewById(R.id.proceed_scan_code);
        tv_proceed_point_weld = (TextView) findViewById(R.id.proceed_point_weld);
        tv_proceed_line_weld = (TextView) findViewById(R.id.proceed_line_weld);

        proceeds.add(tv_proceed_stack_get);
        proceeds.add(tv_proceed_stack_put);
        proceeds.add(tv_proceed_agv_car);
        proceeds.add(tv_proceed_carry_get);
        proceeds.add(tv_proceed_carry_put);
        proceeds.add(tv_proceed_cut);
        proceeds.add(tv_proceed_version);
        proceeds.add(tv_proceed_code);
        proceeds.add(tv_proceed_scan);
        proceeds.add(tv_proceed_point_weld);
        proceeds.add(tv_proceed_line_weld);


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

        actionBar.setIcon(R.drawable.mjlogo);

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


    /*
            更新流程显示
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void UpdateProceedStatus(EventP eventP) {
        if (eventP.getStatus() != null) {

            Log.i("eventbus ui", "更新");

            char[] status = eventP.getStatus();
            switch (status[2]) {
                case '1':
                    if (status[10] == '0') {
                        remoivAllAnimation();
                        startFlick(tv_proceed_stack_put);
                    } else if (status[10] == '1') {
                        remoivAllAnimation();
                        startFlick(tv_proceed_stack_get);
                    }
                    break;
                case '2':
                    remoivAllAnimation();
                    startFlick(tv_proceed_agv_car);
                    break;
                case '3':

                    if ((status[3] == '0' && (status[4] == '1' || status[4] == '3' || status[4] == '4' || status[4] == '5')) || (status[3] == '1' && (status[4] == '2' || status[4] == '3'))) {
                        remoivAllAnimation();
                        startFlick(tv_proceed_carry_get);
                    } else if ((status[3] == '0' && (status[4] == '2' || status[4] == '9' || status[4] == '0')) || (status[3] == '1' && (status[4] == '1' || status[4] == '4' || status[4] == '5'))) {
                        remoivAllAnimation();
                        startFlick(tv_proceed_carry_put);
                    }
                    break;
                case '4':
                    remoivAllAnimation();
                    startFlick(tv_proceed_cut);
                    break;
                case '5':
                    remoivAllAnimation();
                    startFlick(tv_proceed_version);
                    break;
                case '6':
                    remoivAllAnimation();
                    startFlick(tv_proceed_code);
                    break;
                case '7':
                    remoivAllAnimation();
                    startFlick(tv_proceed_scan);
                    break;
                case '8':
                    remoivAllAnimation();
                    startFlick(tv_proceed_point_weld);
                    break;
                case '9':
                    remoivAllAnimation();
                    startFlick(tv_proceed_line_weld);
                    break;

            }
        }

    }

    /**
     * 开启View闪烁效果
     */

    private void startFlick(View view) {

        if (null == view) {

            return;

        }

        Animation alphaAnimation = new AlphaAnimation(1, 0);

        alphaAnimation.setDuration(300);

        alphaAnimation.setInterpolator(new LinearInterpolator());

        alphaAnimation.setRepeatCount(Animation.INFINITE);

        alphaAnimation.setRepeatMode(Animation.REVERSE);

        view.startAnimation(alphaAnimation);

    }

    /**
     * 取消View闪烁效果
     */

    private void stopFlick(View view) {

        if (null == view) {
            return;
        }
        view.clearAnimation();
    }

    /*
    关闭所有进度动画
     */
    private void remoivAllAnimation() {
        for (TextView tv : proceeds) {
            stopFlick(tv);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
