package com.youda.response.api;

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
}
