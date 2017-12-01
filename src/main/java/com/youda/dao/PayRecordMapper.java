package com.youda.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youda.model.PayRecord;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义支付记录映射的接口
 */

@Mapper
public interface PayRecordMapper {
	
	/**
	 * 定义添加支付记录的规范
	 * @param payRecord
	 * @return
	 */
	@Insert("insert into tb_payrecord(payRecordId,payRecordTime,payRecordStyle,payRecordStatus,payRecordTotalAmount,payRecordOrderId,outTradeNo,payRecordUser) values(#{payRecord.payRecordId},#{payRecord.payRecordTime},#{payRecord.payRecordStyle},#{payRecord.payRecordStatus},#{payRecord.payRecordTotalAmount},#{payRecord.payRecordOrderId},#{payRecord.outTradeNo},#{payRecord.payRecordUser})")
	public boolean addPayRecord(@Param("payRecord") PayRecord payRecord);
	
	/**
	 * 定义通过支付记录主键Id来查找支付记录的规范
	 * @param payRecordId
	 * @return
	 */
	@Select("select payRecordId,payRecordTime,payRecordStyle,payRecordStatus,payRecordTotalAmount,payRecordOrderId,outTradeNo,payRecordUser from where payRecordId=#{payRecordId}")
	public PayRecord findByPayRecordId(@Param("payRecordId") String payRecordId);
	
	/**
	 * 定义通过支付用户来查询支付记录的规范
	 * @param payRecordUser
	 * @return
	 */
	@Select("select payRecordId,payRecordTime,payRecordStyle,payRecordStatus,payRecordTotalAmount,payRecordOrderId,outTradeNo,payRecordUser from where payRecordUser=#{payRecordUser}")
	public PayRecord findPayRecordUser(@Param("payRecordUser") String payRecordUser);
	
	/**
	 * 定义通过第三方订单号来查询支付记录的规范
	 * @param outTradeNo
	 * @return
	 */
	@Select("select payRecordId,payRecordTime,payRecordStyle,payRecordStatus,payRecordTotalAmount,payRecordOrderId,outTradeNo,payRecordUser from where outTradeNo=#{outTradeNo}")
	public PayRecord findOutTradeNo(@Param("outTradeNo") String outTradeNo);
	
	/**
	 * 定义通过支付记录主键Id来删除支付记录的规范
	 * @param payRecordId
	 * @return
	 */
	@Delete("delete from tb_payrecord where payRecordId=#{payRecordId}")
	public boolean deletePayRecordId(@Param("payRecordId") String payRecordId);
	
	/**
	 * 定义通过支付用户来删除支付记录的规范
	 * @param payRecordUser
	 * @return
	 */
	@Delete("delete from tb_payrecord where payRecordUser=#{payRecordUser}")
	public boolean deletePayRecordUser(@Param("payRecordUser") String payRecordUser);
	
	/**
	 * 定义通过第三方订单号来删除支付记录的规范
	 * @param outTradeNo
	 * @return
	 */
	@Delete("delete from tb_payrecord where outTradeNo=#{outTradeNo}")
	public boolean deleteOutTradeNo(@Param("outTradeNo") String outTradeNo);
	
	/**
	 * 定义通过支付记录主键Id来修改支付记录的规范
	 * @param payRecordId
	 * @param payRecord
	 * @return
	 */
	@Update("update tb_payrecord set payRecordTime=#{payRecord.payRecordTime},payRecordStyle=#{payRecord.payRecordStyle},payRecordTotalAmount=#{payRecord.payRecordTotalAmount},payRecordOrderId=#{payRecord.payRecordOrderId},outTradeNo=#{payRecord.outTradeNo},payRecordUser=#{payRecord.payRecordUser} where payRecordId=#{payRecordId}")
	public boolean modifyByPayRecordId(@Param("payRecordId") String payRecordId,@Param("payRecord") PayRecord payRecord);
	
	/**
	 * 定义通过第三方订单号来修改支付记录的规范
	 * @param outTradeNo
	 * @param payRecord
	 * @return
	 */
	@Update("update tb_payrecord set payRecordTime=#{payRecord.payRecordTime},payRecordStyle=#{payRecord.payRecordStyle},payRecordTotalAmount=#{payRecord.payRecordTotalAmount},payRecordOrderId=#{payRecord.payRecordOrderId},outTradeNo=#{payRecord.outTradeNo},payRecordUser=#{payRecord.payRecordUser} where outTradeNo=#{outTradeNo}")
	public boolean modifyByOutTradeNo(@Param("outTradeNo") String outTradeNo,@Param("payRecord") PayRecord payRecord);
	
	/**
	 * 定义通过支付用户来修改支付记录的规范
	 * @param payRecordUser
	 * @param payRecord
	 * @return
	 */
	@Update("update tb_payrecord set payRecordTime=#{payRecord.payRecordTime},payRecordStyle=#{payRecord.payRecordStyle},payRecordTotalAmount=#{payRecord.payRecordTotalAmount},payRecordOrderId=#{payRecord.payRecordOrderId},outTradeNo=#{payRecord.outTradeNo},payRecordUser=#{payRecord.payRecordUser} where payRecordUser=#{payRecordUser}")
	public boolean modifyByPayRecordUser(@Param("payRecordUser") String payRecordUser,@Param("payRecord") PayRecord payRecord);
	
	/**
	 * 定义列举所有的支付记录的规范
	 * @return
	 */
	@Select("select payRecordId,payRecordTime,payRecordStyle,payRecordStatus,payRecordTotalAmount,payRecordOrderId,outTradeNo,payRecordUser from tb_payrecord")
	public List<PayRecord> findAllPayRecord();
	
}
