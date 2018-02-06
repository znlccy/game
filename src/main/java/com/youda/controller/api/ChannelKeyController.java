package com.youda.controller.api;

import com.youda.model.Voice;
import com.youda.request.api.ChannelKeyRequest;
import com.youda.service.VoiceService;
import com.youda.util.media.DynamicKey5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public Map<String, Object> generateChannelKey(@RequestBody ChannelKeyRequest channelKeyRequest,@RequestHeader String gameChannelId) {
        Map<String,Object> map = new HashMap<String,Object>();
        if (voiceService.findByGameChannelId(gameChannelId) == null) {
            map.put("status","0404");
            map.put("message","没找到语音配置");
        }
        else {
            Voice voice = voiceService.findByGameChannelId(gameChannelId);
            String appId = voice.getAppId();
            String appCertificate = voice.getAppCertificate();
            String channel = channelKeyRequest.getChannel();
            int ts = (int)(new Date().getTime()/1000);
            int r = new Random().nextInt();
            long uid = Long.valueOf(gameChannelId);
            int expiredTs = 0;

            String channelKey="";
            try {
                channelKey = DynamicKey5.generateMediaChannelKey(appId, appCertificate, channel, ts, r, uid, expiredTs);
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put("status","0200");
            map.put("message","生成Channel Key成功");
            map.put("channelKey",channelKey);
            map.put("appId",appId);
        }
        return map;
    }

    /**
     * @comment: addChannelKey添加语音系统
     * @param: [channelKeyRequest]
     * @return: void
     */
    @RequestMapping(value = "/add/voice",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addChannelKey(@RequestBody Voice voice,@RequestHeader String gameChannelId) {
        Map<String,Object> map = new HashMap<String,Object>();
        if (voiceService.findByGameChannelId(gameChannelId) == null) {
            voice.setGameChannelId(gameChannelId);
            voiceService.addVoice(voice);
            map.put("status","0200");
            map.put("message","添加成功");
            map.put("voiceId",voice.getVoiceId());
        }
        else {
            map.put("status","0208");
            map.put("message","该语音配置已经存在");
            map.put("voiceId",voiceService.findByGameChannelId(gameChannelId).getVoiceId());
        }
        return map;
    }

    /**
     * @comment: updateChannelKey实现更新语音配置
     * @param: [channelKeyRequest]
     * @return: void
     */
    @RequestMapping(value = "/update/voice",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateChannelKey(@RequestBody Voice voice) {
        Map<String,Object> map = new HashMap<String,Object>();
        if (voiceService.findById(voice.getVoiceId()) == null) {
            map.put("status","0404");
            map.put("message","该语音配置不存在");
        }
        else
        {
            voiceService.updateVoice(voice);
            map.put("status","0200");
            map.put("message","更新成功");
            map.put("voiceId",voiceService.findById(voice.getVoiceId()).getVoiceId());
        }
        return map;
    }

    /**
     * @comment: deleteChannelKey实现删除语音配置
     * @param: [channelKeyRequest]
     * @return: void
     */
    @RequestMapping(value = "/delete/voice/{voiceId}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteChannelKey(@PathVariable Long voiceId) {
        Map<String,Object> map = new HashMap<String,Object>();
        if (voiceService.findById(voiceId) == null) {
            map.put("status","0404");
            map.put("message","该语音配置不存在");
        }
        else
        {
            voiceService.deleteVoice(voiceId);
            map.put("status","0200");
            map.put("message","删除成功");
        }
        return map;
    }

    /**
     * @comment: findAll实现遍历所有予以配置
     * @param: []
     * @return: java.util.List<com.youda.model.Voice>
     */
    @RequestMapping(value = "/all/voice", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findAll() {
        List<Voice> voices = new ArrayList<Voice>();
        voices = voiceService.findAll();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status","0200");
        map.put("message","遍历成功");
        map.put("data",voices);
        return map;
    }
}
