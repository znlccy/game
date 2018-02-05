package com.youda.service;

import com.youda.model.Voice;

import java.util.List;

/**
 * @CreateTime:2018/2/5 11:40
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 语音服务接口
 */

public interface VoiceService {

    /**
     * @comment: findById实现查找语音
     * @param: [voiceId]
     * @return: com.youda.model.Voice
     */
    Voice findById(Long voiceId);

    /**
     * @comment: findByAppId
     * @param: [appId]
     * @return: com.youda.model.Voice
     */
    Voice findByAppId(String appId);

    /**
     * @comment: findByGameChannelId
     * @param: [gameChannelId]
     * @return: com.youda.model.Voice
     */
    Voice findByGameChannelId(String gameChannelId);

    /**
     * @comment: findByAppCertificate
     * @param: [appCertificate]
     * @return: com.youda.model.Voice
     */
    Voice findByAppCertificate(String appCertificate);

    /**
     * @comment: addVoice
     * @param: [voice]
     * @return: void
     */
    void addVoice(Voice voice);

    /**
     * @comment: updateVoice
     * @param: [voice]
     * @return: void
     */
    void updateVoice(Voice voice);

    /**
     * @comment: deleteVoice
     * @param: [voiceId]
     * @return: void
     */
    void deleteVoice(Long voiceId);

    /**
     * @comment: deleteByAppId
     * @param: [appId]
     * @return: void
     */
    void deleteByAppId(String appId);
    
    /**
     * @comment: deleteByGameChannelId
     * @param: [gameChannelId]
     * @return: void
     */
    void deleteByGameChannelId(String gameChannelId);

    /**
     * @comment: findAll
     * @param: []
     * @return: java.util.List<com.youda.model.Voice>
     */
    List<Voice> findAll();
}
