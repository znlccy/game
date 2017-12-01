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
 * @date 2017-11-30
 * @introduce 定义短信验证码的实体类
 */

@Entity
@Table(name = "tb_macode", catalog = "db_ydgame")
public class MessageAuthCode {

	/**
	 * 定义短信验证码的主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "macodeId")
	private long macodeId;
	
	/**
	 * 定义短信验证码的内容
	 */
	@Column(name = "macodeContent")
	private String macodeContent;
	
	/**
	 * 定义短息验证码的发送时间
	 */
	@Column(name = "macodeSendTime")
	private Timestamp macodeSendTime;
	
	/**
	 * 定义短信验证码的接收电话
	 */
	@Column(name = "macodePhone")
	private String macodePhone;

	/**
	 * 实现短信验证码主键的get方法
	 * @return
	 */
	public long getMacodeId() {
		return macodeId;
	}

	/**
	 * 实现短信验证码主键的set方法
	 * @param macodeId
	 */
	public void setMacodeId(long macodeId) {
		this.macodeId = macodeId;
	}

	/**
	 * 实现短信验证码内容的get方法
	 * @return
	 */
	public String getMacodeContent() {
		return macodeContent;
	}

	/**
	 * 实现短信验证码的set方法
	 * @param macodeContent
	 */
	public void setMacodeContent(String macodeContent) {
		this.macodeContent = macodeContent;
	}

	/**
	 * 实现短信验证码发送时间的get方法
	 * @return
	 */
	public Timestamp getMacodeSendTime() {
		return macodeSendTime;
	}

	/**
	 * 定义短信验证码发送时间的set方法
	 * @param macodeSendTime
	 */
	public void setMacodeSendTime(Timestamp macodeSendTime) {
		this.macodeSendTime = macodeSendTime;
	}

	/**
	 * 定义短信验证码接收电话的get方法
	 * @return
	 */
	public String getMacodePhone() {
		return macodePhone;
	}

	/**
	 * 定义短信验证码接收电话的set方法
	 * @param macodePhone
	 */
	public void setMacodePhone(String macodePhone) {
		this.macodePhone = macodePhone;
	}

	/**
	 * 定义带有参数的构造函数
	 * @param macodeId
	 * @param macodeContent
	 * @param macodeSendTime
	 * @param macodePhone
	 */
	public MessageAuthCode(long macodeId, String macodeContent, Timestamp macodeSendTime, String macodePhone) {
		super();
		this.macodeId = macodeId;
		this.macodeContent = macodeContent;
		this.macodeSendTime = macodeSendTime;
		this.macodePhone = macodePhone;
	}

	/*定义默认构造函数*/
	public MessageAuthCode() {

	}
}
