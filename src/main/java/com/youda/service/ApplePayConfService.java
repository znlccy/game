package com.youda.service;

import com.youda.model.ApplePayConf;

import java.util.List;

/**
 * @CreateTime:2018/2/8 11:25
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 苹果配置服务接口
 */

public interface ApplePayConfService {

    /**
     * @comment: addApple声明添加apple配置
     * @param: [applePayConf]
     * @return: boolean
     */
    boolean addApple(ApplePayConf applePayConf);

    /**
     * @comment: updateApple声明更新apple配置
     * @param: [applePayConf]
     * @return: boolean
     */
    boolean updateApple(ApplePayConf applePayConf);

    /**
     * @comment: deleteApple声明删除apple配置
     * @param: [applePayConfId]
     * @return: boolean
     */
    boolean deleteApple(Long applePayConfId);

    /**
     * @comment: findById声明查找apple配置
     * @param: [applePayConfId]
     * @return: com.youda.model.ApplePayConf
     */
    ApplePayConf findById(Long applePayConfId);

    /**
     * @comment: findByGameChannelId声明查找apple配置
     * @param: [gameChannelId]
     * @return: com.youda.model.ApplePayConf
     */
    ApplePayConf findByGameChannelId(Long gameChannelId);

    /**
     * @comment: findAll声明查找所有apple配置
     * @param: []
     * @return: java.util.List<com.youda.model.ApplePayConf>
     */
    List<ApplePayConf> findAll();
}
