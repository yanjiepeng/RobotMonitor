package com.zk.eventBus;

/**
 * Created by Administrator on 2016/6/21.
 */
public class EventQR {
    int QRCode ;
    String msg;

    public EventQR( int QRCode , String msg) {
        this.msg = msg;
        this.QRCode = QRCode;
    }

    public int getQRCode() {
        return QRCode;
    }

    public String getMsg() {
        return msg;
    }
}
