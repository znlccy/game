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
 * @introduce 定义微信支付配置的实体类
 */

@Entity
@Table(name="tb_wechatconf",catalog="db_ydgame")
public class WeChatConf {
	
	/**
	 * 定义微信支付配置表的主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="wechatId")
	private long wechatId;
	
	/**
	 * 定义微信支付的应用Id
	 */
	@Column(name="APP_ID")
	private String APP_ID;
	
	/**
	 * 定义微信支付的应用钥匙
	 */
	@Column(name="APP_KEY")
	private String APP_KEY;
	
	/**
	 * 定义微信支付的应用秘钥
	 */
	@Column(name="APP_SECRET")
	private String APP_SECRET;
	
	/**
	 * 定义微信支付的游戏名
	 */
	@Column(name="GAME_NAME")
	private String GAME_NAME;
	
	/**
	 * 定义微信支付游戏包名
	 */
	@Column(name="GAME_PACKAGE")
	private String GAME_PACKAGE;
	
	/**
	 * 定义微信支付的授权类型
	 */
	@Column(name="GRANT_TYPE")
	private String GRANT_TYPE;
	
	/**
	 * 定义微信支付的商户号Id
	 */
	@Column(name="MCH_ID")
	private String MCH_ID;
	
	/**
	 * 定义微信支付的合作伙伴的Id
	 */
	@Column(name="PARTNER_ID")
	private String PARTNER_ID;
	
	/**
	 * 定义微信支付的合作伙伴的钥匙
	 */
	@Column(name="PARTNER_KEY")
	private String PARTNER_KEY;
	
	/**
	 * 定义微信支付的异步通知地址
	 */
	@Column(name="NOTIFY_URL")
	private String NOTIFY_URL;
	
	/**
	 * 定义微信支付的回调地址
	 */
	@Column(name="CALLBACK_URL")
	private String CALLBACK_URL;

	/**
	 * 实现微信支付配置表主键Id的get方法
	 * @return
	 */
	public long getWechatId() {
		return wechatId;
	}

	/**
	 * 实现微信支付配置表主键Id的set方法
	 * @param wechatId
	 */
	public void setWechatId(long wechatId) {
		this.wechatId = wechatId;
	}

	/**
	 * 实现微信支付应用Id的get方法
	 * @return
	 */
	public String getAPP_ID() {
		return APP_ID;
	}

	/**
	 * 实现微信支付应用Id的set方法
	 * @param aPP_ID
	 */
	public void setAPP_ID(String aPP_ID) {
		APP_ID = aPP_ID;
	}

	/**
	 * 实现微信支付应用钥匙的get方法
	 * @return
	 */
	public String getAPP_KEY() {
		return APP_KEY;
	}

	/**
	 * 实现微信支付应用钥匙的set方法
	 * @param aPP_KEY
	 */
	public void setAPP_KEY(String aPP_KEY) {
		APP_KEY = aPP_KEY;
	}

	/**
	 * 实现微信支付应用秘钥的get方法
	 * @return
	 */
	public String getAPP_SECRET() {
		return APP_SECRET;
	}

	/**
	 * 实现微信支付应用秘钥的set方法
	 * @param aPP_SECRET
	 */
	public void setAPP_SECRET(String aPP_SECRET) {
		APP_SECRET = aPP_SECRET;
	}

	/**
	 * 实现微信支付游戏名get方法
	 * @return
	 */
	public String getGAME_NAME() {
		return GAME_NAME;
	}

	/**
	 * 实现微信支付游戏名的set方法
	 * @param gAME_NAME
	 */
	public void setGAME_NAME(String gAME_NAME) {
		GAME_NAME = gAME_NAME;
	}

	/**
	 * 实现微信支付游戏名的get方法
	 * @return
	 */
	public String getGAME_PACKAGE() {
		return GAME_PACKAGE;
	}

	/**
	 * 实现微信支付游戏包名的set方法
	 * @param gAME_PACKAGE
	 */
	public void setGAME_PACKAGE(String gAME_PACKAGE) {
		GAME_PACKAGE = gAME_PACKAGE;
	}

	/**
	 * 实现微信支付授权类型的get方法
	 * @return
	 */
	public String getGRANT_TYPE() {
		return GRANT_TYPE;
	}

	/**
	 * 实现微信支付授权类型的set方法
	 * @param gRANT_TYPE
	 */
	public void setGRANT_TYPE(String gRANT_TYPE) {
		GRANT_TYPE = gRANT_TYPE;
	}

	/**
	 * 实现微信支付商户号Id的get方法
	 * @return
	 */
	public String getMCH_ID() {
		return MCH_ID;
	}

	/**
	 * 实现微信支付商户号Id的set方法
	 * @param mCH_ID
	 */
	public void setMCH_ID(String mCH_ID) {
		MCH_ID = mCH_ID;
	}

	/**
	 * 实现微信支付合作伙伴Id的get方法
	 * @return
	 */
	public String getPARTNER_ID() {
		return PARTNER_ID;
	}

	/**
	 * 实现微信支付合作伙伴Id的set方法
	 * @param pARTNER_ID
	 */
	public void setPARTNER_ID(String pARTNER_ID) {
		PARTNER_ID = pARTNER_ID;
	}

	/**
	 * 实现微信支付合作伙伴钥匙的get方法
	 * @return
	 */
	public String getPARTNER_KEY() {
		return PARTNER_KEY;
	}

	/**
	 * 实现微信支付的合作伙伴钥匙的set方法
	 * @param pARTNER_KEY
	 */
	public void setPARTNER_KEY(String pARTNER_KEY) {
		PARTNER_KEY = pARTNER_KEY;
	}

	/**
	 * 实现微信支付回调地址的get方法
	 * @return
	 */
	public String getNOTIFY_URL() {
		return NOTIFY_URL;
	}

	/**
	 * 实现微信支付异步通知地址的set方法
	 * @param nOTIFY_URL
	 */
	public void setNOTIFY_URL(String nOTIFY_URL) {
		NOTIFY_URL = nOTIFY_URL;
	}

	/**
	 * 实现微信支付回调地址的get方法
	 * @return
	 */
	public String getCALLBACK_URL() {
		return CALLBACK_URL;
	}

	/**
	 * 实现微信支付回调地址的set方法
	 * @param cALLBACK_URL
	 */
	public void setCALLBACK_URL(String cALLBACK_URL) {
		CALLBACK_URL = cALLBACK_URL;
	}
	
	/**
	 * 实现默认的构造函数
	 */
	public WeChatConf() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 实现带有参数的构造函数
	 * @param wechatId
	 * @param aPP_ID
	 * @param aPP_KEY
	 * @param aPP_SECRET
	 * @param gAME_NAME
	 * @param gAME_PACKAGE
	 * @param gRANT_TYPE
	 * @param mCH_ID
	 * @param pARTNER_ID
	 * @param pARTNER_KEY
	 * @param nOTIFY_URL
	 * @param cALLBACK_URL
	 */
	public WeChatConf(long wechatId, String aPP_ID, String aPP_KEY, String aPP_SECRET, String gAME_NAME,
			String gAME_PACKAGE, String gRANT_TYPE, String mCH_ID, String pARTNER_ID, String pARTNER_KEY,
			String nOTIFY_URL, String cALLBACK_URL) {
		super();
		this.wechatId = wechatId;
		APP_ID = aPP_ID;
		APP_KEY = aPP_KEY;
		APP_SECRET = aPP_SECRET;
		GAME_NAME = gAME_NAME;
		GAME_PACKAGE = gAME_PACKAGE;
		GRANT_TYPE = gRANT_TYPE;
		MCH_ID = mCH_ID;
		PARTNER_ID = pARTNER_ID;
		PARTNER_KEY = pARTNER_KEY;
		NOTIFY_URL = nOTIFY_URL;
		CALLBACK_URL = cALLBACK_URL;
	}
	
}

