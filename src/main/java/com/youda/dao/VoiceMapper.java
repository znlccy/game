package com.youda.dao;

import com.youda.model.Voice;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @CreateTime:2018/2/5 11:00
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 语音映射接口
 */

@Mapper
public interface VoiceMapper {

    /*声明并实现通过主键来查找*/
    @Select("select * from tb_voice where voiceId=#{voiceId}")
    Voice findById(@Param("voiceId") Long voiceId);

    /*声明并实现通过应用主键来查找*/
    @Select("select * from tb_voice where appId=#{appId}")
    Voice findByAppId(@Param("appId") String appId);

    /*声明并实现通过项目主键来查找*/
    @Select("select * from tb_voice where gameChannelId=#{gameChannelId}")
    Voice findByGameChannelId(@Param("gameChannelId") String gameChannelId);

    /*声明并实现通过应用证书来查找*/
    @Select("select * from tb_voice where appCertificate=#{appCertificate}")
    Voice findByAppCertificate(@Param("appCertificate") String appCertificate);

    /*声明并实现添加语音*/
    @Insert("insert into tb_voice(appCertificate,appId,channelKey,gameChannelId) values(#{voice.appCertificate},#{voice.appId},#{voice.channelKey},#{voice.gameChannelId})")
    void addVoice(@Param("voice") Voice voice);

    /*声明并实现更新语音*/
    @Update("update tb_voice set appCertificate=#{voice.appCertificate},appId=#{voice.appId},channelKey=#{voice.channelKey},gameChannelId=#{voice.gameChannelId}")
    void updateVoice(@Param("voice") Voice voice);

    /*声明并实现删除语音*/
    @Delete("delete from tb_voice where voiceId = #{voiceId}")
    void deleteVoice(@Param("voiceId") Long voiceId);

    /*声明并实现删除语音*/
    @Delete("delete from tb_voice where appId=#{appId}")
    void deleteByAppId(@Param("appId") String appId);

    /*声明并实现删除语音*/
    @Delete("delete from tb_voice where gameChannelId=#{gameChannelId}")
    void deleteByGameChannelId(@Param("gameChannelId") String gameChannelId);

    /*声明并实现查询所有*/
    @Select("select * from tb_voice")
    List<Voice> findAll();
}
