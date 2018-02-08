package com.youda.service;

import com.youda.model.GooglePayConf;

import java.util.List;

/**
 * @CreateTime:2018/2/8 11:21
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: google配置服务接口类
 */

public interface GooglePayConfService {

    /**
     * @comment: addGoogle声明添加google配置
     * @param: [googlePayConf]
     * @return: boolean
     */
    boolean addGoogle(GooglePayConf googlePayConf);

    /**
     * @comment: updateGoogle声明更新google配置
     * @param: [googlePayConf]
     * @return: boolean
     */
    boolean updateGoogle(GooglePayConf googlePayConf);

    /**
     * @comment: deleteGoogle声明删除google配置
     * @param: [googlePayConfId]
     * @return: boolean
     */
    boolean deleteGoogle(Long googlePayConfId);

    /**
     * @comment: findById声明查找google配置
     * @param: [googlePayConfId]
     * @return: com.youda.model.GooglePayConf
     */
    GooglePayConf findById(Long googlePayConfId);

    /**
     * @comment: findByGameChannelId声明查找google配置
     * @param: [gameChannelId]
     * @return: com.youda.model.GooglePayConf
     */
    GooglePayConf findByGameChannelId(Long gameChannelId);

    /**
     * @comment: findAll查找所有google配置
     * @param: []
     * @return: java.util.List<com.youda.model.GooglePayConf>
     */
    List<GooglePayConf> findAll();
}
