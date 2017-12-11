package com.youda.service;

import com.youda.model.Order;
import com.youda.request.OrderRequest;
import org.springframework.http.ResponseEntity;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义订单接口
 */

public interface OrderService {

    /*创建订单*/
    ResponseEntity createOrder(OrderRequest orderRequest);

    /*删除订单*/
    boolean deleteOrderById(long orderId);

    /*查询订单*/
    Order queryOrderById(long orderId);

    /*修改订单*/
    boolean modifyOrderById(long orderId);
}
