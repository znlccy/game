package com.youda.request.channel;

import com.youda.request.BaseRequest;

/**
 * Created by chenshengyu
 * on 2017/12/14.
 */
public class SignRequest extends BaseRequest {
    private Long signWith;
    private String sign;

    public Long getSignWith() {
        return signWith;
    }

    public void setSignWith(Long signWith) {
        this.signWith = signWith;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public boolean isEmpty() {
        return signWith == null || signWith == 0
                || gameChannelId == null || gameChannelId == 0
                || sign == null || sign.isEmpty();
    }
}
