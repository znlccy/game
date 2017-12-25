package com.youda.controller.api;

import com.youda.request.api.OrderRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    /*实现订单服务的自动依赖注入*/
    @Autowired
    OrderService orderService;

    /*实现创建订单的功能*/
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

    /*实现支付宝支付的功能*/
    @ResponseBody
    @RequestMapping(value = "/alipay", method = RequestMethod.GET)
    public ResponseEntity aliPayOrder(@RequestParam(name = "orderId") Long orderId,@RequestHeader String token) {
        if (orderId == null || orderId == 0) {
            return ResponseStatusCode.nullPointerError();
        }
        return orderService.alipay(orderId);
    }

    /*实现支付H5支付*/
    @RequestMapping(value = "/aliphonepay", method = RequestMethod.GET)
    public ResponseEntity aliPhonePayOrder(@RequestParam(name = "orderId") Long orderId,@RequestHeader String token,HttpServletRequest httpRequest,HttpServletResponse httpResponse) {
        if (orderId == null || orderId == 0) {
            return ResponseStatusCode.nullPointerError();
        }
        return orderService.aliPhonePay(orderId,httpRequest,httpResponse);
    }

    /*实现微信支付的功能*/
    @RequestMapping(value = "/wechatpay", method = RequestMethod.GET)
    public ResponseEntity wechatOrder(@RequestParam(name = "orderId") Long orderId, @RequestHeader String token,HttpServletRequest request, HttpServletResponse response) {
        if (orderId == null || orderId == 0) {
            return ResponseStatusCode.nullPointerError();
        }
        return orderService.wechatpay(orderId,request,response);
    }

    /*实现支付宝验签的功能以及通知第三方*/
    @ResponseBody
    @RequestMapping(value = "/aliattestation",method = RequestMethod.POST)
    public ResponseEntity alipayAttestation(HttpServletRequest request) {
        return orderService.alipayAttestation(request);
    }

    /*实现微信验签的功能以及通知第三方*/
    @RequestMapping(value = "/wechatattestation", method = RequestMethod.POST)
    public ResponseEntity wechatAttestation(HttpServletRequest request,HttpServletResponse response) {
        try {
            return orderService.wechatAttestation(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseStatusCode.putOrGetFailed(null);
    }

}
