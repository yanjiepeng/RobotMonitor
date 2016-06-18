package com.zk.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/18.
 */
public class ChargeEntity {


    /**
     * battery_1 : 823
     * battery_2 : 778
     * elect_1 : 627
     * elect_1_dec : 1
     * elect_1_sign : 0
     * elect_2 : 464
     * elect_2_dec : 1
     * elect_2_sign : 1
     * power_1 : 513
     * power_1_dec : 2
     * power_1_sign : 0
     * power_2 : 508
     * power_2_dec : 2
     * power_2_sign : 0
     */

    private AgvChargeBean agvCharge;
    /**
     * chargeNodes : [{"electricity":29,"highChargeStatus":0,"highShortStatus":0,"impedance":5631,"lowChargeStatus":0,"lowShortStatus":0},{"electricity":29,"highChargeStatus":0,"highShortStatus":0,"impedance":5631,"lowChargeStatus":0,"lowShortStatus":0},{"electricity":29,"highChargeStatus":0,"highShortStatus":0,"impedance":5631,"lowChargeStatus":0,"lowShortStatus":0},{"electricity":29,"highChargeStatus":0,"highShortStatus":0,"impedance":5631,"lowChargeStatus":0,"lowShortStatus":0},{"electricity":29,"highChargeStatus":0,"highShortStatus":0,"impedance":5631,"lowChargeStatus":0,"lowShortStatus":0},{"electricity":29,"highChargeStatus":0,"highShortStatus":0,"impedance":5631,"lowChargeStatus":0,"lowShortStatus":0},{"electricity":29,"highChargeStatus":0,"highShortStatus":0,"impedance":5631,"lowChargeStatus":0,"lowShortStatus":0},{"electricity":29,"highChargeStatus":0,"highShortStatus":0,"impedance":5631,"lowChargeStatus":0,"lowShortStatus":0}]
     * elDecimals : 1
     * elSign : 0
     * electricity : 31
     * poDecimals : 3
     * poSign : 0
     * power : 16
     * rfid : 0
     * status : 192
     * volDecimals : 2
     * volSign : 0
     * voltage : 542
     */

    private ChargeBean charge;

    public AgvChargeBean getAgvCharge() {
        return agvCharge;
    }

    public void setAgvCharge(AgvChargeBean agvCharge) {
        this.agvCharge = agvCharge;
    }

    public ChargeBean getCharge() {
        return charge;
    }

    public void setCharge(ChargeBean charge) {
        this.charge = charge;
    }

    public static class AgvChargeBean {
        private int battery_1;
        private int battery_2;
        private int elect_1;
        private int elect_1_dec;
        private int elect_1_sign;
        private int elect_2;
        private int elect_2_dec;
        private int elect_2_sign;
        private int power_1;
        private int power_1_dec;
        private int power_1_sign;
        private int power_2;
        private int power_2_dec;
        private int power_2_sign;

        public int getBattery_1() {
            return battery_1;
        }

        public void setBattery_1(int battery_1) {
            this.battery_1 = battery_1;
        }

        public int getBattery_2() {
            return battery_2;
        }

        public void setBattery_2(int battery_2) {
            this.battery_2 = battery_2;
        }

        public int getElect_1() {
            return elect_1;
        }

        public void setElect_1(int elect_1) {
            this.elect_1 = elect_1;
        }

        public int getElect_1_dec() {
            return elect_1_dec;
        }

        public void setElect_1_dec(int elect_1_dec) {
            this.elect_1_dec = elect_1_dec;
        }

        public int getElect_1_sign() {
            return elect_1_sign;
        }

        public void setElect_1_sign(int elect_1_sign) {
            this.elect_1_sign = elect_1_sign;
        }

        public int getElect_2() {
            return elect_2;
        }

        public void setElect_2(int elect_2) {
            this.elect_2 = elect_2;
        }

        public int getElect_2_dec() {
            return elect_2_dec;
        }

        public void setElect_2_dec(int elect_2_dec) {
            this.elect_2_dec = elect_2_dec;
        }

        public int getElect_2_sign() {
            return elect_2_sign;
        }

        public void setElect_2_sign(int elect_2_sign) {
            this.elect_2_sign = elect_2_sign;
        }

        public int getPower_1() {
            return power_1;
        }

        public void setPower_1(int power_1) {
            this.power_1 = power_1;
        }

        public int getPower_1_dec() {
            return power_1_dec;
        }

        public void setPower_1_dec(int power_1_dec) {
            this.power_1_dec = power_1_dec;
        }

        public int getPower_1_sign() {
            return power_1_sign;
        }

        public void setPower_1_sign(int power_1_sign) {
            this.power_1_sign = power_1_sign;
        }

        public int getPower_2() {
            return power_2;
        }

        public void setPower_2(int power_2) {
            this.power_2 = power_2;
        }

        public int getPower_2_dec() {
            return power_2_dec;
        }

        public void setPower_2_dec(int power_2_dec) {
            this.power_2_dec = power_2_dec;
        }

        public int getPower_2_sign() {
            return power_2_sign;
        }

        public void setPower_2_sign(int power_2_sign) {
            this.power_2_sign = power_2_sign;
        }
    }

    public static class ChargeBean {
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
         * electricity : 29
         * highChargeStatus : 0
         * highShortStatus : 0
         * impedance : 5631
         * lowChargeStatus : 0
         * lowShortStatus : 0
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
            private int electricity;
            private int highChargeStatus;
            private int highShortStatus;
            private int impedance;
            private int lowChargeStatus;
            private int lowShortStatus;

            public int getElectricity() {
                return electricity;
            }

            public void setElectricity(int electricity) {
                this.electricity = electricity;
            }

            public int getHighChargeStatus() {
                return highChargeStatus;
            }

            public void setHighChargeStatus(int highChargeStatus) {
                this.highChargeStatus = highChargeStatus;
            }

            public int getHighShortStatus() {
                return highShortStatus;
            }

            public void setHighShortStatus(int highShortStatus) {
                this.highShortStatus = highShortStatus;
            }

            public int getImpedance() {
                return impedance;
            }

            public void setImpedance(int impedance) {
                this.impedance = impedance;
            }

            public int getLowChargeStatus() {
                return lowChargeStatus;
            }

            public void setLowChargeStatus(int lowChargeStatus) {
                this.lowChargeStatus = lowChargeStatus;
            }

            public int getLowShortStatus() {
                return lowShortStatus;
            }

            public void setLowShortStatus(int lowShortStatus) {
                this.lowShortStatus = lowShortStatus;
            }
        }
    }
}
