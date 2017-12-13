package com.youda.response.api;

import com.youda.response.BaseResponse;

/**
 * Created by chenshengyu
 * on 2017/12/11.
 */
public class OrderResponse implements BaseResponse {
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderResponse(Long orderId) {
        this.orderId = orderId;
    }
}
