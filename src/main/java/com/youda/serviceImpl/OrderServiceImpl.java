package com.youda.serviceImpl;

import com.youda.dao.OrderMapper;
import com.youda.model.Order;
import com.youda.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private OrderMapper orderMapper;

    /*实现创建订单*/
    @Override
    public ResponseEntity createOrder(OrderRequest orderRequest) {

        if(orderRequest==null)
        {
            orderMapper.createOrder(orderRequest);
            return null;
        }
        else
        {
            return null;
        }
    }

    /*实现删除订单*/
    @Override
    public boolean deleteOrderById(long orderId) {
        Order order = orderMapper.findByOrderId(orderId);
        if (order!=null)
        {
            orderMapper.deleteByOrderId(orderId);
            return true;
        }
        else
        {
            return false;
        }
    }

    /*实现查询订单*/
    @Override
    public Order queryOrderById(long orderId) {

        Order order = orderMapper.findByOrderId(orderId);
        if (order!=null)
        {
            return order;
        }
        else
        {
            return null;
        }
    }

    /*实现修改订单*/
    @Override
    public boolean modifyOrderById(long orderId) {

        Order order = orderMapper.findByOrderId(orderId);
        if (order==null)
        {
            return false;
        }
        else
        {
            if (orderMapper.modifyByOrderId(order)) {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
