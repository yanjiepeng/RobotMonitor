package com.zk.robotmonitor;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_robot_cut , tv_robot_code , tv_robot_weld , tv_robot_carry , tv_robot_check , tv_robot_agv , tv_data_exception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActionBar();
        initWidget();
    }

    private void initWidget() {

        tv_robot_cut = (TextView) findViewById(R.id.tv_robot_cut);
        tv_robot_code = (TextView) findViewById(R.id.tv_robot_code);
        tv_robot_weld = (TextView) findViewById(R.id.tv_robot_weld);
        tv_robot_carry = (TextView) findViewById(R.id.tv_robot_carry);
        tv_robot_check = (TextView) findViewById(R.id.tv_camera_check);
        tv_robot_agv = (TextView) findViewById(R.id.tv_agv_car);
        tv_data_exception = (TextView) findViewById(R.id.tv_exception_data);

        tv_robot_cut.setOnClickListener(this);
        tv_robot_code.setOnClickListener(this);
        tv_robot_weld.setOnClickListener(this);
        tv_robot_carry.setOnClickListener(this);
        tv_robot_check.setOnClickListener(this);
        tv_robot_agv.setOnClickListener(this);
        tv_data_exception.setOnClickListener(this);
    }

    /**
     * 初始化actionba
     */
    private void initActionBar() {

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setCustomView(R.layout.titlebar);

        actionBar.setIcon(R.mipmap.mjlogo);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_exception_data:
                startActivity(new Intent(MainActivity.this , ExceptionActivity.class));
                break;

            case R.id.tv_robot_cut:
                Intent intent = new Intent(MainActivity.this , TaskDetailActivity.class);
                intent.putExtra("deviceID" , 1);
                startActivity(intent);
        }
    }
}
