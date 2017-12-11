package com.youda.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youda.model.Order;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义订单映射接口
 */

@Mapper
public interface OrderMapper {

	/**
	 * 定义添加一个订单的规范
	 * @param order
	 * @return
	 */
	@Insert("insert into tb_order(orderId,orderTotalAmount,orderSubject,createOrderTime,isPushed,otherOrderId,orderPayTime,orderNumber,createOrderUser,orderSource) values(#{order.orderId},#{order.orderTotalAmount},#{order.orderSubject},#{order.orderCreateTime},#{order.isPushed},#{order.otherOrderId},#{order.orderPayTime},#{order.orderNumber},#{order.createOrderUser},#{order.orderSource})")
	public boolean createOrder(@Param("order") Order order);
	
	/**
	 * 定义通过订单主键Id来查询订单的规范
	 * @param orderId
	 * @return
	 */
	@Select("select orderId,orderTotalAmount,orderSubject,createOrderTime,isPushed,otherOrderId,orderPayTime,orderNumber,createOrderUser,orderSource from tb_order where orderId=#{orderId}")
	public Order findByOrderId(@Param("orderId") String orderId);
	
	/**
	 * 定义通过订单商品名称来查询订单的规范
	 * @param orderSubject
	 * @return
	 */
	@Select("select orderId,orderTotalAmount,orderSubject,createOrderTime,isPushed,otherOrderId,orderPayTime,orderNumber,createOrderUser,orderSource from tb_order where orderSubject=#{orderSubject}")
	public List<Order> findByOrderSubject(@Param("orderSubject") String orderSubject);
	
	/**
	 * 定义通过订单主键Id来删除订单
	 * @param orderId
	 * @return
	 */
	@Delete("delete from tb_order where orderId=#{orderId}")
	public boolean deleteByOrderId(@Param("orderId") long orderId);
	
	/**
	 * 定义通过第三方的订单编号来删除订单
	 * @param otherOrderId
	 * @return
	 */
	@Delete("delete from tb_order where otherOrderId=#{otherOrderId}")
	public boolean deleteByOtherId(@Param("otherOrderId") String otherOrderId);
	
	/**
	 * 定义通过订单编号来修改订单的规范
	 * @param order
	 * @return
	 */
	@Update("update tb_order set where orderId=#{orderId}")
	public boolean modifyByOrderId(@Param("order") Order order);
	
	/**
	 * 定义通过订单编号来修改订单的规范
	 * @param order
	 * @return
	 */
	@Update("update tb_order set orderId=#{order.orderId},orderTotalAmount=#{order.orderTotalAmount},orderSubject=#{order.orderSubject},createOrderTime=#{order.createOrderTime},isPushed=#{order.isPushed},otherOrderId=#{order.otherOrderId},orderPayTime=#{order.orderPayTime},orderNumber=#{order.orderNumber},createOrderUser=#{order.createOrderUser},orderSource=#{order.orderSource} where otherOrderId=#{order.otherOrderId}")
	public boolean modifyByOtherId(@Param("order") Order order);
	
}
