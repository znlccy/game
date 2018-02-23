package com.youda.response.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * @Author Chencongye
 * @Date 2017/12/20 10:28
 * @Version 1.0.0
 * @Instructions 定义支付验签返回给客户端
 */

public class AttestationResponse {

    /*定义返回给客户端支付验签结果的订单号*/
    private String outTradeNo;

    /*定义返回给客户端支付验签结果的时间*/
    private Date responseTime;

    /*定义返回给客户端支付验签结果*/
    private String result;

    /*定义返回给客户端支付验签的商品名*/
    private String goodName;

    /*返回用户主键*/
    private String userId;

    /*返回订单金额*/
    private String totalAmount;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    /*实现返回订单号的get方法*/
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /*实现返回订单号的set方法*/
    public Date getResponseTime() {
        return responseTime;
    }

    /*实现返回时间的get方法*/
    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    /*实现返回时间的set方法*/
    public String getResult() {
        return result;
    }

    /*实现返回结果的get方法*/
    public void setResult(String result) {
        this.result = result;
    }

    /*实现返回商品名的get方法*/
    public String getGoodName() {
        return goodName;
    }

    /*实现返回商品名的set方法*/
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    /*实现返回用户主键的get方法*/
    public String getUserId() {
        return userId;
    }

    /*实现返回用户主键的set方法*/
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /*实现返回用户订单金额的get方法*/
    public String getTotalAmount() {
        return totalAmount;
    }

    /*实现返回用户订单金额的set方法*/
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
