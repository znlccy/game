package com.youda.serviceImpl;

import com.youda.model.Order;
import org.springframework.stereotype.Service;

import com.youda.service.OrderService;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义订单服务接口实现类
 */

@Service
public class OrderServiceImpl implements OrderService {

    /*实现创建订单*/
    @Override
    public boolean createOrder(Order order) {
        return false;
    }

    /*实现删除订单*/
    @Override
    public boolean deleteOrderById(long orderId) {
        return false;
    }

    /*实现查询订单*/
    @Override
    public boolean queryOrderById(long orderId) {
        return false;
    }

    /*实现修改订单*/
    @Override
    public boolean modifyOrderById(long orderId) {
        return false;
    }
}
