package com.youda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-12-05
 * @introduce 定义支付宝支付的配置实体类
 */

@Entity
@Table(name="tb_alipayconf",catalog="db_ydgame")
public class AliPayConf {
	
	/**
	 * 定义支付宝支付表的主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="alipayId")
	private long alipayId;
	
	/**
	 * 定义支付宝支付需要的公钥
	 */
	@Column(name="ALIPAY_PUBLIC_KEY",length = 2000)
	private String ALIPAY_PUBLIC_KEY;
	
	/**
	 * 定义支付宝支付需要的私钥
	 */
	@Column(name="APP_PRIVATE_KEY",length = 2000)
	private String APP_PRIVATE_KEY;
	
	/**
	 * 定义支付宝支付需要的应用Id
	 */
	@Column(name="APP_ID")
	private String APP_ID;
	
	/**
	 * 定义支付宝支付需要的游戏名称
	 */
	@Column(name="gameChannelId")
	private Long gameChannelId;
	
	/**
	 * 定义支付宝支付的异步通知地址
	 */
	@Column(name="NOTIFY_URL",length = 800)
	private String NOTIFY_URL;
	
	/**
	 * 定义支付宝支付的回调地址
	 */
	@Column(name="CALLBACK_URL",length = 800)
	private String CALLBACK_URL;

	/**
	 * 实现支付宝支付表主键的get方法
	 * @return
	 */
	public long getAlipayId() {
		return alipayId;
	}

	/**
	 * 实现支付宝支付表主键的set方法
	 * @param alipayId
	 */
	public void setAlipayId(long alipayId) {
		this.alipayId = alipayId;
	}

	/**
	 * 实现支付宝支付的公钥的get方法
	 * @return
	 */
	public String getALIPAY_PUBLIC_KEY() {
		return ALIPAY_PUBLIC_KEY;
	}

	/**
	 * 实现支付宝支付的公钥的set方法
	 * @param aLIPAY_PUBLIC_KEY
	 */
	public void setALIPAY_PUBLIC_KEY(String aLIPAY_PUBLIC_KEY) {
		ALIPAY_PUBLIC_KEY = aLIPAY_PUBLIC_KEY;
	}

	/**
	 * 实现支付宝支付的私钥的get方法
	 * @return
	 */
	public String getAPP_PRIVATE_KEY() {
		return APP_PRIVATE_KEY;
	}

	/**
	 * 实现支付宝支付的私钥的set方法
	 * @param aPP_PRIVATE_KEY
	 */
	public void setAPP_PRIVATE_KEY(String aPP_PRIVATE_KEY) {
		APP_PRIVATE_KEY = aPP_PRIVATE_KEY;
	}

	/**
	 * 实现支付宝支付的应用Id的get方法
	 * @return
	 */
	public String getAPP_ID() {
		return APP_ID;
	}

	/*实现游戏渠道主键的get方法*/
	public Long getGameChannelId() {
		return gameChannelId;
	}

	/*实现游戏渠道主键的set方法*/
	public void setGameChannelId(Long gameChannelId) {
		this.gameChannelId = gameChannelId;
	}

	/**
	 * 实现支付宝支付的应用Id的set方法
	 * @param aPP_ID
	 */
	public void setAPP_ID(String aPP_ID) {
		APP_ID = aPP_ID;
	}

	/**
	 * 实现支付宝支付异步通知地址的get方法
	 * @return
	 */
	public String getNOTIFY_URL() {
		return NOTIFY_URL;
	}

	/**
	 * 实现支付宝支付异步通知地址的set方法
	 * @param nOTIFY_URL
	 */
	public void setNOTIFY_URL(String nOTIFY_URL) {
		NOTIFY_URL = nOTIFY_URL;
	}

	/**
	 * 实现支付宝支付回调地址的get方法
	 * @return
	 */
	public String getCALLBACK_URL() {
		return CALLBACK_URL;
	}

	/**
	 * 实现支付宝支付回调地址的set方法
	 * @param cALLBACK_URL
	 */
	public void setCALLBACK_URL(String cALLBACK_URL) {
		CALLBACK_URL = cALLBACK_URL;
	}
	
	/**
	 * 实现默认的构造函数
	 */
	public AliPayConf() {
		// TODO Auto-generated constructor stub
	}

	
}
