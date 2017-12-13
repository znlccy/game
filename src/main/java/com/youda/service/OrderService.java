package com.youda.service;

import com.youda.model.Order;
import com.youda.request.OrderRequest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义订单接口
 */

public interface OrderService {

    ResponseEntity createOrder(OrderRequest orderRequest);

    ResponseEntity alipay(Long orderId);

    ResponseEntity wechatpay(Long orderId, HttpServletRequest request,HttpServletResponse response);
}
