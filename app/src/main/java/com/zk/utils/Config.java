package com.zk.utils;


/**
 * 配置文件
 *
 * @author yanjiepeng
 */
public class Config {


    public static String card_ip = "192.168.1.102";
    public static String server_address = "http://192.168.1.153:8080/sshe/base/agv!notNeedSecurity_agvChargeStatus.sy" ;
    public static  int data_index = 0;
    /**
     * 板卡1
     */
    // 直流电压变送器1，2电压
    public static final String Voltage_1 = "voltage_1";
    public static final String Voltage_1_H = "voltage_1_h";
    public static final String Voltage_1_L = "voltage_1_l";
    public static final String Voltage_2 = "voltage_2";
    public static final String Voltage_2_H = "voltage_2_h";
    public static final String Voltage_2_L = "voltage_2_l";
    // 直流电流变送器1，2电流

    public static final String Electricity_1 = "electricity_1";
    public static final String Electricity_2 = "electricity_2";
    public static final String Electricity_1_H = "electricity_1_h";
    public static final String Electricity_1_L = "electricity_1_l";
    public static final String Electricity_2_H = "electricity_2_h";
    public static final String Electricity_2_L = "electricity_2_l";
    // 智能电表1参数

    // 三相电压
    public static final String Voltage_Va_1 = "voltage_va_1";
    public static final String Voltage_Va_1_H = "voltage_va_1_h";
    public static final String Voltage_Va_1_L = "voltage_va_1_l";
    public static final String Voltage_Vb_1 = "voltage_vb_1";
    public static final String Voltage_Vb_1_H = "voltage_vb_1_h";
    public static final String Voltage_Vb_1_L = "voltage_vb_1_l";
    public static final String Voltage_Vc_1 = "voltage_vc_1";
    public static final String Voltage_Vc_1_H = "voltage_vc_1_h";
    public static final String Voltage_Vc_1_L = "voltage_vc_1_L";
    // 相电压平均值
    public static final String Voltage_Avg_1 = "voltage_avg_1";
    public static final String Voltage_Avg_1_H = "voltage_avg_1_h";
    public static final String Voltage_Avg_1_L = "voltage_avg_1_l";
    // 三相电流
    public static final String Electricity_A_1 = "electricity_a_1";
    public static final String Electricity_A_1_H = "electricity_a_1_h";
    public static final String Electricity_A_1_L = "electricity_a_1_l";
    public static final String Electricity_B_1 = "electricity_b_1";
    public static final String Electricity_B_1_H = "electricity_b_1_h";
    public static final String Electricity_B_1_L = "electricity_b_1_L";
    public static final String Electricity_C_1 = "electricity_c_1";
    public static final String Electricity_C_1_H = "electricity_c_1_h";
    public static final String Electricity_C_1_L = "electricity_c_1_l";
    // 相电流平均值
    public static final String Electricity_Avg_1 = "electricity_avg_1";
    public static final String Electricity_Avg_1_H = "electricity_avg_1_h";
    public static final String Electricity_Avg_1_L = "electricity_avg_1_l";
    // 正,反向实功电能
    public static final String Energy_Positive_1 = "energy_positive_1";
    public static final String Energy_Positive_1_H = "energy_positive_1_h";
    public static final String Energy_Positive_1_L = "energy_positive_1_l";
    public static final String Energy_Reverse_1 = "energy_reverse_1";
    public static final String Energy_Reverse_1_H = "energy_reverse_1_h";
    public static final String Energy_Reverse_1_L = "energy_reverse_1_l";


    // 智能电表2参数

    // 三相电压
    public static final String Voltage_Va_2 = "voltage_va_2";
    public static final String Voltage_Va_2_H = "voltage_va_2_h";
    public static final String Voltage_Va_2_L = "voltage_va_2_l";
    public static final String Voltage_Vb_2 = "voltage_vb_2";
    public static final String Voltage_Vb_2_H = "voltage_vb_2_h";
    public static final String Voltage_Vb_2_L = "voltage_vb_2_l";
    public static final String Voltage_Vc_2 = "voltage_vc_2";
    public static final String Voltage_Vc_2_H = "voltage_vc_2_h";
    public static final String Voltage_Vc_2_L = "voltage_vc_2_L";
    // 相电压平均值
    public static final String Voltage_Avg_2 = "voltage_avg_2";
    public static final String Voltage_Avg_2_H = "voltage_avg_2_h";
    public static final String Voltage_Avg_2_L = "voltage_avg_2_l";
    // 三相电流
    public static final String Electricity_A_2 = "electricity_a_2";
    public static final String Electricity_A_2_H = "electricity_a_2_h";
    public static final String Electricity_A_2_L = "electricity_a_2_l";
    public static final String Electricity_B_2 = "electricity_b_2";
    public static final String Electricity_B_2_H = "electricity_b_2_h";
    public static final String Electricity_B_2_L = "electricity_b_2_L";
    public static final String Electricity_C_2 = "electricity_c_2";
    public static final String Electricity_C_2_H = "electricity_c_2_h";
    public static final String Electricity_C_2_L = "electricity_c_2_l";
    // 相电流平均值
    public static final String Electricity_Avg_2 = "electricity_avg_2";
    public static final String Electricity_Avg_2_H = "electricity_avg_2_h";
    public static final String Electricity_Avg_2_L = "electricity_avg_2_l";
    // 正,反向实功电能
    public static final String Energy_Positive_2 = "energy_positive_2";
    public static final String Energy_Positive_2_H = "energy_positive_2_h";
    public static final String Energy_Positive_2_L = "energy_positive_2_l";
    public static final String Energy_Reverse_2 = "energy_reverse_2";
    public static final String Energy_Reverse_2_H = "energy_reverse_2_h";
    public static final String Energy_Reverse_2_L = "energy_reverse_2_l";


    /**
     * 板卡2
     */
    // 气体流量计1
    //当前流量
    public static final String Current_Rate_1 = "current_rate_1";
    public static final String Current_Rate_1_H = "current_rate_1_h";
    public static final String Current_Rate_1_L = "current_rate_1_l";
    //总流量
    public static final String Total_Rate_1 = "total_rate_1";
    public static final String Total_Rate_1_H = "total_rate_1_h";
    public static final String Total_Rate_1_M = "total_rate_1_m";
    public static final String Total_Rate_1_L = "total_rate_1_l";

    // 气体流量计2
    //当前流量
    public static final String Current_Rate_2 = "current_rate_2";
    public static final String Current_Rate_2_H = "current_rate_2_h";
    public static final String Current_Rate_2_L = "current_rate_2_l";
    //总流量
    public static final String Total_Rate_2 = "total_rate_2";
    public static final String Total_Rate_2_H = "total_rate_2_h";
    public static final String Total_Rate_2_M = "total_rate_2_m";
    public static final String Total_Rate_2_L = "total_rate_2_l";

    // 送丝量 总长度 速度
    public static final String Total_length = "total_length";
    public static final String Total_length_H = "total_length_h";
    public static final String Total_length_L = "total_length_l";
    public static final String speed = "speed";
    public static final String speed_H = "speed_h";
    public static final String speed_L = "speed_l";


}
