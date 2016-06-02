package com.zk.robotmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.zk.eventBus.EventAA;
import com.zk.service.ZkModbusService;
import com.zk.utils.Config;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AgvChargeActivity extends AppCompatActivity {

    private TextView tv_charge_v;
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

    private void initWiget() {
        tv_charge_v = (TextView) findViewById(R.id.tv_agv_charge_voltage);
    }

    /**
     * 初始化actionbar
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventAA eventAA) {

        if (eventAA.getActionType() == EventAA.ACTION_SEND_MSG) {

                int charge_v = Integer.parseInt(eventAA.getMapMessage().get(Config.Voltage_1));
                tv_charge_v.setText("电压:"+charge_v+"V");
        }



    }


    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
