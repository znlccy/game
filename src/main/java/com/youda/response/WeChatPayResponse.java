package com.youda.response;
/**
 * @Author Chencongye
 * @Date 2017/12/13 14:39
 * @Version 1.0.0
 * @Instructions 声明返回微信支付客户端的字段
 */
public class WeChatPayResponse {
    
    /*声明返回给客户端的prepayid*/
    private String prepayid;

    /*声明返回给客户端的签名*/
    private String sign;

    /*声明返回给客户端的appid*/
    private String appid;

    /*声明返回给客户端的时间戳*/
    private String timestamp;

    /*声明返回给客户端的随机字符串*/
    private String noncestr;

    /*声明返回给客户端的固定常量*/
    private String packageName;

    /*声明返回给客户端的合作者id*/
    private String partnerid;

    /*声明返回给客户端的appkey*/
    private String key;

    /*声明返回给客户端的第三方id*/
    private String otherOrderId;

    /*声明返回给客户端的商品名*/
    private String goodName;

    /*声明返回给客户端的商品价格*/
    private String goodPrice;

    /*实现返回给客户端的prepayid的get方法*/
    public String getPrepayid() {
        return prepayid;
    }

    /*实现返回给客户端的prepayid的set方法*/
    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    /*实现返回给客户端的sign的get方法*/
    public String getSign() {
        return sign;
    }

    /*实现返回给客户端的sign的set方法*/
    public void setSign(String sign) {
        this.sign = sign;
    }

    /*实现返回给客户端的appid的get方法*/
    public String getAppid() {
        return appid;
    }

    /*实现返回给客户端的appid的set方法*/
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /*实现返回给客户端的时间戳的get方法*/
    public String getTimestamp() {
        return timestamp;
    }

    /*实现返回给客户端的时间戳的set方法*/
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /*实现返回给客户端随机字符串的get方法*/
    public String getNoncestr() {
        return noncestr;
    }

    /*实现返回给客户端随机字符串的set方法*/
    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    /*实现返回给客户端固定的常量的get方法*/
    public String getPackageName() {
        return packageName;
    }

    /*实现返回给客户端固定常量的set方法*/
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /*实现返回给客户端合作者id的get方法*/
    public String getPartnerid() {
        return partnerid;
    }

    /*实现返回个客户端合作者id的set方法*/
    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    /*实现返回给客户端的appkey的get方法*/
    public String getKey() {
        return key;
    }

    /*实现返回给客户端的appkey的set方法*/
    public void setKey(String key) {
        this.key = key;
    }

    /*实现返回给客户端第三方订单id的get方法*/
    public String getOtherOrderId() {
        return otherOrderId;
    }

    /*实现返回给客户端第三方订单id的set方法*/
    public void setOtherOrderId(String otherOrderId) {
        this.otherOrderId = otherOrderId;
    }

    /*实现返回给客户端的商品名称的get方法*/
    public String getGoodName() {
        return goodName;
    }

    /*实现返回给客户端的商品名称的set方法*/
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    /*实现返回给客户端商品价格的get方法*/
    public String getGoodPrice() {
        return goodPrice;
    }

    /*实现返回给客户端商品价格的set方法*/
    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }
}
