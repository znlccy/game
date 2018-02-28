package com.youda.request.api;

import com.youda.request.BaseRequest;

/**
 * @Author Chencongye
 * @Date 2017/12/25 18:26
 * @Version 1.0.0
 * @Instructions 这是IOS内购的请求
 */

public class IOSPayRequest extends BaseRequest {

    /*声明凭证变量*/
    private String receipt;

    /*设置获取凭证的get方法*/
    public String getReceipt() {
        return receipt;
    }

    /*设置获取凭证的set方法*/
    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }


    /*判断是否为空*/
    @Override
    public boolean isEmpty() {
        return receipt==null || receipt.isEmpty();
    }
}
