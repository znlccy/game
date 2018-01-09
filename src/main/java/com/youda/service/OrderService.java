package com.youda.service;

import com.youda.request.api.GoogleRequest;
import com.youda.request.api.IOSPayRequest;
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
    ResponseEntity createOrder(OrderRequest orderRequest,String userUseDevice);

    /*定义支付宝支付*/
    ResponseEntity alipay(Long orderId,String token,String gameChannelId);

    /*定义支付宝H5支付*/
    void aliPhonePay(Long orderId,String token,String gameChannelId,HttpServletRequest httpRequest, HttpServletResponse httpResponse);

    /*定义微信支付*/
    ResponseEntity wechatpay(Long orderId,String token,String gameChannelId,HttpServletRequest request, HttpServletResponse response);

    /*定义支付宝支付验签*/
    ResponseEntity alipayAttestation(String token,String gameChannelId,HttpServletRequest request);

    /*定义微信支付验签*/
    ResponseEntity wechatAttestation(String token,String gameChannelId,HttpServletRequest request, HttpServletResponse response) throws IOException;

    /*定义IOS上传凭证的过程*/
    ResponseEntity iosUploadReceipt(IOSPayRequest request, Long orderId,String gameChannelId);

    /*实现苹果内购的验签*/
    ResponseEntity iosAttestation(IOSPayRequest request, Long orderId,String gameChannelId);

    /*实现google内购验签*/
    ResponseEntity googleAttestation(GoogleRequest request,Long orderId,String gameChannelId);

}
