package com.zk.robotmonitor;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.zk.adapter.TaskItemAdapter;
import com.zk.bean.taskbean;
import com.zk.database.DataService;
import com.zk.utils.Config;
import com.zk.utils.FormatUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TaskDetailActivity extends AppCompatActivity {

    private ListView lv_task_detail;
    private TextView tv_device_name, tv_device_version;
    private ImageView iv_device_img;
    private List<taskbean> data;
    private View loadMoreView;
    private TaskItemAdapter mAdapter;
    private int last_index = 102;
    private Handler handler = new Handler();
    private int visibleLastIndex = 0;   //最后的可视项索引
    private int visibleItemCount;       // 当前窗口可见项总数

    DataService ds;
    int arg;
    private boolean isLastRow;
    Timer timer;


    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
//               TaskItemAdapter adapter =new TaskItemAdapter(TaskDetailActivity.this , data);
//                lv_task_detail.setAdapter(adapter);
                mAdapter.notifyDataSetChanged();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);


        initActionBar();
        initWidget();
        initLeftContent();
        new GenerateDataThread().start();
        mAdapter = new TaskItemAdapter(this , data);
        lv_task_detail.setAdapter(mAdapter);
    }



    /*
     初始化左边状态栏及列表内容
     */
    private void initLeftContent() {

        Intent i = getIntent();
        arg = i.getIntExtra("deviceID", 0);
        data = new ArrayList<taskbean>();
        switch (arg) {

            case 1:
                iv_device_img.setImageResource(R.mipmap.cut);
                tv_device_name.setText("切割机器人");
                tv_device_version.setText("Sino-Cut-001");
                break;
            case 2:
                iv_device_img.setImageResource(R.mipmap.code);
                tv_device_name.setText("激光刻码系统");
                tv_device_version.setText("Sino-Cut-001");
                break;
            case 3:
                iv_device_img.setImageResource(R.mipmap.camera);
                tv_device_name.setText("视觉识别系统");
                tv_device_version.setText("Sino-Cut-001");
                break;
            case 4:
                iv_device_img.setImageResource(R.mipmap.car);
                tv_device_name.setText("AGV搬运小车");
                tv_device_version.setText("Sino-Cut-001");
                break;
            case 5:
                iv_device_img.setImageResource(R.mipmap.weld);
                tv_device_name.setText("焊接机器人");
                tv_device_version.setText("Sino-Cut-001");
                break;
            case 6:
                iv_device_img.setImageResource(R.mipmap.manipulator);
                tv_device_name.setText("搬运机器人");
                tv_device_version.setText("Sino-Cut-001");

        }
    }



    /*
     此线程用于生成模拟数据
     */
    class GenerateDataThread extends Thread {
        int id = Config.data_index;
        @Override
        public void run() {
            super.run();
            while (true) {
                taskbean task = new taskbean();
                task.setId(id + "");
                id++;
                Config.data_index = id;
                task.setName(tv_device_name.getText().toString());
                task.setStatus("正常");
                task.setType("时检");
                String current_time = FormatUtil.refFormatNowDate(arg);
                task.setTime(current_time);
                task.setPeriod(FormatUtil.GettimePeriod(current_time , TaskDetailActivity.this));
                data.add(task);
                if (data.size() >= 400) {
                    data = data.subList(data.size()-400,data.size());
                }
                try {
                    sleep(1000);
                    mHandler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 初始化actionbar
     */
    private void initActionBar() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
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
                if (timer != null) {
                    timer.cancel();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 初始化界面控件
     */
    private void initWidget() {

        lv_task_detail = (ListView) findViewById(R.id.lv_task_info);
        iv_device_img = (ImageView) findViewById(R.id.iv_device_img);
        tv_device_name = (TextView) findViewById(R.id.tv_device_name);
        tv_device_version = (TextView) findViewById(R.id.tv_device_version);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }



}
