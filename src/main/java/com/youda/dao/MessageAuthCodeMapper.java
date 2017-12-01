package com.youda.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youda.model.MessageAuthCode;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义短信验证码映射接口
 */

@Mapper
public interface MessageAuthCodeMapper {
	
	/**
	 * 定义添加短信验证码的规范
	 * @param authCode
	 * @return
	 */
	@Insert("insert into tb_messagecode(macode_id,macode_content,macode_send_time,macode_phone) values(#{authCode.macodeId},#{authCode.macodeContent},#{authCode.macodeSendTime},#{authCode.macodePhone})")
	public boolean addMessageAuthCode(@Param("authCode") MessageAuthCode authCode);
	
	/**
	 * 定义通过短信验证码主键Id来查找短信验证码的规范
	 * @param macodeId
	 * @return
	 */
	@Select("select macodeId,macodeContent,macodeSendTime,macodePhone from tb_messagecode where macodeId=#{macodeId}")
	public MessageAuthCode findByMacodeId(@Param("macodeId") long macodeId);
	
	/**
	 * 定义通过短息验证码内容来查找短信验证码的规范
	 * @param macodeContent
	 * @return
	 */
	@Select("select macode_id,macode_content,macode_send_time,macode_phone from tb_messagecode where macode_content=#{macodeContent}")
	public MessageAuthCode findByMacodeContent(@Param("macodeContent") String macodeContent);
	
	/**
	 * 定义通过短信验证码主键的规范
	 * @param macodeId
	 * @return
	 */
	@Delete("delete from tb_messagecode where macodeId=#{macodeId}")
	public boolean deleteByMacodeId(@Param("macodeId") long macodeId);
	
	/**
	 * 定义通过短信验证码的内容来删除短信验证码的规范
	 * @param macodeContent
	 * @return
	 */
	@Delete("delete from tb_messagecode where macodeContent=#{macodeContent}")
	public boolean deleteByMacodeContent(@Param("macodeContent") String macodeContent);

	/**
	 * 定义通过短信验证码主键Id来
	 * @param macodeId
	 * @param authCode
	 * @return
	 */
	@Update("update tb_messagecode set macodeContent=#{authCode.macodeContent},macodeSendTime=#{authCode.macodeSendTime},macodePhone=#{authCode.macodePhone} where macodeId=#{macodeId}")
	public boolean modifyByMacodeId(@Param("macodeId") String macodeId,@Param("authCode") MessageAuthCode authCode);
	
	/**
	 * 定义通过短信内容来查找短信验证码的规范
	 * @param macodeContent
	 * @param authCode
	 * @return
	 */
	@Update("update tb_messagecode set macodeContent=#{authCode.macodeContent},macodeSendTime=#{authCode.macodeSendTime},macodePhone=#{authCode.macodePhone} where macodeContent=#{macodeContent}")
	public boolean modifyByMacodeContent(@Param("macodeContent") String macodeContent,@Param("authCode") MessageAuthCode authCode);
	
	/**
	 * 定义列举所有短信验证码的规范
	 * @return
	 */
	@Select("select macode_id,macode_content,macode_send_time,macode_phone from tb_messagecode")
	public List<MessageAuthCode> findAllMessageCode();
}
