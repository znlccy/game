package com.youda.serviceImpl;

import com.youda.dao.VoiceMapper;
import com.youda.model.Voice;
import com.youda.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime:2018/2/5 11:41
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment:
 */

@Service
public class VoiceServiceImpl implements VoiceService {

    /**
     * 声明语音映射接口的自动依赖注入
     */
    @Autowired
    private VoiceMapper voiceMapper;

    /**
     * @comment: findById
     * @param: [voiceId]
     * @return: com.youda.model.Voice
     */
    @Override
    public Voice findById(Long voiceId) {
        return voiceMapper.findById(voiceId);
    }

    /**
     * @comment: findByAppId
     * @param: [appId]
     * @return: com.youda.model.Voice
     */
    @Override
    public Voice findByAppId(String appId) {
        return voiceMapper.findByAppId(appId);
    }

    /**
     * @comment: findByGameChannelId
     * @param: [gameChannelId]
     * @return: com.youda.model.Voice
     */
    @Override
    public Voice findByGameChannelId(String gameChannelId) {
        return voiceMapper.findByGameChannelId(gameChannelId);
    }

    /**
     * @comment: findByAppCertificate
     * @param: [appCertificate]
     * @return: com.youda.model.Voice
     */
    @Override
    public Voice findByAppCertificate(String appCertificate) {
        return voiceMapper.findByAppCertificate(appCertificate);
    }

    /**
     * @comment: addVoice
     * @param: [voice]
     * @return: void
     */
    @Override
    public void addVoice(Voice voice) {
        voiceMapper.addVoice(voice);
    }

    /**
     * @comment: updateVoice
     * @param: [voice]
     * @return: void
     */
    @Override
    public void updateVoice(Voice voice) {
        voiceMapper.updateVoice(voice);
    }

    /**
     * @comment: deleteVoice
     * @param: [voiceId]
     * @return: void
     */
    @Override
    public void deleteVoice(Long voiceId) {
        voiceMapper.deleteVoice(voiceId);
    }

    /**
     * @comment: deleteByAppId
     * @param: [appId]
     * @return: void
     */
    @Override
    public void deleteByAppId(String appId) {
        voiceMapper.deleteByAppId(appId);
    }

    /**
     * @comment: deleteByGameChannelId
     * @param: [gameChannelId]
     * @return: void
     */
    @Override
    public void deleteByGameChannelId(String gameChannelId) {
        voiceMapper.deleteByGameChannelId(gameChannelId);
    }

    /**
     * @comment: findAll
     * @param: []
     * @return: java.util.List<com.youda.model.Voice>
     */
    @Override
    public List<Voice> findAll() {
        return voiceMapper.findAll();
    }
}
