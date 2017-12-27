package com.youda.dao;

import com.youda.model.GooglePayConf;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/27 10:07
 * @Version 1.0.0
 * @Instructions 实现google支付的Dao层的接口类
 */

@Mapper
public interface GooglePayConfMapper {

    /*实现添加google支付配置表*/
    @Insert("insert into tb_googlepayconf(createTime,gameChannelId,notifyUrl) values(now(),#{googlePayConf.gameChannelId},#{googlePayConf.notifyUrl})")
    @Options(useGeneratedKeys = true,keyProperty = "googlePayConfId")
    boolean addGooglePayConf(@Param("googlePayConf")GooglePayConf googlePayConf);

    /*通过google支付配置主键来删除google支付配置*/
    @Delete("delete from tb_googlepayconf where googlePayConfId=#{googlePayConfId}")
    boolean deleteByGooglePayConfId(@Param("googlePayConfId") Long googlePayConfId);

    /*通过游戏明湖曾来删除google支付配置*/
    @Delete("delete from tb_googlepayconf where gameChannelId=#{gameChannelId}")
    boolean deleteByGameChannelId(@Param("gameChannelId") Long gameChannelId);

    /*修改google支付的配置信息*/
    @Update("update tb_googlepayconf set gameChannelId=#{googlePayConf.gameChannelId},notifyUrl=#{googlePayConf.notifyUrl}")
    boolean modifyGooglePayConf(@Param("googlePayConf") GooglePayConf googlePayConf);

    /*通过google配置主键来获取google支付配置信息*/
    @Select("select * from tb_googlepayconf where googlePayConfId=#{googlePayConfId}")
    GooglePayConf findByGooglePayConfId(@Param("googlePayConfId") Long googlePayConfId);

    /*通过游戏名称来获取google支付配置信息*/
    @Select("select * from tb_googlepayconf where gameChannelId=#{gameChannelId}")
    GooglePayConf findByGameChannelId(@Param("gameChannelId") Long gameChannelId);

    /*查找所有的google支付配置信息*/
    @Select("select * from tb_googlepayconf")
    List<GooglePayConf> findAllGooglePayConf();
}
