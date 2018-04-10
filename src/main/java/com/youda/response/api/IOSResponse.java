package com.youda.response.api;

import com.youda.response.BaseResponse;

/**
 * CreateTime: 2018/4/9 18:08
 * Author: Administrator
 * Version: v-1.0.0
 * Comment: IOS验签响应的数据
 */
public class IOSResponse implements BaseResponse {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public IOSResponse(String status) {
        this.status = status;
    }
}
