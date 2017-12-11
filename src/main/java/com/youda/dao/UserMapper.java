package com.youda.dao;

import com.youda.model.Token;
import com.youda.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

/**
 * @author Chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @fuction 定义用户映射的接口
 */

@Mapper
@CacheConfig(cacheNames = "users")
public interface UserMapper {

    /**
     * 通过用户Id来查询单个用户
     *
     * @param userId
     * @return
     */
    @Select("select * from tb_user where userId=#{userId}")
    User findByUserId(@Param("userId") long userId);

    /**
     * 通过用户名来查询单个用户
     *
     * @param userName
     * @return
     */
    @Select("select * from tb_user where userName=#{userName}")
    User findByUserName(@Param("userName") String userName);

    /**
     * 通过用户Id来删除用户
     *
     * @param userId
     * @return
     */
    @Delete("delete from tb_user where userId=#{userId}")
    boolean deleteByUserId(@Param("userId") long userId);

    /**
     * 通过用户名字来删除用户
     *
     * @param userName
     * @return
     */
    @Delete("delete from tb_user where userName=#{userName}")
    boolean deleteByUserName(@Param("userName") String userName);

    /**
     * 通过用户Id来修改用户
     *
     * @param
     * @return
     */
    @Update("update tb_user set userName=#{user.userName},userPassword=#{user.userPassword},userModifyTime=#{user.userModifyTime} where userId=#{user.userId} or userName=#{user.userName}")
    boolean modifyUserInfo(@Param("user") User user);

    /**
     * 列举所有用户
     *
     * @return
     */
    @Select("select * from tb_user")
    List<User> findAllUser();

    /**
     * 定义用户登录功能的规范
     *
     * @param userName
     * @param userPassword
     * @return
     */
    @Select("select * from tb_user where userName=#{userName} and userPassword=#{userPassword}")
    User userLogin(@Param("userName") String userName, @Param("userPassword") String userPassword);

    /**
     * 定义用户注册功能的规范
     *
     * @param user
     * @return
     */
    @Insert("insert into tb_user(userName,userPassword,userEmail,userRegisteredTime,userUseDevice) values(#{user.userName},#{user.userPassword},#{user.userEmail},#{user.userRegisteredTime},#{user.userUseDevice})")
    boolean addUser(@Param("user") User user);

    /**
     * 定义添加token的规范
     *
     * @param token token
     */
    @Insert("insert into tb_token(token,createTokenTime,createTokenUser) values(#{token.token},#{token.createTokenTime},#{token.createTokenUser})")
    @Options(useGeneratedKeys = true)
    void addToken(@Param("token") Token token);

}
