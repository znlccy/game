package com.youda.serviceImpl;

import com.youda.dao.OrderMapper;
import com.youda.model.Order;
import com.youda.request.OrderRequest;
import com.youda.response.OrderResponse;
import com.youda.response.ResponseStatusCode;
import com.youda.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

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

    @Override
    public ResponseEntity createOrder(OrderRequest request) {
        Order order = new Order();
        order.setOtherOrderId(request.getOtherOrderId());
        order.setOrderTotalAmount(request.getOrderTotalAmount());
        order.setCreateOrderTime(new Timestamp(System.currentTimeMillis()));

        orderMapper.createOrder(order);
        if (order.getOrderId() == 0) {
            return ResponseStatusCode.conflictError();
        }
        return ResponseStatusCode.postSuccess(new OrderResponse(order.getOrderId()));
    }

    @Override
    public ResponseEntity alipay(Long orderId) {
        Order order = orderMapper.findByOrderId(orderId);
        return null;
    }

    @Override
    public ResponseEntity wechatpay(Long orderId) {
        Order order = orderMapper.findByOrderId(orderId);
        return null;
    }
}
