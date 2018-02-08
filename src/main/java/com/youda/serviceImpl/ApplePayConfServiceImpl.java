package com.youda.serviceImpl;

import com.youda.dao.ApplePayConfMapper;
import com.youda.model.ApplePayConf;
import com.youda.service.ApplePayConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime:2018/2/8 11:29
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 实现苹果支付配置的实现类
 */

@Service
public class ApplePayConfServiceImpl implements ApplePayConfService {

    /**
     * 声明apple配置的自动依赖注入
     */
    @Autowired
    private ApplePayConfMapper applePayConfMapper;

    /**
     * @comment: addApple实现添加apple配置
     * @param: [applePayConf]
     * @return: boolean
     */
    @Override
    public boolean addApple(ApplePayConf applePayConf) {
        return applePayConfMapper.addApplePayConf(applePayConf);
    }

    /**
     * @comment: updateApple实现更新apple配置
     * @param: [applePayConf]
     * @return: boolean
     */
    @Override
    public boolean updateApple(ApplePayConf applePayConf) {
        return applePayConfMapper.modifyApplePayConf(applePayConf);
    }

    /**
     * @comment: deleteApple实现删除apple配置
     * @param: [applePayConfId]
     * @return: boolean
     */
    @Override
    public boolean deleteApple(Long applePayConfId) {
        return applePayConfMapper.deleteByApplePayConfId(applePayConfId);
    }

    /**
     * @comment: findById实现查找apple配置
     * @param: [applePayConfId]
     * @return: com.youda.model.ApplePayConf
     */
    @Override
    public ApplePayConf findById(Long applePayConfId) {
        return applePayConfMapper.findByApplePayConfId(applePayConfId);
    }

    /**
     * @comment: findByGameChannelId实现查找apple配置
     * @param: [gameChannelId]
     * @return: com.youda.model.ApplePayConf
     */
    @Override
    public ApplePayConf findByGameChannelId(Long gameChannelId) {
        return applePayConfMapper.findByGameChannelId(gameChannelId);
    }

    /**
     * @comment: findAll实现查找所有apple配置
     * @param: []
     * @return: java.util.List<com.youda.model.ApplePayConf>
     */
    @Override
    public List<ApplePayConf> findAll() {
        return applePayConfMapper.findAllApplePayConf();
    }
}
