package com.youda.timertask;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-12-01
 * @introduce 定义数据库备份实体类
 */

@Configuration
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseBackup {
	
	@Autowired
	private ReadConfiguration readConfiguration;
	
	/**
	 * 定义并实现数据库备份
	 * @throws Exception
	 */
	@Test
	public void BackupDatabase() throws Exception {
		// TODO Auto-generated method stub
		/*readConfiguration.getDatasource();*/
		System.err.println(readConfiguration);
		System.err.println("数据库主机是:"+readConfiguration.getHost());
		System.err.println("数据库端口是:"+readConfiguration.getPort());
		System.err.println("数据库名称是:"+readConfiguration.getDatabase());
		System.err.println("数据库密码是:"+readConfiguration.getPassword());
		
		Runtime runtime = Runtime.getRuntime();
		System.out.println("-----备份数据库开始了-----");
		String cmd = "mysqldump -h"+readConfiguration.getHost()+" -u"+readConfiguration.getUsername()+" -p"+readConfiguration.getPassword()+" "+readConfiguration.getDatabase()+">d:/db_ydgame.sql";
		
		try {
			Process process = runtime.exec("cmd /c"+cmd);
			InputStreamReader inputStreamReader = 
					new InputStreamReader(process.getErrorStream());
			LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
			String line;
			while((line=lineNumberReader.readLine())!=null)
			{
				System.out.println(line+"-------");
			}
			System.out.println("备份成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("备份失败");
		}
		System.out.println("-----备份数据库任务结束了-----");
		/*System.err.println(readConfiguration.getDatasource());*/
	}

}
