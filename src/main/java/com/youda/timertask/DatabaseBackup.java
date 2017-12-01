package com.youda.timertask;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-12-01
 * @introduce 定义数据库备份实体类
 */

public class DatabaseBackup {
	
	@Autowired
	private ReadConfiguration readConfiguration;
	
	/**
	 * 定义并实现数据库备份
	 * @throws Exception
	 */
	public void BackupDatabase() throws Exception {
		// TODO Auto-generated method stub
		/*readConfiguration.getDatasource();*/
		System.err.println(readConfiguration);
		/*System.err.println(readConfiguration.getDatasource());*/
	}

}
