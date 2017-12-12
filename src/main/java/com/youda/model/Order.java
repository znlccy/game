package com.youda.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
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
	 * 创建订单来源
	 */
	@Column(name = "orderSource")
	private String orderSource;

	@Column(name = "userId")
	private long userId;

	@Column(name = "gameId")
	private long gameId;

	/**
	 * 定义用户和订单之间的一对多关系映射
	 */
	/*@ManyToOne(cascade ={CascadeType.ALL})
	@JoinColumn(name = "userId")
	private User user;

	*//**
	 * 定义游戏和订单之间的一对多关系映射
	 *//*
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "gameId")
	private Game game;*/
	
	/**
	 * 定义订单和支付记录之间的一对一关系映射
	 */
	@OneToOne(mappedBy="order")
	private PayRecord payRecord;

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

	public Timestamp getCreateOrderTime() {
		return createOrderTime;
	}

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
	 * 实现用户和订单之间的一对多关系的get方法
	 * @return
	 *//*
	public User getUser() {
		return user;
	}

	*//**
	 * 实现用户和订单之间的一对多关系的set方法
	 * @param user
	 *//*
	public void setUser(User user) {
		this.user = user;
	}

	*//**
	 * 实现游戏和订单之间的一对多关系的get方法
	 * @return
	 *//*
	public Game getGame() {
		return game;
	}

	*//**
	 * 实现游戏和订单之间的一对多关系的set方法
	 * @param game
	 *//*
	public void setGame(Game game) {
		this.game = game;
	}*/
	
	/**
	 * 实现订单和支付记录之间一对一关系的get方法
	 * @return
	 */
	public PayRecord getPayRecord() {
		return payRecord;
	}

	/**
	 * 实现订单和支付记录之间一对一关系的set方法
	 * @param payRecord
	 */
	public void setPayRecord(PayRecord payRecord) {
		this.payRecord = payRecord;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	/**
	 * 实现默认的无参构造函数
	 */
	public Order() {
		
	}
}
