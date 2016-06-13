package com.zk.robotmonitor;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zk.bean.ChargeBean;
import com.zk.eventBus.EventAA;
import com.zk.service.ChargeService;
import com.zk.widget.LaunchedBoxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AgvChargeActivity extends AppCompatActivity {

    private TextView tv_charge_v, tv_charge_elec, tv_charge_power, tv_charge_status;
    private LaunchedBoxView lb;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agv_charge);
        initActionBar();
        initWiget();
        gson = new Gson();

        EventBus.getDefault().register(this);

        Intent intent = new Intent(AgvChargeActivity.this, ChargeService.class);
        startService(intent);

    }

    /*
     初始化界面控件
     */
    private void initWiget() {
        tv_charge_v = (TextView) findViewById(R.id.tv_charge_voltage);
        lb = (LaunchedBoxView) findViewById(R.id.lb_preview);
        tv_charge_elec = (TextView) findViewById(R.id.tv_charge_electricity);
        tv_charge_power = (TextView) findViewById(R.id.tv_charge_power);
        tv_charge_status = (TextView) findViewById(R.id.tv_charge_status);
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

        if (eventAA.getActionType() == EventAA.ACTION_SEND_CHARGE) {

            String result = eventAA.getMessage();
            Log.w("result", result);
            if (result.equals("error")) {
                Toast.makeText(AgvChargeActivity.this, "网络错误！请检查", Toast.LENGTH_SHORT).show();
            } else if (result != null && !result.equals("")) {
                ChargeBean cb = gson.fromJson(result, ChargeBean.class);
                UpdataChargeUI(cb);
            }
        }

    }

    /*
     此处根据获取的数据对界面进行更新
     */
    private void UpdataChargeUI(ChargeBean cb) {
        float charge_station_vol, charge_station_elec, charge_station_power, charge_station_status;

        //充电站电压
        if (cb.getVolSign() > 0)
            charge_station_vol = (float) (cb.getVoltage() / (Math.pow(10, cb.getVolDecimals())));
        else
            charge_station_vol = 0 - (float) (cb.getVoltage() / (Math.pow(10, cb.getVolDecimals())));
        tv_charge_v.setText(charge_station_vol + "V");

        //充电站电流
        if (cb.getElSign() > 0)
            charge_station_elec = (float) (cb.getElectricity() / (Math.pow(10, cb.getElDecimals())));
        else
            charge_station_elec = 0 - (float) (cb.getElectricity() / (Math.pow(10, cb.getElDecimals())));
        tv_charge_elec.setText(charge_station_elec + "A");

        //充电站功率
        if (cb.getPoSign() > 0)
            charge_station_power = (float) (cb.getPower() / (Math.pow(10, cb.getPoDecimals())));
        else
            charge_station_power = 0 - (float) (cb.getPower() / (Math.pow(10, cb.getPoDecimals())));
        tv_charge_power.setText(charge_station_power + "W");

        //充电站状态
        if (cb.getStatus() == 0 || cb.getStatus() == 1) {
            tv_charge_status.setText("正常工作");
        }
        if (cb.getStatus() == 2) {
            tv_charge_status.setText("过载");
            tv_charge_status.setTextColor(Color.RED);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        Intent intent = new Intent(AgvChargeActivity.this, ChargeService.class);
        sendBroadcast(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent();
        intent.setAction("exit");
        sendBroadcast(intent);
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
