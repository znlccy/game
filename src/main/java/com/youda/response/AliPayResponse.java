package com.youda.response;

/**
 * @Author Chencongye
 * @Date 2017/12/13 15:27
 * @Version 1.0.0
 * @Instructions 实现返回支付宝支付返回的数据
 */
public class AliPayResponse {

    /*定义支付宝支付返回给客户端的第三方id*/
    private String otherOrderId;

    /*定义支付宝支付返回给客户端的商品名*/
    private String goodName;

    /*定义返回个客户端的商品价格*/
    private String goodPrice;

    /*定义返回给客户端的支付数据*/
    private String payData;

    /*实现返回给客户端订单编号*/
    private String outTradeNo;

    /*实现返回给客户端的第三方Id的get方法*/
    public String getOtherOrderId() {
        return otherOrderId;
    }

    /*实现返回给客户端第三方Id的set方法*/
    public void setOtherOrderId(String otherOrderId) {
        this.otherOrderId = otherOrderId;
    }

    /*实现返回给客户端商品名称的get方法*/
    public String getGoodName() {
        return goodName;
    }

    /*实现返回给客户端商品名称的set方法*/
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    /*实现返回给客户端商品价格的get方法*/
    public String getGoodPrice() {
        return goodPrice;
    }

    /*实现返回给客户端商品价格set方法*/
    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }

    /*实现返回给客户端支付数据的get方法*/
    public String getPayData() {
        return payData;
    }

    /*实现返回给客户端支付数据的set方法*/
    public void setPayData(String payData) {
        this.payData = payData;
    }

    /*实现返回给客户端的订单编号的get方法*/
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /*实现返回给客户端的订单编号的set方法*/
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
