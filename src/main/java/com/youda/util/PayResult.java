package com.youda.util;

import com.youda.model.PayRecord;
import com.youda.response.api.AttestationResponse;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author Chencongye
 * @Date 2017/12/28 9:58
 * @Version 1.0.0
 * @Instructions 实现支付验签返回结果
 */

public class PayResult {

    /*实现返回验签结果*/
    public static AttestationResponse getAttestationResponse(String orderId,String result,String goodName,String userId, String totalAmount) {
        AttestationResponse attestationResponse = new AttestationResponse();
        attestationResponse.setOutTradeNo(orderId);
        attestationResponse.setResponseTime(new Date());
        attestationResponse.setResult(result);
        attestationResponse.setGoodName(goodName);
        attestationResponse.setUserId(userId);
        attestationResponse.setTotalAmount(totalAmount);
        return attestationResponse;
    }

    /*实现支付结果*/
    public static PayRecord getPayRecord(String status,String payUser,String totalAmount,String orderId,String style,Long gameChannelId,String userUseDevice) {
        PayRecord payRecord = new PayRecord();
        payRecord.setPayRecordStatus(status);
        payRecord.setPayRecordTime(new Timestamp(System.currentTimeMillis()));
        payRecord.setPayRecordUser(payUser);
        payRecord.setPayRecordTotalAmount(totalAmount);
        payRecord.setOutTradeNo(orderId);
        payRecord.setPayRecordStyle(style);
        payRecord.setGameChannelId(gameChannelId);
        payRecord.setUserUseDevice(userUseDevice);
        return payRecord;
    }
}
