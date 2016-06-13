package com.zk.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/2.
 */
public class FormatUtil {

    //保留两位小数 float
    public static float KeepPoint2(String s) {
        float a = Float.parseFloat(s);
        float b = (float) (Math.round(a * 100)) / 100;
        return b;
    }

    //将float保留两位
    public static float KeepPoint2(float arg) {
        float b = (float) (Math.round(arg * 100)) / 100;
        return b;
    }

    public static String refFormatNowDate() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        return retStrFormatNowDate;
    }

    public static String GettimePeriod(String time , Context context) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = "";

        try {
            Date d1 = df.parse(time);
            SharedPreferences sp = context.getSharedPreferences("time", Context.MODE_PRIVATE);
            String boot_time = sp.getString("boot_time","2016-06-13 08:30:00");
            Date d2 = df.parse(boot_time);
            long l = d1.getTime() - d2.getTime();
            long day=l/(24*60*60*1000);
            long hour=(l/(60*60*1000)-day*24);
            long min=((l/(60*1000))-day*24*60-hour*60);
            res = day+"天"+hour+"小时"+min+"分钟";

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  res;
    }




}
