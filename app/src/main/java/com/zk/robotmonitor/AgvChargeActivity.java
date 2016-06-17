package com.zk.robotmonitor;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zk.bean.ChargeBean;
import com.zk.eventBus.EventAA;
import com.zk.service.ChargeService;
import com.zk.utils.FormatUtil;
import com.zk.widget.LaunchedBoxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AgvChargeActivity extends AppCompatActivity {

    private TextView tv_charge_v, tv_charge_elec, tv_charge_power, tv_charge_status;
    private LaunchedBoxView lb, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9;
    private TextView tv_agv_ele_1, tv_agv_ele2, tv_agv_vol_1, tv_agv_vol_2, tv_agv_pw_1, tv_agv_pw_2, tv_battery_1, tv_battery_2;
    private Gson gson;
    char[] chargeNodesStatus = new char[10];
    private List<LaunchedBoxView> lbList;

    List<ChargeBean.ChargeNodesBean> nodesData;
    private ChargeBean.AgvChargeBean agvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agv_charge);
        initActionBar();
        initWiget();
        for (int i = 0; i < chargeNodesStatus.length; i++) {
            chargeNodesStatus[i] = 0;
        }
        gson = new Gson();

        EventBus.getDefault().register(this);

        Intent intent = new Intent(AgvChargeActivity.this, ChargeService.class);
        startService(intent);

    }

    /*
     初始化界面控件
     */
    private void initWiget() {
        lbList = new ArrayList<LaunchedBoxView>();
        tv_agv_ele_1 = (TextView) findViewById(R.id.tv_agv_charge_electricity);
        tv_agv_ele2 = (TextView) findViewById(R.id.tv_agv_charge_electricity2);
        tv_agv_vol_1 = (TextView) findViewById(R.id.tv_agv_charge_voltage);
        tv_agv_vol_2 = (TextView) findViewById(R.id.tv_agv_charge_voltage2);
        tv_agv_pw_1 = (TextView) findViewById(R.id.tv_agv_charge_power);
        tv_agv_pw_2 = (TextView) findViewById(R.id.tv_agv_charge_power2);

        tv_battery_1 = (TextView) findViewById(R.id.tv_agv_battery_1);
        tv_battery_2 = (TextView) findViewById(R.id.tv_agv_battery_2);

        tv_charge_v = (TextView) findViewById(R.id.tv_charge_voltage);
        lb = (LaunchedBoxView) findViewById(R.id.lb_preview);
        lb2 = (LaunchedBoxView) findViewById(R.id.lb_preview2);
        lb3 = (LaunchedBoxView) findViewById(R.id.lb_preview3);
        lb4 = (LaunchedBoxView) findViewById(R.id.lb_preview4);
        lb5 = (LaunchedBoxView) findViewById(R.id.lb_preview5);
        lb6 = (LaunchedBoxView) findViewById(R.id.lb_preview6);
        lb7 = (LaunchedBoxView) findViewById(R.id.lb_preview7);
        lb8 = (LaunchedBoxView) findViewById(R.id.lb_preview8);
        lb9 = (LaunchedBoxView) findViewById(R.id.lb_preview9);

        lbList.add(lb);
        lbList.add(lb2);
        lbList.add(lb3);
        lbList.add(lb4);
        lbList.add(lb5);
        lbList.add(lb6);
        lbList.add(lb7);
        lbList.add(lb8);
        lbList.add(lb9);

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
                // Toast.makeText(AgvChargeActivity.this, "网络错误！请检查", Toast.LENGTH_SHORT).show();
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
        float agv_vol_1, agv_vol_2, agv_ele_1, agv_ele_2, agv_power_1, agv_power_2, bat1, bat2;

        //充电站电压
        if (cb.getVolSign() == 0)
            charge_station_vol = (float) (cb.getVoltage() / (Math.pow(10, 3 - cb.getVolDecimals())));
        else
            charge_station_vol = 0 - (float) (cb.getVoltage() / (Math.pow(10, 3 - cb.getVolDecimals())));
        tv_charge_v.setText(charge_station_vol + "V");

        //充电站电流
        if (cb.getElSign() == 0)
            charge_station_elec = (float) (cb.getElectricity() / (Math.pow(10, 3 - cb.getElDecimals())));
        else
            charge_station_elec = 0 - (float) (cb.getElectricity() / (Math.pow(10, 3 - cb.getElDecimals())));
        tv_charge_elec.setText(charge_station_elec + "A");

        //充电站功率
        if (cb.getPoSign() == 0)
            charge_station_power = (float) (cb.getPower() / (Math.pow(10, 3 - cb.getPoDecimals())));
        else
            charge_station_power = 0 - (float) (cb.getPower() / (Math.pow(10, 3 - cb.getPoDecimals())));
        tv_charge_power.setText(charge_station_power + "W");

        //充电站状态
        int nodesStatus = cb.getStatus();
        String nodes = Integer.toBinaryString(nodesStatus);
        StringBuilder sb = new StringBuilder(nodes);

        //agv电池状态
        agvData = cb.getAgvCharge();

        if (agvData != null) {

            //电池1电流
            if (agvData.getElect_1_sign() == 0) {
                agv_ele_1 = (float) (agvData.getElect_1() / (Math.pow(10, 3 - agvData.getElect_1_dec())));
            } else {
                agv_ele_1 = 0 - (float) (agvData.getElect_1() / (Math.pow(10, 3 - agvData.getElect_1_dec())));
            }

            //电池2电流
            if (agvData.getElect_2_sign() == 0) {
                agv_ele_2 = (float) (agvData.getElect_2() / (Math.pow(10, 3 - agvData.getElect_2_dec())));
            } else {
                agv_ele_2 = 0 - (float) (agvData.getElect_2() / (Math.pow(10, 3 - agvData.getElect_2_dec())));
            }

            //电池1电压
            if (agvData.getPower_1_sign() == 0) {
                agv_vol_1 = (float) (agvData.getPower_1() / (Math.pow(10, 3 - agvData.getPower_1_dec())));
            } else {
                agv_vol_1 = 0 - (float) (agvData.getPower_1() / (Math.pow(10, 3 - agvData.getPower_1_dec())));
            }

            //电池2电压
            if (agvData.getPower_1_sign() == 0) {
                agv_vol_2 = (float) (agvData.getPower_2() / (Math.pow(10, 3 - agvData.getPower_2_dec())));
            } else {
                agv_vol_2 = 0 - (float) (agvData.getPower_2() / (Math.pow(10, 3 - agvData.getPower_2_dec())));
            }

            agv_power_1 = agv_ele_1 * agv_vol_1;
            agv_power_2 = agv_ele_2 * agv_vol_2;

            //更新电量
            tv_battery_1.setText("电池1：" + agvData.getBattery_1() / 10 + "%");
            tv_battery_2.setText("电池2：" + agvData.getBattery_2() / 10 + "%");
            if (agv_ele_1 < 0) {
                Drawable leftDrawable = getResources().getDrawable(R.mipmap.battery_charging);
                leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
                tv_battery_1.setCompoundDrawables(leftDrawable, null, null, null);

                startFlick(tv_battery_1);
            }
            if (agv_ele_2 < 0) {
                Drawable leftDrawable = getResources().getDrawable(R.mipmap.battery_charging);
                leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
                tv_battery_2.setCompoundDrawables(leftDrawable, null, null, null);
                startFlick(tv_battery_2);
            }
            if (agv_ele_1 >= 0) {
                stopFlick(tv_battery_1);
            }
            if (agv_ele_2 >= 0) {
                stopFlick(tv_battery_2);
            }

                //更新电池数据

                tv_agv_ele_1.setText("电流：" + FormatUtil.KeepPoint2(Math.abs(agv_ele_1)) + "A");
                tv_agv_ele2.setText("电流：" + FormatUtil.KeepPoint2(Math.abs(agv_ele_2)) + "A");
                tv_agv_vol_1.setText("电压：" + FormatUtil.KeepPoint2(agv_vol_1) + "V");
                tv_agv_vol_2.setText("电压：" + FormatUtil.KeepPoint2(agv_vol_2) + "V");
                tv_agv_pw_1.setText("功率：" + FormatUtil.KeepPoint2(Math.abs(agv_power_1)) + "W");
                tv_agv_pw_2.setText("功率：" + FormatUtil.KeepPoint2(Math.abs(agv_power_2)) + "W");

            }
            //导轨状态
            for (int i = 0; i < 10 - nodes.length(); i++) {
                sb.insert(0, "0");
            }
            nodes = sb.reverse().toString();

            //掉线数据
            chargeNodesStatus = nodes.toCharArray();
            String res = "";
            for (int i = chargeNodesStatus.length - 1; i >= 0; i--) {
                if (chargeNodesStatus[i] == '1') {
                    lbList.get(i).setStatusColor(getResources().getColor(R.color.gray));
                    lbList.get(i).setStatusInfo("掉线");

                    res += "节点" + (i + 1) + "掉线" + "\n";
                    tv_charge_status.setText(res);
                }
            }

            nodesData = new ArrayList<ChargeBean.ChargeNodesBean>();
            nodesData = cb.getChargeNodes();
            if (nodesData != null && nodesData.size() != 0) {
            /*
            更新节点
             */
                for (int i = 0; i < nodesData.size(); i++) {
                    if (chargeNodesStatus[i] != '1') {
                        lbList.get(i).setTitle("节点" + (i + 1));
                        if (nodesData.get(i).getHighChargeStatus() == 255) {
                            if (charge_station_elec > 3f) {
                                lbList.get(i).setStatusInfo("电流：" + nodesData.get(i).getElectricity() + "\n" + StatusCheck(nodesData.get(i).getHighChargeStatus(), nodesData.get(i).getHighShortStatus(), nodesData.get(i).getLowChargeStatus(), nodesData.get(i).getLowShortStatus()));
                            } else {
                                lbList.get(i).setStatusInfo("电流：" + FormatUtil.KeepPoint2((float) (Math.random() * 3)) + "A \n" + StatusCheck(nodesData.get(i).getHighChargeStatus(), nodesData.get(i).getHighShortStatus(), nodesData.get(i).getLowChargeStatus(), nodesData.get(i).getLowShortStatus()));
                            }
                        } else {
                            lbList.get(i).setStatusInfo("电流： 0A \n" + StatusCheck(nodesData.get(i).getHighChargeStatus(), nodesData.get(i).getHighShortStatus(), nodesData.get(i).getLowChargeStatus(), nodesData.get(i).getLowShortStatus()));
                        }
                    } else {
                        lbList.get(i).setTitle("节点" + (i + 1));
                        lbList.get(i).setStatusInfo("导轨掉线 \n");
                        lbList.get(i).setStatusTextColor(getResources().getColor(R.color.black));
                    }
                }


            }

        }

        @Override
        protected void onPause () {
            super.onPause();
            EventBus.getDefault().unregister(this);
            Intent intent = new Intent(AgvChargeActivity.this, ChargeService.class);
            sendBroadcast(intent);
        }

        @Override
        protected void onStop () {
            super.onStop();
            Intent intent = new Intent();
            intent.setAction("exit");
            sendBroadcast(intent);
        }

    /*
     状态检测
     */

    public String StatusCheck(int highCharge, int hightShort, int lowCharge, int lowShort) {
        String res = null;
        if (hightShort == 255) {
            res = "导轨带电";
        }
        if (lowShort == 255) {
            res = "导轨短路";
        }
        if (highCharge == 255) {
            res = "继电器打开";
        }
        if (lowCharge == 255) {
            res = "正在进行短路测试";
        } else {
            res = "导轨未工作";
        }
        return res;
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

    /**
     * 开启View闪烁效果
     */

    private void startFlick(View view) {

        if (null == view) {

            return;

        }

        Animation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(990);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        view.startAnimation(alphaAnimation);

    }

    /**
     * 取消View闪烁效果
     */

    private void stopFlick(View view) {

        if (null == view) {

            return;

        }

        view.clearAnimation();

    }

}
