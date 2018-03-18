package com.youda.controller.api;

import com.youda.annotation.CurrentUser;
import com.youda.annotation.RequestLimit;
import com.youda.request.api.GoogleRequest;
import com.youda.request.api.IOSPayRequest;
import com.youda.request.api.OrderRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.service.OrderService;
import org.apache.ibatis.annotations.Param;
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
    @CurrentUser
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @RequestLimit(count = 10, time = 5000)
    public ResponseEntity createOrder(@RequestBody OrderRequest request, @RequestHeader("gameChannelId") String gameChannelId, @RequestHeader("userId") String userId,@RequestHeader String userUseDevice) {
        request.setGameChannelId(Long.valueOf(gameChannelId));
        request.setUserId(Long.valueOf(userId));
        if (request.isEmpty()) {
            return ResponseStatusCode.nullPointerError();
        }
        return orderService.createOrder(request,userUseDevice);
    }

    /*实现支付宝支付的功能*/
    @CurrentUser
    @ResponseBody
    @RequestMapping(value = "/alipay/{orderId}", method = RequestMethod.POST)
    public ResponseEntity aliPayOrder(@PathVariable Long orderId, @RequestHeader String token,@RequestHeader String gameChannelId) {
        if (orderId == null || orderId == 0) {
            return ResponseStatusCode.nullPointerError();
        }
        return orderService.alipay(orderId,token,gameChannelId);
    }

    /*实现支付H5支付*/
    @CurrentUser
    @RequestMapping(value = "/aliphonepay/{orderId}", method = RequestMethod.POST)
    public void aliPhonePayOrder(@PathVariable Long orderId, @RequestHeader String token,@RequestHeader String gameChannelId, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        orderService.aliPhonePay(orderId,token,gameChannelId,httpRequest, httpResponse);
    }

    /*实现微信支付的功能*/
    @CurrentUser
    @RequestMapping(value = "/wechatpay/{orderId}", method = RequestMethod.POST)
    public ResponseEntity wechatOrder(@PathVariable Long orderId, @RequestHeader String token,@RequestHeader String gameChannelId,HttpServletRequest request, HttpServletResponse response) {
        /*if (orderId == null || orderId == 0) {
            return ResponseStatusCode.nullPointerError();
        }*/
        return orderService.wechatpay(orderId,token,gameChannelId,request, response);
    }

    /*实现支付宝验签的功能以及通知第三方*/
    @CurrentUser
    @ResponseBody
    @RequestMapping(value = "/aliattestation", method = RequestMethod.POST)
    public ResponseEntity alipayAttestation(@RequestHeader String token,@RequestHeader String gameChannelId, HttpServletRequest request) {
        return orderService.alipayAttestation(token,gameChannelId,request);
    }

    /*实现微信验签的功能以及通知第三方*/
    @CurrentUser
    @RequestMapping(value = "/wechatattestation", method = RequestMethod.POST)
    public ResponseEntity wechatAttestation(HttpServletRequest request, HttpServletResponse response,@RequestHeader String token,@RequestHeader String gameChannelId) {
        try {
            return orderService.wechatAttestation(token,gameChannelId,request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseStatusCode.putOrGetFailed(null);
    }

    /*使用IOS内购进行验签*/
    @CurrentUser
    @ResponseBody
    @RequestMapping(value = "/{orderId}/iosattestation", method = RequestMethod.POST)
    @RequestLimit(count = 10, time = 5000)
    public ResponseEntity iosAttestation(@RequestBody IOSPayRequest request,@RequestHeader String gameChannelId,@PathVariable Long orderId) {
        request.setGameChannelId(Long.valueOf(gameChannelId));
        if (request.isEmpty())
        {
            return ResponseStatusCode.nullPointerError();
        }
        return orderService.iosUploadReceipt(request,orderId,gameChannelId);
    }

    /*使用google内购进行验签*/
    @CurrentUser
    @ResponseBody
    @RequestMapping(value = "/{orderId}/google/pay", method = RequestMethod.PUT)
    @RequestLimit(count = 10, time = 5000)
    public ResponseEntity googlePay(@RequestBody GoogleRequest request,@RequestHeader("gameChannelId") String gameChannelId, @PathVariable int orderId) {
        request.setGameChannelId(Long.valueOf(gameChannelId));
        if (request.isEmpty()) {
            return ResponseStatusCode.nullPointerError();
        }
        return orderService.googleAttestation(request, (long) orderId,gameChannelId);
    }
}
