package com.youda.request.api;

import com.youda.request.BaseRequest;

/**
 * Created by chenshengyu
 * on 2017/12/11.
 */
public class OrderRequest extends BaseRequest {

    private Long userId;
    private String orderTotalAmount;
    private String orderSubject;
    private String otherOrderId;
    private Long gameId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(String orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getOrderSubject() {
        return orderSubject;
    }

    public void setOrderSubject(String orderSubject) {
        this.orderSubject = orderSubject;
    }

    public String getOtherOrderId() {
        return otherOrderId;
    }

    public void setOtherOrderId(String otherOrderId) {
        this.otherOrderId = otherOrderId;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    public boolean isEmpty() {
        return orderTotalAmount == null || orderTotalAmount.isEmpty()
                || gameChannelId == null || gameChannelId == 0
                || userId == null || userId == 0
                || !orderTotalAmount.matches("^(([1-9]\\d*)|([0]))(\\.(\\d){0,2})?$")
                || orderSubject == null || orderSubject.isEmpty()
                || otherOrderId == null || otherOrderId.isEmpty()
                || getGameChannelId() == null || getGameChannelId() == 0;
    }
}
