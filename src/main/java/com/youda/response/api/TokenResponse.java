package com.youda.response.api;

import com.youda.response.BaseResponse;

/**
 * Created by chenshengyu
 * on 2017/12/8.
 */
public class TokenResponse implements BaseResponse {
    private String token;
    private long userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
