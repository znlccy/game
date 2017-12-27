package com.youda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 定义支付记录实体类
 */

@Entity
@Table(name = "tb_payrecord", catalog = "db_ydgame")
public class PayRecord {
	
	/**
	 * 定义支付记录的主键Id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="payRecordId")
	private long payRecordId;
	
	/**
	 * 定义支付记录时间
	 */
	@Column(name = "payRecordTime")
	private Timestamp payRecordTime;
	
	/**
	 * 定义支付记录类型
	 */
	@Column(name = "payRecordStyle",length = 2000)
	private String payRecordStyle;
	
	/**
	 * 定义支付状态
	 */
	@Column(name = "payRecordStatus")
	private String payRecordStatus;
	
	/**
	 * 定义支付记录中总金额
	 */
	private String payRecordTotalAmount;
	
	/**
	 * 定义支付记录中的订单Id
	 */
	private String payRecordOrderId;
	
	/**
	 * 定义支付记录中第三方
	 */
	private String outTradeNo;
	
	/**
	 * 定义支付记录中的支付用户
	 */
	private String payRecordUser;
	
	/**
	 * 实现订单和支付记录之间的一对一关系映射
	 */
	@OneToOne
	@JoinTable(name="tb_orderpayrecord",joinColumns=@JoinColumn(name="payRecordId"),inverseJoinColumns=@JoinColumn(name="orderId"))
	private Order order;

	/**
	 * 实现支付记录主键Id的get方法
	 * @return
	 */
	public long getPayRecordId() {
		return payRecordId;
	}

	/**
	 * 实现支付记录主键的set方法
	 * @param payRecordId
	 */
	public void setPayRecordId(long payRecordId) {
		this.payRecordId = payRecordId;
	}

	/*实现支付记录时间的get方法*/
	public Timestamp getPayRecordTime() {
		return payRecordTime;
	}

	/*实现支付记录时间的set方法*/
	public void setPayRecordTime(Timestamp payRecordTime) {
		this.payRecordTime = payRecordTime;
	}

	/**
	 * 实现支付记录样式的get方法
	 * @return
	 */
	public String getPayRecordStyle() {
		return payRecordStyle;
	}

	/**
	 * 实现支付记录样式的set方法
	 * @param payRecordStyle
	 */
	public void setPayRecordStyle(String payRecordStyle) {
		this.payRecordStyle = payRecordStyle;
	}

	/**
	 * 实现支付记录状态的get方法
	 * @return
	 */
	public String getPayRecordStatus() {
		return payRecordStatus;
	}

	/**
	 * 实现支付记录状态的set方法
	 * @param payRecordStatus
	 */
	public void setPayRecordStatus(String payRecordStatus) {
		this.payRecordStatus = payRecordStatus;
	}

	/**
	 * 实现支付记录金额的get方法
	 * @return
	 */
	public String getPayRecordTotalAmount() {
		return payRecordTotalAmount;
	}

	/**
	 * 实现支付记录金额的set方法
	 * @param payRecordTotalAmount
	 */
	public void setPayRecordTotalAmount(String payRecordTotalAmount) {
		this.payRecordTotalAmount = payRecordTotalAmount;
	}

	/**
	 * 实现支付记录的订单Id的get方法
	 * @return
	 */
	public String getPayRecordOrderId() {
		return payRecordOrderId;
	}

	/**
	 * 实现支付记录订单Id的set方法
	 * @param payRecordOrderId
	 */
	public void setPayRecordOrderId(String payRecordOrderId) {
		this.payRecordOrderId = payRecordOrderId;
	}

	/**
	 * 实现支付记录订单编号的get方法
	 * @return
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}

	/**
	 * 实现支付记录订单编号的set方法
	 * @param outTradeNo
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	/**
	 * 实现支付记录用户的get方法
	 * @return
	 */
	public String getPayRecordUser() {
		return payRecordUser;
	}

	/**
	 * 实现支付记录用户的set方法
	 * @param payRecordUser
	 */
	public void setPayRecordUser(String payRecordUser) {
		this.payRecordUser = payRecordUser;
	}

	/**
	 * 实现订单和支付记录之间的一对一关系的get方法
	 * @return
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * 实现订单和支付记录之间的一对一关系的set方法
	 * @param order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * 实现默认的构造函数
	 */
	public PayRecord() {

	}

}

