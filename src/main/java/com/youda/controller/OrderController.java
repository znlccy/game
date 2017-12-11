package com.youda.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.youda.model.Order;
import com.youda.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.youda.interceptor.UserInterceptor;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义订单控制器
 */

@RestController
/*@RequestMapping(value = "/order")*/
@CrossOrigin(maxAge = 3600, origins = "*")
public class OrderController   {

    /*实现*/
    @Autowired
    private OrderService orderService;

    /*实现添加订单*/
    @ResponseBody
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public void createOrder(Order order) {

    }

    /*实现查询订单*/
    @ResponseBody
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public void queryOrderById(long orderId) {

    }

    /*实现删除订单*/
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public void deleteOrderById(long orderId) {

    }

    /*实现修改订单*/
    @ResponseBody
    @RequestMapping(value = "modify",method = RequestMethod.PUT)
    public void modifyOrderById(long orderId) {

    }
}
