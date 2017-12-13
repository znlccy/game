package com.youda.response.admin;

import com.youda.response.BaseResponse;

/**
 * Created by chenshengyu
 * on 2017/12/13.
 */
public class TokenResponse implements BaseResponse {
    private Long channelId;
    private String token;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenResponse(Long channelId, String token) {
        this.channelId = channelId;
        this.token = token;
    }
}
