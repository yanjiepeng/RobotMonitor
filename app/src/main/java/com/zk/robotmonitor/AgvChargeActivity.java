package com.zk.robotmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.zk.eventBus.EventAA;
import com.zk.service.ZkModbusService;
import com.zk.utils.Config;
import com.zk.widget.LaunchedBoxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AgvChargeActivity extends AppCompatActivity {

    private TextView tv_charge_v;
    private LaunchedBoxView lb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agv_charge);
        initActionBar();
        initWiget();
        EventBus.getDefault().register(this);
        Intent intent = new Intent(this, ZkModbusService.class);
        startService(intent);
    }

    /*
     初始化界面控件
     */
    private void initWiget() {
        tv_charge_v = (TextView) findViewById(R.id.tv_agv_charge_voltage);
        lb = (LaunchedBoxView) findViewById(R.id.lb_preview);
    }

    /*
      初始化actionbar
     */
    private void initActionBar() {

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true); // 决定左上角图标的右侧是否有向左的小箭头, true
            // 有小箭头，并且图标可以点击
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setTitle("AGV充电监测");
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

    /*
        EventBus 自动回调 UI线程
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventAA eventAA) {

        if (eventAA.getActionType() == EventAA.ACTION_SEND_MSG) {

            int charge_v = Integer.parseInt(eventAA.getMapMessage().get(Config.Voltage_1));
            tv_charge_v.setText("电压:" + charge_v + "V");

        }

        //在此处根据下位agv状态更新界面上消息框的位置以及轨道状态

    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }


    /*
      消息框左移动画
     */
    private void AminToLeft() {

        TranslateAnimation ta = new TranslateAnimation(0, -900, 0, 0);
        ta.setDuration(1500);
        ta.setFillAfter(true);
        lb.startAnimation(ta);

    }

    /*
     消息框右移动画
     */

    private void AminToRight() {

        TranslateAnimation ta = new TranslateAnimation(-900, 0, 0, 0);
        ta.setDuration(1500);
        ta.setFillAfter(true);
        lb.startAnimation(ta);

    }

}
