package com.youda.util;

/**
 * 把#####的内容填完即可
 * @author Administrator
 *
 */
public class ConstantUtil {
    /**
     * 微信开发平台应用ID
     */
    public static final String APP_ID="wx1b60b28c4f8d8908";
    /**
     * 应用对应的凭证
     */
    public static final String APP_SECRET="8b4fbeef4b1c78f8aa5c7e073e065904";
    /**
     * 应用对应的密钥
     */
    public static final String APP_KEY="343b1c4a3ea721b2d640fc8700db0f36";
    /**
     * 微信支付商户号
     */
    public static final String MCH_ID="1491013092";
    /**
     * 商品描述
     */
    public static final String BODY="QQ游戏-账户充值";
    
    /**
     * 商户号对应的密钥
     */
    public static final String PARTNER_KEY="343b1c4a3ea721b2d640fc8700db0f36";
    
    /**
     * 商户id，也就是商务登录微信公众平台的账号
     */
    public static final String PARTNER_ID="1491013092@1491013092";
    
    /**
     * 常量固定值
     */
    public static final String GRANT_TYPE="client_credential";
    /**
     * 获取预支付id的接口url
     */
    public static String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    
    /**
     * 微信服务器回调通知url
     */
    public static String NOTIFY_URL="http://www.weixin.qq.com/wxpay/pay.php";
}
