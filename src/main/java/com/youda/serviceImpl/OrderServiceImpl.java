package com.youda.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.youda.dao.OrderMapper;
import com.youda.model.Order;
import com.youda.request.api.GoogleRequest;
import com.youda.request.api.IOSPayRequest;
import com.youda.request.api.OrderRequest;
import com.youda.response.api.AttestationResponse;
import com.youda.response.api.IOSResponse;
import com.youda.response.api.OrderResponse;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.youda.dao.*;
import com.youda.model.*;
import com.youda.response.AliPayResponse;
import com.youda.response.ResponseStatusCode;
import com.youda.response.WeChatPayResponse;
import com.youda.service.GameChannelService;
import com.youda.service.GoogleOrderService;
import com.youda.util.*;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.youda.service.OrderService;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义订单服务接口实现类
 */

@Service
public class OrderServiceImpl implements OrderService {

    /*实现订单dao层的自动依赖注入*/
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    private GameChannelService channelService;

    /*实现游戏dao层的自动依赖注入*/
    @Autowired
    GameMapper gameMapper;

    /*实现用户dao层的自动依赖注入*/
    @Autowired
    UserMapper userMapper;

    /*实现支付宝配置*/
    @Autowired
    AliPayConfMapper aliPayConfMapper;

    /*实现微信支付的自动依赖注入*/
    @Autowired
    WeChatConfMapper weChatConfMapper;

    /*实现支付记录的自动依赖注入*/
    @Autowired
    PayRecordMapper payRecordMapper;

    /*实现TokenMapper的自动依赖注入*/
    @Autowired
    TokenMapper tokenMapper;

    /*实现ApplePayConfMapper的自动依赖注入*/
    @Autowired
    ApplePayConfMapper applePayConfMapper;

    /*实现GooglePayConfMapper的自动依赖注入*/
    @Autowired
    GooglePayConfMapper googlePayConfMapper;

    @Autowired
    GoogleOrderService googleOrderService;

    @Override
    public ResponseEntity createOrder(OrderRequest request,String userUseDevice) {
        if (request.getGameChannelId() == 21) {
            return null;
        } else {
            Order order = new Order();
            Order order_new = orderMapper.findByOtherOrderId(request.getOtherOrderId());
            if (order_new == null)
            {
                order.setOtherOrderId(request.getOtherOrderId());
                order.setOrderTotalAmount(request.getOrderTotalAmount());
                order.setCreateOrderTime(new Timestamp(System.currentTimeMillis()));
                order.setUserUseDevice(userUseDevice);
                GameChannel gameChannel = channelService.findByIds(request.getGameChannelId());
                if (gameChannel == null) {
                    return ResponseStatusCode.nullPointerError();
                }
                order.setGameId(gameChannel.getGameId());
                order.setGameChannelId(request.getGameChannelId());
                order.setOrderSubject(request.getOrderSubject());
                order.setUserId(request.getUserId());
                orderMapper.createOrder(order);
                if (order.getOrderId() == 0) {
                    return ResponseStatusCode.conflictError();
                }
                return ResponseStatusCode.postSuccess(new OrderResponse(order.getOrderId()));
            }
            else
            {
                return ResponseStatusCode.otherOrderIdAlreadyExists(order_new);
            }
        }
    }

    /*实现支付宝支付*/
    @Override
    public ResponseEntity alipay(Long orderId,String token,String gameChannelId) {

        Order order = orderMapper.findByOrderId(orderId);
        AliPayResponse aliPayResponse = new AliPayResponse();
        if (order == null) {
            /*需要给出提示,没有这个订单，需要重新创建*/
            return ResponseStatusCode.nullPointerError();
        } else {
            Game game = gameMapper.findByGameId(order.getGameId());
            User user = userMapper.findByUserId(order.getUserId());
            Token select_token = tokenMapper.findTokenByIds(order.getUserId(),Long.valueOf(gameChannelId));
            if (token.equals(select_token.getAccessToken()))
            {
                /*获取订单一些支付属性*/
                String subject = order.getOrderSubject();
                String total_amount = order.getOrderTotalAmount();
                String out_trade_no = order.getOtherOrderId();

                String gameName = game.getGameName();

                AliPayConf aliPayConf = aliPayConfMapper.findByAliPayGameChannelId(Long.valueOf(gameChannelId));
                if(aliPayConf==null)
                {
                /*获取支付宝配置的基本信息*/
                    String APP_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJzuFpK2ikT/cLzBMQS2G0VBDJM0vjHser7pV+AG5d2kfkSSzRgDMKyTiq871M8jQmEfVlZJNtgXcKdyV5bUhoCNQL4Tq3Jp8Ndo8oAQ/3NvSux794kkq6L2UhHwckJ5yoTb4bNzQYwkGXEmAal22+bZwsc6IVwNzk2TJ0H6VdpVAgMBAAECgYAoA9G/sUoKk/PkPYLJR8ImY5LYSl+hDUKzQX7FwhyE6rfDtocTc2TK7Ig1bJU0CDKZ30q9j8erTDbOi6pn7GMrKAzpF1nSMTjJgio03Kat9784YfI7tcT0YJjaGIsjNCeUiEhy/Hd1LxpExB1Dcet9Siy3USe4qXvzY7lXlkf9AQJBANCY+cWllFUJPwxg3kx77nrqlRBCodKuizcqZBJsZc3k/IDB8LX9UU3sljeNHJM9Ee/AU/fUzDLww4E/BsP0X5UCQQDAl2Nr/RylEw9cveOJDSstYFrVmWU+lZQN0Nq3StFcg/wEtV1H/ajOEHxn4/lYvLN2RcVTgIMm8lwxm1bWu9/BAkAUCU2cjX4E+QFkV/2iTRkoF1ZAHJZcnUVkBB9eoajZsRAL8hUD9hQULxByv4wqHGiXpdqq6HbAwd2VkY89zUBNAkEAl/wgms0RuPfUrMSx9qssws+Cf4RhkMUsJMcIg5OIqzEBRpn19mUovQ3nj3kqgqvQGGsxMRd+6NJkjUVgf2+eQQJAT1uJnT3N9h1O/FAhXrcg1f0tBswtCyvtcZNh3EStARDj2NluJwJiMMbgRZe12jfvfN6lmq0sUvwOT298H8W6qQ==";
                    String APP_ID = "2016080301699003";
                    String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi";

                    String CHARSET = "UTF-8";
                    String CALLBACK_URL = "http://testomsdk.xiaobalei.com:5555/web/u/Pqug-wBEDuehOBFnVc8";
                    String NOTIFY_URL = "http://testomsdk.xiaobalei.com:5555/web/u/Pqug-wBEDuehOBFnVc8";
                    String ALIPAY_GATEWAY = "https://openapi.alipay.com/gateway.do";

                    //实例化客户端
                    AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_GATEWAY, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA");
                    //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
                    AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
                    //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
                    AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
                    //SDK已经封装掉了公共参数，这里只需要传入业务参数
                    //此次只是参数展示，未进行字符串转义，实际情况下请转义
                    model.setBody(out_trade_no);
                    model.setSubject(subject);
                    model.setOutTradeNo(out_trade_no);
                    model.setTimeoutExpress("30m");
                    model.setTotalAmount(total_amount);
                    model.setProductCode("QUICK_MSECURITY_PAY");
                    request.setBizModel(model);
                    request.setNotifyUrl(NOTIFY_URL);
                    request.setReturnUrl(CALLBACK_URL);
                    try {
                        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
                        if(response.getBody()!=null) {

                            payRecordMapper.addPayRecord(PayResult.getPayRecord("0",user.getUserName(),total_amount,out_trade_no,"支付宝APP支付",Long.valueOf(gameChannelId),order.getUserUseDevice()));

                            aliPayResponse.setGoodName(subject);
                            aliPayResponse.setGoodPrice(total_amount);
                            aliPayResponse.setOtherOrderId(out_trade_no);
                            aliPayResponse.setOutTradeNo(out_trade_no);
                            aliPayResponse.setPayData(response.getBody());
                        }
                    } catch (AlipayApiException e) {
                        e.printStackTrace();
                    }
                    return ResponseStatusCode.postSuccess(aliPayResponse);
                }
                else
                {
                /*获取支付宝配置的基本信息*/
                    String APP_PRIVATE_KEY = aliPayConf.getAPP_PRIVATE_KEY();
                    String APP_ID = aliPayConf.getAPP_ID();
                    String ALIPAY_PUBLIC_KEY = aliPayConf.getALIPAY_PUBLIC_KEY();

                    String CHARSET = "UTF-8";
                    String CALLBACK_URL = aliPayConf.getCALLBACK_URL();
                    String NOTIFY_URL = aliPayConf.getNOTIFY_URL();
                    String ALIPAY_GATEWAY = "https://openapi.alipay.com/gateway.do";

                    //实例化客户端
                    AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA");
                    //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
                    AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
                    //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
                    AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
                    //SDK已经封装掉了公共参数，这里只需要传入业务参数
                    //此次只是参数展示，未进行字符串转义，实际情况下请转义
                    model.setBody(out_trade_no);
                    model.setSubject(subject);
                    model.setOutTradeNo(out_trade_no);
                    model.setTimeoutExpress("30m");
                    model.setTotalAmount(total_amount);
                    model.setProductCode("QUICK_MSECURITY_PAY");
                    request.setBizModel(model);
                    request.setNotifyUrl(NOTIFY_URL);
                    request.setReturnUrl(CALLBACK_URL);
                    try {
                        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
                        if(response.getBody()!=null) {

                            payRecordMapper.addPayRecord(PayResult.getPayRecord("0",String.valueOf(user.getUserId()),total_amount,out_trade_no,"支付宝APP支付",Long.valueOf(gameChannelId),order.getUserUseDevice()));

                            aliPayResponse.setGoodName(subject);
                            aliPayResponse.setGoodPrice(total_amount);
                            aliPayResponse.setOtherOrderId(out_trade_no);
                            aliPayResponse.setOutTradeNo(out_trade_no);
                            aliPayResponse.setPayData(response.getBody());
                        }
                    } catch (AlipayApiException e) {
                        e.printStackTrace();
                    }

                    return ResponseStatusCode.postSuccess(aliPayResponse);
                }
            }
            else
            {
                return ResponseStatusCode.nullPointerError();
            }
        }
    }

    /*实现支付宝H5支付*/
    @Override
    public void aliPhonePay(Long orderId, String token,String gameChannelId,HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        Order order = orderMapper.findByOrderId(orderId);
        AliPayResponse aliPayResponse = new AliPayResponse();
        if (order == null) {
            /*需要给出提示,没有这个订单，需要重新创建*/
            System.err.println("创建的订单为空");
        } else {
            Game game = gameMapper.findByGameId(order.getGameId());
            User user = userMapper.findByUserId(order.getUserId());
            /*获取订单一些支付属性*/
            String subject = order.getOrderSubject();
            String total_amount = order.getOrderTotalAmount();
            String out_trade_no = order.getOtherOrderId();
            String gameName = game.getGameName();

            AliPayConf aliPayConf = aliPayConfMapper.findByAliPayGameChannelId(Long.valueOf(gameChannelId));
            if (aliPayConf==null)
            {
                 /*获取支付宝配置的基本信息*/
                String APP_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJzuFpK2ikT/cLzBMQS2G0VBDJM0vjHser7pV+AG5d2kfkSSzRgDMKyTiq871M8jQmEfVlZJNtgXcKdyV5bUhoCNQL4Tq3Jp8Ndo8oAQ/3NvSux794kkq6L2UhHwckJ5yoTb4bNzQYwkGXEmAal22+bZwsc6IVwNzk2TJ0H6VdpVAgMBAAECgYAoA9G/sUoKk/PkPYLJR8ImY5LYSl+hDUKzQX7FwhyE6rfDtocTc2TK7Ig1bJU0CDKZ30q9j8erTDbOi6pn7GMrKAzpF1nSMTjJgio03Kat9784YfI7tcT0YJjaGIsjNCeUiEhy/Hd1LxpExB1Dcet9Siy3USe4qXvzY7lXlkf9AQJBANCY+cWllFUJPwxg3kx77nrqlRBCodKuizcqZBJsZc3k/IDB8LX9UU3sljeNHJM9Ee/AU/fUzDLww4E/BsP0X5UCQQDAl2Nr/RylEw9cveOJDSstYFrVmWU+lZQN0Nq3StFcg/wEtV1H/ajOEHxn4/lYvLN2RcVTgIMm8lwxm1bWu9/BAkAUCU2cjX4E+QFkV/2iTRkoF1ZAHJZcnUVkBB9eoajZsRAL8hUD9hQULxByv4wqHGiXpdqq6HbAwd2VkY89zUBNAkEAl/wgms0RuPfUrMSx9qssws+Cf4RhkMUsJMcIg5OIqzEBRpn19mUovQ3nj3kqgqvQGGsxMRd+6NJkjUVgf2+eQQJAT1uJnT3N9h1O/FAhXrcg1f0tBswtCyvtcZNh3EStARDj2NluJwJiMMbgRZe12jfvfN6lmq0sUvwOT298H8W6qQ==";;
                String APP_ID = "2016080301699003";
                String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi";
                String CHARSET = "UTF-8";

                String CALLBACK_URL = "http://testomsdk.xiaobalei.com:5555/web/u/Pqug-wBEDuehOBFnVc8";
                String NOTIFY_URL = "http://testomsdk.xiaobalei.com:5555/web/u/Pqug-wBEDuehOBFnVc8";
                String ALIPAY_GATEWAY = "https://openapi.alipay.com/gateway.do";
                String product_code = "QUICK_WAP_WAY";

                //实例化客户端
                AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_GATEWAY, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA");
                //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
                AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
                //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
                alipayRequest.setNotifyUrl(NOTIFY_URL);
                alipayRequest.setReturnUrl(CALLBACK_URL);
                alipayRequest.setBizContent("{" +
                        "    \"out_trade_no\":\"" + out_trade_no + "\"," +
                        "    \"product_code\":\"" + product_code + "\"," +
                        "    \"body\":\"" + subject + out_trade_no + "\"," +
                        "    \"total_amount\":\"" + total_amount + "\"," +
                        "    \"subject\":\"" + subject + "\"" +
                        "  }");
                String form = "";
                try {
                    form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
                    payRecordMapper.addPayRecord(PayResult.getPayRecord("0",String.valueOf(user.getUserId()),total_amount,out_trade_no,"支付宝H5支付",Long.valueOf(gameChannelId),order.getUserUseDevice()));

                    aliPayResponse.setGoodName(subject);
                    aliPayResponse.setGoodPrice(total_amount);
                    aliPayResponse.setOtherOrderId(out_trade_no);
                    aliPayResponse.setOutTradeNo(out_trade_no);
                    /*aliPayResponse.setPayData((String) httpResponse.getOutputStream());*/
                } catch (AlipayApiException e) {
                    e.printStackTrace();
                }
                httpResponse.setContentType("text/html;charset=" + CHARSET);
                try {
                    httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
                    httpResponse.getWriter().flush();
                    httpResponse.getWriter().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                    /*获取支付宝配置的基本信息*/
                String APP_PRIVATE_KEY = aliPayConf.getAPP_PRIVATE_KEY();
                String APP_ID = aliPayConf.getAPP_ID();
                String ALIPAY_PUBLIC_KEY = aliPayConf.getALIPAY_PUBLIC_KEY();

                String CHARSET = "UTF-8";
                String CALLBACK_URL = aliPayConf.getCALLBACK_URL();
                String NOTIFY_URL = aliPayConf.getNOTIFY_URL();
                String ALIPAY_GATEWAY = "https://openapi.alipay.com/gateway.do";
                String product_code = "QUICK_WAP_WAY";

                //实例化客户端
                AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_GATEWAY, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA");
                //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
                AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
                //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
                alipayRequest.setNotifyUrl(NOTIFY_URL);
                alipayRequest.setReturnUrl(CALLBACK_URL);
                alipayRequest.setBizContent("{" +
                        "    \"out_trade_no\":\"" + out_trade_no + "\"," +
                        "    \"product_code\":\"" + product_code + "\"," +
                        "    \"body\":\"" + subject + out_trade_no + "\"," +
                        "    \"total_amount\":\"" + total_amount + "\"," +
                        "    \"subject\":\"" + subject + "\"" +
                        "  }");
                String form = "";
                try {
                    form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
                    payRecordMapper.addPayRecord(PayResult.getPayRecord("0",String.valueOf(user.getUserId()),total_amount,out_trade_no,"支付宝H5支付",Long.valueOf(gameChannelId),order.getUserUseDevice()));

                    aliPayResponse.setGoodName(subject);
                    aliPayResponse.setGoodPrice(total_amount);
                    aliPayResponse.setOtherOrderId(out_trade_no);
                    aliPayResponse.setOutTradeNo(out_trade_no);
                    /*aliPayResponse.setPayData((String) httpResponse.getOutputStream());*/
                } catch (AlipayApiException e) {
                    e.printStackTrace();
                }
                httpResponse.setContentType("text/html;charset=" + CHARSET);
                try {
                    httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
                    httpResponse.getWriter().flush();
                    httpResponse.getWriter().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*实现微信支付*/
    @Override
    public ResponseEntity wechatpay(Long orderId, String token,String gameChannelId,HttpServletRequest request, HttpServletResponse response) {
        Order order = orderMapper.findByOrderId(orderId);
        System.err.println("订单信息:"+order.getGameId());
        WeChatPayResponse weChatPayResponse = new WeChatPayResponse();
        if (order == null) {
            /*不存在这个订单,需要有提示去重新生成一个订单*/
            return ResponseStatusCode.nullPointerError();
        } else {
            Game game = gameMapper.findByGameId(order.getGameId());
            User user = userMapper.findByUserId(order.getUserId());
            String gameName = game.getGameName();
            /*获取微信支付配置信息*/
            WeChatConf weChatConf = weChatConfMapper.findWeChatConfByGameChannelId(Long.valueOf(gameChannelId));
            if (weChatConf == null) {
                return ResponseStatusCode.nullPointerError();
                //需要后台去添加响应的微信支付配置信息
            } else {
                //获取订单一些属性
                String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
                String BODY = order.getOrderSubject();
                String TOTAL_FEE = order.getOrderTotalAmount();
                String OUT_TRADE_NO = order.getOtherOrderId();
                String APP_ID = weChatConf.getAPP_ID();
                String APP_KEY = weChatConf.getAPP_KEY();
                String CALLBACK_URL = weChatConf.getCALLBACK_URL();
                String NOTIFY_URL = weChatConf.getNOTIFY_URL();
                String MCH_ID = weChatConf.getMCH_ID();
                String NONCE_STR = WXUtil.getNonceStr();
                String PARTNER_ID = weChatConf.getPARTNER_ID();
                String SPBILL_CREATE_IP = request.getRemoteAddr();
                String TIMESTAMP = WXUtil.getTimeStamp();
                PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
                prepayReqHandler.setParameter("appid", APP_ID);
                prepayReqHandler.setParameter("body", BODY);
                prepayReqHandler.setParameter("mch_id", MCH_ID);
                prepayReqHandler.setParameter("nonce_str", NONCE_STR);
                prepayReqHandler.setParameter("notify_url", NOTIFY_URL);
                prepayReqHandler.setParameter("out_trade_no", OUT_TRADE_NO);
                prepayReqHandler.setParameter("spbill_create_ip", "127.0.0.1");
                prepayReqHandler.setParameter("time_start", TIMESTAMP);
                prepayReqHandler.setParameter("total_fee", String.valueOf((int)(Float.valueOf(TOTAL_FEE)*100)));
                prepayReqHandler.setParameter("trade_type", "APP");
                /*生成签名*/
                String SIGN = prepayReqHandler.createMD5Sign(APP_KEY);
                prepayReqHandler.setParameter("sign", SIGN);
                prepayReqHandler.setGateUrl(GATEURL);
                try {
                    String prepayid = prepayReqHandler.sendPrepay();
                    if (prepayid != null && !prepayid.equals("")) {
                        String signs = "appid=" + APP_ID + "&noncestr=" + NONCE_STR + "&package=Sign=WXPay&partnerid="
                                + PARTNER_ID + "&prepayid=" + prepayid + "&timestamp=" + TIMESTAMP + "&key="
                                + APP_KEY;
                        //需要返回这些字段prepayid,sign,appid,timestamp,noncestr,package,partnerid,key

                        weChatPayResponse.setPrepayid(prepayid);
                        weChatPayResponse.setSign(MD5Util.MD5Encode(signs, "utf8").toUpperCase());
                        weChatPayResponse.setAppid(APP_ID);
                        weChatPayResponse.setTimestamp(TIMESTAMP);
                        weChatPayResponse.setNoncestr(NONCE_STR);
                        weChatPayResponse.setPackageName("Sign=WXPay");
                        weChatPayResponse.setPartnerid(PARTNER_ID);
                        weChatPayResponse.setKey(APP_KEY);
                        weChatPayResponse.setGoodName(BODY);
                        weChatPayResponse.setGoodPrice(TOTAL_FEE);
                        weChatPayResponse.setOtherOrderId(OUT_TRADE_NO);
                        /*生成支付记录*/
                        payRecordMapper.addPayRecord(PayResult.getPayRecord("0",String.valueOf(user.getUserId()),TOTAL_FEE,OUT_TRADE_NO,"微信APP支付",Long.valueOf(gameChannelId),order.getUserUseDevice()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return ResponseStatusCode.postSuccess(weChatPayResponse);
        }
    }

    /*实现支付宝支付验签以及通知第三方服务器地址*/
    @Override
    public ResponseEntity alipayAttestation(String token,String gameChannelId,HttpServletRequest request) {

        /*获取用户提交过来待验签的数据*/
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        /*获取订单信息*/
        String orderId = params.get("out_trade_no");
        /*通过订单号查询游戏*/
        Order order = orderMapper.findByOrderId(Long.valueOf(orderId));
        /*验证数据库中是否存在这个订单，从而获取对应的支付宝配置信息*/
        if (orderId == null || orderId.isEmpty() || order==null) {
            /*不存在这个订单*/
            return ResponseStatusCode.nullPointerError();
        } else {
            /*获取对应的游戏名称*/
            long gameId = order.getGameId();
            Game game = gameMapper.findByGameId(gameId);
            User user = userMapper.findByUserId(order.getUserId());
            String gameName = game.getGameName();
            AliPayConf aliPayConf = aliPayConfMapper.findByAliPayGameChannelId(Long.valueOf(gameChannelId));

            /*获取支付配置信息*/
            /*String APP_ID = aliPayConf.getAPP_ID();
            String APP_PRIVATE_KEY = aliPayConf.getAPP_PRIVATE_KEY();*/
            String CHARSET = "utf-8";
            String ALIPAY_PUBLIC_KEY = aliPayConf.getALIPAY_PUBLIC_KEY();
            /*String ALIPAY_GATEWAY = "https://openapi.alipay.com/gateway.do";*/

            /*验签过程*/
            boolean flag = false;
            try {
                flag = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, "RSA");
                if (flag) {
                    /*实现更新支付记录信息*/
                    PayRecord payRecord = payRecordMapper.findOutTradeNo(orderId);
                    if (payRecord != null)
                    {
                        payRecord.setPayRecordStatus("1");
                        payRecordMapper.modifyPayRecordInfo(payRecord);
                    }
                    else
                    {
                        payRecordMapper.addPayRecord(PayResult.getPayRecord("1",String.valueOf(user.getUserId()),order.getOrderTotalAmount(),orderId,"微信APP支付",Long.valueOf(gameChannelId),order.getUserUseDevice()));
                    }
                    /*实现通知第三方服务器*/
                    String notifyUrl = aliPayConf.getNOTIFY_URL();
                    String isPushed = order.getIsPushed();
                    if (isPushed.equals("") || isPushed.isEmpty() || isPushed == null) {
                        try {
                            PostData.sendData(notifyUrl,PayResult.getAttestationResponse(order.getOtherOrderId(),"10200",gameName, String.valueOf(user.getUserId()), order.getOrderTotalAmount()));
                            order.setIsPushed("1");
                            orderMapper.modifyByOrderId(order);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return ResponseStatusCode.putOrGetSuccess(PayResult.getAttestationResponse(order.getOtherOrderId(),"10200",gameName, String.valueOf(user.getUserId()), order.getOrderTotalAmount()));
                } else {
                    /*实现更新支付记录信息*/
                    PayRecord payRecord = payRecordMapper.findOutTradeNo(orderId);
                    if (payRecord != null)
                    {
                        payRecord.setPayRecordStatus("1");
                        payRecordMapper.modifyPayRecordInfo(payRecord);
                    }
                    else
                    {
                        payRecordMapper.addPayRecord(PayResult.getPayRecord("1",String.valueOf(user.getUserId()),order.getOrderTotalAmount(),orderId,"微信APP支付",Long.valueOf(gameChannelId),order.getUserUseDevice()));
                    }
                    /*实现通知第三方服务器*/
                    String notifyUrl = aliPayConf.getNOTIFY_URL();
                    String isPushed = order.getIsPushed();
                    if (isPushed == null|| isPushed.isEmpty() ) {
                        try {
                            PostData.sendData(notifyUrl,PayResult.getAttestationResponse(order.getOtherOrderId(),"10401",gameName, String.valueOf(user.getUserId()), order.getOrderTotalAmount()));
                            order.setIsPushed("1");
                            orderMapper.modifyByOrderId(order);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return ResponseStatusCode.putOrGetFailed(PayResult.getAttestationResponse(order.getOtherOrderId(),"10401",gameName, String.valueOf(user.getUserId()), order.getOrderTotalAmount()));
                }
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
        }
        return ResponseStatusCode.putOrGetSuccess(null);
    }

    /*实现微信支付验签以及统计第三方服务器地址*/
    @Override
    public ResponseEntity wechatAttestation(String token,String gameChannelId,HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        String orderId = request.getParameter("out_trade_no");
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");
        Map<String, String> map = null;
        try {
            /**
             * 解析微信通知返回的信息
             */
            map = XMLUtil.doXMLParse(result);
        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Order order = orderMapper.findByOrderId(Long.valueOf(orderId));
        /*获取游戏Id*/
        long gameId = order.getGameId();
        Game game = gameMapper.findByGameId(gameId);
        User user = userMapper.findByUserId(order.getUserId());
        String gameName = game.getGameName();
        WeChatConf weChatConf = weChatConfMapper.findWeChatConfByGameChannelId(Long.valueOf(gameChannelId));

        // 若支付成功，则告知微信服务器收到通知
        if (map.get("return_code").equals("SUCCESS")) {
            if (map.get("result_code").equals("SUCCESS")) {
                if (order == null) {
                    return ResponseStatusCode.nullPointerError();
                } else {
                    /*实现更新支付记录信息*/
                    PayRecord payRecord = payRecordMapper.findOutTradeNo(orderId);
                    if (payRecord != null)
                    {
                        payRecord.setPayRecordStatus("1");
                        payRecordMapper.modifyPayRecordInfo(payRecord);
                    }
                    else
                    {
                        payRecordMapper.addPayRecord(PayResult.getPayRecord("1",String.valueOf(user.getUserId()),order.getOrderTotalAmount(),orderId,"微信APP支付",Long.valueOf(gameChannelId),order.getUserUseDevice()));
                    }
                    /*实现通知第三方服务器*/
                    String notifyUrl = weChatConf.getNOTIFY_URL();
                    String isPushed = order.getIsPushed();
                    if (isPushed.equals("") || isPushed.isEmpty() || isPushed == null) {
                        try {
                            PostData.sendData(notifyUrl,PayResult.getAttestationResponse(order.getOtherOrderId(),"10200",gameName, String.valueOf(user.getUserId()), order.getOrderTotalAmount()));
                            order.setIsPushed("1");
                            orderMapper.modifyByOrderId(order);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return ResponseStatusCode.putOrGetSuccess(PayResult.getAttestationResponse(order.getOtherOrderId(),"10200",gameName, String.valueOf(user.getUserId()), order.getOrderTotalAmount()));
                }
            } else {
                /*实现更新支付记录信息*/
                PayRecord payRecord = payRecordMapper.findOutTradeNo(String.valueOf(orderId));
                if (payRecord != null)
                {
                    payRecord.setPayRecordStatus("0");
                    payRecordMapper.modifyPayRecordInfo(payRecord);
                }
                else
                {
                    payRecordMapper.addPayRecord(PayResult.getPayRecord("0",String.valueOf(user.getUserId()),order.getOrderTotalAmount(),String.valueOf(orderId),"微信APP支付",Long.valueOf(gameChannelId),order.getUserUseDevice()));
                }
                /*实现通知第三方服务器*/
                String notifyUrl = weChatConf.getNOTIFY_URL();
                String isPushed = order.getIsPushed();
                if (isPushed.equals("") || isPushed.isEmpty() || isPushed == null) {
                    try {
                        PostData.sendData(notifyUrl,PayResult.getAttestationResponse(order.getOtherOrderId(),"10401",gameName, String.valueOf(user.getUserId()), order.getOrderTotalAmount()));
                        order.setIsPushed("2");
                        orderMapper.modifyByOrderId(order);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return ResponseStatusCode.putOrGetFailed(PayResult.getAttestationResponse(order.getOtherOrderId(),"10401",gameName, String.valueOf(user.getUserId()), order.getOrderTotalAmount()));
            }
        }
        return null;
    }

    /*实现IOS上传凭证的过程*/
    @Async
    @Override
    public ResponseEntity iosUploadReceipt(IOSPayRequest request, Long orderId,String gameChannelId) {
        if (request.getReceipt()==null || request.getReceipt().isEmpty())
        {
            return ResponseStatusCode.uploadFailed();
        }
        else
        {
            System.out.println("调用之前");
            iosAttestation(request,orderId,gameChannelId);
            System.out.println(iosAttestation(request,orderId,gameChannelId));
            System.out.println("调用此处");
            return ResponseStatusCode.uploadSuccess();
        }
    }

    /**
     * @comment: iosSingleGame 实现IOS单击游戏验签
     * @param: [request, orderId, gameChannelId]
     * @return: org.springframework.http.ResponseEntity
     */
    @Override
    public ResponseEntity iosSingleGame(IOSPayRequest request, Long orderId, String gameChannelId) {
        Order order = orderMapper.findByOrderId(orderId);
        if (order == null) {
            return ResponseStatusCode.nullPointerError();
        }
        String url = "https://buy.itunes.apple.com/verifyReceipt";
        ApplePayConf applePayConf = applePayConfMapper.findByGameChannelId(Long.valueOf(gameChannelId));
        if (applePayConf == null) {
            return ResponseStatusCode.nullPointerError();
        }
        JSONObject js = new JSONObject();
        js.put("receipt-data", request.getReceipt());
        IOSResponse iosResult = sendApple(url, js.toString(), order, gameChannelId);
        if (iosResult.getStatus().equals("0")) {
            return ResponseStatusCode.putOrGetSuccess(iosResult);
        }
        else {
            return ResponseStatusCode.postOrGetFailed(iosResult);
        }
    }

    /**
     * @comment: sendApple 发送苹果验签
     * @param: [url, json]
     * @return: com.youda.response.api.IOSResponse
     */
    private IOSResponse sendApple(String url, String json, Order order,String gameChannelId) {
        User user = userMapper.findByUserId(order.getUserId());
        String sandbox = "https://sandbox.itunes.apple.com/verifyReceipt";
        String data = new RestTemplate().postForObject(url, json, String.class);
        IOSResponse iosResult = JSONObject.parseObject(data, IOSResponse.class);
        switch (iosResult.getStatus()) {
            case "0":
                JSONObject realReuslt = JSONObject.parseObject(data);
                JSONObject getReceipt = JSONObject.parseObject(realReuslt.getString("receipt").toString());
                JSONArray jsonArray = getReceipt.getJSONArray("in_app");
                /*System.out.println("Json数组"+jsonArray);*/
                JSONObject jsonObject = JSONObject.parseObject(jsonArray.getJSONObject(0).toString());
                /*System.out.println("transaction_id："+jsonObject.getString("transaction_id"));*/
                String transaction_id = jsonObject.getString("transaction_id").toString();
                JSONObject jsonData = JSONObject.parseObject(json);
                if (payRecordMapper.findByPayRecordOrderId(transaction_id) == null) {
                    PayRecord payRecord = payRecordMapper.findOutTradeNo(String.valueOf(order.getOrderId()));
                    if (payRecord != null)
                    {
                        payRecord.setPayRecordStatus("1");
                        payRecord.setPayRecordOrderId(transaction_id);
                        payRecord.setPayRecordStyle(jsonData.getString("receipt-data"));
                        payRecordMapper.modifyPayRecordInfo(payRecord);
                    }
                    else
                    {
                        payRecordMapper.addPayRecord(PayResult.setPayRecord("1",String.valueOf(user.getUserId()),order.getOrderTotalAmount(),transaction_id,String.valueOf(order.getOrderId()),jsonData.getString("receipt-data"),Long.valueOf(gameChannelId),order.getUserUseDevice()));
                    }
                    return iosResult;
                } else {
                    return new IOSResponse("1401");
                }
            case "21007":
                IOSResponse iosSandboxResult = sendApple(sandbox, json, order, gameChannelId);
                if (iosSandboxResult.getStatus().equals("0")) {
                    PayRecord payRecord = payRecordMapper.findOutTradeNo(String.valueOf(order.getOrderId()));
                    if (payRecord != null)
                    {
                        /*System.out.println("沙箱环境");*/
                        payRecord.setPayRecordStatus("1");
                        payRecordMapper.modifyPayRecordInfo(payRecord);
                    }
                    else
                    {
                        payRecordMapper.addPayRecord(PayResult.getPayRecord("1",String.valueOf(user.getUserId()),order.getOrderTotalAmount(),String.valueOf(order.getOrderId()),json,Long.valueOf(gameChannelId),order.getUserUseDevice()));
                    }
                }
                return iosSandboxResult;
            default:
                return null;
        }
    }

    /*实现苹果内购支付的验签,需要注意的是内购支付的时候要随意切换*/
    @Async
    @Override
    public ResponseEntity iosAttestation(IOSPayRequest request, Long orderId,String gameChannelId) {

        /*这是测试沙箱环境，url:"https://sandbox.itunes.apple.com/verifyReceipt",正式生产环境url:"https://buy.itunes.apple.com/verifyReceiptw"*/
        String  url = "https://buy.itunes.apple.com/verifyReceipt";
        Order order = orderMapper.findByOrderId(orderId);
        if (order==null)
        {
            return ResponseStatusCode.nullPointerError();
        }
        else
        {
            Game game = gameMapper.findByGameId(order.getGameId());
            User user = userMapper.findByUserId(order.getUserId());
            ApplePayConf applePayConf = applePayConfMapper.findByGameChannelId(Long.valueOf(gameChannelId));
            System.out.println("通知地址:"+applePayConf.getNotifyUrl());
            if(applePayConf==null)
            {
                return ResponseStatusCode.nullPointerError();
            }
            try {
                HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setAllowUserInteraction(false);
                PrintStream ps = new PrintStream(connection.getOutputStream());
                ps.print("{\"receipt-data\": \"" + request.getReceipt() + "\"}");
                ps.close();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String str;
                StringBuffer sb = new StringBuffer();
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
                br.close();
                String resultStr = sb.toString();
                JSONObject result = JSONObject.parseObject(resultStr);
                JSONObject result1 = JSONObject.parseObject(result.getString("receipt"));
                System.out.println("返回结果是:"+result);
                System.out.println("返回状态码:"+result.getInteger("status"));
                if (result1 == null) {
                    System.out.println("验证苹果支付失败");
                    if (result != null && result.getInteger("status") == 21007) {
                        String sandBoxUrl1 = "https://sandbox.itunes.apple.com/verifyReceipt";
                        String iosSandboxResult1 = PostData.sendRequest(sandBoxUrl1,request.getReceipt());
                        JSONObject finalSandboxResult = JSONObject.parseObject(iosSandboxResult1);
                        if (finalSandboxResult != null && finalSandboxResult.getInteger("status") == 0) {
                            System.out.println("进入沙箱环境进行保存支付记录");
                            String isPushed = order.getIsPushed();
                            //通知第三方服务器支付情况，支付成功，通知发货
                            if (isPushed == null || isPushed.equals("") || isPushed.isEmpty()) {
                                try {
                                    PayRecord payRecord = payRecordMapper.findOutTradeNo(String.valueOf(orderId));
                                    if (payRecord != null)
                                    {
                                        payRecord.setPayRecordStatus("1");
                                        payRecordMapper.modifyPayRecordInfo(payRecord);
                                    }
                                    else
                                    {
                                        payRecordMapper.addPayRecord(PayResult.getPayRecord("1",String.valueOf(user.getUserId()),order.getOrderTotalAmount(),String.valueOf(orderId),request.getReceipt(),Long.valueOf(gameChannelId),order.getUserUseDevice()));
                                    }
                                    /*实现通知第三方服务器*/
                                    PostData.sendData(applePayConf.getNotifyUrl(),PayResult.getAttestationResponse(String.valueOf(order.getOtherOrderId()),"10200",game.getGameName(),String.valueOf(user.getUserId()),order.getOrderTotalAmount()));
                                    order.setIsPushed("1");
                                    System.out.println("Apple沙箱支付通知第三方:\nurl:"+applePayConf.getNotifyUrl()+"\noutTradeNo:"+String.valueOf(order.getOtherOrderId())+"\nresult:"+"10200"+"\ngoodName:"+game.getGameName()+"\nuserId:"+String.valueOf(user.getUserId())+"\ntotalAmount:"+order.getOrderTotalAmount());
                                    orderMapper.modifyByOrderId(order);
                                    return ResponseStatusCode.putOrGetSuccess(PayResult.getAttestationResponse(String.valueOf(order.getOtherOrderId()),"10200",game.getGameName(), String.valueOf(user.getUserId()), order.getOrderTotalAmount()));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } else {
                    JSONArray jsonArray = result1.getJSONArray("in_app");
                    JSONObject jsonObject = JSONObject.parseObject(jsonArray.getJSONObject(0).toString());
                    System.out.println("transaction_id："+jsonObject.getString("transaction_id"));
                    String transaction_id = jsonObject.getString("transaction_id").toString();
                    System.out.println("查询出来的结果:"+ transaction_id);
                    if (payRecordMapper.findByPayRecordOrderId(transaction_id) == null) {
                        /*沙箱环境，返回码是21007，正式生产环境是21008*/
                        if (result != null && result.getInteger("status") == 21007) {
                            String sandBoxUrl = "https://sandbox.itunes.apple.com/verifyReceipt";
                            String iosSandboxResult = PostData.sendRequest(sandBoxUrl,request.getReceipt());
                            JSONObject finalSandboxResult = JSONObject.parseObject(iosSandboxResult);
                            System.out.println("沙箱环境验证:"+finalSandboxResult);
                            if (finalSandboxResult != null && finalSandboxResult.getInteger("status") == 0) {
                                System.out.println("进入沙箱环境进行保存支付记录");
                                String isPushed = order.getIsPushed();
                                //通知第三方服务器支付情况，支付成功，通知发货
                                if (isPushed == null || isPushed.equals("") || isPushed.isEmpty()) {
                                    try {
                                        PayRecord payRecord = payRecordMapper.findOutTradeNo(String.valueOf(orderId));
                                        if (payRecord != null)
                                        {
                                            payRecord.setPayRecordStatus("1");
                                            payRecordMapper.modifyPayRecordInfo(payRecord);
                                        }
                                        else
                                        {
                                            payRecordMapper.addPayRecord(PayResult.getPayRecord("1",String.valueOf(user.getUserId()),order.getOrderTotalAmount(),String.valueOf(orderId),request.getReceipt(),Long.valueOf(gameChannelId),order.getUserUseDevice()));
                                        }
                                        /*实现通知第三方服务器*/
                                        PostData.sendData(applePayConf.getNotifyUrl(),PayResult.getAttestationResponse(String.valueOf(order.getOtherOrderId()),"10200",game.getGameName(),String.valueOf(user.getUserId()),order.getOrderTotalAmount()));
                                        System.out.println("Apple沙箱支付通知第三方:\nurl:"+applePayConf.getNotifyUrl()+"\noutTradeNo:"+String.valueOf(order.getOtherOrderId())+"\nresult:"+"10200"+"\ngoodName:"+game.getGameName()+"\nuserId:"+String.valueOf(user.getUserId())+"\ntotalAmount:"+order.getOrderTotalAmount());
                                        order.setIsPushed("1");
                                        orderMapper.modifyByOrderId(order);
                                        return ResponseStatusCode.putOrGetSuccess(PayResult.getAttestationResponse(String.valueOf(order.getOtherOrderId()),"10200",game.getGameName(), String.valueOf(user.getUserId()), order.getOrderTotalAmount()));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else if (result != null && result.getInteger("status") == 21000) {
                            System.out.println("App Store不能读取你提供的JSON对象");
                        } else if (result != null && result.getInteger("status") == 21002) {
                            System.out.println("receipt-data域的数据有问题");
                        } else if (result != null && result.getInteger("status") == 21003) {
                            System.out.println("receipt无法通过验证");
                        } else if (result != null && result.getInteger("status") == 21004) {
                            System.out.println("提供的shared secret不匹配你账号中的shared secret");
                        } else if (result != null && result.getInteger("status") == 21005) {
                            System.out.println("receipt服务器当前不可用");
                        } else if (result != null && result.getInteger("status") == 21006) {
                            System.out.println("receipt合法，但是订阅已过期。服务器接收到这个状态码时，receipt数据仍然会解码并一起发送 ");
                        } else if (result != null && result.getInteger("status") == 0) {
                            String iosProdResult = PostData.sendRequest(url,request.getReceipt());
                            JSONObject finalProdReusult = JSONObject.parseObject(iosProdResult);
                            System.out.println("正式环境验证:"+finalProdReusult);
                            if (finalProdReusult != null && finalProdReusult.getInteger("status") == 0) {
                                System.out.println("进入正式环境进行保存支付记录");
                                String isPushed = order.getIsPushed();
                                //通知第三方服务器支付情况，支付成功，通知发货
                                if (isPushed == null || isPushed.equals("") || isPushed.isEmpty()) {
                                    try {
                                        PayRecord payRecord = payRecordMapper.findOutTradeNo(String.valueOf(orderId));
                                        if (payRecord != null)
                                        {
                                            payRecord.setPayRecordStatus("1");
                                            payRecordMapper.modifyPayRecordInfo(payRecord);
                                        }
                                        else
                                        {
                                            payRecordMapper.addPayRecord(PayResult.setPayRecord("1",String.valueOf(user.getUserId()),order.getOrderTotalAmount(),transaction_id,String.valueOf(orderId),request.getReceipt(),Long.valueOf(gameChannelId),order.getUserUseDevice()));
                                        }
                                        /*实现通知第三方服务器*/
                                        PostData.sendData(applePayConf.getNotifyUrl(),PayResult.getAttestationResponse(String.valueOf(order.getOtherOrderId()),"10200",game.getGameName(),String.valueOf(user.getUserId()),order.getOrderTotalAmount()));
                                        System.out.println("Apple正式支付通知第三方:\nurl:"+applePayConf.getNotifyUrl()+"\noutTradeNo:"+String.valueOf(order.getOtherOrderId())+"\nresult:"+"10200"+"\ngoodName:"+game.getGameName()+"\nuserId:"+String.valueOf(user.getUserId())+"\ntotalAmount:"+order.getOrderTotalAmount());
                                        order.setIsPushed("1");
                                        orderMapper.modifyByOrderId(order);
                                        return ResponseStatusCode.putOrGetSuccess(PayResult.getAttestationResponse(String.valueOf(order.getOtherOrderId()),"10200",game.getGameName(), String.valueOf(user.getUserId()), order.getOrderTotalAmount()));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    else {
                        System.out.println("已经验签过了，防止刷单");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ResponseEntity googleAttestation(GoogleRequest request, Long orderId, String gameChannelId) {
        GooglePayConf googlePayConf = googlePayConfMapper.findByGameChannelId(Long.valueOf(gameChannelId));
        Order order = orderMapper.findByOrderId(orderId);
        if (order == null) {
            return ResponseStatusCode.nullPointerError();
        }

        String js = request.getSignedData().replace("\\","");
        GoogleOrder googleOrder = (GoogleOrder) JSON.parseObject(js, GoogleOrder.class);

        Game game = gameMapper.findByGameId(order.getGameId());
        User user = userMapper.findByUserId(order.getUserId());

        GoogleOrderService.PayStatus payStatus = googleOrderService.isPay(googleOrder, true);
        if (payStatus == GoogleOrderService.PayStatus.SUCCESS || (payStatus == GoogleOrderService.PayStatus.NO_SUPPORT &&
                Security.verifyPurchase(googlePayConf.getSignNature(), request.getSignedData(), request.getSignature())
        )) {
            String isPushed = order.getIsPushed();
            if (isPushed == null || isPushed.equals("") || isPushed.isEmpty()) {
                /*实现更新支付记录信息*/
                PayRecord payRecord = payRecordMapper.findOutTradeNo(String.valueOf(orderId));
                if (payRecord != null) {
                    payRecord.setPayRecordStatus("1");
                    payRecordMapper.modifyPayRecordInfo(payRecord);
                } else {
                    payRecordMapper.addPayRecord(PayResult.getPayRecord("1", String.valueOf(user.getUserId()), order.getOrderTotalAmount(), String.valueOf(orderId), "Google内购支付", Long.valueOf(gameChannelId), order.getUserUseDevice()));
                }
                /*实现通知第三方服务器*/
                PostData.sendData(googlePayConf.getNotifyUrl(), PayResult.getAttestationResponse(String.valueOf(order.getOtherOrderId()), "10200", game.getGameName(), String.valueOf(user.getUserId()), order.getOrderTotalAmount()));
                order.setIsPushed("1");
                orderMapper.modifyByOrderId(order);
                return ResponseStatusCode.putOrGetSuccess(PayResult.getAttestationResponse(String.valueOf(order.getOtherOrderId()),"10200",game.getGameName(), String.valueOf(user.getUserId()), order.getOrderTotalAmount()));
            }
        }
        return ResponseStatusCode.verifyError();
    }
}
