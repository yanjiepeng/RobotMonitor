package com.zk.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
public class ChargeBean {


    /**
     * chargeNodes : [{"chargeStatus":1,"electricity":200,"impedance":30,"shortStatus":2}]
     * elDecimals : 2
     * elSign : 2
     * electricity : 400
     * poDecimals : 10
     * poSign : 0
     * power : 50000
     * rfid : 0
     * status : 1
     * volDecimals : 3
     * volSign : 0
     * voltage : 100
     */

    private int elDecimals;
    private int elSign;
    private int electricity;
    private int poDecimals;
    private int poSign;
    private int power;
    private int rfid;
    private int status;
    private int volDecimals;
    private int volSign;
    private int voltage;
    /**
     * chargeStatus : 1
     * electricity : 200
     * impedance : 30
     * shortStatus : 2
     */

    private List<ChargeNodesBean> chargeNodes;

    public int getElDecimals() {
        return elDecimals;
    }

    public void setElDecimals(int elDecimals) {
        this.elDecimals = elDecimals;
    }

    public int getElSign() {
        return elSign;
    }

    public void setElSign(int elSign) {
        this.elSign = elSign;
    }

    public int getElectricity() {
        return electricity;
    }

    public void setElectricity(int electricity) {
        this.electricity = electricity;
    }

    public int getPoDecimals() {
        return poDecimals;
    }

    public void setPoDecimals(int poDecimals) {
        this.poDecimals = poDecimals;
    }

    public int getPoSign() {
        return poSign;
    }

    public void setPoSign(int poSign) {
        this.poSign = poSign;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getRfid() {
        return rfid;
    }

    public void setRfid(int rfid) {
        this.rfid = rfid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getVolDecimals() {
        return volDecimals;
    }

    public void setVolDecimals(int volDecimals) {
        this.volDecimals = volDecimals;
    }

    public int getVolSign() {
        return volSign;
    }

    public void setVolSign(int volSign) {
        this.volSign = volSign;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public List<ChargeNodesBean> getChargeNodes() {
        return chargeNodes;
    }

    public void setChargeNodes(List<ChargeNodesBean> chargeNodes) {
        this.chargeNodes = chargeNodes;
    }

    public static class ChargeNodesBean {
        private int chargeStatus;
        private int electricity;
        private int impedance;
        private int shortStatus;

        public int getChargeStatus() {
            return chargeStatus;
        }

        public void setChargeStatus(int chargeStatus) {
            this.chargeStatus = chargeStatus;
        }

        public int getElectricity() {
            return electricity;
        }

        public void setElectricity(int electricity) {
            this.electricity = electricity;
        }

        public int getImpedance() {
            return impedance;
        }

        public void setImpedance(int impedance) {
            this.impedance = impedance;
        }

        public int getShortStatus() {
            return shortStatus;
        }

        public void setShortStatus(int shortStatus) {
            this.shortStatus = shortStatus;
        }
    }

    @Override
    public String toString() {
        return "ChargeBean{" +
                "chargeNodes=" + chargeNodes +
                ", elDecimals=" + elDecimals +
                ", elSign=" + elSign +
                ", electricity=" + electricity +
                ", poDecimals=" + poDecimals +
                ", poSign=" + poSign +
                ", power=" + power +
                ", rfid=" + rfid +
                ", status=" + status +
                ", volDecimals=" + volDecimals +
                ", volSign=" + volSign +
                ", voltage=" + voltage +
                '}';
    }
}
