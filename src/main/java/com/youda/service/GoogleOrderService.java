package com.youda.service;

import com.youda.model.GoogleOrder;

/**
 * @CreateTime:2018/2/8 11:21
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: google配置服务接口类
 */

public interface GoogleOrderService {

    /**
     * @comment: addGoogle声明添加google配置
     * @param: [googlePayConf]
     * @return: boolean
     */
    PayStatus isPay(GoogleOrder googleOrder,boolean isFirst);

    enum PayStatus {
        SUCCESS, ERROR, NO_SUPPORT
    }
}
