package com.youda.serviceImpl;

import com.youda.dao.GooglePayConfMapper;
import com.youda.model.GooglePayConf;
import com.youda.service.GooglePayConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime:2018/2/8 11:27
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: google服务配置实现
 */

@Service
public class GooglePayConfServiceImpl implements GooglePayConfService {

    /**
     * 声明google配置的自动依赖注入
     */
    @Autowired
    private GooglePayConfMapper googlePayConfMapper;

    /**
     * @comment: addGoogle实现添加google配置
     * @param: [googlePayConf]
     * @return: boolean
     */
    @Override
    public boolean addGoogle(GooglePayConf googlePayConf) {
        return googlePayConfMapper.addGooglePayConf(googlePayConf);
    }

    /**
     * @comment: updateGoogle实现更新google配置
     * @param: [googlePayConf]
     * @return: boolean
     */
    @Override
    public boolean updateGoogle(GooglePayConf googlePayConf) {
        return googlePayConfMapper.modifyGooglePayConf(googlePayConf);
    }

    /**
     * @comment: deleteGoogle实现删除google配置
     * @param: [googlePayConfId]
     * @return: boolean
     */
    @Override
    public boolean deleteGoogle(Long googlePayConfId) {
        return googlePayConfMapper.deleteByGooglePayConfId(googlePayConfId);
    }

    /**
     * @comment: findById实现查找google配置
     * @param: [googlePayConfId]
     * @return: com.youda.model.GooglePayConf
     */
    @Override
    public GooglePayConf findById(Long googlePayConfId) {
        return googlePayConfMapper.findByGooglePayConfId(googlePayConfId);
    }

    /**
     * @comment: findByGameChannelId实现查找google配置
     * @param: [gameChannelId]
     * @return: com.youda.model.GooglePayConf
     */
    @Override
    public GooglePayConf findByGameChannelId(Long gameChannelId) {
        return googlePayConfMapper.findByGameChannelId(gameChannelId);
    }

    /**
     * @comment: findAll实现查找所有google配置
     * @param: []
     * @return: java.util.List<com.youda.model.GooglePayConf>
     */
    @Override
    public List<GooglePayConf> findAll() {
        return googlePayConfMapper.findAllGooglePayConf();
    }
}
