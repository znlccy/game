package com.youda.controller.api;

import com.youda.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CreateTime:2018/2/5 10:54
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 获取门禁卡
 */
@RestController
@RequestMapping(value = "/api")
public class ChannelKeyController {

    /**
     * 声明语音服务的自动依赖注入
     */
    @Autowired
    private VoiceService voiceService;

    /**
     * @comment: getChannelKey
     * @param: []
     * @return: voids
     */
    public void getChannelKey() {
        
    }

}
