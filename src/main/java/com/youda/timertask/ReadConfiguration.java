package com.youda.timertask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-12-01
 * @introduce 读取配置文件
 */


public class ReadConfiguration {
	
	private Map<String, String> datasource = new HashMap();

	public Map<String, String> getDatasource() {
		return datasource;
	}
	
	public void setDatasource(Map<String, String> datasource) {
		this.datasource = datasource;
	}
}
