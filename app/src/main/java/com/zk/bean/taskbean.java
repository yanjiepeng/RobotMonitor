package com.zk.bean;

/**
 * Created by Administrator on 2016/5/30.
 */
public class taskbean {

    String id;
    String name;
    String workzone;
    String status;
    String type;
    String time;
    String period;
    int qrCode;

    public taskbean() {
    }

    public taskbean(String id, String name, String period, int qrCode, String status, String time, String type, String workzone) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.qrCode = qrCode;
        this.status = status;
        this.time = time;
        this.type = type;
        this.workzone = workzone;
    }

    public taskbean(String id, String name, String period, String status, String time, String type, String workzone) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.status = status;
        this.time = time;
        this.type = type;
        this.workzone = workzone;
    }

    public taskbean(String name, String period, String status, String time, String type, String workzone) {
        this.name = name;
        this.period = period;
        this.status = status;
        this.time = time;
        this.type = type;
        this.workzone = workzone;
    }

    public int getQrCode() {
        return qrCode;
    }

    public void setQrCode(int qrCode) {
        this.qrCode = qrCode;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPeriod() {
        return period;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getWorkzone() {
        return workzone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWorkzone(String workzone) {
        this.workzone = workzone;
    }

    @Override
    public String toString() {
        return "taskbean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", workzone='" + workzone + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", time='" + time + '\'' +
                ", period='" + period + '\'' +
                '}';
    }
}
