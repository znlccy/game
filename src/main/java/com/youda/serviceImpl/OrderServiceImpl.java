package com.youda.serviceImpl;

/*import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;*/
import com.youda.dao.*;
import com.youda.model.*;
import com.youda.request.OrderRequest;
import com.youda.response.OrderResponse;
import com.youda.response.ResponseStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.youda.service.OrderService;

import java.sql.Timestamp;

/*import static com.alipay.api.AlipayConstants.APP_ID;
import static com.alipay.api.AlipayConstants.CHARSET;*/

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

    @Autowired
    WeChatConfMapper weChatConfMapper;

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
        String CallBackUrl = aliPayConf.getCALLBACK_URL();
        String NotifyUrl = aliPayConf.getNOTIFY_URL();

        //实例化客户端
        /*AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
        AlipayOpenPublicTemplateMessageIndustryModifyRequest request = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数
        //此次只是参数展示，未进行字符串转义，实际情况下请转义
        request.setNotifyUrl("http://www.baidu.com");
        request.setReturnUrl("http://www.baidu.com");

        request.setBizContent("  {" +
                "    \"primary_industry_name\":\"IT科技/IT软件与服务\"," +
                "    \"primary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_name\":\"IT科技/IT软件与服务\"" +
                " }");
        AlipayOpenPublicTemplateMessageIndustryModifyResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
        }
        //调用成功，则处理业务逻辑
        if(response.isSuccess()){
            //.....
        }*/

        return null;
    }

    /*实现微信支付*/
    @Override
    public ResponseEntity wechatpay(Long orderId) {

        Order order = orderMapper.findByOrderId(orderId);
        Game game = gameMapper.findByGameId(order.getGameId());
        //获取订单一些属性
        String subject = order.getOrderSubject();
        String total_amount = order.getOrderTotalAmount();
        String out_trade_no = order.getOtherOrderId();
        String gameName = game.getGameName();

        /*获取微信支付配置信息*/
        WeChatConf weChatConf = weChatConfMapper.findWeChatConfByGameName(gameName);
        String APP_ID = weChatConf.getAPP_ID();
        String APP_KEY = weChatConf.getAPP_KEY();
        String CALLBACK_URL = weChatConf.getCALLBACK_URL();
        String NOTIFY_URL = weChatConf.getNOTIFY_URL();
        return null;
    }
}
