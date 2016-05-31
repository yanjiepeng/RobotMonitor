package com.zk.robotmonitor;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zk.adapter.TaskItemAdapter;
import com.zk.bean.taskbean;
import com.zk.database.DataService;

import java.util.List;

public class TaskDetailActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    private ListView lv_task_detail;
    private TextView tv_device_name , tv_device_version;
    private ImageView iv_device_img;
    private List<taskbean> data ;
    private View loadMoreView;
   private  TaskItemAdapter mAdapter;
    private int last_index = 102;
    private Handler handler = new Handler();
    private int visibleLastIndex = 0;   //最后的可视项索引
    private int visibleItemCount;       // 当前窗口可见项总数
    DataService ds;
    int arg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);


        initActionBar();
        initWidget();
        initLeftContent();
    }

    private void initLeftContent() {

        Intent i = getIntent();

        arg = i.getIntExtra("deviceID" , 0);
        switch (arg) {

            case 1:
                iv_device_img.setImageResource(R.mipmap.cut);
                tv_device_name.setText("切割机器人");
                tv_device_version.setText("MJ-Cut-001");
                break;
            case 2:
                iv_device_img.setImageResource(R.mipmap.code);
                tv_device_name.setText("激光刻码系统");
                tv_device_version.setText("MJ-Cut-001");
                break;
            case 3:
                iv_device_img.setImageResource(R.mipmap.camera);
                tv_device_name.setText("视觉识别系统");
                tv_device_version.setText("MJ-Cut-001");
                break;
            case 4:
                iv_device_img.setImageResource(R.mipmap.car);
                tv_device_name.setText("AGV搬运小车");
                tv_device_version.setText("MJ-Cut-001");
                break;
            case 5:
                iv_device_img.setImageResource(R.mipmap.weld);
                tv_device_name.setText("焊接机器人");
                tv_device_version.setText("MJ-Cut-001");
                break;
            case 6:
                iv_device_img.setImageResource(R.mipmap.manipulator);
                tv_device_name.setText("搬运机器人");
                tv_device_version.setText("MJ-Cut-001");

        }
         LayoutInflater lif = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loadMoreView = lif.inflate(R.layout.load_more, null);
        //------------------------------------------------------------
        ds = new DataService(MainActivity.sqldb);
        data =  ds.getData(arg ,last_index);
        last_index = Integer.parseInt(data.get(data.size()-1).getId());
        //------------------------------------------------------------

        lv_task_detail.addFooterView(loadMoreView);

        mAdapter = new TaskItemAdapter(TaskDetailActivity.this ,data);

        lv_task_detail.setAdapter(mAdapter);

        lv_task_detail.setOnScrollListener(this);
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

    }


    /**
     * 滑动状态改变回调
     * @param view
     * @param scrollState
     */

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        int itemLastIndex = mAdapter.getCount() - 1; //数据集最后一项的索引
        int lastIndex = itemLastIndex + 1; //加上footer的总数

        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex)
            //此时异步加载数据

            Log.i("LOADMORE" , "loading");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadMoreData();
                last_index = Integer.parseInt(data.get(data.size() -1).getId());
            }
        },1000);


    }



    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.visibleItemCount = visibleItemCount;
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1 ;
    }

    //上拉加载
    private void LoadMoreData() {

        data.clear();
        data.addAll(ds.getData(arg, last_index));
        mAdapter.notifyDataSetChanged();
    }

}
