package com.zk.robotmonitor;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TaskDetailActivity extends AppCompatActivity {

    private ListView lv_task_detail;
    private TextView tv_device_name , tv_device_version;
    private ImageView iv_device_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);


        initActionBar();
        initWidget();
    }

    private void initActionBar() {


        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true); // 决定左上角图标的右侧是否有向左的小箭头, true
            // 有小箭头，并且图标可以点击
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setTitle("设备详情");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initWidget() {

        lv_task_detail = (ListView) findViewById(R.id.lv_task_info);
        iv_device_img = (ImageView) findViewById(R.id.iv_device_img);
        tv_device_name = (TextView) findViewById(R.id.tv_device_name);
        tv_device_version = (TextView) findViewById(R.id.tv_device_version);

        LayoutInflater lif = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View headerView = lif.inflate(R.layout.list_header, lv_task_detail, false);


        String[] data = {"google","amazon","facebook"};

        lv_task_detail.addHeaderView(headerView);

        ArrayAdapter mAdapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1,data);

        lv_task_detail.setAdapter(mAdapter);

    }
}
