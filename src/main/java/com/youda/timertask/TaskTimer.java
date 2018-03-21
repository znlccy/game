package com.youda.timertask;

import com.youda.push.IOSPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-12-01
 * @introduce 定义定时器功能,并且实现定时备份数据库文件
 */

@Component
public class TaskTimer {
	
	/**
	 * 实现数据库备份的自动依赖注入
	 */
	@Autowired
	private DatabaseBackup databaseBackup;

	/*@Autowired
	private IOSPush iosPush;*/
	
	/**
	 * 每100秒执行一次
	 */
	/*@Scheduled(fixedRate = 100000)
	public void timeRate() {
		System.err.println("每100秒执行我一次");
	}*/
	
	/**
	 * 第一次10秒后执行，当执行完后2秒再执行
	 */
	/*@Scheduled(initialDelay = 10000, fixedDelay = 2000)
	public void timeInit() {
		System.out.println("初始化:"+dateFormat.format(new Date()));
	}*/
	
	/**
	 * 每天02:30:00时执行数据库备份
	 */
	@Scheduled(cron = "0 54 18 * * ? ")
	public void timeCron() {
		try {
			/*databaseBackup.BackupDatabase();*/
			System.out.println("进行数据库备份");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 每天08:30:00时执行推送
	 */
	/*@Scheduled(cron = "0 30 08 * * ? ")
	public void sendIOSPush() {
		try {
			iosPush.sendPush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
