package com.youda.dao;

import com.youda.model.MessageAuthCode;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义短信验证码映射接口
 */

@Mapper
@CacheConfig(cacheNames = "authCode")
public interface MessageAuthCodeMapper {

    /**
     * 定义添加短信验证码的规范
     *
     * @param authCode
     * @return
     */
    @Insert("insert into tb_macode(macodeId,macodeContent,macodeSendTime,macodePhone) values(#{authCode.macodeId},#{authCode.macodeContent},#{authCode.macodeSendTime},#{authCode.macodePhone})")
    boolean addMessageAuthCode(@Param("authCode") MessageAuthCode authCode);

    /**
     * 定义通过短信验证码主键Id来查找短信验证码的规范
     *
     * @param macodeId
     * @return
     */
    @Select("select * from tb_macode where macodeId=#{macodeId}")
    MessageAuthCode findByMacodeId(@Param("macodeId") long macodeId);

    /**
     * 定义通过手机来查找短信验证码的规范
     * @param userName 用户名
     * @return MessageAuthCode
     */
    @Select("select * from tb_macode where macodePhone=#{userName}")
    MessageAuthCode findByMacodeContent(@Param("userName") String userName);

    /**
     * 定义通过短信验证码主键的规范
     *
     * @param macodeId
     * @return
     */
    @Delete("delete from tb_macode where macodeId=#{macodeId}")
    boolean deleteByMacodeId(@Param("macodeId") long macodeId);

    /**
     * 定义通过短信验证码的内容来删除短信验证码的规范
     *
     * @param macodeContent
     * @return
     */
    @Delete("delete from tb_macode where macodeContent=#{macodeContent}")
    boolean deleteByMacodeContent(@Param("macodeContent") String macodeContent);

    /**
     * 定义通过短信验证码主键Id来
     *
     * @param macodeId
     * @param
     * @return
     */
    @Update("update tb_macode set macodeContent=#{authCode.macodeContent},macodeSendTime=#{authCode.macodeSendTime},macodePhone=#{authCode.macodePhone} where macodeId=#{macodeId}")
    boolean modifyByMacodeId(@Param("macodeId") long macodeId);

    /**
     * 定义通过短信内容来查找短信验证码的规范
     *
     * @param macodeContent
     * @param authCode
     * @return
     */
    @Update("update tb_macode set macodeContent=#{authCode.macodeContent},macodeSendTime=#{authCode.macodeSendTime},macodePhone=#{authCode.macodePhone} where macodeContent=#{macodeContent}")
    boolean modifyByMacodeContent(@Param("macodeContent") String macodeContent, @Param("authCode") MessageAuthCode authCode);

    /**
     * 定义列举所有短信验证码的规范
     *
     * @return
     */
    @Select("select macodeId,macode_content,macode_send_time,macodePhone from tb_macode")
    List<MessageAuthCode> findAllMessageCode();
}
