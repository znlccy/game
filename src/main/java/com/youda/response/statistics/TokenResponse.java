package com.youda.response.statistics;

import com.youda.response.BaseResponse;

/**
 * Created by chenshengyu
 * on 2017/12/13.
 */
public class TokenResponse implements BaseResponse {
    private Long channelId;
    private String token;
    private int isRoot;
    private String userName;
    private Long channelUserId;

    public Long getChannelUserId() {
        return channelUserId;
    }

    public void setChannelUserId(Long channelUserId) {
        this.channelUserId = channelUserId;
    }

    public int getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(int isRoot) {
        this.isRoot = isRoot;
    }

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
