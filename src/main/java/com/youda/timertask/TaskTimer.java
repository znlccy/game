package com.youda.timertask;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-12-01
 * @introduce 定义定时器功能
 */

@Component
public class TaskTimer {
	
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	/**
	 * 每100秒执行一次
	 */
	@Scheduled(fixedRate = 100000)
	public void timeRate() {
		System.err.println("每100秒执行我一次");
	}
	
	/**
	 * 第一次10秒后执行，当执行完后2秒再执行
	 */
	/*@Scheduled(initialDelay = 10000, fixedDelay = 2000)
	public void timeInit() {
		System.out.println("初始化:"+dateFormat.format(new Date()));
	}*/
	
	/**
	 * 每天10:13:00时执行
	 */
	@Scheduled(cron = "0 00 02 * * ? ")
	public void timeCron() {
		System.err.println("每天02:00:00执行数据库备份和还原："+dateFormat.format(new Date()));
	}
	
	
}
