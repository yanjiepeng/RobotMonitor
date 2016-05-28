package com.zk.robotmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ExceptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("异常数据统计");
        setContentView(R.layout.activity_exception);
    }
}
