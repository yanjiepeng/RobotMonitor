package com.zk.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.zk.eventBus.EventAA;
import com.zk.utils.Config;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChargeService extends Service {

    private OkHttpClient client = new OkHttpClient();
    Timer timer = new Timer();

    public ChargeService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("exit");
        registerReceiver(stopReceiver, intentFilter);
        timer.schedule(new ChargeTask(), 0, 1000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
        unregisterReceiver(stopReceiver);
    }

    class ChargeTask extends TimerTask {

        @Override
        public void run() {
            enqueue();
        }
    }

    /*
            异步请求网络数据
     */

    private void enqueue() {
        Request request = new Request.Builder().url(Config.server_address2).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                EventBus.getDefault().post(new EventAA("error", EventAA.ACTION_SEND_CHARGE));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    EventBus.getDefault().post(new EventAA(result, EventAA.ACTION_SEND_CHARGE));
                }
            }
        });
    }


    /*
      停止服务
     */
    BroadcastReceiver stopReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            stopSelf();
            timer.cancel();
        }
    };


}
