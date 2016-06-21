package com.zk.eventBus;

/**
 * Created by Administrator on 2016/6/20.
 */
public class EventP {
    char[] status = new char[15];

    public EventP(char[] status) {
        this.status = status;
    }

    public char[] getStatus() {
        return status;
    }
}
