package com.youda.controller.api;

import com.youda.request.OrderRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义订单控制器
 */

@RestController
@RequestMapping(value = "/api/order")
@CrossOrigin(maxAge = 3600, origins = "*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody OrderRequest request, @RequestHeader("gameChannelId") String gameChannelId, @RequestHeader("userId") String userId) {
        request.setGameChannelId(Long.valueOf(gameChannelId));
        request.setUserId(Long.valueOf(userId));
        if (request.isEmpty()) {
            return ResponseStatusCode.nullPointerError();
        }
        return orderService.createOrder(request);
    }

    @ResponseBody
    @RequestMapping(value = "/alipay", method = RequestMethod.GET)
    public ResponseEntity aliPayOrder(@RequestParam(name = "orderId") Long orderId) {
        if (orderId == null || orderId == 0) {
            return ResponseStatusCode.nullPointerError();
        }
        /*return orderService.alipay(orderId);*/
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/wechatpay", method = RequestMethod.GET)
    public ResponseEntity wechatOrder(@RequestParam(name = "orderId") Long orderId) {
        if (orderId == null || orderId == 0) {
            return ResponseStatusCode.nullPointerError();
        }
        /*return orderService.wechatpay(orderId);*/
        return null;
    }


}
