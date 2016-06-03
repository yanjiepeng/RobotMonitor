package com.zk.utils;

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
}
