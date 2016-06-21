package com.zk.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.zk.eventBus.EventP;
import com.zk.utils.Config;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdateProceedService extends Service {
    OkHttpClient client ;
    public UpdateProceedService() {
        client = new OkHttpClient();
        Timer timer = new Timer();
        timer.schedule(new ProceedTask() , 0 , 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    class ProceedTask extends TimerTask {

        @Override
        public void run() {
            RequestBody fromBody = new FormBody.Builder().add("req" , "1").build();
            Request request = new Request.Builder().url(Config.proceed_address).post(fromBody).build();

            /*
            响应回调
             */
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("tag" , "internet error" ) ;
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        char[] status = response.body().string().toCharArray();
                        EventBus.getDefault().post(new EventP(status));
                    }
                }
            });
        }
    }


}
