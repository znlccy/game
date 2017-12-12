package com.youda.dao;

import com.youda.model.Order;
import com.youda.request.OrderRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
    @Insert("insert into tb_order(orderTotalAmount,orderSubject,createOrderTime,otherOrderId,userId,gameId) values(#{order.orderTotalAmount},#{order.orderSubject},#{order.createOrderTime},#{order.otherOrderId},#{userId},#{gameId})")
    @Options(useGeneratedKeys = true)
    public ResponseEntity createOrder(@Param("order") Order order,@Param("userId") long userId,@Param("gameId") long gameId);

    /**
     * 定义通过订单主键Id来查询订单的规范
     * @param orderId
     * @return
     */
    @Select("select * from tb_order where orderId=#{orderId}")
    public Order findByOrderId(@Param("orderId") long orderId);

    /**
     * 定义通过订单商品名称来查询订单的规范
     * @param orderSubject
     * @return
     */
    @Select("select * from tb_order where orderSubject=#{orderSubject}")
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
    /*@Update("update tb_order set() values(#{},#{},#{},#{}) where orderId=#{order.orderId}")*/
    public boolean modifyByOrderId(@Param("order") Order order);

    /**
     * 定义通过订单编号来修改订单的规范
     * @param order
     * @return
     */
    @Update("update tb_order set orderTotalAmount=#{order.orderTotalAmount},orderSubject=#{order.orderSubject},createOrderTime=#{order.createOrderTime},isPushed=#{order.isPushed},otherOrderId=#{order.otherOrderId},orderPayTime=#{order.orderPayTime},orderNumber=#{order.orderNumber},createOrderUser=#{order.createOrderUser},orderSource=#{order.orderSource} where otherOrderId=#{order.otherOrderId}")
    public boolean modifyByOtherId(@Param("order") Order order);

}
