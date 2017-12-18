package com.youda.response.admin;

public class DengesPayStatisticsResponse {

    /*定义支付日期*/
    private String ddate;

    /*定义支付金额*/
    private String payMoney;

    /*定义付费率*/
    private String payRate;

    /*实现支付日期的get方法*/
    public String getDdate() {
        return ddate;
    }

    /*实现支付日期的set方法*/
    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    /*实现支付金额的get方法*/
    public String getPayMoney() {
        return payMoney;
    }

    /*实现支付金额的set方法*/
    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    /*实现付费率的get方法*/
    public String getPayRate() {
        return payRate;
    }

    /*实现付给率的set方法*/
    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }
}
