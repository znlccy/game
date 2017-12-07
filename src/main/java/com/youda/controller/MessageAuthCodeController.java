package com.youda.controller;

import com.youda.interceptor.ResponseStatusCode;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;
/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义短信验证码控制器
 */

@RestController
@RequestMapping(value = "message")
@CrossOrigin(maxAge = 3600, origins="*")
public class MessageAuthCodeController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	/*声明国内任信了账户号*/
	private String domesticUser = "13402040612";

	/*声明国内任信了账户密码*/
	private String domesticPassword = "abc85410d238d4b5bae2ea3830e3d787";

	/*声明国内任信了Mid编号*/
	private String domesticMid = "14341";

	/*声明国外任信了用户编号*/
	private String foreignUser = "13402040612";

	/*声明国外任信了用户密码*/
	private String foreignPassword = "abc85410d238d4b5bae2ea3830e3d787";

	/*声明国外任信了Mid编号*/
	private String foreignMid = "14337";

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return ERROR_PATH;
	}

	/**
	 * 实现发送短信验证码
	 * @param phone
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/demostics", method = RequestMethod.GET)
	public ResponseEntity sendMessageCode(@RequestParam String phone) throws UnsupportedEncodingException {

		if (phone == "" || "".equals(phone))
		{
			return ResponseStatusCode.nullPointerError();
		}
		else
		{
			int messageAuthCode = (int)((Math.random()*9+1)*100000);
			String sendUrl = "http://apis.renxinl.com:8080/smsgate/varsend.do?"+"user="+domesticUser+"&"+"pwd="+domesticPassword+"&"+"params="+phone+","+messageAuthCode+"&"+"mid="+domesticMid;

			try {
				//创建连接
				URL url = new URL(sendUrl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setRequestMethod("POST");
		       /* connection.setRequestProperty("Authorization", token);*/
				connection.setUseCaches(false);
				connection.setInstanceFollowRedirects(true);
				connection.setRequestProperty("Content-Type","application/json");
				connection.connect();

				//POST请求
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				JSONObject obj = new JSONObject();
				obj.put("user", domesticUser);
				obj.put("pwd", domesticPassword);
				obj.put("phone", "15900785383");
				obj.put("mid", "86");

				out.writeBytes(obj.toString());
				out.flush();
				out.close();

				//读取响应
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String lines;
				StringBuffer sb = new StringBuffer("");

				while ((lines = reader.readLine()) != null) {
					lines = new String(lines.getBytes(), "utf-8");
					sb.append(lines);
		            /*map.put("data", sb);*/
					String capture = new String(sb);
					JSONObject codeData = JSONObject.parseObject(capture);
					String code = (String) codeData.get("code");
					if(!code.equals("0000")) {
						return ResponseStatusCode.cashError();
					}
				}
				reader.close();
				// 断开连接
				connection.disconnect();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResponseStatusCode.putOrGetSuccess(null);
	}

	/**
	 * 实现发送短信验证码
	 * @param phone
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/foreign", method = RequestMethod.GET)
	public ResponseEntity sendMessageCode(@RequestParam String phone,String countryCode) throws UnsupportedEncodingException {

		if (phone == "" || "".equals(phone))
		{
			return ResponseStatusCode.nullPointerError();
		}
		else
		{
			int messageAuthCode = (int)((Math.random()*9+1)*100000);
			String sendUrl = "http://apis.renxinl.com:8080/smsgate/wtemplatesend.do?"+"user="+foreignUser+"&"+"pwd="+foreignPassword+"&"+"phone="+countryCode+phone+","+messageAuthCode+"&"+"mid="+foreignMid;

			try {
				//创建连接
				URL url = new URL(sendUrl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setRequestMethod("POST");
		       /* connection.setRequestProperty("Authorization", token);*/
				connection.setUseCaches(false);
				connection.setInstanceFollowRedirects(true);
				connection.setRequestProperty("Content-Type","application/json");
				connection.connect();

				//POST请求
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				JSONObject obj = new JSONObject();
				obj.put("user", domesticUser);
				obj.put("pwd", domesticPassword);
				obj.put("phone", "15900785383");
				obj.put("mid", "86");

				out.writeBytes(obj.toString());
				out.flush();
				out.close();

				//读取响应
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String lines;
				StringBuffer sb = new StringBuffer("");

				while ((lines = reader.readLine()) != null) {
					lines = new String(lines.getBytes(), "utf-8");
					sb.append(lines);
		            /*map.put("data", sb);*/
					String capture = new String(sb);
					JSONObject codeData = JSONObject.parseObject(capture);
					String code = (String) codeData.get("code");
					if(!code.equals("0000")) {
						return ResponseStatusCode.cashError();
					}
				}
				reader.close();
				// 断开连接
				connection.disconnect();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResponseStatusCode.putOrGetSuccess(null);
	}
}
