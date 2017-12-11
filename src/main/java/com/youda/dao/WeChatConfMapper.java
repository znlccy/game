package com.youda.dao;

import com.youda.model.WeChatConf;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WeChatConfMapper {

    /*添加微信支付的配置*/
    @Insert("insert into tb_wechatconf(APP_ID,APP_KEY,APP_SECRET,CALLBACK_URL,GAME_NAME,GAME_PACKAGE,GRANT_TYPE,MCH_ID,NOTIFY_URL,PARTNER_ID,PARTNER_KEY) values(#{weChatConf.APP_ID},#{weChatConf.APP_KEY},#{weChatConf.APP_SECRET},#{weChatConf.CALLBACK_URL},#{weChatConf.GAME_NAME},#{weChatConf.GAME_PACKAGE},#{weChatConf.GRANT_TYPE},#{weChatConf.MCH_ID},#{weChatConf.NOTIFY_URL},#{weChatConf.PARTNER_ID},#{weChatConf.PARTNER_KEY})")
    boolean addWeChatConf(@Param("weChatConf") WeChatConf weChatConf);

    /*通过微信支付的主键来查找微信配置*/
    @Select("delete from tb_wechatconf where wechatId=#{wechatId}")
    boolean deleteWeChatConf(@Param("wechatId") long wechatId);

    /*修改微信支付配置*/
    @Update("update tb_wechatconf set APP_ID=#{weChatConf.APP_ID},APP_KEY=#{weChatConf.APP_KEY},APP_SECRET=#{weChatConf.APP_SECRET},CALLBACK_URL=#{weChatConf.CALLBACK_URL},GAME_NAME=#{weChatConf.GAME_NAME},GAME_PACKAGE=#{weChatConf.GAME_PACKAGE},GRANT_TYPE=#{weChatConf.GRANT_TYPE},MCH_ID=#{weChatConf.MCH_ID},NOTIFY_URL=#{weChatConf.NOTIFY_URL},PARTNER_ID=#{weChatConf.PARTNER_ID},PARTNER_KEY=#{weChatConf.PARTNER_KEY} where wechatId=#{weChatConf.wechatId}")
    boolean mofifyWeChatConf(@Param("weChatConf") WeChatConf weChatConf);

    /*通过微信支付配置的Id来查找微信支付配置*/
    @Select("select * from tb_wechatconf where wechatId=#{wechatId}")
    WeChatConf findWeChatConfById(@Param("wechatId") long wechatId);

    /*查询所有的微信配置*/
    @Select("select * from tb_wechatconf")
    List<WeChatConf> findAllWeChatConf();

    @Select("select * from tb_wechatconf where APP_ID=#{APP_ID}")
    WeChatConf findWeChatByAppId(@Param("APP_ID") String APP_ID);
}
