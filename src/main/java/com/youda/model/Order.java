package com.youda.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 定义订单实体类
 */

@Entity
@Table(name="tb_order", catalog="db_ydgame")
public class Order {
	
	/**
	 * 定义订单主键Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId")
	private long orderId;
	
	/**
	 * 定义订单总金额
	 */
	@Column(name = "orderTotalAmount")
	private String orderTotalAmount;
	
	/**
	 * 定义订单商品名称
	 */
	@Column(name = "orderSubject")
	private String orderSubject;
	
	/**
	 * 定义订单创建时间
	 */
	@Column(name = "createOrderTime")
	private Timestamp createOrderTime;
	
	/**
	 * 定义订单是否被推送到第三方平台
	 */
	@Column(name = "isPushed")
	private String isPushed;
	
	/**
	 * 定义订单第三接入的订单号
	 */
	@Column(name = "otherOrderId")
	private String otherOrderId;
	
	/**
	 * 定义订单交易时间
	 */
	@Column(name = "orderPayTime")
	private Timestamp orderPayTime;
	
	/**
	 * 定义订单数量
	 */
	@Column(name = "orderNumber")
	private String orderNumber;
	
	/**
	 * 定义订单的创建者
	 */
	@Column(name = "createOrderUser")
	private String createOrderUser;
	
	/**
	 * 创建订单来源
	 */
	@Column(name = "orderSource")
	private String orderSource;

	/**
	 * 定义订单主键Id的get方法
	 * @return
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * 定义订单主键Id的set方法
	 * @param orderId
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * 定义订单总额的get方法
	 * @return
	 */
	public String getOrderTotalAmount() {
		return orderTotalAmount;
	}

	/**
	 * 定义订单总额的set方法
	 * @param orderTotalAmount
	 */
	public void setOrderTotalAmount(String orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	/**
	 * 定义订单描述的get方法
	 * @return
	 */
	public String getOrderSubject() {
		return orderSubject;
	}

	/**
	 * 定义订单描述的set方法
	 * @param orderSubject
	 */
	public void setOrderSubject(String orderSubject) {
		this.orderSubject = orderSubject;
	}

	/**
	 * 定义创建订单时间的get方法
	 * @return
	 */
	public Timestamp getCreateOrderTime() {
		return createOrderTime;
	}

	/**
	 * 定义创建订单时间的set方法
	 * @param createOrderTime
	 */
	public void setCreateOrderTime(Timestamp createOrderTime) {
		this.createOrderTime = createOrderTime;
	}

	/**
	 * 定义订单是否已经推送给第三方服务器的get方法
	 * @return
	 */
	public String getIsPushed() {
		return isPushed;
	}

	/**
	 * 定义订单是否已经推送给第三方服务器的get方法
	 * @param isPushed
	 */
	public void setIsPushed(String isPushed) {
		this.isPushed = isPushed;
	}

	/**
	 * 定义接入第三方其他订单号的get方法
	 * @return
	 */
	public String getOtherOrderId() {
		return otherOrderId;
	}

	/**
	 * 定义接入第三方其他订单号的set方法
	 * @param otherOrderId
	 */
	public void setOtherOrderId(String otherOrderId) {
		this.otherOrderId = otherOrderId;
	}

	/**
	 * 定义订单支付时间的get方法
	 * @return
	 */
	public Timestamp getOrderPayTime() {
		return orderPayTime;
	}

	/**
	 * 定义订单支付时间的set方法
	 * @param orderPayTime
	 */
	public void setOrderPayTime(Timestamp orderPayTime) {
		this.orderPayTime = orderPayTime;
	}

	/**
	 * 定义订单总量的get方法
	 * @return
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * 定义订单总量的set方法
	 * @param orderNumber
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * 定义创建订单用户的get方法
	 * @return
	 */
	public String getCreateOrderUser() {
		return createOrderUser;
	}

	/**
	 * 定义创建订单用户的set方法
	 * @param createOrderUser
	 */
	public void setCreateOrderUser(String createOrderUser) {
		this.createOrderUser = createOrderUser;
	}

	/**
	 * 定义订单来源的get方法
	 * @return
	 */
	public String getOrderSource() {
		return orderSource;
	}

	/**
	 * 定义订单来源的set方法
	 * @param orderSource
	 */
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	/**
	 * 定义带有参数的构造函数
	 * @param orderId
	 * @param orderTotalAmount
	 * @param orderSubject
	 * @param createOrderTime
	 * @param isPushed
	 * @param otherOrderId
	 * @param orderPayTime
	 * @param orderNumber
	 * @param createOrderUser
	 * @param orderSource
	 */
	public Order(long orderId, String orderTotalAmount, String orderSubject, Timestamp createOrderTime,
			String isPushed, String otherOrderId, Timestamp orderPayTime, String orderNumber, String createOrderUser,
			String orderSource) {
		super();
		this.orderId = orderId;
		this.orderTotalAmount = orderTotalAmount;
		this.orderSubject = orderSubject;
		this.createOrderTime = createOrderTime;
		this.isPushed = isPushed;
		this.otherOrderId = otherOrderId;
		this.orderPayTime = orderPayTime;
		this.orderNumber = orderNumber;
		this.createOrderUser = createOrderUser;
		this.orderSource = orderSource;
	}
	
}
