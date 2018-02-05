package com.youda.controller.api;

import com.youda.model.Voice;
import com.youda.request.api.ChannelKeyRequest;
import com.youda.service.VoiceService;
import com.youda.util.media.DynamicKey5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

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
    @RequestMapping(value = "/generate/channel/key",method = RequestMethod.POST)
    @ResponseBody
    public String generateChannelKey(@RequestBody ChannelKeyRequest channelKeyRequest) {

        String appId = channelKeyRequest.getAppId();
        Voice voice = voiceService.findByAppId(channelKeyRequest.getAppId());
        String appCertificate = "5cfd2fd1755d40ecb72977518be15d3b";
        String channel = channelKeyRequest.getChannel();
        int ts = (int)(new Date().getTime()/1000);
        int r = new Random().nextInt();
        long uid = channelKeyRequest.getGameChannelId();
        int expiredTs = 0;

        String channelKey="";
        try {
            channelKey = DynamicKey5.generateMediaChannelKey(appId, appCertificate, channel, ts, r, uid, expiredTs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channelKey;
    }

}
