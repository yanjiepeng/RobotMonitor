package com.zk.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.ip.IpParameters;
import com.zk.eventBus.EventAA;
import com.zk.utils.Config;
import com.zk.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

public class ZkModbusService extends Service {

    private boolean mConnected = false;
    private ModbusMaster master;
    private final static int SLAVE_ID = 24;
    private int PORT = 502;
    private int TIME_OUT = 5000;
    private int RETRY_TIME = 5;
    private String str = "";
    private Thread mConnectThread;

    public ZkModbusService() {
    }


    /*
    读板卡线程
     */
    Runnable readTask = new Runnable() {
        @Override
        public void run() {
            while (mConnected) {
                try {
                    String msg = Utils.imitateData(master, SLAVE_ID).toString();
                    Log.i("modbus MSG", msg);
                    Map<String, String> map = Utils.formatResult(msg);
                    if (!map.isEmpty() && (!str.equals(msg) || Utils.getServiceOnStart())) {
                        EventBus.getDefault().post(new EventAA(map, EventAA.ACTION_SEND_MSG));
                        Utils.setServiceOnStart(false);
                    }
                    str = msg;
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        connectIpThread();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Utils.setServiceOnStart(true);
        if (mConnectThread != null && !mConnectThread.isAlive()) {
            connectIpThread();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void connectIpThread() {
        if (!mConnected) {
            mConnectThread = new Thread() {
                @Override
                public void run() {
                    connectIP();
                }
            };
            mConnectThread.start();
        }
    }

    /*
     ModbusTcp连接
     */
    private void connectIP() {
        try {
            /*String cardIp= SPUtils.get(this, Config.CARD_IP,Config.STR_EMPTY).toString();
            if (cardIp==null||cardIp.isEmpty()){
                return;
            }*/
            IpParameters tcpParameters = new IpParameters();
            tcpParameters.setHost(Config.card_ip);
            tcpParameters.setPort(PORT);
            ModbusFactory modbusFactory = new ModbusFactory();

            master = modbusFactory.createTcpMaster(tcpParameters, true);
            master.setTimeout(TIME_OUT);
            master.setRetries(0);
            master.init();
            if (master.isInitialized()) {
                mConnected = true;
                new Thread(readTask).start();
            }
        } catch (Exception e) {
            master.destroy();
            e.printStackTrace();

            try {
                Thread.sleep(10 * 1000);
            } catch (Exception ex) {
            }
            connectIP();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        master.destroy();
        try {
            master.destroy();
        } catch (Exception e) {
        }
    }
}
