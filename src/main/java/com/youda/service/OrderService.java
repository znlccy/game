package com.youda.service;

import com.youda.request.api.OrderRequest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义订单接口
 */

public interface OrderService {

    /*定义创建订单*/
    ResponseEntity createOrder(OrderRequest orderRequest);

    /*定义支付宝支付*/
    ResponseEntity alipay(Long orderId);

    /*定义支付宝H5支付*/
    ResponseEntity aliPhonePay(Long orderId,HttpServletRequest httpRequest,HttpServletResponse httpResponse);

    /*定义微信支付*/
    ResponseEntity wechatpay(Long orderId, HttpServletRequest request,HttpServletResponse response);

    /*定义支付宝支付验签*/
    ResponseEntity alipayAttestation(HttpServletRequest request);

    /*定义微信支付验签*/
    ResponseEntity wechatAttestation(HttpServletRequest request,HttpServletResponse response) throws IOException;

    /*实现苹果内购的验签*/
    ResponseEntity iosAttestation(String receipt);
}
