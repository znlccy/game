package com.youda.serviceImpl;

/*import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;*/
import com.youda.dao.GameMapper;
import com.youda.dao.OrderMapper;
import com.youda.model.Game;
import com.youda.model.Order;
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

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GameMapper gameMapper;

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

        String APP_PRIVATE_KEY="";
        String APP_ID="";
        String ALIPAY_PUBLIC_KEY="";
        String CHARSET="";
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
        return null;
    }
}
