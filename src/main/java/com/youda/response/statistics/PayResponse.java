package com.youda.response.statistics;

/**
 * @Author Chencongye
 * @Date 2017/12/15 11:34
 * @Version 1.0.0
 * @Instructions 实现返回给客户端的数据内容
 */

public class PayResponse {

    /*定义返回的日期类型*/
    private String ddate;

    /*定义支付的用户总数*/
    private String payCount;

    /*定义支付的金额总数*/
    /*private String payMoneyTotal;*/

    /*定义支付的用户*/
    /*private String payUser;*/

    /*实现支付日期的get方法*/
    public String getDdate() {
        return ddate;
    }

    /*实现支付日期的set方法*/
    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    /*实现支付多少人的统计的get方法*/
    public String getPayCount() {
        return payCount;
    }

    /*实现支付多少人的统计的set方法*/
    public void setPayCount(String payCount) {
        this.payCount = payCount;
    }

    /*实现支付金额的get方法*/
    /*public String getPayMoneyTotal() {
        return payMoneyTotal;
    }

    *//*实现支付金额的set方法*//*
    public void setPayMoneyTotal(String payMoneyTotal) {
        this.payMoneyTotal = payMoneyTotal;
    }*/

    /*实现支付用户的get方法*/
    /*public String getPayUser() {
        return payUser;
    }

    *//*实现支付用户的set的方法*//*
    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }*/
}
