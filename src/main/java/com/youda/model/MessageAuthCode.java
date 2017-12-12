package com.youda.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
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
	 * 定义短信验证码的国家代码
	 */
	@Column(name = "conntryCode")
	private String conntryCode;

	/**
	 * 定义短信验证码的国家名称
	 */
	@Column(name = "countryName")
	private String countryName;

	/**
	 * 定义短信验证码的国家时区
	 */
	@Column(name = "timeZone")
	private String timeZone;

	/**
	 * 定义短信验证码的接收电话
	 */
	@Column(name = "sendTime")
	private Timestamp sendTime;
	
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
	 * 实现获得国家代码的get方法
	 * @return
	 */
	public String getConntryCode() {
		return conntryCode;
	}

	/**
	 * 实现获得国家代码的set方法
	 * @param conntryCode
	 */
	public void setConntryCode(String conntryCode) {
		this.conntryCode = conntryCode;
	}

	/**
	 * 实现获得国家名称的get方法
	 * @return
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * 实现获得国家名称的set方法
	 * @param countryName
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * 实现获得国家时区的get方法
	 * @return
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * 实现获得国家时区的set方法
	 * @param timeZone
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * 实现获得发送时间的get方法
	 * @return
	 */
	public Timestamp getSendTime() {
		return sendTime;
	}

	/**
	 * 实现获得发送时间的set方法
	 * @param sendTime
	 */
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 实现无参构造函数 
	 */
	public MessageAuthCode() {

	}

}
