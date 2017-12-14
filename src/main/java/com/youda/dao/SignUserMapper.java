package com.youda.dao;

import com.youda.model.SignUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;

/**
 * @author Chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @fuction 定义用户映射的接口
 */

@Mapper
@CacheConfig(cacheNames = "signUser")
public interface SignUserMapper {

    @Select("select * from tb_sign_user where signId=#{signId} and sign = #{sign}")
    SignUser findBySign(@Param("signId") long signId, @Param("sign") String sign);

    @Insert("insert into tb_sign_user(signId,sign,userId) values(#{user.signId},#{user.sign},#{user.userId})")
    void addSignUser(@Param("user") SignUser signUser);
}
