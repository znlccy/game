package com.youda.request;

/**
 * Created by chenshengyu
 * on 2017/12/8.
 */
public abstract class BaseRequest {

    public Long getGameChannelId() {
        return gameChannelId;
    }

    public void setGameChannelId(Long gameChannelId) {
        this.gameChannelId = gameChannelId;
    }

    protected Long gameChannelId;

    abstract boolean isEmpty();

}
