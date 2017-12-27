package com.youda.dao;

import com.youda.model.ApplePayConf;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/27 10:05
 * @Version 1.0.0
 * @Instructions 声明苹果支付的Dao层接口
 */

@Mapper
public interface ApplePayConfMapper {

    /*添加苹果支付配置*/
    @Insert("insert into tb_applepayconf(createTime,gameName,notifyUrl) values(now(),#{applePayConf.gameName},#{applePayConf.notifyUrl})")
    @Options(useGeneratedKeys = true,keyProperty = "applePayConfId")
    boolean addApplePayConf(@Param("applePayConf") ApplePayConf applePayConf);

    /*根据配置的Id删除苹果支付配置*/
    @Delete("delete from tb_applepayconf where applePayConfId=#{applePayConfId}")
    boolean deleteByApplePayConfId(@Param("applePayConfId") Long applePayConfId);

    /*通过游戏名称删除苹果支付配置信息*/
    @Delete("delete from tb_applepayconf where gameName=#{gameName}")
    boolean deleteByGameName(@Param("gameName") String gameName);

    /*修改苹果支付配置信息*/
    @Update("update tb_applepayconf set gameName=#{applePayConf.gameName},notifyUrl=#{applePayConf.notifyUrl}")
    boolean modifyApplePayConf(@Param("applePayConf") ApplePayConf applePayConf);

    /*通过苹果支付主键来获取苹果支付配置信息*/
    @Select("select * from tb_applepayconf where applePayConfId=#{applePayConfId}")
    ApplePayConf findByApplePayConfId(@Param("applePayConfId") Long applePayConfId);

    /*通过游戏名查找苹果支付配置信息*/
    @Select("select * from tb_applepayconf where gameName=#{gameName}")
    ApplePayConf findByGameName(@Param("gameName") String gameName);

    /*查找所有苹果支付配置信息*/
    @Select("select * from tb_applepayconf")
    List<ApplePayConf> findAllApplePayConf();
}
