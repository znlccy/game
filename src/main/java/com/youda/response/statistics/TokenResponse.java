package com.youda.response.statistics;

import com.youda.response.BaseResponse;

/**
 * Created by chenshengyu
 * on 2017/12/13.
 */
public class TokenResponse implements BaseResponse {
    private Long channelId;
    private String token;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

}
