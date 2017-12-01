package com.youda.interceptor;

import org.springframework.context.annotation.Bean;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 这是Web端返回的结果
 */

public class ResponseResult {
	
	/**
	 * 声明返回状态
	 */
	private String status;
	
	/**
	 * 声明返回结果
	 */
	private String result;
	
	/**
	 * 声明返回结果 
	 */
	private Object data;

	/**
	 * 实现返回状态码的get方法
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 实现返回状态码的set方法
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 实现返回结果的get方法
	 * @return
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 实现返回结果的set方法
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 实现返回数据的get方法
	 * @return
	 */
	public Object getData() {
		return data;
	}

	/**
	 * 实现返回数据的set方法
	 * @param data
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 定义带有参数的构造函数
	 * @param status
	 * @param result
	 * @param data
	 */
	public ResponseResult(String status, String result, Object data) {
		super();
		this.status = status;
		this.result = result;
		this.data = data;
	}
	
}
