package com.youda.response.admin;

/**
 * @Author Chencongye
 * @Date 2017/12/15 11:34
 * @Version 1.0.0
 * @Instructions 实现返回给客户端的数据内容
 */
public class PayStatisticsResponse {

    /*定义返回的日期类型*/
    private String ddate;

    /*定义支付的用户总数*/
    private String payCount;

    /*定义支付的金额总数*/
    private String payMoneyTotal;

    /*定义支付的用户*/
    private String payUser;

    /*实现*/
    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public String getPayCount() {
        return payCount;
    }

    public void setPayCount(String payCount) {
        this.payCount = payCount;
    }

    public String getPayMoneyTotal() {
        return payMoneyTotal;
    }

    public void setPayMoneyTotal(String payMoneyTotal) {
        this.payMoneyTotal = payMoneyTotal;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }
}
