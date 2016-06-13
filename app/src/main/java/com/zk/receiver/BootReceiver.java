package com.zk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.zk.robotmonitor.MainActivity;
import com.zk.utils.FormatUtil;

public class BootReceiver extends BroadcastReceiver {
    // 系统启动完成
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {     // boot
            Intent intent2 = new Intent(context, MainActivity.class);
//          intent2.setAction("android.intent.action.MAIN");
//          intent2.addCategory("android.intent.category.LAUNCHER");
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);

            sp = context.getSharedPreferences("time",Context.MODE_PRIVATE);
            editor = sp.edit();
            editor.putString("boot_time" , FormatUtil.refFormatNowDate());
            Log.w("存储开机时间",FormatUtil.refFormatNowDate());
            editor.commit();
        }
    }
}
