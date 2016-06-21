package com.zk.robotmonitor;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zk.adapter.TaskItemAdapter;
import com.zk.bean.taskbean;
import com.zk.database.DataService;
import com.zk.eventBus.EventQR;
import com.zk.utils.Config;
import com.zk.utils.FormatUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class TaskDetailActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView lv_task_detail;
    private TextView tv_device_name, tv_device_version;
    private ImageView iv_device_img;
    private  List<taskbean> data;
    private  List<taskbean> dataQR;
    private  List<taskbean> dataVersion;
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
            if (msg.what == 0 && arg != 3 && arg != 2 ) {
                mAdapter = new TaskItemAdapter(TaskDetailActivity.this , data);
                lv_task_detail.setAdapter(mAdapter);
                lv_task_detail.setSelection(lv_task_detail.getCount());

            }else if (msg.what == 1 && arg == 2) {
                TaskItemAdapter adapter = new TaskItemAdapter(TaskDetailActivity.this , dataQR);
                lv_task_detail.setAdapter(adapter);
                lv_task_detail.setSelection(lv_task_detail.getCount());
            }else if (msg.what == 1 && arg == 3) {
                TaskItemAdapter adapter = new TaskItemAdapter(TaskDetailActivity.this , dataVersion);
                lv_task_detail.setAdapter(adapter);
                lv_task_detail.setSelection(lv_task_detail.getCount());
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
        EventBus.getDefault().register(this);
        new GenerateDataThread().start();

    }



    /*
     初始化左边状态栏及列表内容
     */
    private void initLeftContent() {

        Intent i = getIntent();
        arg = i.getIntExtra("deviceID", 0);
        data = new ArrayList<taskbean>();
        dataQR = new ArrayList<taskbean>();
        dataVersion = new ArrayList<taskbean>();
        switch (arg) {

            case 1:
                iv_device_img.setImageResource(R.mipmap.cut);
                tv_device_name.setText("切割机器人");
                tv_device_version.setText("Sino-Cut-001");
                break;
            case 2:
                iv_device_img.setImageResource(R.mipmap.code);
                tv_device_name.setText("激光刻码系统");
                tv_device_version.setText("Sino-QR-001");
                break;
            case 3:
                iv_device_img.setImageResource(R.mipmap.camera);
                tv_device_name.setText("视觉识别系统");
                tv_device_version.setText("Sino-Visual-001");
                break;
            case 4:
                iv_device_img.setImageResource(R.mipmap.car);
                tv_device_name.setText("AGV搬运小车");
                tv_device_version.setText("Sino-AGV-001");
                break;
            case 5:
                iv_device_img.setImageResource(R.mipmap.weld);
                tv_device_name.setText("焊接机器人");
                tv_device_version.setText("Sino-Weld-001");
                break;
            case 6:
                iv_device_img.setImageResource(R.mipmap.manipulator);
                tv_device_name.setText("搬运机器人");
                tv_device_version.setText("Sino-Carrier-001");

        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (arg == 2) {
            Intent intent = new Intent(TaskDetailActivity.this , QRCodeActivity.class);
            intent.putExtra("code", dataQR.get(position).getQrCode() );
            startActivity(intent);
        }else if (arg == 3) {
            Intent intent = new Intent(TaskDetailActivity.this , QRCodeActivity.class);
            intent.putExtra("code", dataVersion.get(position).getQrCode() );
            startActivity(intent);
        }
    }


    @Subscribe (threadMode = ThreadMode.MAIN)
    public void GenerateDataQR(EventQR eventQR) {
        if (eventQR.getMsg().equals("code")) {
            //生成1条刻码数据
            taskbean task = new taskbean();
            task.setId(Config.data_index+"");
            task.setName("激光刻码系统");
            task.setTime(FormatUtil.refFormatNowDate(arg));
            task.setPeriod(FormatUtil.GettimePeriod(FormatUtil.refFormatNowDate(arg), TaskDetailActivity.this));
            task.setStatus("正常");
            task.setType("时检");
            task.setQrCode(eventQR.getQRCode());
            dataQR.add(task);
            if (dataQR.size() >= 400) {
                dataQR.clear();
            }
            mHandler.sendEmptyMessage(1);
        } else if (eventQR.getMsg().equals("version")) {
            //生成1条视觉检测数据
            taskbean task = new taskbean();
            task.setId(Config.data_index+"");
            task.setName("视觉检测系统");
            task.setTime(FormatUtil.refFormatNowDate(arg));
            task.setPeriod(FormatUtil.GettimePeriod(FormatUtil.refFormatNowDate(arg), TaskDetailActivity.this));
            task.setStatus("正常");
            task.setType("时检");
            task.setQrCode(eventQR.getQRCode());
            dataVersion.add(task);
            if (dataVersion.size() >= 400) {
                dataVersion.clear();
            }
            mHandler.sendEmptyMessage(2);
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
                    data.clear();
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

        lv_task_detail.setOnItemClickListener(this);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }



}
