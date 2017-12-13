package com.youda.serviceImpl;

import com.youda.dao.OrderMapper;
import com.youda.model.Order;
import com.youda.request.api.OrderRequest;
import com.youda.response.api.OrderResponse;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.youda.dao.*;
import com.youda.model.*;
import com.youda.response.AliPayResponse;
import com.youda.response.ResponseStatusCode;
import com.youda.response.WeChatPayResponse;
import com.youda.util.MD5Util;
import com.youda.util.PrepayIdRequestHandler;
import com.youda.util.WXUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.youda.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.management.MemoryUsage;
import java.sql.Timestamp;

import static com.alipay.api.AlipayConstants.APP_ID;
import static com.alipay.api.AlipayConstants.CHARSET;

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

    @Override
    public ResponseEntity createOrder(OrderRequest request) {
        Order order = new Order();
        order.setOtherOrderId(request.getOtherOrderId());
        order.setOrderTotalAmount(request.getOrderTotalAmount());
        order.setCreateOrderTime(new Timestamp(System.currentTimeMillis()));
        long userId= 1;
        long gameId = 1001;
        orderMapper.createOrder(order);
        if (order.getOrderId() == 0) {
            return ResponseStatusCode.conflictError();
        }
        return ResponseStatusCode.postSuccess(new OrderResponse(order.getOrderId()));
    }

    /*实现支付宝支付*/
    @Override
    public ResponseEntity alipay(Long orderId) {
        Order order = orderMapper.findByOrderId(orderId);
        AliPayResponse aliPayResponse = new AliPayResponse();
        if (order==null)
        {
            /*需要给出提示,没有这个订单，需要重新创建*/
            return ResponseStatusCode.nullPointerError();
        }
        else
        {
            Game game = gameMapper.findByGameId(order.getGameId());
            User user = userMapper.findByUserId(order.getUserId());
            /*获取订单一些支付属性*/
            String subject = order.getOrderSubject();
            String total_amount = order.getOrderTotalAmount();
            String out_trade_no = order.getOtherOrderId();
            String gameName = game.getGameName();

            AliPayConf aliPayConf = aliPayConfMapper.findByAliPayGameName(gameName);
            /*获取支付宝配置的基本信息*/
            String APP_PRIVATE_KEY=aliPayConf.getAPP_PRIVATE_KEY();
            String APP_ID=aliPayConf.getAPP_ID();
            String ALIPAY_PUBLIC_KEY=aliPayConf.getALIPAY_PUBLIC_KEY();;
            String CHARSET="UTF-8";
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
            AlipayTradeAppPayResponse response = null;
            try {
                response = alipayClient.execute(request);
            } catch (AlipayApiException e) {
            }
            //调用成功，则处理业务逻辑
            if(response.isSuccess()){
                PayRecord payRecord = new PayRecord();
                payRecord.setPayRecordStyle("支付宝APP支付");
                payRecord.setOutTradeNo(out_trade_no);
                payRecord.setPayRecordTime(new Timestamp(System.currentTimeMillis()));
                payRecord.setPayRecordTotalAmount(total_amount);
                payRecord.setPayRecordUser(user.getUserName());
                payRecord.setPayRecordStatus("0");
                payRecordMapper.addPayRecord(payRecord);

                aliPayResponse.setGoodName(subject);
                aliPayResponse.setGoodPrice(total_amount);
                aliPayResponse.setOtherOrderId(out_trade_no);
                aliPayResponse.setOutTradeNo(out_trade_no);
                aliPayResponse.setPayData(response.getBody());
            }
            return ResponseStatusCode.postSuccess(aliPayResponse);
        }
    }

    /*实现微信支付*/
    @Override
    public ResponseEntity wechatpay(Long orderId, HttpServletRequest request, HttpServletResponse response) {
        Order order = orderMapper.findByOrderId(orderId);
        WeChatPayResponse weChatPayResponse = new WeChatPayResponse();
        if(order==null)
        {
            /*不存在这个订单,需要有提示去重新生成一个订单*/
            return ResponseStatusCode.nullPointerError();
        }
        else
        {
            Game game = gameMapper.findByGameId(order.getGameId());
            User user = userMapper.findByUserId(order.getUserId());
            String gameName = game.getGameName();
            /*获取微信支付配置信息*/
            WeChatConf weChatConf = weChatConfMapper.findWeChatConfByGameName(gameName);
            if (weChatConf==null) {
                return ResponseStatusCode.nullPointerError();
                //需要后台去添加响应的微信支付配置信息
            }
            else
            {
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
                PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request,response);
                prepayReqHandler.setParameter("appid",APP_ID);
                prepayReqHandler.setParameter("body",BODY);
                prepayReqHandler.setParameter("mch_id",MCH_ID);
                prepayReqHandler.setParameter("nonce_str",NONCE_STR);
                prepayReqHandler.setParameter("notify_url",NOTIFY_URL);
                prepayReqHandler.setParameter("out_trade_no",OUT_TRADE_NO);
                prepayReqHandler.setParameter("spbill_create_ip",SPBILL_CREATE_IP);
                prepayReqHandler.setParameter("time_start",TIMESTAMP);
                prepayReqHandler.setParameter("total_fee",TOTAL_FEE);
                prepayReqHandler.setParameter("trade_type","APP");
                /*生成签名*/
                String SIGN = prepayReqHandler.createMD5Sign(APP_KEY);
                prepayReqHandler.setParameter("sign",SIGN);
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
                        PayRecord payRecord = new PayRecord();
                        payRecord.setPayRecordStyle("微信APP支付");
                        payRecord.setOutTradeNo(OUT_TRADE_NO);
                        payRecord.setPayRecordTime(new Timestamp(System.currentTimeMillis()));
                        payRecord.setPayRecordTotalAmount(TOTAL_FEE);
                        payRecord.setPayRecordUser(user.getUserName());
                        payRecord.setPayRecordStatus("0");
                        payRecordMapper.addPayRecord(payRecord);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return ResponseStatusCode.postSuccess(weChatPayResponse);
        }
    }

}
