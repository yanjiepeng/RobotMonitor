package com.zk.robotmonitor;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.zk.adapter.TaskItemAdapter;
import com.zk.bean.taskbean;
import com.zk.database.DataService;

import java.util.List;

public class ExceptionActivity extends AppCompatActivity {


    private ListView lv_exception_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception);
        initActionBar();

        lv_exception_data = (ListView) findViewById(R.id.lv_exception_data);

        initContent();
    }

    /*
        初始化内容
     */
    private void initContent() {
        List<taskbean> data = new DataService(MainActivity.sqldb).QueryExceptiontData();
        TaskItemAdapter exceptAdapter = new TaskItemAdapter(ExceptionActivity.this, data);

        lv_exception_data.setAdapter(exceptAdapter);

    }

    /*
        初始化action bar
     */
    private void initActionBar() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true); // 决定左上角图标的右侧是否有向左的小箭头, true
            // 有小箭头，并且图标可以点击
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setTitle("异常数据统计");
        }

    }

    /*
        ActionBar的菜单监听
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
